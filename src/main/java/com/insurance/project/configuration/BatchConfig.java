package com.insurance.project.configuration;

import com.insurance.project.component.InsuranceItemProcessor;
import com.insurance.project.component.InsuranceItemReader;
import com.insurance.project.component.InsuranceItemWriter;
import com.insurance.project.dto.InsurancePolicy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private InsuranceItemReader insuranceItemReader;

    @Autowired
    private InsuranceItemProcessor insuranceItemProcessor;

    @Autowired
    private InsuranceItemWriter insuranceItemWriter;

    @Bean
    public Job myJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("myJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step importInsurancePolicyStep(JobRepository jobRepository,
                                          InsuranceItemReader insuranceItemReader,
                                          InsuranceItemProcessor insuranceItemProcessor,
                                          InsuranceItemWriter insuranceItemWriter,
                                          PlatformTransactionManager transactionManager) {

        return new StepBuilder("importInsurancePolicyStep", jobRepository)
                .<InsurancePolicy, InsurancePolicy>chunk(10)
                .reader(insuranceItemReader)
                .processor(insuranceItemProcessor)
                .writer(insuranceItemWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
