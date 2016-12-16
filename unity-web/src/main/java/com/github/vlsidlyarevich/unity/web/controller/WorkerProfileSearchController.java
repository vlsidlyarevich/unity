package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.model.model.Name;
import com.github.vlsidlyarevich.unity.db.services.service.SearchService;
import com.github.vlsidlyarevich.unity.db.services.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/workers/search")
public class WorkerProfileSearchController {

    @Autowired
    private SearchService service;

    @Autowired
    private WorkerProfileService workerProfileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getWorkersByFilters(@RequestBody Map<String, String> filters) {
        return new ResponseEntity<>(service.find(filters), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getWorkerByName(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) {
        return new ResponseEntity<>(workerProfileService.findByName(new Name(firstName, lastName)), HttpStatus.OK);
    }
}
