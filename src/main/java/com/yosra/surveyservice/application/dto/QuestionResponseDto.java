package com.yosra.surveyservice.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponseDto {
    private String uuid;
    private String title;
    private List<QuestionOptionResponseDto> options;
}
