package com.sukhdev.rems.repository;

import com.sukhdev.rems.entity.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
}
