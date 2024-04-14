package heeheepresso.recommend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class Error {
//    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
