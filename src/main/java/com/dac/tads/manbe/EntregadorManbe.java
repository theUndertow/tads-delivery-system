/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.facade.EntregaFacade;
import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "entregadorManbe")
@ViewScoped
public class EntregadorManbe implements Serializable {

    private List<Entrega> listaEntregasAguardando;
    private List<Entrega> listaEntregasEntregador;
    private List<Entrega> listaTodasEntregasEntregador;
    private List<Entrega> listaEntregasAlterados;
    private int idInput;
    private Entregador entregador;
    private String error;
    private String info;

    public List<Entrega> getListaEntregasAguardando() {
        return listaEntregasAguardando;
    }

    public void setListaEntregasAguardando(List<Entrega> listaEntregasAguardando) {
        this.listaEntregasAguardando = listaEntregasAguardando;
    }

    public List<Entrega> getListaEntregasEntregador() {
        return listaEntregasEntregador;
    }

    public void setListaEntregasEntregador(List<Entrega> listaEntregasEntregador) {
        this.listaEntregasEntregador = listaEntregasEntregador;
    }

    public List<Entrega> getListaTodasEntregasEntregador() {
        return listaTodasEntregasEntregador;
    }

    public void setListaTodasEntregasEntregador(List<Entrega> listaTodasEntregasEntregador) {
        this.listaTodasEntregasEntregador = listaTodasEntregasEntregador;
    }

    public List<Entrega> getListaEntregasAlterados() {
        return listaEntregasAlterados;
    }

    public void setListaEntregasAlterados(List<Entrega> listaEntregasAlterados) {
        this.listaEntregasAlterados = listaEntregasAlterados;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LoginManbe getLoginManbe() {
        return loginManbe;
    }

    public void setLoginManbe(LoginManbe loginManbe) {
        this.loginManbe = loginManbe;
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
    public void init() {

        if (loginManbe.getUsuario().getTipo() != 'e') {
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            handler.handleNavigation(FacesContext.getCurrentInstance(), null, "gerente?faces-redirect=true");
            // renderiza a tela
            FacesContext.getCurrentInstance().renderResponse();
        }

        if (loginManbe.getUsuario().getTipo() == 'e') {
            entregador = loginManbe.getUsuario().getEntregador();
            listaEntregasEntregador = EntregaFacade.listToDeliveryman(entregador);
            listaTodasEntregasEntregador = EntregaFacade.listAllToDeliveryman(entregador);
        } else {
            entregador = new Entregador();
            listaEntregasEntregador = new ArrayList<>();
        }
        listaEntregasAguardando = EntregaFacade.listAllWaiting();
        listaEntregasAlterados = new ArrayList<>();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String atribuirEntrega(Entrega entrega) {
        this.error = EntregaFacade.assingDelivery(entrega, entregador);
        if (this.error == null) {
            return "entregador_lista_entrega.xhtml";
        } else {
            return "";
        }
    }

    public void buscaPedido() {
        Entrega temp = EntregaFacade.selectDelivery(idInput);
        if (temp != null) {
            int i = 0;
            for (Entrega e : listaEntregasAguardando) {
                if (e.getId() == temp.getId()) {
                    listaEntregasAguardando.remove(i);
                    listaEntregasAguardando.add(0, temp);
                    break;
                }
                i++;
            }
        }
    }

    public void buscaPedidoEntregador() {
        Entrega temp = EntregaFacade.selectDelivery(idInput);
        if (temp != null) {
            int i = 0;
            for (Entrega e : listaEntregasEntregador) {
                if (e.getId() == temp.getId()) {
                    listaEntregasEntregador.remove(i);
                    listaEntregasEntregador.add(0, temp);
                    break;
                }
                i++;
            }
        }
    }

    public void buscaTodosEntregador() {
        Entrega temp = EntregaFacade.selectDelivery(idInput);
        if (temp != null) {
            int i = 0;
            for (Entrega e : listaTodasEntregasEntregador) {
                if (e.getId() == temp.getId()) {
                    listaTodasEntregasEntregador.remove(i);
                    listaTodasEntregasEntregador.add(0, temp);
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

    public void infoToSave(Entrega entrega) {
        this.info = "Clique no botão Salvar para que as alterações sejam feitas no sistema";
        int i = 0;

        if (!listaEntregasAlterados.contains(entrega)) {
            listaEntregasAlterados.add(entrega);
        } else {
            for (Entrega e : listaEntregasAlterados) {
                if (e.equals(entrega)) {
                    listaEntregasAlterados.set(i, entrega);
                    break;
                }
                i++;
            }
        }
    }

    public void saveNewListSystem() {

        if (!listaEntregasAlterados.isEmpty()) {
            this.error = EntregaFacade.updateDeliveries(listaEntregasAlterados);
            if (this.error == null) {
                this.info = "Salvo com sucesso meu filho";
                return;
            }
        }
        this.info = "Algo deu errado meu filho";
    }

    public String failShippment(Entrega entrega) {
        if (entrega.getDescricao().equals("Em Entrega")) {
            entrega.setDescricao("Não Entregue");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entregaFail", entrega);
            return "falha_entrega";
        }
        return "";
    }
}
