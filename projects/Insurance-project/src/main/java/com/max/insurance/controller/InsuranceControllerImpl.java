/**
 * This is REST controller implementation
 * All the calls will come to this first
 */

package com.max.insurance.controller;

import com.max.insurance.entity.ClaimDetails;
import com.max.insurance.services.ClaimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alok Agarwal
 *
 */
@Slf4j
@RestController
public class InsuranceControllerImpl implements InsuranceController{

    @Autowired
    ClaimService service;

    @Override
    public ResponseEntity<?> getClaimDetails(Long claimId) {
        ClaimDetails claimDetails = service.getClaimDetails(claimId);

        if(claimDetails !=null) {
            return new ResponseEntity<>(claimDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createClaim(ClaimDetails claimDetails) {
        ClaimDetails details = service.createClaim(claimDetails);

        if(claimDetails !=null) {
            return new ResponseEntity<>(claimDetails, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Unable to Create", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
}
