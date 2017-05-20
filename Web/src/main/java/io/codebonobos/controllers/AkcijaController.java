package io.codebonobos.controllers;

import io.codebonobos.daos.AkcijaDao;
import io.codebonobos.entities.Akcija;
import io.codebonobos.utils.IdWrapper;
import io.codebonobos.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by afilakovic on 20.05.17..
 */

@RestController
@RequestMapping("/api/akcije")
public class AkcijaController {
    @Autowired
    private AkcijaDao akcijaDao;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Akcija> list = null;
        String message = null;

        try {
            list = akcijaDao.getAll();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseWrapper<>(list, message), HttpStatus.OK);
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity<?> getActive() {
        List<Akcija> list;
        String message = null;

        try {
            list = akcijaDao.getActive();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseWrapper<>(list, message), HttpStatus.OK);
    }

//    @RequestMapping(value = "/inactive", method = RequestMethod.GET)
//    public ResponseWrapper<List<Akcija>> getInactive() {
//        List<Akcija> list = null;
//        String message = null;
//
//        return new ResponseWrapper<>(list, message);
//    }

//    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
//    public ResponseWrapper<Akcija> getActionByUserId(@PathVariable String id) {
//        Akcija akcija = null;
//        String message = null;
//
//        return new ResponseWrapper<>(akcija, message);
//    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity addAction(@RequestBody Akcija action) {
        IdWrapper id;
        String message = null;
        try {
            id = new IdWrapper(akcijaDao.saveAction(action));
        } catch (Exception e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseWrapper<>(id, message), HttpStatus.OK);
    }

    @RequestMapping(value = "end", method = RequestMethod.GET)
    public void finishAction(@RequestParam String actionId) {
        akcijaDao.finishAction(actionId);
    }
}