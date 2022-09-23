package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.WorkGroupType;
import com.ep.repository.WorkGroupTypeRepository;
import com.ep.service.workgrouptype.WorkGroupTypeImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WorkGroupTypeController {

  private WorkGroupTypeRepository workGroupTypeRepository;
  private WorkGroupTypeImp workGroupTypeImp;

  @GetMapping ("workgrouptypes/{id}")
  public ResponseEntity<WorkGroupType> getOneWorkGroupType(@PathVariable (value = "id") Long id) {
    WorkGroupType workGroupType = workGroupTypeImp.getOne(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

    return new ResponseEntity<>(workGroupType, HttpStatus.OK);
  }

  @PostMapping ("workgrouptypes")
  public ResponseEntity<WorkGroupType> createWorkGroupType(@RequestBody WorkGroupType workGroupType) {
    var created = workGroupTypeImp.create(workGroupType).get();
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @GetMapping ("workgrouptypes")
  public ResponseEntity<List<WorkGroupType>> getlAllWorkGroupTypes() {
    var list = workGroupTypeImp.getAll() ;
    return new ResponseEntity<>(list, HttpStatus.CREATED);
  }

  @DeleteMapping("/work_group_types")
  public ResponseEntity<String> deleteComment() {
    workGroupTypeRepository.deleteAll();
    String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }
}
