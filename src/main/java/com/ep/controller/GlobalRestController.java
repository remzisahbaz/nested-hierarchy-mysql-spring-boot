package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.User;
import com.ep.model.WorkGroup;
import com.ep.repository.*;
import com.ep.service.user.UserImp;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GlobalRestController {

  private final AssigmentRepository assigmentRepository;
  private JobRepository jobRepository;
  private WorkGroupRepository workGroupRepository;
  private WorkGroupTypeRepository workGroupTypeRepository;
  private PositionRepository positionRepository;
  private UserRepository userRepository;

  @Transactional
  @DeleteMapping("/delete_all")
  public ResponseEntity<String> deleteAll() {
    userRepository.deleteAll();
    positionRepository.deleteAll();
    positionRepository.deleteAll();
    userRepository.deleteAll();
    workGroupRepository.deleteAll();
    workGroupTypeRepository.deleteAll();
    assigmentRepository.deleteAll();

    String delete= "DELETED ALL ENTITIES";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<String> createDatabase(){

   // @Query(value = "CREATE DATABASE epdb");
    String created= "DELETED ALL ENTITIES";
    return new ResponseEntity<>(created,HttpStatus.OK);
  }

}