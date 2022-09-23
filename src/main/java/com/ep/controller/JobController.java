package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.Job;
import com.ep.repository.PositionRepository;
import com.ep.repository.JobRepository;
import com.ep.service.job.JobImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class JobController {

  private final JobRepository jobRepository;
  private PositionRepository positionRepository;
  private final JobImp jobImp;


  @GetMapping ("jobs/{id}")
  public ResponseEntity<Job> getOneUser(@PathVariable (value = "id") Long id) {
    Job job = jobImp.getOne(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

    return new ResponseEntity<>(job, HttpStatus.OK);
  }

  @GetMapping ("positions/{positionId}/jobs")
  public ResponseEntity<List<Job>> getAllByPositionId(@PathVariable (value = "positionId") Long positionId) {
    var list= jobImp.getJobsByPositionId(positionId)
            .orElseThrow(()-> new ResolutionException("not found by id: "+positionId));

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping ("positions/{positionId}/jobs")
  public ResponseEntity<Job> createUser(@RequestBody Job jobRequest,
                                        @PathVariable("positionId") Long positionId) {

    Job new_job = positionRepository.findById(positionId).map(position -> {
      jobRequest.setPosition(position);
      return jobRepository.save(jobRequest);
    }).orElseThrow(()-> new ResolutionException(""+positionId));

    return new ResponseEntity<>(new_job, HttpStatus.CREATED);

  }

  @DeleteMapping("/jobs")
  public ResponseEntity<String> deleteComment() {
    jobRepository.deleteAll();
    String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }
}


