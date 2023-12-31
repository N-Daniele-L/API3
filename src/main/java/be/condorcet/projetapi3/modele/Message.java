package be.condorcet.projetapi3.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMMESSAGE", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mess_generator")
    @SequenceGenerator(name="mess_generator", sequenceName = "EXAMMESSAGE_SEQ1", allocationSize=1)
    @Column(name = "id_mess")
    private Integer idMess;
    @NonNull
    private String objet;
    @NonNull
    private String contenu;
    @NonNull
    private Date dateenvoi;

    @NonNull
    @ManyToOne @JoinColumn(name = "id_employe")
    @ToString.Exclude
    private Employe employe;
    @JsonIgnore
    @OneToMany(mappedBy = "message")
    @ToString.Exclude
    List<Infos> infos;

}
