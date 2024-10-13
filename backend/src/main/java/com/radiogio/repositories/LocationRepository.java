package com.radiogio.repositories;

import com.radiogio.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

    @Query(value = "{}", fields = "{ 'continent' : 1 }")
    List<String> findDistinctContinents();

    @Query(value = "{ 'continent' : ?0 }", fields = "{ 'countries.name' : 1 }")
    List<String> findCountriesByContinent(String continent);

    @Query(value = "{ 'countries.name' : ?0 }", fields = "{ 'countries.states.name' : 1 }")
    List<String> findStatesByCountry(String country);
}
