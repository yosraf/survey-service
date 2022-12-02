package com.yosra.surveyservice.application.service;

import com.yosra.surveyservice.application.dto.SurveyRequestDto;
import com.yosra.surveyservice.application.dto.SurveyResponseDto;
import com.yosra.surveyservice.application.mapper.SurveyMapper;
import com.yosra.surveyservice.infrastructure.entity.QuestionEntity;
import com.yosra.surveyservice.infrastructure.entity.SurveyEntity;
import com.yosra.surveyservice.infrastructure.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Override
    public SurveyResponseDto create(SurveyRequestDto surveyRequestDto) {
        if (surveyRequestDto == null) {
            throw new IllegalArgumentException("survey dto could not be null");
        }
        SurveyEntity surveyEntity = surveyMapper.toSurveyEntity(surveyRequestDto);
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
        return surveyMapper.toSurveyResponseDto(surveyEntity);
    }
}
