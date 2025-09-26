package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.util.Collection;

public class OrArea implements Area {
    private final Collection<Area> areas;

    public OrArea(Collection<Area> areas) {
        this.areas = areas;
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    @Override
    public boolean hit(Point point) {
        return areas.stream().anyMatch(area -> area.hit(point));
    }
}
