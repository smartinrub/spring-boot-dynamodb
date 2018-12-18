package org.smatinrubio.springbootdynamodb.repository;

import org.smatinrubio.springbootdynamodb.model.Movie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByYear(String year, Pageable pageable);
}
