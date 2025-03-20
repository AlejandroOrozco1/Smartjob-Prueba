package co.com.api.rest.config;

import co.com.api.model.common.MessageDTO;
import co.com.api.model.common.ValidationDTO;
import co.com.api.model.common.exception.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body( new MessageDTO<>(e.getMessage())
        );
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<MessageDTO<String>> errorException(ErrorException e){
        return ResponseEntity.status(e.getCode()).body(new MessageDTO<>(e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO<List<ValidationDTO>>> validationException(
            MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errors = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            errors.add( new ValidationDTO(e.getField(), e.getDefaultMessage()) );
        }
        return ResponseEntity.badRequest().body( new MessageDTO<>(errors) );
    }
}