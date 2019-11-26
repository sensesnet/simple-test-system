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
    public @NonNull Integer answerId;
    public @NonNull String answerDescription;
    public @NonNull Integer questionId;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof TestAnswer)) return false;
        TestAnswer that = (TestAnswer) obj;
        return getAnswerId().equals(that.getAnswerId()) &&
                getAnswerDescription().equals(that.getAnswerDescription()) &&
                getQuestionId().equals(that.getQuestionId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAnswerId(), getAnswerDescription(), getQuestionId());
    }
}
