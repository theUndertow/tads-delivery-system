/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.LoginDAO;
import com.dac.tads.model.Usuario;

/**
 *
 * @author marco
 */
public class LoginFacade {
    public static Usuario FazerLogin(String login, String senha){
        LoginDAO loginDAO = new LoginDAO();
        return loginDAO.selectUsuario(login, senha);
    }
}
