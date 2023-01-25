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
}