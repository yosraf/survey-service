package com.yosra.surveyservice.presentation.mapper.survey;

import com.yosra.surveyservice.application.domain.Survey;
import com.yosra.surveyservice.presentation.dto.survey.SurveyRequestDto;
import com.yosra.surveyservice.presentation.dto.survey.SurveyResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyMapper {

    SurveyResponseDto toSurveyResponseDto(Survey survey);
    Survey toSurveyDomain(SurveyRequestDto surveyRequestDto);
}
