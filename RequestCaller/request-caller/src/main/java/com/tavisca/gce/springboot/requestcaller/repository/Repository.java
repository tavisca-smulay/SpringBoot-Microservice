package com.tavisca.gce.springboot.requestcaller.repository;

import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<User, Long> {
}