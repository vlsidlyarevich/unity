package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/workers/delete")
public class WorkerProfileDeleteQueryController {

    @Autowired
    private WorkerProfileService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> deleteQuery(@RequestBody Map<String, String> ids) {
        return new ResponseEntity<>(service.deleteQuery(ids), HttpStatus.OK);
    }
}
