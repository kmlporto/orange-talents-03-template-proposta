package br.com.zup.desafios.proposta.config.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    public void addGlobalError(String errorMessage) {
        globalErrorMessages.add(errorMessage);
    }

    public void addFieldError(String field, String errorMessage) {
        FieldErrorDto fieldErrorDto = new FieldErrorDto(field, errorMessage);
        fieldErrors.add(fieldErrorDto);
    }
}
