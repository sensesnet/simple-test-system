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
    private String userName;
    private String userSurname;
    private String userAddress;
    private String userBirthday;
    private String userPhone;

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;

        User user = (User) object;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getUserLogin().equals(user.getUserLogin())) return false;
        if (!getUserPassword().equals(user.getUserPassword())) return false;
        if (!getUserRole().equals(user.getUserRole())) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (!getUserSurname().equals(user.getUserSurname())) return false;
        if (!getUserAddress().equals(user.getUserAddress())) return false;
        if (!getUserBirthday().equals(user.getUserBirthday())) return false;
        return getUserPhone().equals(user.getUserPhone());

    }

    @Override
    public int hashCode()
    {
        int result = getUserId().hashCode();
        result = 31 * result + getUserLogin().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        result = 31 * result + getUserRole().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserSurname().hashCode();
        result = 31 * result + getUserAddress().hashCode();
        result = 31 * result + getUserBirthday().hashCode();
        result = 31 * result + getUserPhone().hashCode();
        return result;
    }
}
