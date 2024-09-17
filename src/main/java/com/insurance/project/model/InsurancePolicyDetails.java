package com.insurance.project.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class InsurancePolicyDetails {
    private String policyNumber;
    private String policyType;
    private String policyCoverageAmount;
    private String policyPremium;
    private String policyStartDate;
    private String policyEndDate;
    private String policyPrimaryHolder;
    private String policySecondaryHolder;
    private List<InsurancePolicyCoverageType> policyCoverageTypeList;
    private String insurerName;
    private String insuredItem;
    private String insuredItemValue;
    private String policyStatus;
    private String policyIssueDate;

}
