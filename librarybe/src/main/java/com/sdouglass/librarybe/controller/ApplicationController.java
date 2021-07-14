package com.sdouglass.librarybe.controller;

import com.sdouglass.librarybe.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

}
