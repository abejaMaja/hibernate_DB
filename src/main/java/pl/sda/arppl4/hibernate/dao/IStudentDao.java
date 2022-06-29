package pl.sda.arppl4.hibernate.dao;

import pl.sda.arppl4.hibernate.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentDao {
    // Create
    public void dodajStudenta(Student student);
    // Delete
    public void usunStudenta(Student student);

    Optional<Student> zwrocStudenta();

    // Read
    public Optional<Student> zwrocStudenta(Long id);
    // Read (select * from student)
    public List<Student> zwrocListeStudentow();
    // Update (update student set name="pawel" where id=1)
    public void updateStudent(Student student);
}
