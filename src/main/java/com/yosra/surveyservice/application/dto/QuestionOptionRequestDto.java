package com.yosra.surveyservice.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QuestionOptionRequestDto {

    @NotNull
    @NotEmpty
    private String optionValue;
}
