package pl.sda.arppl4.hibernate.dao;

import pl.sda.arppl4.hibernate.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Parser {
    private final StudentDao studentDao;
    private final Scanner scanner;
    private Student student;

    public Parser(StudentDao studentDao, Scanner scanner) {
        this.studentDao = studentDao;
        this.scanner = scanner;
    }

    public void doIt() {
        String komenda;
        do {
            System.out.println("Podaj komendę #dodaj# #zwrocListe# #zwrocStudenta# #update#  #usun# ");
            komenda = scanner.next();

            switch (komenda) {
                case "dodaj":
                    obslugaDodaj();
                    break;
                case "zwrocListe":
                    obslugaZwrocListe();
                    break;
                case "zwrocStudenta":
                    obslugaZwrocStudenta();
                    break;
                case "usun":
                    obslugaUsun();
                    break;
                case "update":
                    obslugaAktualizuj();
                    break;
            }
        } while (!komenda.equalsIgnoreCase("koniec"));
    }

    public void obslugaDodaj() {
        System.out.println("Podaj imię studenta");
        String name = scanner.next();
        System.out.println("Podaj nazwisko studenta");
        String surname = scanner.next();
        System.out.println("Podaj indeks studenta");
        String indeksNumber = scanner.next();

        Student student = new Student(null, name, surname, indeksNumber, LocalDate.now());
        studentDao.dodajStudenta(student);
        System.out.println("Student został dodany do bazy danych");

    }

    public void obslugaZwrocListe() {
        List<Student> lista = studentDao.zwrocListeStudentow();
        System.out.println("Studenci: " + lista);

    }

    public void obslugaZwrocStudenta() {
        System.out.println("Podaj id studenta");
        Long id = scanner.nextLong();
        Optional<Student> optionalStudent = studentDao.zwrocStudenta(id);
        System.out.println("Szukany student to " + optionalStudent);

    }

    public void obslugaUsun() {
        System.out.println("Podaj id studenta");
        Long id = scanner.nextLong();
        Optional<Student> optionalStudent = studentDao.zwrocStudenta(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentDao.usunStudenta(student);
        }
        System.out.println("Usunięto studenta  " + student);

    }

    public void obslugaAktualizuj() {
        System.out.println("Podaj id studenta, którego chcesz aktualizować");
        Long id = scanner.nextLong();
        Optional<Student> optionalStudent = studentDao.zwrocStudenta(id);
        if (optionalStudent.isPresent()) {
            System.out.println("Co zmieniamy, imie czy nazwisko");
            String output = scanner.next();
            Student studentAktualizowany = optionalStudent.get();
            switch (output) {
                case "imie":
                    System.out.println("Podaj imię " + optionalStudent);
                    String name = scanner.next();
                    studentAktualizowany.setName(name);
                    break;
                case "nazwisko":
                    System.out.println("Podaj nazwisko " + optionalStudent);
                    String surname = scanner.next();
                    studentAktualizowany.setSurname(surname);
                    break;
            }

            studentDao.updateStudent(studentAktualizowany);
            System.out.println("Dokonano aktualizacji danych dla studetna " + studentAktualizowany);
        }


    }
}
