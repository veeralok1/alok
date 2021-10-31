package com.max.insurance.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Alok Agarwal
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClaimDetails {

    @ApiModelProperty(notes = "Claim Id", hidden = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long claimId;

    @ApiModelProperty(notes = "Policy Number")
    String policyNum;

    @ApiModelProperty(notes = "Settlement Amount")
    Double amount;
}
