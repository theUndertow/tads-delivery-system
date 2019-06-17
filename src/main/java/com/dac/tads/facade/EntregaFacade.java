/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.CidadeDAO;
import com.dac.tads.dao.EnderecoDAO;
import com.dac.tads.dao.EntregaDAO;
import com.dac.tads.dao.EntregadorDAO;
import com.dac.tads.dao.HistoricoDAO;
import com.dac.tads.model.*;
import com.dac.tads.util.Coisa;
import com.dac.tads.util.Delivery;
import com.dac.tads.util.StrangerCoisa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marco
 */
public class EntregaFacade {

    public static void insertEntrega(StrangerCoisa strangerCoisa) {
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
        entrega.setNum_pedido(strangerCoisa.getPedido().getId());
        entrega.setDescricao((strangerCoisa.getPedido().getSituacao()
                .equals("Pago") ? "Aguardando" : "Indefinido"));
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

    public static List<Entrega> listAllWaiting() {
        return EntregaDAO.listaAllWaiting();
    }

    public static Entrega selectDelivery(int id) {
        return EntregaDAO.selectEntrega(id);
    }

    public static List<Entrega> listToDeliveryman(Entregador deliveryman) {
        return EntregadorDAO.listToDeliveryman(deliveryman);
    }

    public static String assingDelivery(Entrega delivery, Entregador deliveryman) {

        List<Entrega> deliveries = EntregadorDAO.listToDeliveryman(deliveryman);

        if (deliveries.size() < 5) {
            EntregaDAO entregaDAO = new EntregaDAO();
            delivery.setDescricao("Em Entrega");
            delivery.setEntregador(deliveryman);
            Coisa coisa = new Coisa();
            coisa.setEstado(delivery.getDescricao());
            coisa.setId_pedido(delivery.getNum_pedido());
            int erroOuAcertoKKJJ = Delivery.updateDelivery(coisa);
            if (erroOuAcertoKKJJ == 200) {
                entregaDAO.updateEntrega(delivery);
            } else {
                return "Erro : " + erroOuAcertoKKJJ + " Pule pela janela meu filho";
            }
            entregaDAO.updateEntrega(delivery);
        } else {
            return "Erro : Ja antingiu o limite de entregas";
        }
        return null;
    }

    public static String updateDeliveries(List<Entrega> listaEntregasAlterados) {
        EntregaDAO entregaDAO = new EntregaDAO();

        for (Entrega e : listaEntregasAlterados) {
            if (e.getDescricao().equals("Entregue")) {
                Coisa coisa = new Coisa();
                coisa.setEstado(e.getDescricao());
                coisa.setId_pedido(e.getNum_pedido());
                int erroOuAcertoKKJJ = Delivery.updateDelivery(coisa);
                if (erroOuAcertoKKJJ == 200) {
                    entregaDAO.updateEntrega(e);
                } else {
                    return "Pedido "+ e.getId()+" Não pode ser entregue. Erro:" + erroOuAcertoKKJJ + " Pule pela janela meu filho";
                }
                entregaDAO.updateEntrega(e);
            }
        }
        return null;
    }

    public static void reasonFailShippment(Entrega entrega) {
        EntregaDAO entregaDAO = new EntregaDAO();
        Coisa coisa = new Coisa();

        coisa.setEstado(entrega.getDescricao());
        coisa.setId_pedido(entrega.getNum_pedido());
        coisa.setMotivo(entrega.getMotivo());
        Delivery.updateDelivery(coisa);
        entregaDAO.updateEntrega(entrega);
    }

    public static List<Entrega> listaAllDeliveriesToManager() {
        return EntregaDAO.selectListEntrega();
    }

    public static List<Entrega> listAllToDeliveryman(Entregador entregador) {
        return EntregadorDAO.listAllToDeliveryman(entregador);
    }
}
