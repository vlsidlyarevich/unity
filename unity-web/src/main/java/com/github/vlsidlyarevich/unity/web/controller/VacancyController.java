package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.service.VacancyService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.VacancyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    @Autowired
    private VacancyService service;

    @Autowired
    private ConverterFacade converterFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllVacancies() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getVacancyById(@PathVariable String id) {
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVacancy(@RequestBody VacancyDTO vacancy) {
        return new ResponseEntity<>(service.create(converterFacade.convert(vacancy)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateVacancyById(@PathVariable String id, @RequestBody VacancyDTO vacancy) {
        return new ResponseEntity<>(service.update(id, converterFacade.convert(vacancy)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVacancyById(@PathVariable String id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
