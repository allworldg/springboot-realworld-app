package realworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import realworld.common.HttpCommon;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(HttpCommon.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentNotValid(MethodArgumentNotValidException e) {
        e.getAllErrors().forEach(error -> {
            System.out.println("key: " + error.getObjectName());
            System.out.println("value: " + error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getBindingResult().getAllErrors());
    }


//    @ExceptionHandler(InvalidEmailOrPasswordException.class)
//    public ResponseEntity<Object> handleInvalidEmailOrPassword(InvalidEmailOrPasswordException e) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body()
//    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorize(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpCommon.UNAUTHORIZED);
    }

}
