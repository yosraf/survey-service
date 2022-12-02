package com.yosra.surveyservice.presentation.controller;

import com.yosra.surveyservice.application.dto.SurveyRequestDto;
import com.yosra.surveyservice.application.dto.SurveyResponseDto;
import com.yosra.surveyservice.application.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrl.SURVEY_URL)
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    @PostMapping
    public ResponseEntity<SurveyResponseDto> create(@Validated @RequestBody SurveyRequestDto surveyRequestDto){
      return   new ResponseEntity<>(surveyService.create(surveyRequestDto), HttpStatus.CREATED);
    }
}
