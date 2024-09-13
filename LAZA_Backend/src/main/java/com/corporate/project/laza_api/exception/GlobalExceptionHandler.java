package com.corporate.project.laza_api.exception;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;
import java.time.Instant;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Cannot Not Found this item");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("localhost:8085/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(DuplicateException.class)
    ProblemDetail handleDuplicateException(DuplicateException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("This item already exists");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("localhost:8085/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleErrorField(
            MethodArgumentNotValidException exception) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        StringBuilder str = new StringBuilder();
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        str.append(fieldErrors.get(0).getField()).append(" : ").append(fieldErrors.get(0).getDefaultMessage());
        messageResponse.setMessage(str.toString());
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
}