package com.estatepass.services;

import com.estatepass.data.models.Resident;
import com.estatepass.data.repositories.ResidentRepository;
import com.estatepass.dtos.requests.OnboardResidentRequest;
import com.estatepass.dtos.responses.OnboardResidentResponse;
import com.estatepass.dtos.responses.ViewAllResidentsResponse;
import com.estatepass.exceptions.ResidentAlreadyRegisteredException;
import com.estatepass.exceptions.ResidentDisabledException;
import com.estatepass.exceptions.ResidentDoesNotExistException;
import com.estatepass.exceptions.ResidentEnabledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.estatepass.utils.Mapper.map;
@Service
public class ResidentManagementService {
    @Autowired
    private ResidentRepository residentRepository;



//    public OnboardResidentResponse onboardingResident(OnboardResidentRequest onboardResidentRequest) {
//         Resident existingResident = residentRepository.findByPhoneNumber(onboardResidentRequest.getPhoneNumber()).orElseThrow(() -> new ResidentAlreadyRegisteredException("Resident Already Exist"));
////        GatePass pass = gatePassRepository.findByCode(code).orElseThrow(() -> new GatePassDoesNotExist("GatePass does not exist"));
//
//
////        validationForDuplicate(existingResident);
//
//        Resident resident = map(onboardResidentRequest);
//
//        residentRepository.save(resident);
//
//
//        return map(resident);
//    }
public OnboardResidentResponse onboardingResident(OnboardResidentRequest onboardResidentRequest) {
    Optional<Resident> existingResident = residentRepository.findByPhoneNumber(onboardResidentRequest.getPhoneNumber());

    validationForDuplicate(existingResident);
    Resident resident = map(onboardResidentRequest);
    resident.setEnabled(true);
    Resident savedResident = residentRepository.save(resident);

    return map(savedResident);
}


    public String disableResident (String phoneNumber){
      Resident resident = residentRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResidentDoesNotExistException("Resident does not exist"));

//        if (resident == null){
//            throw new ResidentDoesNotExistException("Resident Does not exist.");
//        }

        if(!resident.isEnabled()){
            throw new ResidentDisabledException("Resident is already disabled");
        }

      resident.setEnabled(false);

      residentRepository.save(resident);

      return (resident.getName())+" Has Successfully been Disabled Until further Notice";
    }


    public String enableResident (String phoneNumber){
    Resident resident = residentRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResidentDoesNotExistException("Resident does not exist"));

    if(resident.isEnabled()){
        throw new ResidentEnabledException("Resident is not Disabled");
    }
    resident.setEnabled(true);

    residentRepository.save(resident);

    return resident.getName()+" is no more disabled, he or she can fully use the system now.";
    }

    public String deleteResident (String phoneNumber){
        Resident resident = residentRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResidentDoesNotExistException("Resident Does not exsit"));

//        if (resident == null){
//            throw new ResidentDoesNotExistException("Resident Does not exist.");
//        }

        residentRepository.delete(resident);

        return "Resident Has been Deleted.";
    }

    public List<ViewAllResidentsResponse> viewResidents (){

        List<ViewAllResidentsResponse> residents = new ArrayList<>();


        for(Resident resident: residentRepository.findAll()){
            ViewAllResidentsResponse response = new ViewAllResidentsResponse();
            response.setName(resident.getName());
            response.setEnabled(resident.isEnabled());
            response.setAddress(resident.getHouseAddress());
            response.setPhoneNumber(resident.getPhoneNumber());

            residents.add(response);

        }

        return residents;
    }

    private void validationForDuplicate(Optional<Resident> resident){
        if (resident.isPresent())
            throw new ResidentAlreadyRegisteredException("Resident Is Already Registered");

    }


}
