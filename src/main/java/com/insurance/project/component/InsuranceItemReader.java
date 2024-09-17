package com.insurance.project.component;

import com.insurance.project.dto.InsurancePolicy;
import com.insurance.project.repository.InsurancePolicyRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
@Component
public class InsuranceItemReader implements ItemReader<InsurancePolicy> {


    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    private Iterator<InsurancePolicy> policyIterator;

    @Override
    public InsurancePolicy read() throws Exception {
        if (policyIterator == null || !policyIterator.hasNext()) {
            initializePolicyIterator();
        }
        return policyIterator.hasNext() ? policyIterator.next() : null;
    }

    private void initializePolicyIterator() {
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        policyIterator = policies.iterator();
    }
}