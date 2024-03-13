package com.Devansh.JobApplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/jobs")
public class jobController {
    private final jobService jobservice ;
    public jobController(jobService jobservice) {
        this.jobservice = jobservice;
    }
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobservice.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobservice.createJob(job);
         return new ResponseEntity<>("Job created successfully" , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobservice.getJobById(id);
        return job == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(job , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean job = jobservice.deleteJobById(id);
        return job ? new ResponseEntity<>("Job delete successfully" , HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id , @RequestBody Job updatedJob){
        boolean job = jobservice.updateJobById(id, updatedJob);
        return job ? new ResponseEntity<>("Job updated successfully" , HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
