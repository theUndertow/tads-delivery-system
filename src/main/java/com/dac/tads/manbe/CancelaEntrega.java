/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.facade.EntregaFacade;
import com.dac.tads.model.Entrega;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "cancelaEntrega")
@ViewScoped
public class CancelaEntrega implements Serializable{
    private Entrega entrega;

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    @Inject
    LoginManbe loginManbe;
    
    @PostConstruct
    public void init(){
        entrega = (Entrega) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entregaCancel");
        if(entrega == null && loginManbe.getUsuario().getTipo() == 'g'){
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
    
    public String failShippmentReason(){
        if(!entrega.getMotivo().equals("")){
            entrega.setDescricao("Cancelado");
            EntregaFacade.reasonFailShippment(entrega);
            return "gerente";
        }else{
            return "";
        }
    }
}
