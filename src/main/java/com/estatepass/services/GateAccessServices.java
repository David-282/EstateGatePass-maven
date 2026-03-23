package com.estatepass.services;

import com.estatepass.data.models.GatePass;
import com.estatepass.data.models.Resident;
import com.estatepass.data.models.Visitor;
import com.estatepass.data.repositories.GatePassRepository;
import com.estatepass.data.repositories.ResidentRepository;
import com.estatepass.dtos.requests.*;
import com.estatepass.dtos.responses.*;
import com.estatepass.exceptions.GatePassDoesNotExist;
import com.estatepass.exceptions.InvalidGatePassException;
import com.estatepass.exceptions.ResidentDisabledException;
import com.estatepass.exceptions.ResidentDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatepass.utils.RandomCodeGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.estatepass.utils.Mapper.extendCodeMap;
import static com.estatepass.utils.Mapper.map;
@Service
public class GateAccessServices {
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;

    private String generateCode() {
        return RandomCodeGenerator.codeGenerator();
    }


    public GenerateVisitorEntryCodeResponse generateVisitorsEntryCode(GenerateVisitorEntryCodeRequest request) {

        Resident exsitingResident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new ResidentDoesNotExistException("Resident does not exist"));


//        if (exsitingResident == null) {
//            throw new ResidentDoesNotExistException("Resident Does Not Exist In Our Server.");
//        }

        validateResidentIsActive(exsitingResident);
        Visitor visitor = map(request);
//        visitor.setId(RandomCodeGenerator.codeGenerator());
//        visitor.setName(request.getVisitorName());
//        visitor.setPurposeOfComing(request.getPurposeOfVisit());
//        visitor.setPhoneNumber(request.getPhoneNumber());


        GatePass pass = map(request, visitor);
//        pass.setResidentId(request.getResidentId());
//        pass.setVisitor(visitor);
//        pass.setPassType(Type.ENTRY);
        pass.setCode(generateCode());
//        pass.setCreatedAt(LocalDateTime.now());
//        pass.setExpirationDate(LocalDateTime.now().plusHours(request.getValidHour()));
        gatePassRepository.save(pass);

//            GenerateVisitorEntryCodeResponse response = new GenerateVisitorEntryCodeResponse();
//            response.setCodeType(pass.getPassType());
//            response.setCode(pass.getCode());
//            response.setVisitorName(pass.getVisitor().getName());
//            response.setResidentName(resident.getName());

        return map(pass);
    }


    public String disableCode(String code) {

        if (code.length() != 10){
            throw new IllegalArgumentException("The length of the code must be exactly 10, not more than or less than.");
        }

        GatePass pass = gatePassRepository.findByCode(code).orElseThrow(() -> new GatePassDoesNotExist("GatePass does not exist"));

        validatingIfCodeIsActive(pass);

        Resident resident = residentRepository.findById(pass.getResidentId()).orElseThrow(() -> new ResidentDoesNotExistException("Resident does not exist"));

        validateResidentIsActive(resident);

        if(pass.isUsed()){
            throw new InvalidGatePassException("GatePass is used,So therefore it cannot be disabled.");
        }

        pass.setValid(false);
        gatePassRepository.save(pass);

        return (code) + " Has been Disabled";
    }


    public ValidateCodeResponse validateCode(ValidateCodeRequest request) {

        GatePass pass = gatePassRepository.findByCode(request.getCode()).orElseThrow(() -> new InvalidGatePassException("GatePass does not exist"));
        Resident resident = residentRepository.findById(pass.getResidentId()).get();
        validatingIfCodeIsActive(pass);

        if (!pass.isValid()) {
            throw new InvalidGatePassException("Gate pass is not active ");
        }

        if(pass.isUsed()){
            throw new InvalidGatePassException("GatePass is already used");
        }


//        ValidateCodeResponse response = new ValidateCodeResponse();
//        response.setCodeType(request.getCodeType());
//        response.setValid(true);
//        response.setVisitorsName(pass.getVisitor().getName());
//        response.setResidentName(resident.getName());

        return map(request, pass, resident);

    }

    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest request) {

        Resident resident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new ResidentDoesNotExistException("Resident does not exist"));;

//        if (resident == null) {
//            throw new ResidentDoesNotExistException("Resident does not exist.");
//        }


        validateResidentIsActive(resident);


        GatePass pass = map(request);
        pass.setCode(generateCode());


//        pass.setResidentId(request.getResidentId());
//        pass.setPassType(Type.ENTRY);
//
//        pass.setCreatedAt(LocalDateTime.now());
//        pass.setValid(true);
//        pass.setExpirationDate(request.getValidTill());

