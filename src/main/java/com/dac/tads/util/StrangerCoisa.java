/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.util;

import com.dac.tads.transactionLOL.*;
import java.io.Serializable;

/**
 *
 * @author marco
 */
public class StrangerCoisa implements Serializable{
    private PedidoLol pedido;
    private ClienteLol cliente;
    private EnderecoLol endereco;
    private EstadoLol estado;
    private CidadeLol cidade;
    private UsuarioLol usuario;

    public PedidoLol getPedido() {
        return pedido;
    }

    public void setPedido(PedidoLol pedido) {
        this.pedido = pedido;
    }

    public ClienteLol getCliente() {
        return cliente;
    }

    public void setCliente(ClienteLol cliente) {
        this.cliente = cliente;
    }

    public EnderecoLol getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoLol endereco) {
        this.endereco = endereco;
    }

    public EstadoLol getEstado() {
        return estado;
    }

    public void setEstado(EstadoLol estado) {
        this.estado = estado;
    }

    public CidadeLol getCidade() {
        return cidade;
    }

    public void setCidade(CidadeLol cidade) {
        this.cidade = cidade;
    }

    public UsuarioLol getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLol usuario) {
        this.usuario = usuario;
    }
    
}
