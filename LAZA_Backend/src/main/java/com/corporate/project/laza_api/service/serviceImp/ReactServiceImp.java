package com.corporate.project.laza_api.service.serviceImp;

import com.corporate.project.laza_api.assembler.ReactModelAssembler;
import com.corporate.project.laza_api.controller.ReactRestController;
import com.corporate.project.laza_api.mapper.ReactMapper;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.React;
import com.corporate.project.laza_api.repository.ReactRepository;
import com.corporate.project.laza_api.service.ReactService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactServiceImp implements ReactService {
    private final ReactRepository reactRepository;
    private final ReactMapper reactMapper;
    private final ReactModelAssembler reactModelAssembler;


    @Override
    public CollectionModel<?> findAllReact() {
        List<React> reacts = reactRepository.findAll();
        return reactModelAssembler.toCollectionModel(reacts);
    }

    @Override
    public EntityModel<?> findReactById(Long id) {
     Optional<React> react = reactRepository.findById(id);
        return  reactModelAssembler.toModel(react.orElseThrow());
    }

    @Override
    public Long createNewReact(CreateReactDto createReactDto) {
        React react = reactMapper.mapCreateReactDtoToReact(createReactDto);
        reactRepository.save(react);
        System.out.println(react);
        return react.getId();
    }

    @Override
    public Long updateReactById(Long id, CreateReactDto createReactDto) {
        React react = reactRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"React not found"));
        reactMapper.mapForPartialUpdate(react, createReactDto);
        reactRepository.save(react);
        return react.getId();
    }

    @Override
    public void deleteReactById(Long id) {
        reactRepository.deleteById(id);
    }


}
