package com.tavisca.gce.springboot.initialrequest.repository;

import com.tavisca.gce.springboot.initialrequest.model.InputRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<InputRequest, Long>{
}