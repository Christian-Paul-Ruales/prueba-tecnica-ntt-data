package ec.nttdata.person_user_ms.infrastructure.exception;

import ec.nttdata.person_user_ms.domain.result.ResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResultError> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "%s: %s".formatted(fieldError.getCode(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining(", "));

        log.error(" --- MethodArgumentNotValidException: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultError("2", message));

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResultError> handleException(Exception ex) {
        log.error(" --- GENERIC Exception: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultError(ex.getClass().getSimpleName(), ex.getMessage()));

    }
}
