package com.insurance.project.component;

import com.insurance.project.dto.InsurancePolicy;
import com.insurance.project.repository.InsurancePolicyRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsuranceItemWriter implements ItemWriter<InsurancePolicy> {

    @Autowired
    private InsurancePolicyRepository insuranceRepository;

    @Override
    public void write(Chunk<? extends InsurancePolicy> chunk) throws Exception {
             insuranceRepository.saveAll(chunk);
    }
}