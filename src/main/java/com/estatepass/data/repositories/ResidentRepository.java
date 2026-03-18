package com.estatepass.data.repositories;

import com.estatepass.data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepository extends MongoRepository <Resident, String>{


       Optional<Resident> findByPhoneNumber(String phoneNumber);

}
