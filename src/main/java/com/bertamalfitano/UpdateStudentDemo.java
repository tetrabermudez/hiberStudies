package com.bertamalfitano;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\n Getting student with id " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            //change the name of the student with id 1
            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();


            //update email for all students
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Update email for all students");
            session.createQuery("UPDATE Student set email='foo@yandex.ru'").executeUpdate();
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


