//        GenerateResidentEntryCodeResponse response = new GenerateResidentEntryCodeResponse();
//
//        response.setDestination(resident.getHouseAddress());
//        response.setCodeType(pass.getPassType());
//        response.setResidentName(resident.getName());
//        response.setCode(pass.getCode());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        response.setValidTill(pass.getExpirationDate().format(formatter));

        return map(resident, pass);
    }


    public GenerateExitCodeResponse generateExitCode(GenerateExitCodeRequest request) {

        GatePass pass = gatePassRepository.findByCode(request.getCode()).orElseThrow(() -> new GatePassDoesNotExist("GatePass does not exist"));

//        validateCodeExistence(pass);

        Resident resident = residentRepository.findById(pass.getResidentId()).get();

        if (!pass.isValid()) {
            throw new InvalidGatePassException("Code Has been disabled.");
        }

        if (!pass.isUsed()){
            throw new InvalidGatePassException("GatePass has not been used,It cannot be used to generate an exit code.");
        }

        validateResidentIsActive(resident);

//        pass.setValid(true);

        GatePass existCode = map(pass, request);
        existCode.setCode(generateCode());

        gatePassRepository.save(existCode);

//        GatePass existCode = new GatePass();
//
//        existCode.setPassType(Type.EXIT);
//        existCode.setResidentId(pass.getResidentId());
//        existCode.setCode(RandomCode);
//        existCode.setCreatedAt(LocalDateTime.now());
//        existCode.setExpirationDate(request.getValidTill());

//        GenerateExitCodeResponse response = new GenerateExitCodeResponse();
//        response.setCodeType(existCode.getPassType());
//        response.setHouseAddress(resident.getHouseAddress());
//        response.setCode(existCode.getCode());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        response.setValidTill(existCode.getExpirationDate().format(formatter));
//
//        if (pass.getVisitor() == null){
//            response.setName(resident.getName());
//        }
//        else{
//            response.setHouseAddress(pass.getVisitor().getName());
//        }

        return map(existCode, resident, pass);
    }


    public ExtendCodeResponse extendCode(ExtendCodeRequest request) {
        GatePass pass = gatePassRepository.findByCode(request.getCode()).orElseThrow(() -> new GatePassDoesNotExist("GatePass does not exist"));

//        validateCodeExistence(pass);
        validatingIfCodeIsActive(pass);

        if (pass.isUsed()){
            throw new InvalidGatePassException("GatePass is already used");
        }

        if (!pass.isValid()) {
            throw new InvalidGatePassException("Code is not active.");
        }
        LocalDateTime newExpiration = pass.getExpirationDate().plusHours(request.getHoursToExtendBy());
        LocalDateTime midnight = LocalDateTime.now().toLocalDate().atTime(23, 59, 59);
        if (newExpiration.isAfter(midnight)) {
            newExpiration = midnight;
        }
        pass.setExpirationDate(newExpiration);
        return extendCodeMap(pass);
    }


    //         *     private String residentName;
    public List<ViewAllGatePassesResponse> viewAllGatePasses() {
        List <ViewAllGatePassesResponse> gatePasses = new ArrayList<>();

        /**
//         *     private String code;
//         *     private String visitorsName;
//         *     private String purposeOfComing;
//         *     private String visitorsPhoneNumber;
//         *     private String residentPhoneNumber;
//         *     private String residentAddress;
         *
         */

        for (GatePass pass: gatePassRepository.findAll()) {
            ViewAllGatePassesResponse  response = new ViewAllGatePassesResponse();
            response.setCode(pass.getCode());

            if (pass.getVisitor() != null) {
                response.setVisitorsName(pass.getVisitor().getName());
                response.setVisitorsPhoneNumber(pass.getVisitor().getPhoneNumber());
                response.setPurposeOfComing(pass.getVisitor().getPurposeOfComing());
            }
            else {
                response.setVisitorsName("Resident GatePass");
            }

            Resident resident   = residentRepository.findById(pass.getResidentId()).get();
            response.setResidentName(resident.getName());
            response.setResidentPhoneNumber(resident.getPhoneNumber());
            response.setResidentAddress(resident.getHouseAddress());

            gatePasses.add(response);
        }



        return gatePasses;
    }
    private static void validatingIfCodeIsActive(GatePass pass) {
        if (LocalDateTime.now().isAfter(pass.getExpirationDate())) {
            throw new InvalidGatePassException("Gate pass is no longer active, Its is expired.");
        }
    }

//    private static void validateCodeExistence(GatePass pass) {
//        if (pass == null) {
//            throw new InvalidGatePassException("Code is not in Existence");
//        }
//    }

    private static void validateResidentIsActive(Resident resident) {
        if (!resident.isEnabled()) {
            throw new ResidentDisabledException("Resident is Disabled");
        }
    }
}