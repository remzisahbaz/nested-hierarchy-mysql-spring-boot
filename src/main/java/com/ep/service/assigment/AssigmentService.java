package com.ep.service.assigment;


import com.ep.model.Assigment;
import com.ep.model.Position;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AssigmentService {

    public Optional<Assigment> getOne(Long id);
    public Optional<Assigment> create(Assigment assigment);
    public Optional<Assigment> update(Assigment assigment);

    public Optional<List<Assigment>>  getAll();
    @Transactional
    public  Assigment addPositionToAssigment(Long positionId, Long assigmentId);
   @Transactional
    public  Assigment addUserToAssigment(Long userId, Long assigmentId);

   public Optional<List<Assigment>> getAssigmentOfUserByUsername(String username);
   public Optional<List<Assigment>> findAssigmentByUserId(Long id);
}
