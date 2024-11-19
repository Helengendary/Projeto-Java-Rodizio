package com.example.demo.dto;

public record PermissionDto(
    Long userId,
    Long spaceId,
    Boolean isAdm
) {
    
}
