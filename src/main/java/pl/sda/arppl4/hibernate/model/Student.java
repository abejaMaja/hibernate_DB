package pl.sda.arppl4.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
        //                          Klasa mapowana jest na tabele
        //          każda instancja tej klasy mapowana jest na rekord w bazie
@Entity // (baza) encja = rekord = wpis w bazie \/ instancja = obiekt (świat obiektowy/java)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    private String name;
    private String surname;
    private String indexNumber;
    private LocalDate birthDate;
}
