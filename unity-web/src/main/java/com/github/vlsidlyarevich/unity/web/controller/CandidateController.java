package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.service.CandidateService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vacancies/{vacancyId}/candidates")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @Autowired
    private ConverterFacade converterFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCandidates(@PathVariable String vacancyId) {
        return new ResponseEntity<>(service.findAll(vacancyId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidateById(@PathVariable String vacancyId, @PathVariable String candidateId) {
        return new ResponseEntity<>(service.find(vacancyId, candidateId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCandidate(@PathVariable String vacancyId, @RequestBody CandidateDTO candidate) {
        return new ResponseEntity<>(service.create(vacancyId, converterFacade.convert(candidate)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCandidateById(@PathVariable String vacancyId, @PathVariable String candidateId,
                                                 @RequestBody CandidateDTO candidate) {
        return new ResponseEntity<>(service.update(vacancyId, candidateId, converterFacade.convert(candidate)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCandidateById(@PathVariable String vacancyId, @PathVariable String candidateId) {
        return new ResponseEntity<>(service.delete(vacancyId, candidateId), HttpStatus.OK);
    }
}
