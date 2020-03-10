package com.bertamalfitano;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new student object...");

            Student tempStudent = new Student("Paul", "Wall", "paul@gmail.com");
            Student tempStudent1 = new Student("Baul", "Fall", "fallin@mail.ru");

            session.beginTransaction();
            session.save(tempStudent);

            session.save(tempStudent1);

            System.out.println("Saving the student");

            session.getTransaction().commit();

            System.out.println("Done");

        } finally {
            factory.close();
        }
    }
}
