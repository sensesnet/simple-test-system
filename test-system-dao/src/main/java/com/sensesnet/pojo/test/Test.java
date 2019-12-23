package com.sensesnet.pojo.test;


import lombok.*;

import java.sql.Time;
import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Pojo: Test.
 */

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Test
{
    private @NonNull Integer testId;
    private @NonNull String testName;
    private @NonNull String testDescription;
    private @NonNull Integer testValue;
    private @NonNull Time testTime;

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;

        Test test = (Test) object;

        if (!getTestId().equals(test.getTestId())) return false;
        if (!getTestName().equals(test.getTestName())) return false;
        if (!getTestDescription().equals(test.getTestDescription())) return false;
        if (!getTestValue().equals(test.getTestValue())) return false;
        return getTestTime().equals(test.getTestTime());

    }

    @Override
    public int hashCode()
    {
        int result = getTestId().hashCode();
        result = 31 * result + getTestName().hashCode();
        result = 31 * result + getTestDescription().hashCode();
        result = 31 * result + getTestValue().hashCode();
        result = 31 * result + getTestTime().hashCode();
        return result;
    }
}
