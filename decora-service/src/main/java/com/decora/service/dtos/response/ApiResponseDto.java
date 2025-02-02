package com.decora.service.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto<T> {
    private boolean success;
    private String message;
    private T response;
}
