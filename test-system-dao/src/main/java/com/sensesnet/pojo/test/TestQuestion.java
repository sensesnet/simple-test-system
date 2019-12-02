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
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof TestQuestion)) return false;
        TestQuestion that = (TestQuestion) obj;
        return getQuestionId().equals(that.getQuestionId()) &&
                getQuestionDesc().equals(that.getQuestionDesc()) &&
                getQuestionValue().equals(that.getQuestionValue()) &&
                getAnswerId().equals(that.getAnswerId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getQuestionId(), getQuestionDesc(), getQuestionValue(), getAnswerId());
    }
}
