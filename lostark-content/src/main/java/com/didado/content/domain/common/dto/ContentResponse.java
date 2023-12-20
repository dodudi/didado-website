package com.didado.content.domain.common.dto;

public record ContentResponse(String message, int statusCode, Object data) {
}
