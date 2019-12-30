package com.sensesnet.dto;

import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestAnswer;
import lombok.*;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TestDto: contains Test entity & Answer entity
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestDto
{
    private Test test;
    private TestAnswer testAnswer;
}
