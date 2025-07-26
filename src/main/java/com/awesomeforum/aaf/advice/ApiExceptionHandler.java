package com.awesomeforum.aaf.advice;

import com.awesomeforum.aaf.advice.exception.AlreadyExistsException;
import com.awesomeforum.aaf.advice.exception.ContentBadRequestException;
import com.awesomeforum.aaf.advice.exception.NotFoundException;
import com.awesomeforum.aaf.dto.exception.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ApiExceptionHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @ExceptionHandler(value={ContentBadRequestException.class})
    public ResponseEntity<ApiException> handleContentBadRequest(ContentBadRequestException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage(),"400",400, LocalDateTime.now().format(formatter)), HttpStatusCode.valueOf(400));
    }
    @ExceptionHandler(value={NotFoundException.class})
    public ResponseEntity<ApiException> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage(),"404",404, LocalDateTime.now().format(formatter)), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value={AlreadyExistsException.class})
    public ResponseEntity<ApiException> handleAlreadyExists(AlreadyExistsException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage(),"409",409, LocalDateTime.now().format(formatter)), HttpStatusCode.valueOf(400));
    }



}