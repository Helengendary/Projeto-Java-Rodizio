package com.example.demo.dto;

public record AnswerDto(
    String statement,
    Long questionId,
    Long spaceId
) {}
