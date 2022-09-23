package com.ep.service.workgroup;

import com.ep.model.WorkGroup;
import com.ep.repository.WorkGroupRepository;
import com.ep.repository.WorkGroupTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkGroupeImp implements WorkGroupService {
    public WorkGroupRepository workGroupRepository;
    private WorkGroupTypeRepository workGroupTypeRepository;

    @Override
    public Optional<WorkGroup> create(WorkGroup workGroup) {
        return Optional.ofNullable(workGroupRepository.save(workGroup));
    }

    @Override
    public Optional<List<WorkGroup>> getOne(Long id) {
        return Optional.ofNullable(workGroupRepository.findWorkGroupByWorkGroupTypeId(id).stream().collect(Collectors.toList()));
    }

    @Override
    public List<WorkGroup> getAll() {
        return workGroupRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<WorkGroup> addWorkGroupTypetoWorkGroup(Long workGroupId, Long workGroupTypeId) {
        var workgGroup_exist = workGroupRepository.findById(workGroupId);
        var workgGroupType_exist = workGroupTypeRepository.findById(workGroupTypeId);
        List<WorkGroup> workGroup =  new ArrayList<>();

        if (workgGroup_exist != null) {
            if (workgGroupType_exist != null) {
             var wtp= workgGroupType_exist.get().getWorkGroups().add(workgGroup_exist.get());
              //  workGroup.setWorkGroupType(wtp.get());
                workGroupTypeRepository.save(workgGroupType_exist.get());
            }
        }
        //positionRepository.save(position_exist.get());
        return Optional.ofNullable(workGroupRepository.save(workgGroup_exist.get()));
    }

}
