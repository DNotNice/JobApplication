package com.Devansh.JobApplication.job;

import java.util.List;

public interface jobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id , Job job);


}
