package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/workers/search")
public class WorkerProfileSearchController {

    @Autowired
    private SearchService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getWorkersByFilters(@RequestBody Map<String, String> filters) {
        return new ResponseEntity<>(service.find(filters), HttpStatus.OK);
    }

}
