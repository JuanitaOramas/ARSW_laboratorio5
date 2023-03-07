/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */


@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bs;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> allBlueprints() {
        try {
            ObjectMapper objectMapperSetToJSON = new ObjectMapper();
            // convertir a json
            String json = objectMapperSetToJSON.writeValueAsString(bs.getAllBlueprints());
            return new ResponseEntity<>(json, HttpStatus.ACCEPTED);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createBluePrint(@RequestBody Blueprint bp){
        try {
            bs.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error create Blueprint",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{author}/{bpname}")
    public ResponseEntity<?> updateBluePrint(@RequestBody Blueprint bp, @PathVariable String author, @PathVariable String bpname){
        try {
            bs.updateBlueprint(bp, author, bpname);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error create Blueprint",HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/{author}") // localhost/blueprints/sam
    public ResponseEntity<?> allBlueprintsByAthor(@PathVariable String author){

        try {
            if(bs.getBlueprintsByAuthor(author).isEmpty()){
                throw new ResourceNotFoundException();
            }
            ObjectMapper objectMapperSetToJSON = new ObjectMapper();
            // convertir a json
            String json = objectMapperSetToJSON.writeValueAsString(bs.getBlueprintsByAuthor(author));
            return new ResponseEntity<>(json, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{author}/{bpname}") // localhost/blueprints/sam/
    public ResponseEntity<?> allBlueprintsByAthor(@PathVariable String author, @PathVariable String bpname){

        try {
            if(bs.getBlueprint(author,bpname ) == null){
                throw new ResourceNotFoundException();
            }
            ObjectMapper objectMapperSetToJSON = new ObjectMapper();
            // convertir a json
            String json = objectMapperSetToJSON.writeValueAsString(bs.getBlueprint(author,bpname ));
            return new ResponseEntity<>(json, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }

    }

}

