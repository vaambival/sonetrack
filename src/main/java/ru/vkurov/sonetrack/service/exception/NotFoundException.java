package ru.vkurov.sonetrack.service.exception;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private final String entity;
    private final Map<String, String> fieldValue;

    public NotFoundException(String entity, Map<String, String> fieldValue) {
        super(String.format("Entity %s is not found by fields: %s", entity, formatExceptionMessage(fieldValue)));
        this.entity = entity;
        this.fieldValue = fieldValue;
    }

    private static String formatExceptionMessage(Map<String, String> fieldValue) {
        return fieldValue.entrySet()
                .stream()
                .map(entry -> String.format("field='%s',value='%s'", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(";"));
    }
}
