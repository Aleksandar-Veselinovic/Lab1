package org.example;

import org.slf4j.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AccessingData {
    private static final Logger log = LoggerFactory.getLogger(AccessingData.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingData.class);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository repository1, BuddyInfoRepository repository2) {
        return (args) -> {
            BuddyInfo buddy1 = new BuddyInfo("Bob", "123");
            BuddyInfo buddy2 = new BuddyInfo("Joe", "456");
            BuddyInfo buddy3 = new BuddyInfo("John", "789");
            repository2.save(buddy1);
            repository2.save(buddy2);
            repository2.save(buddy3);

            AddressBook book1 = new AddressBook();
            AddressBook book2 = new AddressBook();
            List<BuddyInfo> list1 = new ArrayList<BuddyInfo>();
            List<BuddyInfo> list2 = new ArrayList<BuddyInfo>();
            list1.add(buddy1);
            list2.add(buddy2);
            list2.add(buddy3);
            book1.setBuddies(list1);
            book2.setBuddies(list2);
            repository1.save(book1);
            repository1.save(book2);

            log.info("AddressBooks found with findAll():");
            log.info("-------------------------------");
            for (AddressBook book : repository1.findAll()) {
                log.info(book.toString());
            }
            log.info("");

            AddressBook book = repository1.findById(1L);
            log.info("AddressBook found with findById(1L):");
            log.info("--------------------------------");
            log.info(book.toString());
            log.info("");

            log.info("BuddyInfos found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo buddy : repository2.findAll()) {
                log.info(buddy.toString());
            }
            log.info("");

            BuddyInfo buddy = repository2.findById(1L);
            log.info("BuddyInfo found with findById(1L):");
            log.info("--------------------------------");
            log.info(buddy.toString());
            log.info("");

            log.info("BuddyInfo found with findByName('Joe'):");
            log.info("--------------------------------------------");
            repository2.findByName("Joe").forEach(joe -> {
                log.info(joe.toString());
            });
            log.info("");
        };
    }
}
