package com.sensesnet.pojo.test;

import lombok.*;

import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: Test answer.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestAnswer
{
    public Integer answerId;
    public String answerDescription;
    public @NonNull
    Integer questionId;

    public TestAnswer(String answerDescription, Integer questionId)
    {
        this.answerId = null;
        this.answerDescription = answerDescription;
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (getClass() != object.getClass()) return false;

        TestAnswer that = (TestAnswer) object;

        if (!getAnswerId().equals(that.getAnswerId())) return false;
        if (!getAnswerDescription().equals(that.getAnswerDescription())) return false;
        return getQuestionId().equals(that.getQuestionId());

    }

    @Override
    public int hashCode()
    {
        int result = getAnswerId().hashCode();
        result = 31 * result + getAnswerDescription().hashCode();
        result = 31 * result + getQuestionId().hashCode();
        return result;
    }
}
