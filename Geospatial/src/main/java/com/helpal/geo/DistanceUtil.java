package com.helpal.geo;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceCalculator;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceUtil {

//    public List<Point> getAllPointsInRadius(Point center, List<? extends Point> coordList, Double distance) {
//        List<Point> retVal = null;
//
//        DistanceCalculator distCalc = SpatialContext.GEO.getDistCalc();
//        Iterator<? extends Point> iterator = coordList.iterator();
//
//        while (iterator.hasNext()) {
//            Point next = iterator.next();
//
//            if (distCalc.within(center, next.getX(), next.getY(), distance)) {
//                if (retVal == null) {
//                    retVal = new ArrayList<>();
//                }
//
//                retVal.add(next);
//            }
//        }
//
//        return retVal;
//    }

    public Boolean isPointWithinDistance(Point a, Point b, Double distance) {
        DistanceCalculator distCalc = SpatialContext.GEO.getDistCalc();


        return (distCalc.distance(a, b) * DistanceUtils.DEG_TO_KM <= distance);
    }
}
