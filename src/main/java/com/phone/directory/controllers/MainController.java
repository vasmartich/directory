package com.phone.directory.controllers;

import com.phone.directory.models.Directory;
import com.phone.directory.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @Autowired
    private DirectoryRepository directoryRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Directory> directory = directoryRepository.findAll();
        model.addAttribute("directory", directory);
        return "home";
    }

    @PostMapping
    public String phoneAdd(@RequestParam String firstName, @RequestParam String secondName,
                           @RequestParam String lastName, @RequestParam String adress, @RequestParam String phone,
                           Model model) {
        Directory directory = new Directory(firstName, secondName, lastName, adress, phone);
        directoryRepository.save(directory);
        return "redirect:/";
    }

    @PostMapping("filter")
    public String search(@RequestParam String search, Model model) {
        Iterable<Directory> directory;
        if (search != null && !search.isEmpty()) {
            directory = directoryRepository.findByFirstNameOrSecondNameOrLastNameOrPhoneOrAdress(search,
                    search, search, search, search);
        } else {
            directory = directoryRepository.findAll();
        }
        model.addAttribute("directory", directory);
        return "home";
    }
}


