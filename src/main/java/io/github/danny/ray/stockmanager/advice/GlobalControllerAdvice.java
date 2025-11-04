package io.github.danny.ray.stockmanager.advice;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.exception.NotFoundException;
import io.github.danny.ray.stockmanager.exception.RegisterException;
import io.github.danny.ray.stockmanager.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RegisterException.class)
    public BaseResult<Void> handleRegisterException(RegisterException e) {
        return BaseResult.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(FetchException.class)
    public BaseResult<Void> handleFetchException(FetchException e) {

        return switch (e) {
            case NotFoundException ex -> BaseResult.error(HttpStatus.NOT_FOUND, ex.getMessage());
            default -> BaseResult.error(HttpStatus.NOT_FOUND, e.getMessage());
        };
    }

    @ExceptionHandler(UnauthorizedException.class)
    public BaseResult<Void> handleUnauthorizedException(UnauthorizedException e) {
        return BaseResult.error(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

}
