package com.yosra.surveyservice.application.domain;

import lombok.Data;

import java.util.List;

@Data
public class Survey {
    private String uuid;
    private String name;
    private List<Question> questions;
}
