package com.Devansh.JobApplication.companies.impl;

import com.Devansh.JobApplication.companies.Company;
import com.Devansh.JobApplication.companies.companyRepository;
import com.Devansh.JobApplication.companies.companyService;
import com.Devansh.JobApplication.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class companyServiceImpl implements companyService  {
    private companyRepository Companyrepository;

    public companyServiceImpl(companyRepository Companyrepository) {
        this.Companyrepository = Companyrepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return Companyrepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id , Company company) {
        Optional<Company> company1 = Companyrepository.findById(id);
        if(company1.isPresent()){
            Company company2 = company1.get();
            company2.setName(company.getName());
            company2.setDescription(company.getDescription());
            company2.setJobs(company.getJobs());
            Companyrepository.save(company2);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        Companyrepository.save(company);

    }

    @Override
    public Company getCompanyById(Long id) {
         return Companyrepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(Companyrepository.findById(id).isPresent()) {
            Companyrepository.deleteById(id);
            return true;
        }
        return false;
    }
}
