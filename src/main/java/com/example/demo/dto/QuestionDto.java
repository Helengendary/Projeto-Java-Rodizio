package com.example.demo.dto;

public record QuestionDto(
    String statement,
    Long idSpace,
    Long idQuestioner
) {}
