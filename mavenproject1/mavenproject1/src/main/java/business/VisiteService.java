package business;

import entities.Visite;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class VisiteService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void ajouterVisite(Visite visite) {
        em.persist(visite);
    }

    public List<Visite> listerVisites() {
        return em.createQuery("SELECT v FROM Visite v", Visite.class).getResultList();
    }
}
