package be.condorcet.projetapi3.modele;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMEMPLOYE", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
    @SequenceGenerator(name="emp_generator", sequenceName = "EXAMEMPLOYE_SEQ", allocationSize=1)
    @Column(name = "id_employe")
    private Integer idEmploye;
    @NonNull
    @Column(name = "mail_emp")
    private String mailEmp;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    //@ForeignKey
    @Column(name = "id_bureau")
    private Integer idBureau;
}