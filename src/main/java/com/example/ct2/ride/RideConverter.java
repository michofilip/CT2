package com.example.ct2.ride;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class RideConverter implements Converter<String, Ride> {

    private final RideRepository rideRepository;

    @Override
    public Ride convert(String source) {
        try {
            return rideRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
