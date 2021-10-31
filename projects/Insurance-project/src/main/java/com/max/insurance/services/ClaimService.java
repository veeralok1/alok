
/**
 * Here will write the all business logic
 *
 */
package com.max.insurance.services;

import com.max.insurance.entity.ClaimDetails;
import com.max.insurance.repo.ClaimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alok Agarwal
 *
 */

@Service
public class ClaimService {

    @Autowired
    ClaimRepo repo;

   /* This is going to fetch the claim details as per @claimID*/
    public ClaimDetails getClaimDetails(Long claimId) {
        return repo.findClaimDetailsByClaimId(claimId);

    }

    /*This method is going to save the claim details*/
    public ClaimDetails createClaim(ClaimDetails claimDetails) {
        ClaimDetails details = repo.save(claimDetails);
        return details;
    }

}
