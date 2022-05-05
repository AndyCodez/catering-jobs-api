package com.andycodez.cateringapi.data.repository;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateringJobRepository extends CrudRepository<CateringJob, Long> {
    List<CateringJob> findAll();

    List<CateringJob> findCateringJobByStatus(Status status);
}
