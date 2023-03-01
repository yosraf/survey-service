package com.yosra.surveyservice.infrastructure.dao.survey;

import com.yosra.surveyservice.application.domain.Survey;
import com.yosra.surveyservice.application.service.survey.SurveyDao;
import com.yosra.surveyservice.infrastructure.entity.survey.QuestionEntity;
import com.yosra.surveyservice.infrastructure.entity.survey.SurveyEntity;
import com.yosra.surveyservice.infrastructure.mapper.SurveyEntityMapper;
import com.yosra.surveyservice.infrastructure.repository.survey.SurveyRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

@Component
public class SurveyDaoImpl implements SurveyDao {
    private final SurveyRepository surveyRepository;
    private final SurveyEntityMapper surveyMapper;

    public SurveyDaoImpl(SurveyRepository surveyRepository, SurveyEntityMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Override
    public Survey create(Survey survey) {
        if (survey== null){
            throw new IllegalArgumentException("survey could not be null");
        }
        SurveyEntity surveyEntity = surveyMapper.toEntity(survey);
        surveyEntity.setUuid(UUID.randomUUID().toString());
        if (!CollectionUtils.isEmpty(surveyEntity.getQuestions())) {
            for (QuestionEntity question : surveyEntity.getQuestions()) {
                question.setSurvey(surveyEntity);
                question.setUuid(UUID.randomUUID().toString());
                if (!CollectionUtils.isEmpty(question.getOptions())) {
                    question.getOptions().forEach(questionOptionEntity -> {
                        questionOptionEntity.setUuid(UUID.randomUUID().toString());
                        questionOptionEntity.setQuestion(question);
                    });
                }
            }
        }
        surveyEntity = surveyRepository.save(surveyEntity);
        return surveyMapper.toDomain(surveyEntity);    }
}
