package com.cgm.qanda.dataobject;

/**
 * Answer.java - This class defines variables or attributes,setter and getter methods,which interacts with h2 database using spring boot
 * @author Manjunath Golla Bala
 * @version 1.0
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private String answer;

    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
