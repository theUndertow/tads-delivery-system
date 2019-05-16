/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.dao;

import com.dac.tads.model.Historico;
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
public class HistoricoDAO {
    
    
    public boolean insertHistorico(Historico historico) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(historico);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Retornará um único historico
    public Historico selectHistorico(int id) {
        Historico historico = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Historico where id = :id");
            query.setInteger("id", id);
            historico = (Historico) query.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return historico;
    }
    
    // Retorna uma lista de todos os historicos
    public List<Historico> selectListHistorico() {
        List<Historico> historicos;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Historico");
            historicos = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return historicos;
    }
    
    // Retorna um boolean em relação ao resultado do update
    public boolean updateHistorico(Historico historico) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(historico);
            session.getTransaction().commit();
            session.clear();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // Retorna um boolean em relação a deleção de um historico
    public boolean deleteHistorico(Historico historico) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(historico);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
