package com.ep.service.assigment;


import com.ep.model.Assigment;
import com.ep.repository.AssigmentRepository;
import com.ep.repository.PositionRepository;
import com.ep.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class AssigmentImp implements AssigmentService {

    private AssigmentRepository assigmentRepository;
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;



    @Override
    public Optional<Assigment> getOne(Long id) {
        return Optional.of(assigmentRepository.findById(id).get());
    }

    @Override
    public Optional<Assigment> create(Assigment assigment) {
        return Optional.of(assigmentRepository.save(assigment));
    }

    @Override
    public Optional<Assigment> update(Assigment assigment) {

        System.out.println(assigment.getUser());
        var exist = assigmentRepository.findById(assigment.getId());
        if (exist != null) {
            System.out.println(assigment.getId());
        }
        return Optional.empty();
    }

    @Override
    public Assigment addPositionToAssigment(Long positionId, Long assigmentId) {
        var position_exist = positionRepository.findById(positionId);
        var assigment_exist = assigmentRepository.findById(assigmentId);


        if (position_exist != null) {
            if (assigment_exist != null) {
                position_exist.get().getAssigments().add(assigment_exist.get());
                assigment_exist.get().setPosition(position_exist.get());

            }
        }
        //positionRepository.save(position_exist.get());
        return assigmentRepository.save(assigment_exist.get());
    }

    @Override
    public Assigment addUserToAssigment(Long userId, Long assigmentId) {
        var user_exist = userRepository.findById(userId);
        var assigment_exist = getOne(assigmentId);
        if (user_exist != null) {
            if (assigment_exist != null) {
                user_exist.get().getAssigments().add(assigment_exist.get());
                assigment_exist.get().setUser(user_exist.get());
            }

        }

        return assigmentRepository.save(assigment_exist.get());
    }
    @Override
    public Optional<List<Assigment>>  getAll() {
        List<Assigment> assigments= new ArrayList<Assigment>();

        return Optional.ofNullable(assigmentRepository.findAll());

    }
    @Override
    public Optional<List<Assigment>> getAssigmentOfUserByUsername(String username) {

        return Optional.ofNullable(assigmentRepository.findAssigmentByUserName(username).stream().collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Assigment>> findAssigmentByUserId(Long id) {
        return Optional.ofNullable(assigmentRepository.findAssigmentByUserId(id).stream().collect(Collectors.toList()));
    }
}
