package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.Assigment;
import com.ep.model.WorkGroup;
import com.ep.repository.WorkGroupRepository;
import com.ep.repository.WorkGroupTypeRepository;
import com.ep.service.workgroup.WorkGroupeImp;
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
public class WorkGroupController {

  public WorkGroupRepository workGroupRepository;
  public WorkGroupTypeRepository workGroupTypeRepository;
  public WorkGroupeImp workGroupeImp;

  @GetMapping ("work_group_types/{id}/work_groups")
  public ResponseEntity<List<WorkGroup>> getOneWorkGroup(@PathVariable (value = "id") Long id) {
    var list = workGroupeImp.getOne(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping ("workgroups")
  public ResponseEntity<List<WorkGroup>> getlAllWorkGroups() {
    var list = workGroupeImp.getAll() ;
    return new ResponseEntity<>(list, HttpStatus.CREATED);
  }

  @PostMapping("work_group_types/{workgrouptypeId}/work_groups")
  private ResponseEntity<WorkGroup>  createWorkGroupType(
                                                                 @PathVariable("workgrouptypeId") Long workGroupTypeId,
                                                                 @RequestBody WorkGroup workGroupRequest,
                                                                 @RequestParam(name = "work_group_parentid") Long workGroupParentId){


    WorkGroup new_workgroup= workGroupTypeRepository.findById(workGroupTypeId).map(workGroupType -> {
      workGroupRequest.setWorkGroupType(workGroupType);
      if(workGroupRepository.count()>0){ workGroupRequest.setWorkGroupParent(workGroupRepository.findById(workGroupParentId).get());}

      return workGroupRepository.save(workGroupRequest);
    }).orElseThrow(()-> new ResolutionException("not found  id"+workGroupTypeId));

   // var added=workGroupeImp.addWorkGroupTypetoWorkGroup(workGroupId,workGroupTypeId).get();
    return new ResponseEntity<>(new_workgroup,HttpStatus.OK);

  }



  @PostMapping("work_groups/{workgroupId}/work_groups")
  private ResponseEntity<WorkGroup>  createWorkGroup(
          @PathVariable("workgroupId") Long workGroupId,
          @RequestBody WorkGroup workGroupRequest,
          @RequestParam(name = "work_group_parentid") Long workGroupParentId,
          @RequestParam(name = "work_group_typeid") Long workGroupTypetId
  ){

    WorkGroup new_workgroup= null;
    if(workGroupRepository.count()>0) {
       new_workgroup = workGroupRepository.findById(workGroupId).map(workGroup -> {
        workGroup.getWorkGroups().add(workGroupRequest);
         workGroupRequest.setWorkGroupType(workGroupTypeRepository.findById(workGroupTypetId).get());
        workGroupRequest.setWorkGroupParent(workGroupRepository.findById(workGroupParentId).get());
        return workGroupRepository.save(workGroupRequest);
      }).orElseThrow(() -> new ResolutionException("work_groups/{workgroupId} " + workGroupId));


    }
    else{
     // workGroupRequest.setWorkGroupType(workGroupTypeRepository.findById(workGroupTypetId).get());
        workGroupRequest.setWorkGroupType(workGroupTypeRepository.findById(workGroupTypetId).get());
        new_workgroup=workGroupRepository.save(workGroupRequest);

    }
    // var added=workGroupeImp.addWorkGroupTypetoWorkGroup(workGroupId,workGroupTypeId).get();
    return new ResponseEntity<>(new_workgroup,HttpStatus.OK);

  }

    @DeleteMapping("/work_groups")
  public ResponseEntity<String> deleteComment() {
        workGroupRepository.deleteAll();
      String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }
}
