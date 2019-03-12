package ru.tn.tnJetmanRating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, Object ... fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, Arrays.toString(fieldValue)));
    }
}
