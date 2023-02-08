import org.example.BuddyInfo;
import org.junit.Test;

import jakarta.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class BuddyInfoTest {
    @Test
    public void testPersistence() {
        BuddyInfo buddy1 = new BuddyInfo();
        buddy1.setId(1L);
        buddy1.setName("Bob");
        buddy1.setPhoneNumber("123");

        BuddyInfo buddy2 = new BuddyInfo();
        buddy2.setId(2L);
        buddy2.setName("Joe");
        buddy2.setPhoneNumber("456");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(buddy1);
        em.persist(buddy2);

        em.getTransaction().commit();

        Query q = em.createQuery("SELECT b FROM org.example.BuddyInfo b");
        List<BuddyInfo> buddies = q.getResultList();

        assertEquals(buddy1.getId(), buddies.get(0).getId());
        assertEquals(buddy1.getName(), buddies.get(0).getName());
        assertEquals(buddy1.getPhoneNumber(), buddies.get(0).getPhoneNumber());

        assertEquals(buddy2.getId(), buddies.get(1).getId());
        assertEquals(buddy2.getName(), buddies.get(1).getName());
        assertEquals(buddy2.getPhoneNumber(), buddies.get(1).getPhoneNumber());

        em.close();
        emf.close();
    }

    @Test
    public void testGetName() {
        BuddyInfo buddy = new BuddyInfo("Bob", "123");
        assertEquals("Bob", buddy.getName());
    }

    @Test
    public void testGetPhoneNumber() {
        BuddyInfo buddy = new BuddyInfo("Bob", "123");
        assertEquals("123", buddy.getPhoneNumber());
    }

    @Test
    public void testSetName() {
        BuddyInfo buddy = new BuddyInfo("Bob", "123");
        buddy.setName("Joe");
        assertEquals("Joe", buddy.getName());
    }

    @Test
    public void testSetPhoneNumber() {
        BuddyInfo buddy = new BuddyInfo("Bob", "123");
        buddy.setPhoneNumber("456");
        assertEquals("456", buddy.getPhoneNumber());
    }

    @Test
    public void testToString() {
        BuddyInfo buddy = new BuddyInfo("Bob", "123");
        assertEquals("Name: Bob, Phone Number: 123", buddy.toString());
    }
}