package com.yosra.surveyservice.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "question_options")
public class QuestionOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String optionValue;

    @ManyToOne
    @JoinColumn(name="question_id",nullable = false)
    private QuestionEntity question;

}
