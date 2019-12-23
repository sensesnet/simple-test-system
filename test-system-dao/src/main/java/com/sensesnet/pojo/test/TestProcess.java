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
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;

        TestProcess that = (TestProcess) object;

        if (isCompleted() != that.isCompleted()) return false;
        if (!getTestProcessId().equals(that.getTestProcessId())) return false;
        if (!getTestProcessDate().equals(that.getTestProcessDate())) return false;
        if (!getUserId().equals(that.getUserId())) return false;
        if (!getResultId().equals(that.getResultId())) return false;
        if (!getTestId().equals(that.getTestId())) return false;
        return getMainResultValue().equals(that.getMainResultValue());

    }

    @Override
    public int hashCode()
    {
        int result = getTestProcessId().hashCode();
        result = 31 * result + getTestProcessDate().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getResultId().hashCode();
        result = 31 * result + getTestId().hashCode();
        result = 31 * result + getMainResultValue().hashCode();
        result = 31 * result + (isCompleted() ? 1 : 0);
        return result;
    }
}
