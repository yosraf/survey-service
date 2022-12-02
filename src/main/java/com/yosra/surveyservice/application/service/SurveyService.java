package com.yosra.surveyservice.application.service;

import com.yosra.surveyservice.application.dto.SurveyRequestDto;
import com.yosra.surveyservice.application.dto.SurveyResponseDto;

public interface SurveyService {
    SurveyResponseDto create(SurveyRequestDto surveyRequestDto);
}
