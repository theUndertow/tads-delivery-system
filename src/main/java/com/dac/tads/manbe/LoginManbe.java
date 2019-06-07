/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.criptografia.MDFive;
import com.dac.tads.facade.LoginFacade;
import com.dac.tads.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@SessionScoped
@Named(value = "loginManbe")
public class LoginManbe implements Serializable{
    private String login;
    private String password;
    private Usuario usuario;

    /**
     * Creates a new instance of LoginManbe
     */
    public LoginManbe() {
    }

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean isLogado(){
        return this.getLogin() != null;
    }
    
    
    public String verifyLogin(){
        String pass = MDFive.encripta(this.getPassword());
        System.out.println(MDFive.encripta(this.getPassword()));
        usuario = LoginFacade.FazerLogin(this.getLogin(), pass);
        if(usuario != null){
            if(usuario.getTipo() == 'e')
                return "entregador";   
            else if(usuario.getTipo() == 'g')
                return "gerente";
        }
        
        return "index";       
    }
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
}
