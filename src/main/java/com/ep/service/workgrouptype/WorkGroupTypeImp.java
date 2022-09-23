package com.ep.service.workgrouptype;

import com.ep.model.WorkGroupType;
import com.ep.repository.WorkGroupTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkGroupTypeImp implements WorkGroupTypeService {
    private WorkGroupTypeRepository workGroupTypeRepository;

    @Override
    public Optional<WorkGroupType> create(WorkGroupType workGroupType) {
        return Optional.ofNullable(workGroupTypeRepository.save(workGroupType));
    }

    @Override
    public Optional<WorkGroupType> getOne(Long id) {
        return workGroupTypeRepository.findById(id);
    }

    @Override
    public List<WorkGroupType> getAll() {
        return workGroupTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}
