package com.sensesnet.dto;

import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
import lombok.*;

import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DTO: User details
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto
{
    private User user;
    private UserInfo userInfo;
    private UserRole userRole;
}
