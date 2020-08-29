package dbfacade;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author hassanainali
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer findById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            Customer cus = em.find(Customer.class,id);
            return cus;
        }finally {
            em.close();
        }
    }
    
    public Customer findByLastName(String lastName){
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :name",Customer.class);
            List<Customer> lis = query.setParameter("name", lastName).getResultList();
            for(Customer cus : lis){
            return cus;
            }
        }finally {
            em.close();
        }
    return c;
    }
    
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("Select customer from Customer Customer",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public int getNumberOfCumstomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT COUNT(*) from Customer Customer", Customer.class);
            return query.getFirstResult();
        } finally{
            em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        Customer cus = new Customer(firstName, lastName, new Date());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        }finally {
            em.close();
        }
    }
    
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);

        System.out.println("Customer 1: " + cf.findById(1).getFirstName() + " " + cf.findById(1).getLastName());
        System.out.println("Total number of customers: " + cf.allCustomers().size());
        System.out.println("First name: " + cf.findByLastName("Ali").getFirstName());
    }
}