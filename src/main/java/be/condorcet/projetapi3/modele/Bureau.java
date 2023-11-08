package be.condorcet.projetapi3.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMBUREAU", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Bureau {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bur_generator")
    @SequenceGenerator(name="bur_generator", sequenceName = "EXAMBUREAU_SEQ", allocationSize=1)
    @Column(name = "id_bureau")
    private Integer idBureau;
    @NonNull
    private String sigle;
    @NonNull
    private String tel;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "bureau")
    private List<Employe> employes;
}