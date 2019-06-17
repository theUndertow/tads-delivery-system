/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.facade.EntregadorFacade;
import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "alteracaoEntregador")
@ViewScoped
public class AlteracaoEntregadorManbe implements Serializable{
    private List<Entregador> listaEntregadores;
    private Long idInput;

    public List<Entregador> getListaEntregadores() {
        return listaEntregadores;
    }

    public void setListaEntregadores(List<Entregador> listaEntregadores) {
        this.listaEntregadores = listaEntregadores;
    }public Long getIdInput() {
        return idInput;
    }

    public void setIdInput(Long idInput) {
        this.idInput = idInput;
    }
    
    @Inject
    LoginManbe loginManbe;
    
    @PostConstruct
    public void init(){
        if(loginManbe.getUsuario().getTipo() != 'g'){
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            handler.handleNavigation(FacesContext.getCurrentInstance(), null, "entregador?faces-redirect=true");
            // renderiza a tela
            FacesContext.getCurrentInstance().renderResponse();
        }
        listaEntregadores = EntregadorFacade.listAllDeliveryman();
    }
    
    public void buscaEntregador(){
        Entregador temp = EntregadorFacade.listDeliveryman(idInput);
        if (temp != null) {
            int i = 0;
            for (Entregador e : listaEntregadores) {
                if (e.getId() == temp.getId()) {
                    listaEntregadores.remove(i);
                    listaEntregadores.add(0, temp);
                    break;
                }
                i++;
            }
        }
    }
    
    public String details(Entregador entregador) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entregadorDetail", entregador);
        return "visualizacao_entregador";
    }
}
