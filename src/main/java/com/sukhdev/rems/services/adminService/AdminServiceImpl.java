package com.sukhdev.rems.services.adminService;

import com.sukhdev.rems.dto.PropertyDto;
import com.sukhdev.rems.entity.Property;
import com.sukhdev.rems.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminServiceImpl implements AdminService{

    private final PropertyRepository propertyRepository;

    @Override
    public boolean postProperty(PropertyDto propertyDto) {
        try{
            Property property = new Property();
            property.setName(propertyDto.getName());
            property.setDescription(propertyDto.getDescription());
            property.setAddress(propertyDto.getAddress());
            property.setPropertyType(propertyDto.getPropertyType());
            property.setLocationType(propertyDto.getLocationType());
            property.setPrice(propertyDto.getPrice());
            property.setImage(propertyDto.getImage() != null ? propertyDto.getImage().getBytes(): null);
            property.setNumberOfRooms(propertyDto.getNumberOfRooms());
            propertyRepository.save(property);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<PropertyDto> getAllProperty() {
        return propertyRepository.findAll().stream().
                map(Property::getPropertyDto).collect(Collectors.toList());
    }
}
