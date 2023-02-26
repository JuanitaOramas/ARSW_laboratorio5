package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;

import java.util.ArrayList;
import java.util.List;


public class SubsamplingFilter implements Filter {
    List<Point> arrayPro;
    List <Point> arrayFinal = new ArrayList<>();
    int counter = 0;

    @Override
    public Blueprint applyFilter(Blueprint bp) {
        arrayPro = bp.getPoints();
        for (Point p:  arrayPro){
            if (counter % 2 == 0){
                arrayFinal.add(new Point(p.getX(), p.getY()));
            }
            counter+=1;
        }
        bp.setPoints(arrayFinal);

        return bp;
    }




}
