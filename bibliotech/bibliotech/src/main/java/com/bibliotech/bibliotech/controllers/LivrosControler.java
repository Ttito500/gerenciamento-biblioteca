package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivrosControler {

    @Autowired
    private LivrosService livrosService;


}
