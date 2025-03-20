package co.com.api.model.common;


public record ValidationDTO(
        String field,
        String error
) {
}