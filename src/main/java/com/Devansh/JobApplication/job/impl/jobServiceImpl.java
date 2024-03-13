package com.Devansh.JobApplication.job.impl;
import com.Devansh.JobApplication.job.Job;
import com.Devansh.JobApplication.job.JobRepository;
import com.Devansh.JobApplication.job.jobService;
import com.sun.source.tree.LiteralTree;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class jobServiceImpl implements jobService {


    JobRepository jobRepository;
    public jobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    @Override
    public void createJob(Job job) {
       jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }
    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        Optional<Job> jobOptional= jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedjob.getTitle());
            job.setDescription(updatedjob.getDescription());
            job.setMinSalary(updatedjob.getMinSalary());
            job.setMaxSalary(updatedjob.getMaxSalary());
            job.setLocation(updatedjob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
