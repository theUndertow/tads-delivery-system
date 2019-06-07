/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.criptografia.MDFive;
import com.dac.tads.facade.CadastroFacade;
import com.dac.tads.model.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "cadastroManbe")
@ViewScoped
public class CadastroManbe implements Serializable{

    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Estado estadoSelecionado;
    private Cidade cidadeSelecionada;
    private Usuario usuario;
    private Gerente gerente;
    private Entregador entregador;
    private Endereco endereco;
    private String error;

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public Cidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(Cidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @PostConstruct
    public void init() {
        //initiate objects
        usuario = new Usuario();
        gerente = new Gerente();
        endereco = new Endereco();
        entregador = new Entregador();

        listaEstados = CadastroFacade.selectAllState();
        estadoSelecionado = CadastroFacade.selectStateId(Long.parseLong("10"));
        listaCidades = CadastroFacade.selectAllCityById(estadoSelecionado.getId());
    }

    public void buscarCidades() {
        if (estadoSelecionado != null) {
            listaCidades = CadastroFacade.selectAllCityById(estadoSelecionado.getId());
        }
    }

    public String cadastroUsuario() {
        // Check the type of a user to add a database
        // encrypt the actual pass 
        String newPass = MDFive.encripta(usuario.getSenha());
        usuario.setSenha(newPass);
        // Set the city to address
        endereco.setCidade(cidadeSelecionada);

        // set address to the cliente
        usuario.setEndereco(endereco);
        
        if (usuario.getTipo() == 'g') {
            usuario = cadastroGerente();
        } else if (usuario.getTipo() == 'e') {
            usuario = cadastroEntregador();
        } else{
            return "";
        }
        
        error = CadastroFacade.registerUsuario(usuario);
        return "./gerente.xhtml";
    }

    private Usuario cadastroGerente() {
     
        // Set user to a manager 
        usuario.setGerente(gerente);

        // Set manager to a user
        gerente.setUsuario(usuario);
        
        // Pass the user and employee to facade to make the register
        return usuario;
    }

    private Usuario cadastroEntregador() {

        // Set user to a deliveryman 
        usuario.setEntregador(entregador);

        // Set deliveryman to a user
        entregador.setUsuario(usuario);
        
        // Pass the user and employee to facade to make the register
        return usuario;
    }
}
