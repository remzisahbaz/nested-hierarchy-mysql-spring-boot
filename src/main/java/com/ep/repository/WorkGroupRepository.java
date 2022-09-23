package com.ep.repository;


import com.ep.model.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkGroupRepository extends JpaRepository<WorkGroup,Long> {
    public List<WorkGroup> findWorkGroupByWorkGroupTypeId(Long id);
}

