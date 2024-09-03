package com.example.fakestoreproductservice.advice;

import com.example.fakestoreproductservice.dto.ExceptionDto;
import com.example.fakestoreproductservice.exceptions.BadRequestException;
import com.example.fakestoreproductservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex) {

        ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
