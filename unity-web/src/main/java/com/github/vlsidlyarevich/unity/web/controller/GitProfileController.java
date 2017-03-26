package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.git.populator.GitProfilePopulator;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/git/profile")
public class GitProfileController {

    @Autowired
    private GitProfilePopulator gitProfilePopulator;

    @Autowired
    private GitProfileService gitProfileService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable String id) {
        return new ResponseEntity(gitProfilePopulator.populate(gitProfileService.getGitProfile(id).get()),
                HttpStatus.OK);
    }
}
