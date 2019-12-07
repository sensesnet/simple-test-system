package com.sensesnet.pojo.test;

import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Pojo: Test process
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestProcess
{
    private Integer testProcessId;
    private Date testProcessDate;
    private Integer userId;
    private Integer resultId;
    private Integer testId;
    private Integer mainResultValue;
    private boolean isCompleted;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof TestProcess)) return false;
        TestProcess that = (TestProcess) obj;
        return isCompleted() == that.isCompleted() &&
                getTestProcessId().equals(that.getTestProcessId()) &&
                getTestProcessDate().equals(that.getTestProcessDate()) &&
                getUserId().equals(that.getUserId()) &&
                getResultId().equals(that.getResultId()) &&
                getTestId().equals(that.getTestId()) &&
                getMainResultValue().equals(that.getMainResultValue());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getTestProcessId(), getTestProcessDate(), getUserId(), getResultId(), getTestId(), getMainResultValue(), isCompleted());
    }
}
