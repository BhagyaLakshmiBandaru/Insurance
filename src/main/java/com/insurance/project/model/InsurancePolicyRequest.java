package com.insurance.project.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsurancePolicyRequest {
    private String insurancePolicyNumber;
    private String insurancePolicyType;
    private String  personName;
    private String id ;
}
