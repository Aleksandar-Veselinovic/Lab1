package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookRepository bookRepo;

    @Autowired
    private BuddyInfoRepository buddyRepo;

    @GetMapping("/addressBook")
    public String addressBookForm(Model model) {
        model.addAttribute("addressBook", new AddressBook());
        return "addressBookForm";
    }

    @PostMapping("/addressBook")
    public String addressBookSubmit(@ModelAttribute AddressBook book, Model model) {
        bookRepo.save(book);
        model.addAttribute("addressBook", book);
        return "addressBookResult";
    }

    @GetMapping("/addBuddy")
    public String addBuddyForm(Model model) {
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "addBuddyForm";
    }

    @PostMapping("/addBuddy")
    public String addBuddySubmit(@ModelAttribute BuddyInfo buddy, Model model) {
        buddyRepo.save(buddy);
        AddressBook book = bookRepo.findById(Long.parseLong(buddy.getAddressBookId()));
        book.addBuddy(buddy);
        bookRepo.save(book);

        model.addAttribute("buddyInfo", buddy);
        return "addBuddyResult";
    }

    @GetMapping("/addressBookContent")
    public String addressBookContentForm(Model model) {
        model.addAttribute("addressBookId", "");
        return "addressBookContentForm";
    }

    @PostMapping("/addressBookContent")
    public String addressBookContentSubmit(@ModelAttribute(value = "addressBookId") String addressBookId, Model model) {
        AddressBook book = bookRepo.findById(Long.parseLong(addressBookId));
        model.addAttribute("addressBook", book);
        return "addressBookContentResult";
    }

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
