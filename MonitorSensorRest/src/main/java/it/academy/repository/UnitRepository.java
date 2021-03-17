package it.academy.repository;

import it.academy.model.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository <SensorUnit, Long> {
}
