package com.yosra.surveyservice.presentation.dto.survey;

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
