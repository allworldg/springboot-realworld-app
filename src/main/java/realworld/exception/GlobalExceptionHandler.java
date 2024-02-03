package realworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import realworld.common.HttpCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(HttpCommon.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentNotValid(MethodArgumentNotValidException e) {
        CustomErrors customErrors = new CustomErrors();
        Map<String, List<String>> errors = customErrors.getErrors();
        e.getFieldErrors().forEach(error -> {
            List<String> list = errors.getOrDefault(error.getField(), new ArrayList<>());
            list.add(error.getDefaultMessage());
            errors.put(error.getField(), list);
        });
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customErrors);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        CustomErrors customErrors = new CustomErrors();
        Map<String, List<String>> map = customErrors.getErrors();
        ArrayList<String> list = new ArrayList<>();
        list.add(HttpCommon.IS_INVALID);
        map.put(HttpCommon.EMAIL_PASSWORD_INVALID, list);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customErrors);
    }


//    @ExceptionHandler({InvalidEmailOrPasswordException.class})
//    public ResponseEntity<Object> handleInvalidEmailOrPassword(InvalidEmailOrPasswordException e) {
//        CustomErrors customErrors = new CustomErrors();
//        Map<String, List<String>> errors = customErrors.getErrors();
//        ArrayList<String> list = new ArrayList<>();
//        list.add(HttpCommon.IS_INVALID);
//        errors.put(HttpCommon.EMAIL_PASSWORD_INVALID, list);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customErrors);
//    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorize(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpCommon.UNAUTHORIZED);
    }

}
