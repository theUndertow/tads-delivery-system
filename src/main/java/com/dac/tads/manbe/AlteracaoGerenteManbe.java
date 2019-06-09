/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.manbe;

import com.dac.tads.criptografia.MDFive;
import com.dac.tads.facade.CadastroFacade;
import com.dac.tads.model.Cidade;
import com.dac.tads.model.Endereco;
import com.dac.tads.model.Entregador;
import com.dac.tads.model.Estado;
import com.dac.tads.model.Gerente;
import com.dac.tads.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marco
 */

@Named(value = "alteracaoGerente")
@ViewScoped
public class AlteracaoGerenteManbe implements Serializable{

    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Estado estadoSelecionado;
    private Cidade cidadeSelecionada;
    private Usuario usuario;
    private Gerente gerente;
    private Entregador entregador;
    private Endereco endereco;
    private String error;
    private String cpf;
    private String email;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Inject
    LoginManbe loginManbe;
    
    @PostConstruct
    public void init(){
        //initiate objects
        
        usuario = loginManbe.getUsuario();
        cpf = usuario.getCpf();
        email = usuario.getEmail();
        
        endereco = usuario.getEndereco();
        
        cidadeSelecionada = endereco.getCidade();
        estadoSelecionado = endereco.getCidade().getEstado();
        
        listaEstados = CadastroFacade.selectAllState();
        listaCidades = CadastroFacade.selectAllCityById(estadoSelecionado.getId());
    }
    
    public void buscarCidades() {
        if (estadoSelecionado != null) {
            listaCidades = CadastroFacade.selectAllCityById(estadoSelecionado.getId());
        }
    }
    
    public String updateUsuario(){
        // Check the type of a user to add a database
        // encrypt the actual pass 
        String newPass = MDFive.encripta(usuario.getSenha());
        usuario.setSenha(newPass);
        
        // Set the city to address
        endereco.setCidade(cidadeSelecionada);

        // set address to the cliente
        usuario.setEndereco(endereco);
        
        if (usuario.getTipo() == 'g') {
            error = CadastroFacade.updateUser(usuario,email,cpf);
        } else if (usuario.getTipo() == 'e') {
            error = CadastroFacade.updateUser(usuario,email,cpf);
        } else{
            return "Selecione um dos tipos de usuário";
        }
        return "gerente.xhtml";
    }
    
}
