package com.example.ct2.busstop;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class BusstopConverter implements Converter<String, Busstop> {

    private final BusstopRepository busstopRepository;

    @Override
    public Busstop convert(String source) {
        try {
            return busstopRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
