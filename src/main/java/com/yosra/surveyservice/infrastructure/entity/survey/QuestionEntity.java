package com.yosra.surveyservice.infrastructure.entity.survey;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String title;

    private Boolean activated = true;
    @ManyToOne
    @JoinColumn(name="survey_id",nullable = false)
    private SurveyEntity survey;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "question")
    private List<QuestionOptionEntity> options ;


}
