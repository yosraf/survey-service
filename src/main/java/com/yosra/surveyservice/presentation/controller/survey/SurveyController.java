package com.yosra.surveyservice.presentation.controller.survey;

import com.yosra.surveyservice.application.domain.Survey;
import com.yosra.surveyservice.presentation.dto.survey.SurveyRequestDto;
import com.yosra.surveyservice.presentation.dto.survey.SurveyResponseDto;
import com.yosra.surveyservice.application.service.survey.SurveyService;
import com.yosra.surveyservice.presentation.controller.ApiUrl;
import com.yosra.surveyservice.presentation.mapper.survey.SurveyMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.SURVEY_URL)
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyMapper surveyMapper;

    public SurveyController(SurveyService surveyService, SurveyMapper surveyMapper) {
        this.surveyService = surveyService;
        this.surveyMapper = surveyMapper;
    }
    @PostMapping
    public ResponseEntity<SurveyResponseDto> create(@Valid @RequestBody SurveyRequestDto surveyRequestDto){
        Survey survey = surveyMapper.toSurveyDomain(surveyRequestDto);
      return   new ResponseEntity<>(surveyMapper.toSurveyResponseDto(surveyService.create(survey)), HttpStatus.CREATED);
    }
}
