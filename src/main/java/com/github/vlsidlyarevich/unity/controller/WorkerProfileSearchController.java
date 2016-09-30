package com.github.vlsidlyarevich.unity.controller;

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
@RequestMapping("/api/workers/profile/search")
public class WorkerProfileSearchController {

    //TODO:// FIXME: 30.09.16
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getWorkersByFilters(MultiValueMap<String, String> filters) {
//        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
//            return new ResponseEntity<>(service.findAllBySpeciality(Speciality.valueOf(speciality)), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
