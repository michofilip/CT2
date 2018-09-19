package com.example.ct2.schedule;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class ScheduleConverter implements Converter<String, Schedule> {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule convert(String source) {
        try {
            return scheduleRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
