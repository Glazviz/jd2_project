package it.academy.controller;

import it.academy.model.SensorInfo;
import it.academy.model.SensorType;
import it.academy.model.SensorUnit;
import it.academy.repository.SensorRepository;
import it.academy.repository.TypeRepository;
import it.academy.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class SensorController {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    UnitRepository unitRepository;

        @GetMapping("/sensors")
    public ResponseEntity<Map<String, Object>> getAllSensors(@RequestParam(required = false) String search,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int pageSize) {
            System.out.println(page);
            try {
            List<SensorInfo> sensorsInfo = new ArrayList<>();
            Pageable paging = PageRequest.of(page, pageSize);

            Page<SensorInfo> sensorInfoPage;
            if (search == null)
                sensorInfoPage = sensorRepository.findAll(paging);
            else
                sensorInfoPage = sensorRepository.findBySensorsNameContaining(search, paging);

            sensorsInfo = sensorInfoPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("sensors", sensorsInfo);
            response.put("currentPage", sensorInfoPage.getNumber());
            response.put("totalItem", sensorInfoPage.getTotalElements());
            response.put("totalPages", sensorInfoPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sensors/{id}")
    public ResponseEntity<SensorInfo> getSensorById(@PathVariable("id") long id) {
        Optional<SensorInfo> sensorData = sensorRepository.findById(id);
        return sensorData.map(sensorInfo -> new ResponseEntity<>(sensorInfo, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/sensors")
    public ResponseEntity<SensorInfo> createSensor(@RequestBody SensorInfo sensorInfo) {
        SensorInfo sensorInfoCreate = sensorRepository.save(new SensorInfo(sensorInfo.getSensorsName(),
                sensorInfo.getSensorsModel(),
                sensorInfo.getSensorsRangeFrom(),
                sensorInfo.getSensorsRangeTo(),
                sensorInfo.getSensorsLocation(),
                sensorInfo.getDescriptions(),
                sensorInfo.getSensorType(),
                sensorInfo.getSensorUnit()));
        return new ResponseEntity<>(sensorInfoCreate, HttpStatus.CREATED);
    }

    @PutMapping("/sensors/{id}")
    public ResponseEntity<SensorInfo> updateSensor(@PathVariable("id") long id, @RequestBody SensorInfo sensorInfo) {
        Optional<SensorInfo> sensorData = sensorRepository.findById(id);

        if (sensorData.isPresent()) {
            SensorInfo sensorUpdate = sensorData.get();
            sensorUpdate.setSensorsName(sensorInfo.getSensorsName());
            sensorUpdate.setSensorsModel(sensorInfo.getSensorsModel());
            sensorUpdate.setSensorsRangeFrom(sensorInfo.getSensorsRangeFrom());
            sensorUpdate.setSensorsRangeTo(sensorInfo.getSensorsRangeTo());
            sensorUpdate.setSensorsLocation(sensorInfo.getSensorsLocation());
            sensorUpdate.setDescriptions(sensorInfo.getDescriptions());
            sensorUpdate.setSensorUnit(sensorInfo.getSensorUnit());
            sensorUpdate.setSensorType(sensorInfo.getSensorType());
            return new ResponseEntity<>(sensorRepository.save(sensorUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sensors/{id}")
    public ResponseEntity<HttpStatus> deleteSensor(@PathVariable("id") long id) {
        try {
            sensorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type")
    public List<SensorType> type() {
        return typeRepository.findAll();
    }


    @GetMapping("/unit")
    public List<SensorUnit> unit() {
        return unitRepository.findAll();
    }
}
