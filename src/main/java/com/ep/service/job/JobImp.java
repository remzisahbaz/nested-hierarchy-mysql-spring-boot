package com.ep.service.job;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.Job;
import com.ep.repository.AssigmentRepository;
import com.ep.repository.JobRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class JobImp implements JobService {
    public final JobRepository jobRepository;
    private final AssigmentRepository assigmentRepository;

    @Override
    public Optional<Job> getOne(Long id) {

        return jobRepository.findById(id);
    }

    @Override
    public Optional<List<Job>> getJobsByPositionId(Long id) {
        return Optional.ofNullable(jobRepository.findJobByPositionId(id).stream().collect(Collectors.toList()));
    }

    @Override
    public Optional<Job> oneUpdate(Long jobId, Job job) {
     Job exist= jobRepository.findById(jobId)
                .map(job_ -> {
                    job_.setJobName(job.getJobName());
                    return jobRepository.save(job_);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("Not found Tutorial with id = " + jobId));


        return Optional.ofNullable(exist);
    }

    @Override
    public Optional<Job> create(Job job) {
        return Optional.ofNullable(jobRepository.save(job));
    }

}
