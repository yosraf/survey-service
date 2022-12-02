package com.yosra.surveyservice.presentation.controller;

import com.fasterxml.jackson.core.type.TypeReference;

import com.yosra.surveyservice.application.dto.SurveyRequestDto;
import com.yosra.surveyservice.application.dto.SurveyResponseDto;
import com.yosra.surveyservice.configuration.IntegrationTesting;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SurveyControllerTest extends IntegrationTesting {

    private static final String NAME_FIELD ="name" ;

    @Test
    public void create() throws Exception {

        //GIVEN
        SurveyRequestDto surveyRequestDto = podamFactory.manufacturePojo(SurveyRequestDto.class);

        String surveyDto = objectMapper.writeValueAsString(surveyRequestDto);

        // WHEN
        ResultActions createResult =
                mockMvc.perform(
                        post(ApiUrl.SURVEY_URL)
                                .contentType((MediaType.APPLICATION_JSON))
                                .content(surveyDto));

        // THEN
        createResult.andExpect(status().isCreated());
        assertTrue(
                StringUtils.hasText(createResult.andReturn().getResponse().getContentAsString()));
        String survey = createResult.andReturn().getResponse().getContentAsString();
        assertTrue(StringUtils.hasText(survey));
        SurveyResponseDto surveyResponseDto =
                objectMapper.readValue(survey, new TypeReference<SurveyResponseDto>() {});

        assertNotNull(surveyResponseDto);
        assertEquals(surveyRequestDto.getName(),surveyResponseDto.getName());
        assertEquals(surveyRequestDto.getQuestions().size(),surveyResponseDto.getQuestions().size());
        assertNotNull(surveyResponseDto.getUuid());
    }
    @Test
    public void createShouldFailWhenSurveyNameInvalid() throws Exception {
        //GIVEN
        SurveyRequestDto surveyRequestDto = new SurveyRequestDto();
        String surveyDto = objectMapper.writeValueAsString(surveyRequestDto);
        // WHEN
        ResultActions invalidResult =
                mockMvc.perform(
                        post(ApiUrl.SURVEY_URL)
                                .contentType((MediaType.APPLICATION_JSON))
                                .content(surveyDto));

        // THEN
        invalidResult.andExpect(status().is5xxServerError());
        invalidResult.andExpect(
                res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException));
        invalidResult.andExpect(
                res -> {
                    assertNotNull(
                            ((MethodArgumentNotValidException) res.getResolvedException())
                                    .getFieldError(NAME_FIELD));
                });

    }
}