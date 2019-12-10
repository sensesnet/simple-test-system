package com.sensesnet.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Common impl interface
 */
public interface ICommand
{
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
