package com.insurance.project.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class InsurancePolicyCoverageType {
    private String coverageType;
    private String description;
}
