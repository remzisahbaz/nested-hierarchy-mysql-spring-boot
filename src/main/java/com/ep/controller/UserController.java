package com.ep.controller;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.User;
import com.ep.repository.AssigmentRepository;
import com.ep.repository.UserRepository;
import com.ep.service.user.UserImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

  private final AssigmentRepository assigmentRepository;

  private UserRepository userRepository;
  private final UserImp userImp;


/*  @GetMapping("/users/{usersId}/assigments")
  public ResponseEntity<List<User>> getAllUsersByAssigment(@PathVariable(value = "userId") Long assigmentId) {
    Assigment assigment = assigmentRepository.findById(assigmentId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + assigmentId));

    List<User> users = new ArrayList<User>();
    users.addAll(assigment.getUsers());

    return new ResponseEntity<>(users, HttpStatus.OK);
  }*/

  @GetMapping("users/{id}")
  public ResponseEntity<User> getOneUser(@PathVariable(value = "id") Long id) {
    User user = userImp.getOne(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping("users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    var created=userImp.create(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @DeleteMapping("/users")
  public ResponseEntity<String> deleteComment() {
    userRepository.deleteAll();
    String delete= "DELETED";
    return new ResponseEntity<>(delete,HttpStatus.OK);
  }
/*  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User userRequest) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

    user.setUsername(userRequest.getUsername());

    return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
  }*/

/*  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
    userRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }*/
  
/*  @DeleteMapping("/assigments/{assigmentId}/users")
  public ResponseEntity<List<User>> deleteAllCommentsOfTutorial(@PathVariable(value = "assigmentId") Long assigmentId) {
    Assigment assigment = assigmentRepository.findById(assigmentId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + assigmentId));
    
    assigment.removeComments();
    assigmentRepository.save(assigment);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }*/
}
