package com.insurance.project.component;

import com.insurance.project.dto.InsurancePolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InsuranceItemProcessor implements ItemProcessor<InsurancePolicy, InsurancePolicy> {

    @Override
    public InsurancePolicy process(InsurancePolicy policy) throws Exception {
        // Example processing logic: Increase premium by 10%
        double increasedPremium = policy.getPolicyPremium() * 1.10;
        policy.setPolicyPremium(increasedPremium);

        // You can add more processing logic here if needed

        return policy;
    }
    }

