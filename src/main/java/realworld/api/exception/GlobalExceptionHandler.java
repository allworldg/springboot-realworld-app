package realworld.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import realworld.common.HttpCommon;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpCommon.CODE_NOT_FOUND)
                             .body(HttpCommon.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorize(UnAuthorizedException e) {
        return ResponseEntity.status(HttpCommon.CODE_UNAUTHORIZED).body(HttpCommon.UNAUTHORIZED);
    }

}
