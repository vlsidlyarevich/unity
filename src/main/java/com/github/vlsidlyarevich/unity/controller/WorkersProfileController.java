package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vlad on 28.09.16.
 */
@RestController
@RequestMapping("/api/workers")
public class WorkersProfileController {

    @Autowired
    private WorkerProfileService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllWorkers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateWorkerById(@RequestBody WorkerProfile profile) {
        service.updateWorkerProfileById(profile);
        return new ResponseEntity<>(profile.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addWorker(@RequestBody WorkerProfile profile) {
        service.save(profile);
        return new ResponseEntity<>(profile.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkerById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWorkerById(@PathVariable String id) {
        service.deleteWorkerProfileById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
