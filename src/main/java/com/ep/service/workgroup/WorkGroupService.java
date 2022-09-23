package com.ep.service.workgroup;

import com.ep.model.Assigment;
import com.ep.model.WorkGroup;
import com.ep.model.WorkGroupType;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface WorkGroupService {
    public Optional<WorkGroup> create (WorkGroup workGroup);
    public Optional<List<WorkGroup>> getOne (Long id);
    public List<WorkGroup> getAll ();

    public Optional<WorkGroup> addWorkGroupTypetoWorkGroup(Long workGroupId, Long workGroupTypeId);
}
