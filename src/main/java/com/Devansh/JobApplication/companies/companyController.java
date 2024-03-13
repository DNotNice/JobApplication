package com.Devansh.JobApplication.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class companyController {
    private companyService Companyservice;

    public companyController(companyService Companyservice) {
        this.Companyservice = Companyservice;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
         return new ResponseEntity<>(Companyservice.getAllCompanies() , HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id , @RequestBody Company company){
        boolean res =  Companyservice.updateCompany(id, company);
        return res ? new ResponseEntity<>("Company Updated Successfully",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        try {
        Companyservice.createCompany(company);
        return new ResponseEntity<>("Company added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company c = Companyservice.getCompanyById(id);
        return c != null ? new ResponseEntity<>(c , HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean res = Companyservice.deleteCompanyById(id);
        return res ? new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
