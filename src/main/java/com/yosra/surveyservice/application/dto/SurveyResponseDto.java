package com.yosra.surveyservice.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyResponseDto {
    private String uuid;
    private String name;
    private List<QuestionResponseDto> questions;

}
