package heeheepresso.recommend.global.common;

import heeheepresso.recommend.global.error.Error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@AllArgsConstructor
@Builder
public class CommonApiResponse<T> {
    private boolean success;
    private T data;
    private Error error;
    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));


//    public static ResponseEntity<?> toEntity(final HttpStatus httpStatus) {
//        log.info("recommendRequestDto: {}", httpStatus);
//        CommonApiResponse commonApiResponse = CommonApiResponse.builder()
//                .success(true)
//                .data(httpStatus)
//                .error(null)
//                .build();
//        return ResponseEntity.status(httpStatus).body(commonApiResponse);
//    }

    public static <T> ResponseEntity<CommonApiResponse> success(T data, final HttpStatus httpStatus) {
        CommonApiResponse commonApiResponse = CommonApiResponse.builder()
                .success(true)
                .data(data)
                .error(null)
                .build();
        return ResponseEntity.status(httpStatus).body(commonApiResponse);
    }
//
//    public static <T> ResponseDto<T> fail(String code, String message) {
//        return new ResponseDto<>(false, null, new Error(code, message));
//    }
}
