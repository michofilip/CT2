package com.example.ct2.routenode;

import com.example.ct2.busstop.Busstop;
import com.example.ct2.track.Track;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "route_nodes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RouteNode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    private Track track;

    @ManyToOne
    private Busstop busstop;

    @NotNull
    private Double orderNo;

}
