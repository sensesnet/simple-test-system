package com.sensesnet.view;

import com.sensesnet.pojo.test.*;
import lombok.*;


/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestHistoryView
{
    public TestProcess testProcess;
    public Test test;
}
