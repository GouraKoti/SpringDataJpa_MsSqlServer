
package com.molcon.henkelJpaWithMsSql.controller;

import com.molcon.henkelJpaWithMsSql.entity.JpaHenkel;
import com.molcon.henkelJpaWithMsSql.service.HenkelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/henkelJpa")
public class controller {
    @Autowired
    HenkelService service;

    //excel file into MS Sql Server
    @PostMapping("/uploadExcelIntoSQL")
    ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return  service.uploadService(file);
    }

    // get API
    @GetMapping("/getFromSql")
    public Iterable<JpaHenkel> getFromSql() {
        return service.getChemicals();
    }

    //get by ID
    @GetMapping("/findFromSql/{mcid}")
    public Optional<JpaHenkel> findById(@PathVariable String mcid) {
        return service.findById(mcid);
    }

    //post Api
    @PostMapping("/insertIntoSql")
    public JpaHenkel insertChemical(@RequestBody JpaHenkel henkel){
        return service.insertOneChemical(henkel);
    }

    //put api
    @PutMapping("/updateIntoSql/{mcid}")
    public ResponseEntity<?> updateChemical(@RequestBody JpaHenkel updateHenkel, @PathVariable String mcid){
        return service.putInSql(updateHenkel, mcid);
    }

    //patch api
    @PatchMapping("/updateIntoSql/{mcid}/{commonName}")
    public ResponseEntity<?> updateName(@PathVariable String mcid, @PathVariable String commonName){
        return service.patchName(mcid, commonName);
    }

    //delete api
    @DeleteMapping("/delete/{mcid}")
    public ResponseEntity<?> updateRec(@PathVariable String mcid){
        return service.deleteRecord(mcid);
    }


}