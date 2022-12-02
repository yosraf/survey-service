package com.yosra.surveyservice.application.mapper;

import com.yosra.surveyservice.application.dto.SurveyRequestDto;
import com.yosra.surveyservice.application.dto.SurveyResponseDto;
import com.yosra.surveyservice.infrastructure.entity.SurveyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyMapper {

    SurveyResponseDto toSurveyResponseDto(SurveyEntity surveyEntity);
    SurveyEntity toSurveyEntity(SurveyRequestDto surveyRequestDto);
}
