package com.EstatePass.services;

import com.EstatePass.data.models.Resident;
import com.EstatePass.data.repositories.ResidentRepository;
import com.EstatePass.dtos.requests.OnboardResidentRequest;
import com.EstatePass.dtos.responses.OnboardResidentResponse;
import com.EstatePass.exceptions.ResidentAlreadyRegisteredException;
import com.EstatePass.exceptions.ResidentDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.EstatePass.utils.Mapper.map;
@Service
public class ResidentManagementService {
    @Autowired
    private ResidentRepository residentRepository;



    public OnboardResidentResponse onboardingResident(OnboardResidentRequest onboardResidentRequest) {
        Resident exsitingResident = residentRepository.findByPhoneNumber(onboardResidentRequest.getPhoneNumber());

        validationForDuplicate(exsitingResident);

        Resident resident = map(onboardResidentRequest);

        residentRepository.save(resident);


        return map(resident);
    }

    public String disableResident (String phoneNumber){
      Resident resident = residentRepository.findByPhoneNumber(phoneNumber);

        if (resident == null){
            throw new ResidentDoesNotExistException("Resident Does not exist.");
        }

      resident.setEnabled(false);

      return (resident.getName())+" Has Successfully been Disabled Until further Notice";
    }

    public String deleteResident (String phoneNumber){
        Resident resident = residentRepository.findByPhoneNumber(phoneNumber);

        if (resident == null){
            throw new ResidentDoesNotExistException("Resident Does not exist.");
        }

        residentRepository.delete(resident);

        return "Resident Has been Deleted.";
    }

    public List<Resident> veiwResident (){
        return residentRepository.findAll();
    }

    private void validationForDuplicate(Resident resident){
        if (resident != null)
            throw new ResidentAlreadyRegisteredException("Resident Is Already Registered");

    }


}
