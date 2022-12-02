package com.yosra.surveyservice.configuration;


import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public abstract class UnitTesting {

  protected PodamFactory podamFactory = new PodamFactoryImpl();
}
