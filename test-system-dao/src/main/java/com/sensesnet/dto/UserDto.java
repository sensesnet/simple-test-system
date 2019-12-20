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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return getUser().equals(userDto.getUser()) &&
                getUserRole().equals(userDto.getUserRole()) &&
                getUserInfo().equals(userDto.getUserInfo());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getUserRole(), getUserInfo());
    }
}
