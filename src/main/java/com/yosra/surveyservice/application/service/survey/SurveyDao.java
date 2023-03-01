package com.yosra.surveyservice.application.service.survey;

import com.yosra.surveyservice.application.domain.Survey;

public interface SurveyDao {
    Survey create(Survey survey);
}
