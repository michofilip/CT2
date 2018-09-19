package com.example.ct2.routenode;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class RouteNodeConverter implements Converter<String, RouteNode> {

    private final RouteNodeRepository routeNodeRepository;

    @Override
    public RouteNode convert(String source) {
        try {
            return routeNodeRepository.findOne(Long.parseLong(source));
        } catch (Exception e) {
            return null;
        }
    }
}
