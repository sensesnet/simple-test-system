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
    private Integer resultId;
    private @NonNull String testProcessId;
    private @NonNull Integer questionId;
    private @NonNull Integer answerId;

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (getClass() != object.getClass()) return false;

        TestResult that = (TestResult) object;

        if (!getResultId().equals(that.getResultId())) return false;
        if (!getTestProcessId().equals(that.getTestProcessId())) return false;
        if (!getQuestionId().equals(that.getQuestionId())) return false;
        return getAnswerId().equals(that.getAnswerId());

    }

    @Override
    public int hashCode()
    {
        int result = getResultId().hashCode();
        result = 31 * result + getTestProcessId().hashCode();
        result = 31 * result + getQuestionId().hashCode();
        result = 31 * result + getAnswerId().hashCode();
        return result;
    }
}
