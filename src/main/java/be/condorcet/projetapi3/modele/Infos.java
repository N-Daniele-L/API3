package be.condorcet.projetapi3.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@ToString
@Entity
@Table(name = "EXAMINFOS", schema = "ORA7",catalog = "ORCL.CONDORCET.BE")
public class Infos {
    @EmbeddedId //primary key => instance of InfosKey
    @NonNull
    @ToString.Exclude
    private InfosKey id;

    @ManyToOne
    @MapsId("idEmploye") // foreign key and part of the primary key
    @JoinColumn(name = "id_employe")
    private Employe employe;
    @ManyToOne
    @MapsId("idMess") // foreign key and part of the primary key
    @JoinColumn(name = "id_mess")
    private Message message;
    @NonNull
    private Date datelecture;
}
