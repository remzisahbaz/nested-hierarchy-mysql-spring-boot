package com.ep.repository;


import com.ep.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    public List<Position> findPositionByWorkGroupId(Long id);
}
