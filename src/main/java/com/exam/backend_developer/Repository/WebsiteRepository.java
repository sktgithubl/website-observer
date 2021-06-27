package com.exam.backend_developer.Repository;

import com.exam.backend_developer.Model.WebsiteRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends CrudRepository<WebsiteRequest, Long> {
}
