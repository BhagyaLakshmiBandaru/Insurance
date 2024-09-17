package com.insurance.project.dao;

import com.insurance.project.dto.InsurancePolicy;
import com.insurance.project.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class InsurancePolicyDao {
    private  InsurancePolicyRepository insurancePolicyRepository;
    @Autowired
    public InsurancePolicyDao(InsurancePolicyRepository insurancePolicyRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
    }


    public InsurancePolicy insertInsurancePolicy(InsurancePolicy insurancePolicy) {
        return insurancePolicyRepository.save(insurancePolicy);
    }

    public Optional<InsurancePolicy> getByInsurancePolicyId(int insurancePolicyId) {
        return insurancePolicyRepository.findById(insurancePolicyId);
    }

    public List<InsurancePolicy> getInsurancePolicyWithPremiumGreaterThan(Double premium) {
        return insurancePolicyRepository.findByPolicyPremiumGreaterThan(premium);
    }

}
