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
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;

        UserRole userRole = (UserRole) object;

        if (!getRoleId().equals(userRole.getRoleId())) return false;
        if (!getRoleName().equals(userRole.getRoleName())) return false;
        return getRoleDesc().equals(userRole.getRoleDesc());

    }

    @Override
    public int hashCode()
    {
        int result = getRoleId().hashCode();
        result = 31 * result + getRoleName().hashCode();
        result = 31 * result + getRoleDesc().hashCode();
        return result;
    }
}
