/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author marco
 */
@Entity
@Named(value = "tb_gerente")
public class Gerente implements Serializable {
    private long gerente_id;
    private long gerente_usuario;
    private Usuario usuario = new Usuario();

    public Gerente() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getGerente_id() {
        return gerente_id;
    }

    public void setGerente_id(long gerente_id) {
        this.gerente_id = gerente_id;
    }

    public long getGerente_usuario() {
        return gerente_usuario;
    }

    public void setGerente_usuario(long gerente_usuario) {
        this.gerente_usuario = gerente_usuario;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="gerente_usuario", updatable=true)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
