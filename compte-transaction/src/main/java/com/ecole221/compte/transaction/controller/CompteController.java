package com.ecole221.compte.transaction.controller;

import com.ecole221.compte.transaction.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    @GetMapping
    @ResponseBody
    public ResponseEntity findAll(){
        return new ResponseEntity(compteService.getAllComptes(), HttpStatus.OK);
    }

}
