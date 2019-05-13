/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marco
 */
@Entity
@Named(value = "tb_entrega")
public class Entrega implements Serializable {
    private long entrega_id;
    private String entrega_descricao;
    private String entrega_destinatario;
    private Date entrega_data;
    private String entrega_motivo;
    private long entrega_entregador;
    private long entrega_historico;
    private long entrega_endereco;
    private Entregador entregador = new Entregador();
    private Historico historico = new Historico();
    private Endereco endereco = new Endereco();

    public Entrega() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(long entrega_id) {
        this.entrega_id = entrega_id;
    }

    public String getEntrega_descricao() {
        return entrega_descricao;
    }

    public void setEntrega_descricao(String entrega_descricao) {
        this.entrega_descricao = entrega_descricao;
    }

    public String getEntrega_destinatario() {
        return entrega_destinatario;
    }

    public void setEntrega_destinatario(String entrega_destinatario) {
        this.entrega_destinatario = entrega_destinatario;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEntrega_data() {
        return entrega_data;
    }

    public void setEntrega_data(Date entrega_data) {
        this.entrega_data = entrega_data;
    }

    public String getEntrega_motivo() {
        return entrega_motivo;
    }

    public void setEntrega_motivo(String entrega_motivo) {
        this.entrega_motivo = entrega_motivo;
    }

    public long getEntrega_entregador() {
        return entrega_entregador;
    }

    public void setEntrega_entregador(long entrega_entregador) {
        this.entrega_entregador = entrega_entregador;
    }

    public long getEntrega_historico() {
        return entrega_historico;
    }

    public void setEntrega_historico(long entrega_historico) {
        this.entrega_historico = entrega_historico;
    }

    public long getEntrega_endereco() {
        return entrega_endereco;
    }

    public void setEntrega_endereco(long entrega_endereco) {
        this.entrega_endereco = entrega_endereco;
    }
    
    
    @ManyToOne
    @JoinColumn(name = "entrega_entregador")
    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="entrega_historico", updatable=true)
    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }
    
    @ManyToOne
    @JoinColumn(name = "entrega_endereco")
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
