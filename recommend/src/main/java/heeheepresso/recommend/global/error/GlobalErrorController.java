package heeheepresso.recommend.global.error;

import heeheepresso.recommend.global.common.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception e) {
//        return CommonApiResponse.builder().message(e.getMessage()).build().toEntity(HttpStatus.NOT_FOUND);
        return null;
    }
}
