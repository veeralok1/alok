/**
 * This interface is to do the CURD operation for ClaimDetails
 */

package com.max.insurance.repo;

import com.max.insurance.entity.ClaimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alok Agarwal
 *
 */

@Repository
public interface ClaimRepo extends JpaRepository<ClaimDetails, Long> {

    public ClaimDetails findClaimDetailsByClaimId(Long ClaimId);
}
