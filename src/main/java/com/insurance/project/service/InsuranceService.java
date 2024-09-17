package com.insurance.project.service;

import com.insurance.project.dao.InsurancePolicyDao;
import com.insurance.project.dto.Address;
import com.insurance.project.dto.InsurancePolicy;
import com.insurance.project.model.InsurancePolicyCoverageType;
import com.insurance.project.model.InsurancePolicyDetails;
import com.insurance.project.model.InsurancePolicyRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class InsuranceService {
    private InsurancePolicyDao insurancePolicyDao;

    public InsuranceService(InsurancePolicyDao insurancePolicyDao) {
        this.insurancePolicyDao = insurancePolicyDao;
    }

    public String getAllList() {
        return "Successfully returned...";
    }

    public String addInsurance(Long insuranceId) {
        return "123 added successfully.";
    }
    public InsurancePolicyDetails createInsurancePolicy(InsurancePolicyRequest policyRequest) {
        InsurancePolicyCoverageType liabilityCoverage = InsurancePolicyCoverageType.builder()
                .coverageType("Liability Coverage")
                .description("Protects against claims resulting from injuries and damage to people or property caused by you or your employees.")
                .build();

        InsurancePolicyCoverageType collisionCoverage = InsurancePolicyCoverageType.builder()
                .coverageType("Collision Coverage")
                .description("Pays for damage to your vehicle caused by a collision with another vehicle or object.")
                .build();

        InsurancePolicyCoverageType comprehensiveCoverage = InsurancePolicyCoverageType.builder()
                .coverageType("Comprehensive Coverage")
                .description("Covers damage to your vehicle caused by events other than collisions, such as theft, vandalism, fire, or natural disasters.")
                .build();


        return InsurancePolicyDetails.builder()
                .policyNumber(policyRequest.getInsurancePolicyNumber())
                .policyType(policyRequest.getInsurancePolicyType())
                .policyCoverageAmount("50000")
                .policyPremium("1000")
                .policyStartDate("2024-01-01")
                .policyEndDate("2025-01-01")
                .policyPrimaryHolder("John Doe")
                .policySecondaryHolder("Jane Doe")
                .policyCoverageTypeList(Arrays.asList(liabilityCoverage,collisionCoverage,comprehensiveCoverage))
                .insurerName("XYZ Insurance Company")
                .insuredItem("Car")
                .insuredItemValue("20000")
                .policyStatus("Active")
                .policyIssueDate("2024-01-01")
                .build();


    }
    public InsurancePolicy insertInsurancePolicy(InsurancePolicy insurancePolicy) {
        return insurancePolicyDao.insertInsurancePolicy(insurancePolicy);
    }
    public Optional<InsurancePolicy> getByInsurancePolicyId(int insurancePolicyId) {
        return insurancePolicyDao.getByInsurancePolicyId(insurancePolicyId);
    }
    public List<InsurancePolicy> getInsurancePolicyWithPremiumGreaterThan(Double premium) {
        return insurancePolicyDao.getInsurancePolicyWithPremiumGreaterThan(premium);
    }

    @Async
    public CompletableFuture<InsurancePolicy> getInsurancePolicyIdAsync(int insurancePolicyId) {
        // Retrieve address details from the DAO by policy ID
        Optional<InsurancePolicy> insurancePolicyOptional = insurancePolicyDao.getByInsurancePolicyId(insurancePolicyId);
        return CompletableFuture.completedFuture(insurancePolicyOptional.orElse(null));
    }
}

