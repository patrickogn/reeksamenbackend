package rest;

import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import io.restassured.parsing.Parser;


import static io.restassured.RestAssured.given;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class AdminResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Trip t1, t2, t3;
    private static Guide g1, g2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        t1 = new Trip("trip1","20/20-2023","10:00","Mount everest","10 timer","vandresko og rygsæk");
        t2 = new Trip("trip2","21/20-2023","11:00","Big mountain","11 timer","vandresko og rygsæk");
        t3 = new Trip("trip3","23/20-2023","13:00","Big mountain3","13 timer","vandresko og rygsæk");
        g1 = new Guide("guidename","guidegender","guidebirthyear","guideprofile","guideimageurl");
        g2 = new Guide("guidename2","guidegender2","guidebirthyear2","guideprofile2","guideimageurl2");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Guide.deleteAllRows").executeUpdate();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.persist(g1);
            em.persist(g2);
            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/xxx").then().statusCode(200);
    }


    @Test
    void createTrip() {
        given()
                .contentType(ContentType.JSON)
                .body(new TripDTO(t1))
                .when()
                .post("admin/createtrip")
                .then()
                .body("name", equalTo("trip1"))
                .body("date", equalTo("20/20-2023"))
                .body("time", equalTo("10:00"))
                .body("location", equalTo("Mount everest"))
                .body("duration", equalTo("10 timer"))
                .body("packinglist", equalTo("vandresko og rygsæk"))
                .body("id", notNullValue());
    }

    @Test
    void createGuide() {
        given()
                .contentType(ContentType.JSON)
                .body(new GuideDTO(g1))
                .when()
                .post("admin/createguide")
                .then()
                .body("name", equalTo("guidename"))
                .body("gender", equalTo("guidegender"))
                .body("birthyear", equalTo("guidebirthyear"))
                .body("profile", equalTo("guideprofile"))
                .body("imageurl", equalTo("guideimageurl"))
                .body("id", notNullValue());
    }

    @Test
    void deleteTrip() throws Exception{

        TripDTO trip = new TripDTO(t1);

        given()
                .contentType("application/json")
                .delete("admin/deletetrip/" + trip.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }
}