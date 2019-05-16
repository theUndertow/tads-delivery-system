/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.dao;

import com.dac.tads.model.Gerente;
import com.dac.tads.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author marco
 */
public class GerenteDAO {
    
    
    public boolean insertGerente(Gerente gerente) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(gerente);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Retornará um único gerente
    public Gerente selectGerente(int id) {
        Gerente gerente = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Gerente where Gerente.id = :id");
            query.setInteger("id", id);
            gerente = (Gerente) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return gerente;
    }
    
    // Retorna uma lista de todos os gerentes
    public List<Gerente> selectListGerente() {
        List<Gerente> gerentes;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Gerente");
            gerentes = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return gerentes;
    }
    
    // Retorna um boolean em relação ao resultado do update
    public boolean updateGerente(Gerente gerente) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(gerente);
            session.getTransaction().commit();
            session.clear();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // Retorna um boolean em relação a deleção de um gerente
    public boolean deleteGerente(Gerente gerente) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(gerente);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
