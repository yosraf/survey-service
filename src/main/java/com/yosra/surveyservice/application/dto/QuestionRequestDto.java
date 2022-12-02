package com.yosra.surveyservice.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class QuestionRequestDto {
    @NotNull
    @NotEmpty
    private String title;
    private List<QuestionOptionRequestDto> options;

}
