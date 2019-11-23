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
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Test)) return false;
        Test test = (Test) obj;
        return testId.equals(test.testId) &&
                testName.equals(test.testName) &&
                testDescription.equals(test.testDescription) &&
                testValue.equals(test.testValue) &&
                testTime.equals(test.testTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(testId, testName, testDescription, testValue, testTime);
    }

}
