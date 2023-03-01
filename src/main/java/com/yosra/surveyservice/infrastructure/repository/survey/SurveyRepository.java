package com.yosra.surveyservice.infrastructure.repository.survey;

import com.yosra.surveyservice.infrastructure.entity.survey.SurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<SurveyEntity,Long> {
}
