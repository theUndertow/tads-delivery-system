/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.facade.EntregaFacade;
import com.dac.tads.model.Entrega;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "gerenteManbe")
@RequestScoped
public class GerenteManbe implements Serializable{
    
    private List<Entrega> listaGerenteEntregas;
    private int idInput;

    public List<Entrega> getListaGerenteEntregas() {
        return listaGerenteEntregas;
    }

    public void setListaGerenteEntregas(List<Entrega> listaGerenteEntregas) {
        this.listaGerenteEntregas = listaGerenteEntregas;
    }

    public int getIdInput() {
        return idInput;
    }

    public void setIdInput(int idInput) {
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
        listaGerenteEntregas = EntregaFacade.listaAllDeliveriesToManager();
    }
    
    public String logout() {
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
    
    public void buscaPedido() {
        Entrega temp = EntregaFacade.selectDelivery(idInput);
        if (temp != null) {
            int i = 0;
            for (Entrega e : listaGerenteEntregas) {
                if (e.getId() == temp.getId()) {
                    listaGerenteEntregas.remove(i);
                    listaGerenteEntregas.add(0, temp);
                    break;
                }
                i++;
            }
        }
    }
    
    public String details(Entrega entrega) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entregaDetail", entrega);
        return "visualizacao_entrega";
    }
    
    public String cancelDelivery(Entrega entrega){
        if(entrega.getDescricao().equals("Aguardando") 
                || entrega.getDescricao().equals("Em Entrega")){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entregaCancel", entrega);
        return "cancelamento_entrega";
        }else{
            return "";
        }
    }
}
