package com.sensesnet.pojo.authentication;


import lombok.*;

import java.util.Objects;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * POJO: User role.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole
{
    private @NonNull Integer roleId;
    private @NonNull String roleName;
    private String roleDesc;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return getRoleId().equals(userRole.getRoleId()) &&
                getRoleName().equals(userRole.getRoleName()) &&
                Objects.equals(getRoleDesc(), userRole.getRoleDesc());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getRoleId(), getRoleName(), getRoleDesc());
    }
}
