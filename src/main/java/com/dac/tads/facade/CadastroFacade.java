/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.facade;

import com.dac.tads.dao.*;
import com.dac.tads.model.*;
import java.util.List;

/**
 *
 * @author marco
 */
public class CadastroFacade {

    public static final String msgErrorEmail = " com o mesmo email ja adicionado no banco meu bom";
    public static final String msgErrorCpf = " com o mesmo cpf ja adicionado no banco meu bom";

    //Busca todas as cidades pelo Id do estado (cidades ligadas com estado)
    public static List<Cidade> selectAllCityById(long id) {
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.selectEstadoId(id);
        List<Cidade> cidades = estado.getCidades();
        return cidades;
    }

    //Passa um ID e busca a cidade que possui esse id
    public static Cidade selectCityById(long id) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.selectCidade(id);
        return cidade;
    }

    public static List<Cidade> selectAllCity() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.selectListCidade();
    }

    public static List<Estado> selectAllState() {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectListEstado();
    }

    public static Estado selectStateSigla(String sigla) {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectEstadoSigla(sigla);
    }

    public static Estado selectStateId(Long id) {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.selectEstadoId(id);
    }

    public static String registerUsuario(Usuario user) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        CadastroDAO cadastroDAO = new CadastroDAO();

        if (!cadastroDAO.validateEmail(user)) {
            return "Usuario " + msgErrorEmail;
        }

        if (!cadastroDAO.validateCPF(user)) {
            return "Usuario " + msgErrorCpf;
        }

        usuarioDAO.insertUsuario(user);
        if (user.getTipo() == 'g') {
            gerenteDAO.insertGerente(user.getGerente());
        } else if (user.getTipo() == 'e') {
            entregadorDAO.insertEntregador(user.getEntregador());
        }

        return "";
    }

    public static String updateUser(Usuario user, String email, String cpf) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        CadastroDAO cadastroDAO = new CadastroDAO();

        if (!user.getEmail().equals(email)) {
            if (!cadastroDAO.validateEmailUpdate(email)) {
                return "Usuario " + msgErrorEmail;
            }
        }
        
        if (!user.getCpf().equals(cpf)) {
            if (!cadastroDAO.validateCPFUpdate(cpf)) {
                return "Usuario " + msgErrorCpf;
            }
        }

        user.setCpf(cpf);
        user.setEmail(email);
        usuarioDAO.insertUsuario(user);
        if (user.getTipo() == 'g') {
            gerenteDAO.insertGerente(user.getGerente());
        } else if (user.getTipo() == 'e') {
            entregadorDAO.insertEntregador(user.getEntregador());
        }

        return "";
    }

}
