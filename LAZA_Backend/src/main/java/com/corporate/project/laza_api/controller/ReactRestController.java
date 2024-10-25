package com.corporate.project.laza_api.controller;

import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.service.ReactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reacts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ReactRestController {

    private final ReactService reactService;

    @GetMapping
    public ApiResponse<CollectionModel<?>> getAllReact() {
        var reacts = reactService.findAllReact();
        System.out.println(reacts);
        return new ApiResponse<>("success get React", reacts, 200);
    }

    @GetMapping("/{id}")
    public ApiResponse<EntityModel<?>> getReactById(@PathVariable("id") Long id) {
        var react = reactService.findReactById(id);
        return new ApiResponse<>("success get React", react, 200);
    }


    @PostMapping("/create")
    public ApiResponse  addReact(@RequestBody CreateReactDto createReactDto) {
       Long reactId =  reactService.createNewReact(createReactDto);
    var  reactCreated = reactService.findReactById(reactId);
        return new ApiResponse<>("success create React", reactCreated, 200);
    }


    @PatchMapping("update/{id}")
    public  ApiResponse  updateReactById(@PathVariable Long id, @RequestBody CreateReactDto createReactDto) {
      Long reactId =   reactService.updateReactById(id,createReactDto);
      var  reactUpdated = reactService.findReactById(reactId);
      return new ApiResponse<>("success update React", reactUpdated, 200);
    }


    @DeleteMapping("delete/{id}")
    public ApiResponse deleteReactById(@PathVariable Long id) {
        reactService.deleteReactById(id);
        return new ApiResponse<>("success delete React", null, 200);
    }

}
