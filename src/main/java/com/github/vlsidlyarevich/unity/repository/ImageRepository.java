package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vladislav on 10/11/16.
 */
@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    Image findById(String id);
}
