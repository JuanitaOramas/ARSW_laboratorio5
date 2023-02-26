package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class RedundancyFilter implements Filter {
    List <Point> arrayPro;
    List <Point> arrayFinal = new ArrayList<>();
    List<Tuple<Integer,Integer>>myPoints=new ArrayList<>();

    @Override
    public Blueprint applyFilter(Blueprint bp) {
        arrayPro = bp.getPoints();
        for (Point p:  arrayPro){
            Tuple Tupla2 = new Tuple<>(p.getX(),p.getY());
            if (!pointExists(Tupla2)){
                arrayFinal.add(new Point(p.getX(), p.getY()));
            }
        }
        bp.setPoints(arrayFinal);

        return bp;
    }

    public boolean pointExists(Tuple t){
        for (Tuple<Integer,Integer> tuple: myPoints){
//            if (tuple.equals(t)){return true;}
            if (tuple.getElem1() == t.getElem1() && tuple.getElem2() == tuple.o2){return true;}
        }
        return false;
    }

}
