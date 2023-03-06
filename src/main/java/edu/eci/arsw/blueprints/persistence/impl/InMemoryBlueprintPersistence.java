/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */

@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data

        // Blueprint 1
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("sam", "planito",pts);

        // Blueprint 2
        Point[] pts2=new Point[]{new Point(160, 141),new Point(153, 155)};
        Blueprint bp2=new Blueprint("sam", "planito1",pts2);

        // Blueprint 3
        Point[] pts3=new Point[]{new Point(154, 254),new Point(147, 145)};
        Blueprint bp3=new Blueprint("sam", "planito2",pts3);

        // Blueprint 4
        Point[] pts4=new Point[]{new Point(154, 254),new Point(147, 145)};
        Blueprint bp4=new Blueprint("wilson", "planito3",pts4);



        // HashMap que guarda los Blueprint
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);

        System.out.println("PREGUNTEMOSSSSS: " + blueprints.get(new Tuple<>("juan", "planito")));

        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {

        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {

        Set<Blueprint> myBlueprints = new HashSet<>();

        for (Blueprint b : blueprints.values()) {

            if (b.getAuthor().equals(author)){
                myBlueprints.add(b);
            }


        }

        return myBlueprints;
    }

    @Override
    public Set<Blueprint> getBlueprints() {

        Set<Blueprint> myBlueprints = new HashSet<>();

        for (Blueprint b : blueprints.values()) {
            myBlueprints.add(b);
        }

        return myBlueprints;
    }


}
