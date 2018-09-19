package com.example.ct2.bus;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class BusConverter implements Converter<String, Bus> {

    private final BusRepository busRepository;

    @Override
    public Bus convert(String source) {
        try {
            return busRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
