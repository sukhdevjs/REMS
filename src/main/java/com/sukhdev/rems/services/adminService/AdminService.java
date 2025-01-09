package com.sukhdev.rems.services.adminService;

import com.sukhdev.rems.dto.PropertyDto;

import java.util.List;

public interface AdminService {

    boolean postProperty(PropertyDto propertyDto);

    List<PropertyDto> getAllProperty();
}
