/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marco
 */
@Entity
@Named(value = "tb_entregador")
public class Entregador implements Serializable {
    private long entregador_id;
    private long entregador_usuario;
    private Usuario usuario = new Usuario();

    public Entregador() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getEntregador_id() {
        return entregador_id;
    }

    public void setEntregador_id(long entregador_id) {
        this.entregador_id = entregador_id;
    }

    public long getEntregador_usuario() {
        return entregador_usuario;
    }

    public void setEntregador_usuario(long entregador_usuario) {
        this.entregador_usuario = entregador_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
