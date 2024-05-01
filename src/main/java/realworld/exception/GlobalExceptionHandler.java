package realworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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
                             .body(HttpCommon.RESOURCE_NOT_FOUND);
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

    @ExceptionHandler(TitleAlreadyExistException.class)
    public ResponseEntity<Object> handleTitleAlreadyExist(TitleAlreadyExistException e) {
        CustomErrors customErrors = new CustomErrors();
        Map<String, List<String>> errors = customErrors.getErrors();
        List<String> list = new ArrayList<>();
        list.add(e.getMessage());
        errors.put("title", list);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> InvalidUsernameOrPassword(AuthenticationException e) {
        CustomErrors customErrors = new CustomErrors();
        Map<String, List<String>> map = customErrors.getErrors();
        ArrayList<String> list = new ArrayList<>();
        list.add(HttpCommon.IS_INVALID);
        map.put(HttpCommon.EMAIL_PASSWORD_INVALID, list);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customErrors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpCommon.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handlerAuthenticationError(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorize(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpCommon.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Object> handleEmailExist(EmailAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<Object> handleUsernameExist(UserNameAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }


}
