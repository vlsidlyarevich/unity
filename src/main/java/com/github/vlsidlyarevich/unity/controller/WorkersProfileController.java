package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateWorkerById(@RequestBody WorkerProfile profile) {
        service.updateWorkerProfileById(profile);
        return new ResponseEntity<>(profile.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWorkerById(@PathVariable Long id) {
        service.deleteWorkerProfileById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findWorkerByName(@RequestBody Name name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findWorkerBySpeciality(@RequestParam String speciality) {
        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
            return new ResponseEntity<>(service.findAllBySpeciality(Speciality.valueOf(speciality)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findWorkersByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(service.findAllByAge(age), HttpStatus.OK);
    }
}
