import org.example.AddressBook;
import org.example.BuddyInfo;
import org.junit.Test;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {
    @Test
    public void testPersistence() {
        AddressBook book = new AddressBook();
        book.setId(1L);

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

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        em.persist(book);

        em.getTransaction().commit();

        Query q = em.createQuery("SELECT a FROM org.example.AddressBook a");
        List<AddressBook> books = q.getResultList();

        List<BuddyInfo> buddies = books.get(0).getBuddies();

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
    public void testAddBuddy() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Bob", "123");
        BuddyInfo buddy2 = new BuddyInfo("Joe", "456");
        addressBook.addBuddy(buddy1);
        assertEquals(1, addressBook.getBuddies().size());
        addressBook.addBuddy(buddy2);
        assertEquals(2, addressBook.getBuddies().size());
    }
}