package com.ep.repository;


import com.ep.model.WorkGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkGroupTypeRepository extends JpaRepository<WorkGroupType,Long> {
}
