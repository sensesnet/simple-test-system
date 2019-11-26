package com.sensesnet.pojo.test;

import lombok.*;
import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: Test result.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestResult
{
    private @NonNull Integer resultId;
    private @NonNull Integer testProcessId;
    private @NonNull Integer questionId;
    private @NonNull Integer answerId;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof TestResult)) return false;
        TestResult that = (TestResult) obj;
        return getResultId().equals(that.getResultId()) &&
                getTestProcessId().equals(that.getTestProcessId()) &&
                getQuestionId().equals(that.getQuestionId()) &&
                getAnswerId().equals(that.getAnswerId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getResultId(), getTestProcessId(), getQuestionId(), getAnswerId());
    }
}
