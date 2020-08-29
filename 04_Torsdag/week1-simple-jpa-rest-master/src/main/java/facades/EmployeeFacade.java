/*
getEmployeeById
getEmployeesByName

 */
package facades;

import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author hassanainali
 */
public class EmployeeFacade {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");     
    private static EmployeeFacade instance;
    private static EmployeeFacade ef = EmployeeFacade.getFacadeExample(emf);
    
    public static void main(String[] args) {
        
        Employee e1 = ef.createEmployee("Sode", "Charlottenlund", 7000);
        Employee e2 = ef.createEmployee("Jens", "Gentofte", 9000);
        Employee e3 = ef.createEmployee("Hassanain", "Lyngby", 8000);
        
        //getAllEmployees
      //  System.out.println("Get all employees: " + ef.getAllEmployees());
        
              
    }
    
        public Employee createEmployee(String name, String adress, int salary){
            Employee e1 = new Employee(name, adress, salary);
            EntityManager em = emf.createEntityManager();
            try{
                em.getTransaction().begin();
                em.persist(e1);
                em.getTransaction().commit();
                return e1;
            }finally {
                em.close();
            }
        }
    
    /*public List<Employee> getAllEmployees(){
         EntityManager em = emf.createEntityManager(); 
         try{
            TypedQuery<Employee> query = em.createQuery("Select e FROM Employee e",Employee.class);
            List<Employee> employees =  query.getResultList();
            return employees;
        }finally {
            em.close();
        }
    
    
    public Employee getEmployeeWithHighestSalary(){
        EntityManager em = emf.createEntityManager(); 
         try{
            TypedQuery<Employee> query = em.createQuery("Select e FROM Employee e WHERE MAX(e.salary)",Employee.class);
            Employee result = query.getSingleResult();
            System.out.println("Highest salary is: " + result);
        }finally {
            em.close();
        }
        return null;
    }*/
    
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
}
