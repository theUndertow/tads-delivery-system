/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.facade.CadastroFacade;
import com.dac.tads.model.Entregador;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marco
 */
@Named(value = "alteracaoEntregador")
@ViewScoped
public class AlteracaoEntregadorManbe implements Serializable{
    private List<Entregador> listaEntregadores;
    private String id;

    public List<Entregador> getListaEntregadores() {
        return listaEntregadores;
    }

    public void setListaEntregadores(List<Entregador> listaEntregadores) {
        this.listaEntregadores = listaEntregadores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @PostConstruct
    public void init(){
        
    }
    
     
}
