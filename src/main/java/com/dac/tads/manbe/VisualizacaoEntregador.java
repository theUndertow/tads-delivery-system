/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marco
 */

@Named(value = "visualizacaoEntregador")
@RequestScoped
public class VisualizacaoEntregador implements Serializable{
    private Entregador entregador;

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
    
    @Inject
    LoginManbe loginManbe;
    
    @PostConstruct
    public void init(){
        entregador = (Entregador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entregadorDetail");
    
        if(entregador == null && loginManbe.getUsuario().getTipo() == 'g'){
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            handler.handleNavigation(FacesContext.getCurrentInstance(), null, "gerente?faces-redirect=true");
            // renderiza a tela
            FacesContext.getCurrentInstance().renderResponse();
        }else if(loginManbe.getUsuario().getTipo() != 'g'){
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            handler.handleNavigation(FacesContext.getCurrentInstance(), null, "entregador?faces-redirect=true");
            // renderiza a tela
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
}
