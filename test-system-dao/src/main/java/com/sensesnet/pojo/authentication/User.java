package com.sensesnet.pojo.authentication;

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
    private Integer userId;
    private @NonNull String userLogin;
    private @NonNull String userPassword;
    private Integer userRole;
    private Integer userInfo;

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
                Objects.equals(getUserInfo(), user.getUserInfo());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUserId(), getUserLogin(), getUserPassword(), getUserRole(), getUserInfo());
    }
}
