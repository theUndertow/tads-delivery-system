/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.CadastroDAO;
import com.dac.tads.dao.CidadeDAO;
import com.dac.tads.dao.EntregadorDAO;
import com.dac.tads.dao.EstadoDAO;
import com.dac.tads.dao.GerenteDAO;
import com.dac.tads.dao.UsuarioDAO;
import com.dac.tads.model.Cidade;
import com.dac.tads.model.Entregador;
import com.dac.tads.model.Estado;
import com.dac.tads.model.Gerente;
import com.dac.tads.model.Usuario;
import java.util.List;

/**
 *
 * @author marco
 */
public class CadastroFacade {
     //Busca todas as cidades pelo Id do estado (cidades ligadas com estado)
    public static List<Cidade> selectAllCityById(long id){
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.selectEstadoId(id);
        List<Cidade> cidades = estado.getCidades();
        return cidades;
    }
    
    //Passa um ID e busca a cidade que possui esse id
    public static Cidade selectCityById(long id){
        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.selectCidade(id);
        return cidade;
    }
    
    public static List<Cidade> selectAllCity(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.selectListCidade();
    }
    
    public static List<Estado> selectAllState(){
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectListEstado();
    }
    
    public static Estado selectStateSigla(String sigla){
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectEstadoSigla(sigla);
    }
    
    public static Estado selectStateId(Long id){
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectEstadoId(id);
    }

    public static String registerEntregador(Usuario user, Entregador deliveryman) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        CadastroDAO cadastroDAO = new CadastroDAO();
        
        if(!cadastroDAO.validateEmail(user)){
            return "Entregador com o mesmo email ja adicionado no banco meu bom";
        }
        usuarioDAO.insertUsuario(user);
        entregadorDAO.insertEntregador(deliveryman);
        
        return "";
    }

    public static String registerGerente(Usuario user, Gerente manager) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        CadastroDAO cadastroDAO = new CadastroDAO();
        
        if(!cadastroDAO.validateEmail(user)){
            return "Gerente com o mesmo email ja adicionado no banco meu bom";
        }
        usuarioDAO.insertUsuario(user);
        gerenteDAO.insertGerente(manager);
        
        return "";
    }

}
