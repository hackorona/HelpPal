package com.helpal.model.coord;

import java.util.List;

public interface CoordFilterRepository {
    List<Coord> findAllCoordsWithinDistance(Coord center, Double distance);
}
