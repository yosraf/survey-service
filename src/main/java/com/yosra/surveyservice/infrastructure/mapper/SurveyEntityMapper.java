package com.yosra.surveyservice.infrastructure.mapper;

import com.yosra.surveyservice.application.domain.Survey;
import com.yosra.surveyservice.infrastructure.entity.survey.SurveyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyEntityMapper {
    SurveyEntity toEntity(Survey survey);
    Survey toDomain(SurveyEntity surveyEntity);
}
