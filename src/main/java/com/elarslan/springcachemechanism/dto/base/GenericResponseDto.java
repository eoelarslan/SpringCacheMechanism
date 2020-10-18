package com.elarslan.springcachemechanism.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class GenericResponseDto<T> {

    private String message;
    private HttpStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> results;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T singleResult;

    public GenericResponseDto(HttpStatus status, String message, List<T> results) {
        this.status = status;
        this.message = message;

        this.results = Objects.requireNonNullElseGet(results, ArrayList::new);
    }

    public GenericResponseDto(HttpStatus status, String message, T singleResult) {
        this.status = status;
        this.message = message;

        this.singleResult = Objects.requireNonNullElseGet(singleResult, () -> (T) new Object());
    }
}
