package facades;

import dtos.TripDTO;
import entities.Trip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class TripFacade {
    private static TripFacade instance;
    private static EntityManagerFactory emf;

    public TripFacade() {
    }

    public List<TripDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Trip> query = em.createQuery("SELECT p FROM Trip p", Trip.class);
        List<Trip> rms = query.getResultList();
        return TripDTO.getDtos(rms);
    }

    public static TripFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    /*public TripDTO create(TripDTO tdto) {
        EntityManager em = emf.createEntityManager();
        Trip boat = new Trip(bdto.getId(), bdto.getBrand(), bdto.getImage(), bdto.getMake(), bdto.getName());
        try
        {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
        return new BoatDTO(boat);
    }
}*/

}
