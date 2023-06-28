package guru.springframework.spring6webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler
    ResponseEntity<List<Map<String, String>>> handleJPAViolations(TransactionSystemException e){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        if(e.getCause().getCause() instanceof ConstraintViolationException){
            ConstraintViolationException ve = (ConstraintViolationException) e.getCause().getCause();

            List<Map<String,String>> errors = ve.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    Map<String, String> errMap = new HashMap<>();
                    errMap.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
                    return errMap;
                }).collect(Collectors.toList());
            return responseEntity.body(errors);
        }
        
        return responseEntity.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<Map<String,String>>> handleBindErrors(MethodArgumentNotValidException e){

        List<Map<String,String>> errorList = e.getFieldErrors().stream()
            .map(fieldError -> {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                return errorMap;
            }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorList);
    }
    
}
