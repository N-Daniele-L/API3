package be.condorcet.projetapi3.modele;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "EXAMBUREAU", schema = "ORA7", catalog = "ORCL.CONDORCET.BE")
public class Bureau {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bur_generator")
    @SequenceGenerator(name="bur_generator", sequenceName = "EXAMBUREAU_SEQ", allocationSize=1)
    private Integer id_bureau;
    @NonNull
    private String sigle;
    @NonNull
    private String tel;
}