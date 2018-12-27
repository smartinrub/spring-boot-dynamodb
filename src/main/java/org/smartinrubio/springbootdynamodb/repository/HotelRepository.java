package org.smartinrubio.springbootdynamodb.repository;

import org.smartinrubio.springbootdynamodb.model.Hotel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HotelRepository extends CrudRepository<Hotel, String>, CustomHotelRepository {

}
