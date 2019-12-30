package com.sensesnet.dto;

import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserRole;
import lombok.*;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * UserDto: User entity & UserRole entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto
{
    private User user;
    private UserRole userRole;
}
