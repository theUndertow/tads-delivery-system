/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.model.Endereco;
import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
import com.dac.tads.model.Usuario;
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
@Named(value = "entregaVisualizacao")
@RequestScoped
public class EntregaVisualizacao implements Serializable {

    private Entrega entrega;
    private Entregador entregador;
    private String responsavel;
    private Endereco endereco;

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Inject
    LoginManbe loginManbe;

    @PostConstruct
    public void init() {

        entrega = (Entrega) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entregaDetail");
        
        if(entrega == null){
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            if(loginManbe.getUsuario().getTipo() == 'e'){
                handler.handleNavigation(FacesContext.getCurrentInstance(), null, "entregador?faces-redirect=true");
            }
            if(loginManbe.getUsuario().getTipo() == 'g'){
                handler.handleNavigation(FacesContext.getCurrentInstance(), null, "gerente?faces-redirect=true");
            }
            FacesContext.getCurrentInstance().renderResponse();
        }
        endereco = entrega.getEndereco();
        if(entrega.getEntregador() == null){
            responsavel = "Esperando entregador";
        }else{
            responsavel = entrega.getEntregador().getUsuario().getNome();
        }
    }
}
