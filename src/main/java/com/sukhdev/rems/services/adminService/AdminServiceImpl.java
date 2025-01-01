package com.sukhdev.rems.services.adminService;

import com.sukhdev.rems.dto.PropertyDto;
import com.sukhdev.rems.entity.Property;
import com.sukhdev.rems.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final PropertyRepository propertyRepository;

    @Override
    public boolean postProperty(PropertyDto propertyDto) {
        try{
            Property property = new Property();
            property.setName(propertyDto.getName());
            property.setDescription(propertyDto.getAddress());
            property.setAddress(propertyDto.getAddress());
            property.setType(propertyDto.getType());
            property.setPrice(propertyDto.getPrice());
            property.setImage(propertyDto.getImage().getBytes());
            property.setNumberOfRooms(propertyDto.getNumberOfRooms());
            propertyRepository.save(property);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
