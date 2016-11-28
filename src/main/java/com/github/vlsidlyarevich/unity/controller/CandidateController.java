package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vacancies/{vacancyId}/candidates")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCandidates(@PathVariable Long vacancyId) {
        return new ResponseEntity<>(service.findAll(vacancyId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidateById(@PathVariable Long vacancyId, @PathVariable Long candidateId) {
        return new ResponseEntity<>(service.find(vacancyId, candidateId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCandidate(@PathVariable Long vacancyId, @RequestBody CandidateDTO candidate) {
        return new ResponseEntity<>(service.create(vacancyId, candidate), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCandidateById(@PathVariable Long vacancyId, @PathVariable Long candidateId,
                                                 @RequestBody CandidateDTO candidate) {
        return new ResponseEntity<>(service.update(vacancyId, candidateId, candidate), HttpStatus.OK);
    }

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCandidateById(@PathVariable Long vacancyId, @PathVariable Long candidateId) {
        return new ResponseEntity<>(service.delete(vacancyId, candidateId), HttpStatus.OK);
    }
}
