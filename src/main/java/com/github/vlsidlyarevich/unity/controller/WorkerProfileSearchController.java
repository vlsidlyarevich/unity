package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.service.WorkerProfileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vlad on 30.09.16.
 */
@RestController
@RequestMapping("/api/workers/search")
public class WorkerProfileSearchController {

    @Autowired
    private WorkerProfileSearchService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getWorkersByFilters(MultiValueMap<String, String> filters) {
        return new ResponseEntity<>(service.findByFilters(filters), HttpStatus.OK);
    }

}
