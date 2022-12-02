package com.yosra.surveyservice.infrastructure.repository;

import com.yosra.surveyservice.infrastructure.entity.SurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<SurveyEntity,Long> {
}
