/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.CidadeDAO;
import com.dac.tads.dao.EnderecoDAO;
import com.dac.tads.dao.EntregaDAO;
import com.dac.tads.dao.EstadoDAO;
import com.dac.tads.dao.HistoricoDAO;
import com.dac.tads.model.*;
import com.dac.tads.util.StrangerCoisa;
import java.util.Date;



/**
 *
 * @author marco
 */
public class CadastroEntregaFacade {
    public static void insertEntrega(StrangerCoisa strangerCoisa){
        HistoricoDAO historicoDAO = new HistoricoDAO();
        EntregaDAO entregaDAO = new EntregaDAO();
        CidadeDAO cidadeDAO = new CidadeDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        
        Entrega entrega = new Entrega();
        Historico historico = new Historico();
        Cidade cidade = cidadeDAO.selectCidade(strangerCoisa.getCidade().getId());
        
        Endereco endereco = new Endereco();
        
        endereco.setCidade(cidade);
        endereco.setBairro(strangerCoisa.getEndereco().getBairro());
        endereco.setComplemento(strangerCoisa.getEndereco().getComplemento());
        endereco.setNumero(strangerCoisa.getEndereco().getNumero());
        endereco.setRua(strangerCoisa.getEndereco().getRua());
        
        enderecoDAO.insertEndereco(endereco);
        
        // Set atributos para entrega
        entrega.setEndereco(endereco);
        entrega.setDescricao(strangerCoisa.getPedido().getSituacao());
        entrega.setDestinatario(strangerCoisa.getUsuario().getNome());
        entrega.setData(strangerCoisa.getPedido().getTempo());
        
        //Set atributos para historico
        historico.setEntrega(entrega);
        historico.setHistorico(entrega.getDescricao());
        Date date = new Date();
        historico.setTempo(date);
        
        entrega.setHistorico(historico);
        
        historicoDAO.insertHistorico(historico);
        entregaDAO.insertEntrega(entrega);
        
    }
}
