package pl.sda.arppl4.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernate.util.HibernateUtil;
import pl.sda.arppl4.hibernate.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Scanner scanner = new Scanner(System.in);

        Parser parser = new Parser(studentDao, scanner);
        parser.doIt();
    }
}
