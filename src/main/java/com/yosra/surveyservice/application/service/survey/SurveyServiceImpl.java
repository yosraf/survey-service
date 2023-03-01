package com.yosra.surveyservice.application.service.survey;

import com.yosra.surveyservice.application.domain.Survey;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyDao surveyDao;

    public SurveyServiceImpl(SurveyDao surveyDao) {

        this.surveyDao = surveyDao;
    }

    @Override
    public Survey create(Survey survey) {
        if (survey == null) {
            throw new IllegalArgumentException("survey could not be null");
        }
        return surveyDao.create(survey);
    }
}
