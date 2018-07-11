package com.example.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Config;

public interface ConfigRepository extends CrudRepository<Config, Long> {
	List<Config> findByDomain(String domain);
}
