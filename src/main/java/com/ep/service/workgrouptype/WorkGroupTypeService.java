package com.ep.service.workgrouptype;

import com.ep.model.WorkGroupType;

import java.util.List;
import java.util.Optional;

public interface WorkGroupTypeService  {
    public Optional<WorkGroupType> create (WorkGroupType workGroupType);
    public Optional<WorkGroupType> getOne (Long id);
    public List<WorkGroupType> getAll ();
}
