package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.util.Collection;

public record AndArea(Collection<Area> areas) implements Area {

    public void addArea(Area area) {
        areas.add(area);
    }

    @Override
    public boolean hit(Point point) {
        return areas.stream().allMatch(area -> area.hit(point));
    }
}
