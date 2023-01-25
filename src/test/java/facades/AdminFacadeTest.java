package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminFacadeTest {
    private static EntityManagerFactory emf;
    private static AdminFacade facade;
    private static TripFacade tripfacade;
    private static GuideFacade guidefacade;

    private static Trip t1, t2;
    private static Guide g1, g2;

    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AdminFacade.getFacadeExample(emf);
        tripfacade = TripFacade.getFacadeExample(emf);
        guidefacade = GuideFacade.getFacadeExample(emf);

    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        g1 = new Guide("guidename","guidegender","guidebirthyear","guideprofile","guideimageurl");
        g2 = new Guide("guidename2","guidegender2","guidebirthyear2","guideprofile2","guideimageurl2");
        t1 = new Trip("trip1","20/20-2023","10:00","Mount everest","10 timer","vandresko og rygsæk");
        t2 = new Trip("trip2","21/20-2023","11:00","Big mountain","11 timer","vandresko og rygsæk");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Guide.deleteAllRows").executeUpdate();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.persist(g1);
            em.persist(g2);
            em.persist(t1);
            em.persist(t2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createTrip() {
        Trip trip = t1;
        TripDTO tripDTO = new TripDTO(trip);
        TripDTO newTrip = facade.createTrip(tripDTO);
        assertNotNull(newTrip.getId());
        assertEquals(3, tripfacade.getAll().size());
    }

    @Test
    void createGuide() {
        Guide guide = g1;
        GuideDTO guideDTO = new GuideDTO(guide);
        GuideDTO newGuide = facade.createGuide(guideDTO);
        assertNotNull(newGuide.getId());
        assertEquals(3, guidefacade.getAll().size());
    }
    @Test
    void deleteTrip()
    {
        int t1Id = t1.getId();
        int t2Id = t2.getId();
        facade.deleteTrip(t1Id);
        EntityManager em = emf.createEntityManager();
        try {
            List<Trip> tripList = em.createQuery("select t from Trip t").getResultList();
            assertEquals(1, tripList.size(), "Expects 1 Trips in the DB");

            tripList = em.createQuery("select t from Trip t WHERE t.id = " + t1Id).getResultList();
            assertEquals(0, tripList.size(), "Expects 1 persons in the DB");
            Trip t = em.find(Trip.class, t1Id);
            assertNull(t, "Expects that trip is removed and t is null");

            t = em.find(Trip.class, t2Id);
            assertNotNull(t, "Expects that trip is removed and t is null");
        } finally {
            em.close();
        }
    }
}