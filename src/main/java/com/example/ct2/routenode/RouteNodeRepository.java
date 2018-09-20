package com.example.ct2.routenode;


import com.example.ct2.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteNodeRepository extends JpaRepository<RouteNode, Long> {

    @Query("select ent from RouteNode ent where ent.track = :track order by ent.orderNo")
    List<RouteNode> findByTrack(@Param("track") Track track);

    long countByTrack(Track track);
}
