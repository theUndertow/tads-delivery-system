/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
@Named(value = "tb_historico")
public class Historico implements Serializable {
    private long historico_id;
    private String historico_historico;
    private Date tempo;

    public Historico() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getHistorico_id() {
        return historico_id;
    }

    public void setHistorico_id(long historico_id) {
        this.historico_id = historico_id;
    }

    public String getHistorico_historico() {
        return historico_historico;
    }

    public void setHistorico_historico(String historico_historico) {
        this.historico_historico = historico_historico;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }
    
    
}
