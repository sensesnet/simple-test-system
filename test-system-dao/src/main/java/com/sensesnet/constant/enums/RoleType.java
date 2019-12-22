package com.sensesnet.constant.enums;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Enum: Type of roles
 */
enum RoleType
{
    ADMIN(1, "ADMIN"),
    USER(2, "USER");


    private int roleId;
    private String roleName;

    RoleType(int roleId, String roleName)
    {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }
}
