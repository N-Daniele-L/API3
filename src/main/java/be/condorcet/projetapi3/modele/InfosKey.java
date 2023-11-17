package be.condorcet.projetapi3.modele;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
public class InfosKey implements Serializable {

    @Column(name = "id_employe")
    private Integer idEmploye;
    @Column(name = "id_mess")
    private Integer idMess;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfosKey infosKey = (InfosKey) o;
        return Objects.equals(idEmploye, infosKey.idEmploye) && Objects.equals(idMess, infosKey.idMess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmploye, idMess);
    }
}
