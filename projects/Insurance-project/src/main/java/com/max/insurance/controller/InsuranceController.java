package com.max.insurance.controller;

import com.max.insurance.entity.ClaimDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alok Agarwal
 *
 */
@Api(value = "Insurance Claim Api", tags = "Insurance Claim Api")
@RequestMapping("/claim")
public interface InsuranceController {

    @ApiOperation(value = "Get Claim Details", notes= "fetch the Insurance claim details ", tags = {"Insurance Claim Api"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful got the claim details"),
            @ApiResponse(code = 200, message = "Invalid claimId"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{claimId}")
    public ResponseEntity<?> getClaimDetails(@PathVariable("claimId") Long claimId) ;

    @ApiOperation(value = "Create Claim Details", notes= "Creating  the Insurance claim details ", tags = {"Insurance Claim Api"})
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "successful created the claim details"),
            @ApiResponse(code = 404, message = "Invalid Request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(value = "/")
    public ResponseEntity<?> createClaim(@RequestBody ClaimDetails claimDetails) ;
}
