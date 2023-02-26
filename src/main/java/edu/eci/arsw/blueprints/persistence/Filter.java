package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

public interface Filter {

    Blueprint  applyFilter(Blueprint bp);

}
