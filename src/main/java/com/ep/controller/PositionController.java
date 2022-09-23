package com.ep.controller;



import com.ep.model.Position;
import com.ep.model.WorkGroup;
import com.ep.repository.PositionRepository;
import com.ep.repository.WorkGroupRepository;
import com.ep.service.position.PositionImp;
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
public class PositionController {

  private final PositionImp positionImp;
  private final PositionRepository positionRepository;
  private final WorkGroupRepository workGroupRepository;

  @GetMapping("/positions/{id}")
  public ResponseEntity<Position> getPositonById(@PathVariable("id") long id) {

    return new ResponseEntity<>(positionImp.getOne(id).get(), HttpStatus.OK);
  }
  @GetMapping("work_groups/{id}/positions")
  public ResponseEntity<List<Position>> getPositonsByWorkGroupId(@PathVariable("id") long id) {
    var list =positionImp.getPositonsByWorkGroupId(id).orElseThrow(()->
            new ResolutionException("not found workgroup by id: "+id));
    return new ResponseEntity<>(list, HttpStatus.OK);
  }


  @PostMapping("work_group/{workgroupId}/position")
  private ResponseEntity<Position>  createPositon(
          @PathVariable("workgroupId") Long workGroupId,
          @RequestBody Position positionRequest){

    Position new_position= workGroupRepository.findById(workGroupId).map(workGroup -> {
      positionRequest.setWorkGroup(workGroup);
      return positionRepository.save(positionRequest);
    }).orElseThrow(()-> new ResolutionException("not found y id"+workGroupId));

    return new ResponseEntity<>(new_position,HttpStatus.OK);

  }

  @DeleteMapping("/positions")
  public ResponseEntity<String> deleteComment() {
    positionRepository.deleteAll();
    String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }
}
