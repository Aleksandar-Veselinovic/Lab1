package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookRepository bookRepo;

    @Autowired
    private BuddyInfoRepository buddyRepo;

    @RequestMapping(value = "/buddies", method = RequestMethod.GET)
    public String getBuddies(@RequestParam(value = "id", defaultValue = "50") String id, Model model) {
        AddressBook book = bookRepo.findById(Long.parseLong(id));
        model.addAttribute("buddiesList", book.getBuddies());
        return "buddies";
    }

    @RequestMapping(value = "/buddies", method = RequestMethod.POST)
    public String getBuddies(@RequestParam(value = "id") String id, @RequestParam(value = "name", defaultValue = "Bob") String name, @RequestParam(value = "number", defaultValue = "123") String number, Model model) {
        BuddyInfo buddy = new BuddyInfo(name, number);
        buddyRepo.save(buddy);
        AddressBook book = bookRepo.findById(Long.parseLong(id));
        book.addBuddy(buddy);
        bookRepo.save(book);
        model.addAttribute("buddiesList", book.getBuddies());
        return "buddies";
    }
}
