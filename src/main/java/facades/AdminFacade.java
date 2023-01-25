package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class AdminFacade {
    private static AdminFacade instance;
    private static EntityManagerFactory emf;

    public AdminFacade() {
    }

    public static AdminFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AdminFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public TripDTO createTrip(TripDTO tdto) {
        EntityManager em = emf.createEntityManager();
        Trip trip = new Trip(tdto.getName(), tdto.getDate(), tdto.getTime(), tdto.getLocation(), tdto.getDuration(), tdto.getPackinglist());
        try
        {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
        return new TripDTO(trip);
    }

    public GuideDTO createGuide(GuideDTO gDTO) {
        EntityManager em = emf.createEntityManager();
        Guide guide = new Guide(gDTO.getName(), gDTO.getGender(), gDTO.getBirthyear(), gDTO.getProfile(), gDTO.getImageurl());
        try
        {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
        return new GuideDTO(guide);
    }


    public void deleteTrip(int id) {
        EntityManager em = emf.createEntityManager();
        Trip trip = (em.find(Trip.class, id));
        if (trip == null)
            throw new WebApplicationException("Trip with id: " + trip + " doesn't exist");
        try {
            em.getTransaction().begin();
            em.remove(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

