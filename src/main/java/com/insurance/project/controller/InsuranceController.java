package com.insurance.project.controller;

import com.insurance.project.dto.Address;
import com.insurance.project.dto.InsurancePolicy;
import com.insurance.project.exception.IdNotFoundException;
import com.insurance.project.model.InsurancePolicyDetails;
import com.insurance.project.model.InsurancePolicyRequest;
import com.insurance.project.service.AdressService;
import com.insurance.project.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@RestController
@RequestMapping("api/v1")
public class InsuranceController {
    private InsuranceService insuranceService;
    @Autowired
    private AdressService adressService;
   @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("/project")
    public String getAllList() {
        return insuranceService.getAllList();
    }

    @PostMapping("/add/{insuranceId}")
    public String addInsurance(@PathVariable Long insuranceId) {
        return insuranceService.addInsurance(insuranceId);
    }

    @PostMapping("/create-policy")
    public ResponseEntity<InsurancePolicyDetails> createPolicy(@RequestBody InsurancePolicyRequest policyRequest) {
        InsurancePolicyDetails createdPolicy = insuranceService.createInsurancePolicy(policyRequest);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);

    }


    @PostMapping("/insurance-policies")
    public ResponseEntity<InsurancePolicy> insertInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy) {
        InsurancePolicy insertedPolicy = insuranceService.insertInsurancePolicy(insurancePolicy);
        if (insertedPolicy != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedPolicy);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/getByInsurancePolicyId/{insurancePolicyId}")
    public ResponseEntity<InsurancePolicy> getByInsurancePolicyId(@PathVariable int insurancePolicyId) {
        Optional<InsurancePolicy> policy = insuranceService.getByInsurancePolicyId(insurancePolicyId);

        if (policy.isPresent()) {
            return ResponseEntity.ok(policy.get());
        } else {
            throw new IdNotFoundException("Insurance policy ID " + insurancePolicyId + " not Found in database");
        }
    }

    @GetMapping("/getByInsurancePolicyId1/{insurancePolicyId}")
    public ResponseEntity<InsurancePolicy> getByInsurancePolicyId1(@PathVariable int insurancePolicyId) {
        return insuranceService.getByInsurancePolicyId(insurancePolicyId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IdNotFoundException("Insurance policy ID " + insurancePolicyId + " not Found in database"));
    }

    @GetMapping("/insurance-policies/premium-greater-than/{premium}")
    public ResponseEntity<List<InsurancePolicy>> getInsurancePolicyWithPremiumGreaterThan(@PathVariable Double premium) {
        List<InsurancePolicy> insurancePolicies = insuranceService.getInsurancePolicyWithPremiumGreaterThan(premium);
        if (!insurancePolicies.isEmpty()) {
            return ResponseEntity.ok(insurancePolicies);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }


    @GetMapping("/insurancedetails/{policyId}")
    public ResponseEntity<?> getInsuranceDetails(@PathVariable int policyId) {
        CompletableFuture<InsurancePolicy> insuranceFuture = insuranceService.getInsurancePolicyIdAsync(policyId);
        CompletableFuture<Address> addressFuture = adressService.getAddressByPolicyIdAsync(policyId);

        try {
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(insuranceFuture, addressFuture);
            allFutures.join(); // Wait for all futures to complete
        } catch (CompletionException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching data.");
        }

        InsurancePolicy insuranceDetails = insuranceFuture.join();
        Address address = addressFuture.join();

        if (insuranceDetails == null || address == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not found in database");
        } else {
            Map<String, Object> combinedResult = new HashMap<>();
            combinedResult.put("insuranceDetails", insuranceDetails);
            combinedResult.put("address", address);
            return ResponseEntity.ok(combinedResult);
        }
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8081/api/v1/jpa/products/allproducts",
                HttpMethod.GET,
                entity,
                String.class);

        return responseEntity.getBody();
    }
}

