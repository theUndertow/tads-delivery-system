/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.EntregadorDAO;
import com.dac.tads.model.Entregador;
import java.util.List;

/**
 *
 * @author marco
 */
public class EntregadorFacade {
    public static List<Entregador> listAllDeliveryman(){
        return EntregadorDAO.selectListEntregador();
    }

    public static Entregador listDeliveryman(Long idInput) {
        return EntregadorDAO.selectEntregador(idInput);
    }
}
