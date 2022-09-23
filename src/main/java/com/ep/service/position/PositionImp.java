package com.ep.service.position;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.Position;
import com.ep.repository.PositionRepository;
import com.ep.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionImp implements PositionService {

    private PositionRepository positionRepository;
    private JobRepository jobRepository;

    @Override
    public Optional<Position> getOne(Long id) {
        return Optional.ofNullable(positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id)));
    }

    @Override
    public Optional<Position> create(Position position) {
        return Optional.of(positionRepository.save(position));
    }


    @Transactional
    @Override
    public Optional<Position> update(Position position) {
        var exist=positionRepository.findById(position.getId()).get();
        if(exist!=null){
            Optional.of(positionRepository.saveAndFlush(position));
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
       positionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Optional<List<Position>> getPositonsByWorkGroupId(Long id) {
        return Optional.ofNullable(positionRepository.findPositionByWorkGroupId(id).stream().collect(Collectors.toList()));
    }



}
