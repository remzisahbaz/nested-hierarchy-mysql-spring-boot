package com.ep.service.position;



import com.ep.model.Position;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    public Optional<Position> getOne(Long id);
     public Optional<Position> create(Position position);
    public Optional<Position> update(Position position);
    public ResponseEntity<HttpStatus> delete(Long id);
    public Optional<List<Position>> getPositonsByWorkGroupId(Long id);


}
