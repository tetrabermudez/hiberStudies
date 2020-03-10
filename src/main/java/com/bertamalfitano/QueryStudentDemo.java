package com.bertamalfitano;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents= session.createQuery("from Student").getResultList();
            //display the students
            displayTheStudents(theStudents);

            //query students : lastName = "Fall"
            theStudents = session.createQuery("from Student s where s.lastName='Fall'").getResultList();
            System.out.println("Students who have last name Fall");
            displayTheStudents(theStudents);

            //query students : lastName = Duck or firstName = Paul
            theStudents = session.createQuery(
                    "from Student s where s.lastName='Duck' OR s.firstName='Paul'").getResultList();
            System.out.println("The students with Duck last name or Paul firrstName");
            displayTheStudents(theStudents);

            //query students where email LIke '%mail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%mail.com'").getResultList();
            System.out.println("The students with such emails");
            displayTheStudents(theStudents);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}


























