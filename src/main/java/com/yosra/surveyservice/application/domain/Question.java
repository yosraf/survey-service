package com.yosra.surveyservice.application.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String uuid;
    private String title;
    private Boolean activated;
    private List<QuestionOption> options;
}
