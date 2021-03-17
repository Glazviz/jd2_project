package it.academy.repository;

import it.academy.model.SensorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SensorRepository extends JpaRepository<SensorInfo, Long> {
    List<SensorInfo> findBySensorsNameContaining (String search);
    /*
    Page<SensorInfo> findAll (Pageable pageable);
    Page<SensorInfo> findBySensorsNameContaining (String search, Pageable pageable);
    */
}
