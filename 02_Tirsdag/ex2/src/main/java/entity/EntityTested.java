/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hassanainali
 */
public class EntityTested {

    public static void main (String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer cus = new Customer("Hassanain", "Ali", new Date());
        Customer cus1 = new Customer("Andreas", "Andersen", new Date());
        try{
        em.getTransaction().begin();
        em.persist(cus);
        em.persist(cus1);
        em.getTransaction().commit();
        } finally{
            em.close();
        }
    }
}
