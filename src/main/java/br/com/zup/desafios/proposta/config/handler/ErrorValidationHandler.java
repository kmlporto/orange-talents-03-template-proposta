package br.com.zup.desafios.proposta.config.handler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorValidationHandler {

    private final MessageSource messageSource;

    public ErrorValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException exception){
        List<String> mesangens = buildMessagesError(exception.getBindingResult());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, mesangens);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handle(EntityNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Não existe recurso com este id");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handle(MissingRequestHeaderException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Header " + exception.getHeaderName() + " obrigatório");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ApiError> handle(ApiErrorException apiErrorException) {
        ApiError apiError = new ApiError(apiErrorException.getHttpStatus(), apiErrorException.getReason());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    private List<String> buildMessagesError(BindingResult bindingResult) {
        List<String> messages = new ArrayList<>();
        bindingResult.getGlobalErrors().forEach(objectError -> messages.add(getErrorMessage(objectError)));
        bindingResult.getFieldErrors().forEach(fieldError -> messages.add(fieldError.getField() + " " + getErrorMessage(fieldError)));

        return messages;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getErrorMessage(ObjectError error){
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
