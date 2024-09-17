package com.insurance.project.dto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "insurancePolicy")
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policyId")
    private int policyId;

    @Column(name = "policyNumber")
    private String policyNumber;

    @Column(name = "policyType")
    private String policyType;

    @Column(name = "policyCoverageAmount")
    private String policyCoverageAmount;

    @Column(name = "policyPremium")
    private double policyPremium;

    @Column(name = "policyStartDate")
    private String policyStartDate;

    @Column(name = "policyEndDate")
    private String policyEndDate;

    @Column(name = "policyPrimaryHolder")
    private String policyPrimaryHolder;

    @Column(name = "policySecondaryHolder")
    private String policySecondaryHolder;

    @Column(name = "policyStatus")
    private String policyStatus;

    @Column(name = "policyIssueDate")
    private String policyIssueDate;

    @Column(name = "insurerName")
    private String insurerName;

    @Column(name = "insuredItem")
    private String insuredItem;

    @Column(name = "insuredItemValue")
    private String insuredItemValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
}
