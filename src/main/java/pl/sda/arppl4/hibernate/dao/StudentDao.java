package pl.sda.arppl4.hibernate.dao;


import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernate.model.Student;
import pl.sda.arppl4.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements IStudentDao{


    @Override
    public void dodajStudenta(Student student) {
        SessionFactory fabrykaPolaczen = HibernateUtil.INSTANCE.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) { //przy takim rozwiązaniu nie musimy się martwić o zamykaniu

            // ACID
            //  - ATOMICITY
            //  - CONSISTENCY
            //  - ISOLATION
            //  - DURABILITY
            Transaction transaction = session.beginTransaction(); // używamy tylko wtedy kiedy dokonujemy zmian w bazie na danych

            session.save(student);

            transaction.commit();

        } catch (SessionException sessionException) {
            System.out.println("Tutaj miejsce na wyjatek");

        }

    }

    @Override
    public void usunStudenta(Student student) {
        SessionFactory fabrykaPolaczen = HibernateUtil.INSTANCE.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) { //przy takim rozwiązaniu nie musimy się martwić o zamykaniu


            Transaction transaction = session.beginTransaction(); // używamy tylko wtedy kiedy dokonujemy zmian w bazie na danych

            session.remove(student);

            transaction.commit();

        }

    }

    @Override
    public Optional<Student> zwrocStudenta() {
        return Optional.empty();
    }

    @Override
    public Optional<Student> zwrocStudenta(Long id) {
        SessionFactory fabrykaPolaczen = HibernateUtil.INSTANCE.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Student obiektStudent = session.get(Student.class, id);
            return Optional.ofNullable(obiektStudent);
        }

    }

    @Override
    public List<Student> zwrocListeStudentow() {
        List<Student> studentList = new ArrayList<>();
        SessionFactory fabrykaPolaczen = HibernateUtil.INSTANCE.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) { //przy takim rozwiązaniu nie musimy się martwić o zamykaniu
            TypedQuery<Student> zapytanie = session.createQuery("from Student", Student.class);
            List<Student> wynikZapytania = zapytanie.getResultList();
            studentList.addAll(wynikZapytania);


        } catch (SessionException sessionException) {
            System.out.println("Błąd wczytywania danych");


        }
        return studentList;
    }


    @Override
    public void updateStudent(Student student) {
        SessionFactory fabrykaPolaczen = HibernateUtil.INSTANCE.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(student);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    }

