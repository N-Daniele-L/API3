package be.condorcet.projetapi3.modele;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMEMPLOYE", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
    @SequenceGenerator(name = "emp_generator", sequenceName = "EXAMEMPLOYE_SEQ1", allocationSize = 1)
    @Column(name = "id_employe")
    private Integer idEmploye;
    @NonNull
    @Column(name = "mail_emp")
    private String mailEmp;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @OneToMany(mappedBy = "employe")
    @ToString.Exclude
    @JsonIgnore
    private List<Message> message;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_bureau")
    @ToString.Exclude
    private Bureau bureau;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(mailEmp, employe.mailEmp);
    }
}
