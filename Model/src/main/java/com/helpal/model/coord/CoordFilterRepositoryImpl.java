package com.helpal.model.coord;

import com.helpal.geo.DistanceUtil;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

public class CoordFilterRepositoryImpl implements CoordFilterRepository {

    @Lazy
    @Autowired
    private CoordRepository coordRepository;

    @Autowired
    private DistanceUtil distanceUtil;

    @Override
    public List<Coord> findAllCoordsWithinDistance(Coord center, Double distance) {
        // Change this - quickly!!
        List<Coord> retVal = null;
        List<Coord> allCoords = coordRepository.findAll();
        Point centerPoint = SpatialContext.GEO.getShapeFactory().pointXY(center.getX(), center.getY());

        for (Coord coord : allCoords) {
            if (coord.equals(center)){
                continue;
            }

            Point curPoint = SpatialContext.GEO.getShapeFactory().pointXY(coord.getX(), coord.getY());

            if (distanceUtil.isPointWithinDistance(centerPoint, curPoint, distance)) {
                if (retVal == null){
                    retVal = new ArrayList<>();
                }

                retVal.add(coord);
            }
        }

        return retVal;
    }
}
