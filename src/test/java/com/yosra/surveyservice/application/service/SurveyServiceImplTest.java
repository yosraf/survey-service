package com.yosra.surveyservice.application.service;

import com.yosra.surveyservice.application.domain.Survey;
import com.yosra.surveyservice.application.service.survey.SurveyDao;
import com.yosra.surveyservice.application.service.survey.SurveyServiceImpl;
import com.yosra.surveyservice.configuration.UnitTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class SurveyServiceImplTest extends UnitTesting {

    @Mock
    private SurveyDao surveyDao;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    private static final String ERROR_MSG = "survey could not be null";

    @BeforeEach
    public void initTest() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void create() {
        Survey survey = podamFactory.manufacturePojo(Survey.class);

        Mockito.when(surveyDao.create(survey)).thenReturn(survey);

        //WHEN
        survey = surveyService.create(survey);
        //THEN
        assertNotNull(survey);
        assertNotNull(survey.getName());
        assertFalse(CollectionUtils.isEmpty(survey.getQuestions()));
    }

    @Test
    void createShouldFailWhenDtoInvalid() {
        //GIVEN
        Survey survey = null;

        try {
            //WHEN
            surveyService.create(survey);

        } catch (IllegalArgumentException exception) {
            //THEN
            assertEquals(ERROR_MSG, exception.getMessage());
            return;
        }
        Assertions.fail("an error should be thrown");

    }
}