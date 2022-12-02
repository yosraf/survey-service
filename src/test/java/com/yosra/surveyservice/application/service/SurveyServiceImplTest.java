package com.yosra.surveyservice.application.service;

import com.yosra.surveyservice.application.dto.*;
import com.yosra.surveyservice.application.mapper.SurveyMapper;
import com.yosra.surveyservice.configuration.UnitTesting;
import com.yosra.surveyservice.infrastructure.entity.QuestionEntity;
import com.yosra.surveyservice.infrastructure.entity.QuestionOptionEntity;
import com.yosra.surveyservice.infrastructure.entity.SurveyEntity;
import com.yosra.surveyservice.infrastructure.repository.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyServiceImplTest extends UnitTesting {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyMapper surveyMapper;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    private static final String ERROR_MSG="survey dto could not be null";

    @BeforeEach
    public void initTest() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void create() {
        String surveyName = podamFactory.manufacturePojo(String.class);
        String questionName = podamFactory.manufacturePojo(String.class);
        String value= podamFactory.manufacturePojo(String.class);

        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setName(surveyName);

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTitle(questionName);

        QuestionOptionEntity questionOptionEntity = new QuestionOptionEntity();
        questionOptionEntity.setOptionValue(value);

        questionEntity.setOptions(List.of(questionOptionEntity));
        surveyEntity.setQuestions(List.of(questionEntity));
        SurveyRequestDto surveyRequestDto = podamFactory.manufacturePojo(SurveyRequestDto.class);

        Mockito.when(surveyMapper.toSurveyEntity(surveyRequestDto)).thenReturn(surveyEntity);
        Mockito.when(surveyRepository.save(surveyEntity)).thenReturn(surveyEntity);

        SurveyResponseDto surveyResponseDto = podamFactory.manufacturePojo(SurveyResponseDto.class);
        Mockito.when(surveyMapper.toSurveyResponseDto(surveyEntity)).thenReturn(surveyResponseDto);

        //WHEN
        SurveyResponseDto responseDto = surveyService.create(surveyRequestDto);
        responseDto.setName(surveyName);
        //THEN
        assertNotNull(responseDto);
        assertNotNull(responseDto.getName());
        assertTrue(StringUtils.hasLength(responseDto.getName()));
        assertEquals(surveyEntity.getName(), responseDto.getName());
        assertFalse(CollectionUtils.isEmpty(responseDto.getQuestions()));
    }
    @Test
    void createShouldFailWhenDtoInvalid() {
        //GIVEN
        SurveyRequestDto surveyRequestDto = null;

        try{
            //WHEN
            surveyService.create(surveyRequestDto);

        }catch (IllegalArgumentException exception){
            //THEN
            assertEquals(ERROR_MSG,exception.getMessage());
            return;
        }
        Assertions.fail("an error should be thrown");

    }
}