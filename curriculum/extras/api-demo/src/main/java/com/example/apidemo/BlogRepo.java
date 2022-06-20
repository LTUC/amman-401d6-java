package com.example.apidemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * <h3>api-demo</h3>
 *
 * @author jason
 * @description <p>blog repo</p>
 * @date 2022-06-20 10:17
 **/
@RepositoryRestResource
public interface BlogRepo extends JpaRepository<Blog, Long> {
}
