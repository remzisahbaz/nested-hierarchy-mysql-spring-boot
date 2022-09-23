package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.Assigment;
import com.ep.repository.AssigmentRepository;
import com.ep.repository.UserRepository;
import com.ep.service.assigment.AssigmentImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AssigmentController {

  private final AssigmentImp assigmentImp;
  private final AssigmentRepository assigmentRepository;
  private final UserRepository userRepository;

  @GetMapping("/assigments/{id}")
  public ResponseEntity<Assigment> getTutorialById(@PathVariable("id") long id) {
    Assigment assigment = assigmentImp.getOne(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    return new ResponseEntity<>(assigment, HttpStatus.OK);
  }

  @GetMapping("/assigments")
  public ResponseEntity<List<Assigment>> getAllAssigment() {
    var assigment = assigmentImp.getAll().get();

    return new ResponseEntity<>(assigment, HttpStatus.OK);
  }

  @GetMapping("assigments/users/{userId}")
  public ResponseEntity<List<Assigment>> getAssigmentByUserId(@PathVariable("userId") Long userId){
    var list = assigmentImp.findAssigmentByUserId(userId).orElseThrow(()->
            new ResolutionException("find Assigment ByUserId :"+userId));

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping("/assigments")
  public ResponseEntity<Assigment> createAssigment(@RequestBody Assigment assigment) {
    var created=assigmentImp.create(assigment).get();
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }


  @PostMapping("/users/{userId}/assigments")
  public ResponseEntity<Assigment> createdAssigment(

          @PathVariable(value = "userId") Long userId,
          @RequestBody Assigment assigmentRequesst) {

        Assigment new_assigment=  userRepository.findById(userId)
                .map(user -> {
            assigmentRequesst.setUser(user);
            return assigmentRepository.save(assigmentRequesst);
          }).orElseThrow(()->
          new ResourceNotFoundException("No found by userid:"+userId));

   // var created=assigmentImp.create(assigment).get();
    return new ResponseEntity<>(new_assigment, HttpStatus.CREATED);
  }


  @PostMapping("/positions/{positionId}/assigments/{assigmentId}")
  public ResponseEntity<Assigment> addOnePositionToAssigment(@PathVariable(value = "assigmentId") Long assigmentId, @PathVariable(value = "positionId") Long positionId) {

    var added=assigmentImp.addPositionToAssigment(positionId,assigmentId);
    return new ResponseEntity<>(added,HttpStatus.OK);
  }

  @GetMapping("assigments/users")
  public ResponseEntity<List<Assigment>> getAssigmentOfUserByUsername(
          @RequestParam(name = "username") String username
  ){
    var list=assigmentImp.getAssigmentOfUserByUsername(username)
            .orElseThrow(()-> new ResourceNotFoundException("Not found getA ssigment Of User By Username :"+ username));

    return new ResponseEntity<>(list,HttpStatus.OK);
  }

  @DeleteMapping("/assigments")
  public ResponseEntity<String> deleteComment() {
    assigmentRepository.deleteAll();
    String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }

}
