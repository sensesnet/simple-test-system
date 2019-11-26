package com.sensesnet.pojo.authentication;

import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: User information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo
{
    private @NonNull Integer infoId;
    private @NonNull String userName;
    private @NonNull String userSurname;
    private @NonNull String userAddress;
    private @NonNull Date userBirthday;
    private @NonNull String userPhone;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) obj;
        return getInfoId().equals(userInfo.getInfoId()) &&
                getUserName().equals(userInfo.getUserName()) &&
                getUserSurname().equals(userInfo.getUserSurname()) &&
                getUserAddress().equals(userInfo.getUserAddress()) &&
                getUserBirthday().equals(userInfo.getUserBirthday()) &&
                getUserPhone().equals(userInfo.getUserPhone());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getInfoId(), getUserName(), getUserSurname(), getUserAddress(), getUserBirthday(), getUserPhone());
    }
}
