package be.condorcet.projetapi3.modele;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMMESSAGE", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
    @SequenceGenerator(name="emp_generator", sequenceName = "EXAMEMPLOYE_SEQ", allocationSize=1)
    @Column(name = "id_mess")
    private Integer idMess;
    @NonNull
    private String objet;
    @NonNull
    private String contenu;
    @NonNull
    private Date dateenvoi;
    //@ForeignKey
    @Column(name = "id_employe")
    private Integer idEmp;
    @NonNull
    @ManyToOne @JoinColumn(name = "id_employe")
    private Employe employe;

}
