package com.insurance.project.repository;

import com.insurance.project.dto.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy,Integer> {
    List<InsurancePolicy> findByPolicyPremiumGreaterThan(double premium);
}
