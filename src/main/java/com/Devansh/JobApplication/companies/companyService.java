package com.Devansh.JobApplication.companies;

import com.Devansh.JobApplication.job.Job;

import java.util.List;

public interface companyService {
    List<Company>  getAllCompanies();
    boolean updateCompany(Long id , Company company);
    void createCompany(Company company);

    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);


}
