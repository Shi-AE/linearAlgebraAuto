package com.AE.linearAlgebraAuto.controller.exception;

import com.AE.linearAlgebraAuto.controller.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({InfinitelySolutionException.class, NoSolutionException.class, DetIsZeroException.class})
    public Result doInfinitelySolutionException(Exception exception) {
        return new Result(null, exception.getMessage());
    }
}
