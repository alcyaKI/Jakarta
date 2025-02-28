package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class VisiteId implements Serializable {
    private int idUtilisateur;
    private int idLieu;
    private LocalDateTime dateVisite;

    // Constructeurs, equals() et hashCode()
    
    public VisiteId() {}

    public VisiteId(int idUtilisateur, int idLieu, LocalDateTime dateVisite) {
        this.idUtilisateur = idUtilisateur;
        this.idLieu = idLieu;
        this.dateVisite = dateVisite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisiteId)) return false;
        VisiteId visiteId = (VisiteId) o;
        return idUtilisateur == visiteId.idUtilisateur && 
               idLieu == visiteId.idLieu && 
               dateVisite.equals(visiteId.dateVisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idLieu, dateVisite);
    }
}
