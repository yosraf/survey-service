package com.yosra.surveyservice.presentation.dto.survey;

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
