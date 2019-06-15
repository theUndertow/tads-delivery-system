/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.dao;

import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
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
public class EntregadorDAO {
    
    
    public boolean insertEntregador(Entregador entregador) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entregador);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Retornará um único entregador
    public Entregador selectEntregador(int id) {
        Entregador entregador = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Entregador where id = :id");
            query.setInteger("id", id);
            entregador = (Entregador) query.uniqueResult();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return entregador;
    }
    
    // Retorna uma lista de todos os entregadors
    public List<Entregador> selectListEntregador() {
        List<Entregador> entregadors;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Entregador");
            entregadors = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return entregadors;
    }
    
    // Retorna um boolean em relação ao resultado do update
    public boolean updateEntregador(Entregador entregador) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entregador);
            session.getTransaction().commit();
            session.clear();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // Retorna um boolean em relação a deleção de um entregador
    public boolean deleteEntregador(Entregador entregador) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(entregador);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static List<Entrega> listToDeliveryman(Entregador deliveryman){
        List<Entrega> deliveries;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Entrega where entrega_entregador = :id_entregador")
                    .setParameter("id_entregador", deliveryman.getId());
            deliveries = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return deliveries;
    }
}
