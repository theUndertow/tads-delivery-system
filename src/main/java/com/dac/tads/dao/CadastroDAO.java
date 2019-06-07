/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.dao;

import com.dac.tads.model.Usuario;
import com.dac.tads.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author marco
 */
public class CadastroDAO {
    public boolean validateEmail(Usuario user){
        List<Usuario> users = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Usuario where usuario_email = :email").setParameter("email", user.getEmail());
            users =  query.list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (users.isEmpty());
    }
    
    public boolean validateCPF(Usuario user){
        List<Usuario> users = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Usuario where usuario_cpf = :cpf").setParameter("cpf", user.getCpf());
            users =  query.list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (users.isEmpty());
    }
    
    public boolean validateEmailUpdate(String email){
        List<Usuario> users = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Usuario where usuario_email = :email").setParameter("email", email);
            users =  query.list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (users.isEmpty());
    }

    public boolean validateCPFUpdate(String cpf){
        List<Usuario> users = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Usuario where usuario_cpf = :cpf").setParameter("cpf", cpf);
            users =  query.list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (users.isEmpty());
    }
}
