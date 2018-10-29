package com.chariot.backend.measurement.service.mapper;

import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import com.chariot.backend.model.Measurement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dariusz Kijania on 7/24/2017.
 */
@Service
public class ModelEntityMapper {

    private final ModelMapper modelMapper  = new ModelMapper();

    public MeasurementEntity toMeasurementEntity(Measurement measurement){
        return modelMapper.map(measurement,MeasurementEntity.class);
    }

    public Measurement toMeasurementModel(MeasurementEntity measurementEntity){
        return modelMapper.map(measurementEntity,Measurement.class);
    }

    public List<Measurement> toMeasurementModelList(List<MeasurementEntity> inputList ){
        return inputList.stream()
                .map(this::toMeasurementModel)
                .collect(Collectors.toList());
    }
}
