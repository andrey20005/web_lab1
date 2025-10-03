package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.util.Collection;

public record OrArea(Collection<Area> areas) implements Area {
    public void addArea(Area area) {
        areas.add(area);
    }

    @Override
    public boolean hit(Point point) {
        return areas.stream().anyMatch(area -> area.hit(point));
    }
}
