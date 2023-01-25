package facades;

import dtos.TripDTO;
import entities.Trip;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class TripFacadeTest {
    private static EntityManagerFactory emf;
    private static TripFacade facade;
    private static Trip t1, t2;


    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = TripFacade.getFacadeExample(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        t1 = new Trip("trip1","20/20-2023","10:00","Mount everest","10 timer","vandresko og rygsæk");
        t2 = new Trip("trip2","21/20-2023","11:00","Big mountain","11 timer","vandresko og rygsæk");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.persist(t1);
            em.persist(t2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAll() {
        List<TripDTO> tripDTOList = facade.getAll();
        int expected = 2;
        int actual = tripDTOList.size();
        assertEquals(expected, actual);
        assertThat(tripDTOList, containsInAnyOrder(new TripDTO(t1),new TripDTO(t2)));
    }
}