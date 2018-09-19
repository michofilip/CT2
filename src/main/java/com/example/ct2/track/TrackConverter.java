package com.example.ct2.track;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class TrackConverter implements Converter<String, Track> {

    private final TrackRepository trackRepository;

    @Override
    public Track convert(String source) {
        try {
            return trackRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
