package com.ep.repository;


import com.ep.model.Assigment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AssigmentRepository extends JpaRepository<Assigment, Long> {

    @Query ( "select u from assigments u inner join u.user r where r.username in :username" )
    public List<Assigment> findAssigmentByUserName(String username);
    public List<Assigment> findAssigmentByUserId(Long id);
}
