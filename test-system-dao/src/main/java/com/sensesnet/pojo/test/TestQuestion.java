package com.sensesnet.pojo.test;

import lombok.*;
import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: Test question.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestQuestion
{
    private @NonNull Integer questionId;
    private @NonNull String questionDesc;
    private @NonNull Integer questionValue;
    private @NonNull Integer answerId;
    private @NonNull Integer testId;
    private @NonNull String questionClarification;

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;

        TestQuestion that = (TestQuestion) object;

        if (!getQuestionId().equals(that.getQuestionId())) return false;
        if (!getQuestionDesc().equals(that.getQuestionDesc())) return false;
        if (!getQuestionValue().equals(that.getQuestionValue())) return false;
        if (!getAnswerId().equals(that.getAnswerId())) return false;
        if (!getTestId().equals(that.getTestId())) return false;
        return getQuestionClarification().equals(that.getQuestionClarification());

    }

    @Override
    public int hashCode()
    {
        int result = getQuestionId().hashCode();
        result = 31 * result + getQuestionDesc().hashCode();
        result = 31 * result + getQuestionValue().hashCode();
        result = 31 * result + getAnswerId().hashCode();
        result = 31 * result + getTestId().hashCode();
        result = 31 * result + getQuestionClarification().hashCode();
        return result;
    }
}
