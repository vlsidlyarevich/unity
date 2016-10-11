package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.model.Image;
import com.github.vlsidlyarevich.unity.repository.ImageRepository;
import com.github.vlsidlyarevich.unity.service.ImageService;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vladislav on 10/11/16.
 */
@Service
public class ImageServiceImpl implements ImageService<Image> {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private WorkerProfileService workerProfileService;

    @Override
    public Image find(String id) {
        return null;
    }

    @Override
    public Image create(Image object) {
        return null;
    }

    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
