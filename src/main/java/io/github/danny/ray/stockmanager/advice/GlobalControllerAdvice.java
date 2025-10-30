package io.github.danny.ray.stockmanager.advice;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(FetchException.class)
    public BaseResult<Void> handleFetchException(FetchException e) {

        return switch (e) {
            case NotFoundException ex -> BaseResult.error(HttpStatus.NOT_FOUND, ex.getMessage());
            default -> BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        };
    }

}
