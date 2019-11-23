package com.sensesnet.pojo.authentication;

import com.sun.istack.internal.NotNull;
import lombok.*;

import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: User.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User
{
    private @NotNull Integer userId;
    private @NotNull String userLogin;
    private @NotNull String userPassword;
    private @NotNull UserRole userRole;
    private @NotNull Integer userInfoId;
    private @NotNull UserInfo userInfo;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId().equals(user.getUserId()) &&
                getUserLogin().equals(user.getUserLogin()) &&
                getUserPassword().equals(user.getUserPassword()) &&
                getUserRole().equals(user.getUserRole()) &&
                getUserInfoId().equals(user.getUserInfoId()) &&
                Objects.equals(getUserInfo(), user.getUserInfo());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUserId(), getUserLogin(), getUserPassword(), getUserRole(), getUserInfoId(), getUserInfo());
    }
}
