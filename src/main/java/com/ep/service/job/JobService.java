package com.ep.service.job;


import com.ep.model.Job;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface JobService {
    public Optional<Job> create(Job job);
    public Optional<Job> getOne(Long id);
    public Optional<List<Job>> getJobsByPositionId(Long id);

    @Transactional
    public Optional<Job> oneUpdate(Long jobId, Job job);

}
