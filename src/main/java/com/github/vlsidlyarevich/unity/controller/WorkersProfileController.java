package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vlad on 28.09.16.
 */
@RestController
@RequestMapping("/api/workers/profile")
public class WorkersProfileController {


    @Autowired
    private WorkerProfileService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllWorkers() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

}
