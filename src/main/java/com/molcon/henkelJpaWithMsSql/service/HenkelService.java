
package com.molcon.henkelJpaWithMsSql.service;


import com.molcon.henkelJpaWithMsSql.entity.JpaHenkel;
import com.molcon.henkelJpaWithMsSql.repo.JpaRepo;
import com.molcon.henkelJpaWithMsSql.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class HenkelService {
    @Autowired
    JpaRepo jpaRepo;


    public ResponseEntity<String> uploadService(MultipartFile file) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        excelReader.readExcel(file, jpaRepo);
        return new ResponseEntity<>("File Uploaded successfully into SQL", HttpStatus.OK);
    }



    public Iterable<JpaHenkel> getChemicals() {
        return jpaRepo.findAll();
    }


    public Optional<JpaHenkel> findById(String mcid) {
        return jpaRepo.findById(mcid);
    }

    public JpaHenkel insertOneChemical(JpaHenkel henkel) {
        return jpaRepo.save(henkel);
    }

    public ResponseEntity<?> putInSql(JpaHenkel updateHenkel, String mcid) {
        Optional<JpaHenkel> jpaHenkel = jpaRepo.findById(mcid);
        jpaHenkel.ifPresent(m -> {
            m.setCommonName(updateHenkel.getCommonName());
            m.setDescription(updateHenkel.getDescription());
            m.setSapCs(updateHenkel.getSapCs());
            jpaRepo.save(m);
        });
        return new ResponseEntity<>("Updated Chemical id " + mcid, HttpStatus.OK);
    }

    public ResponseEntity<?> patchName(String mcid, String commonName) {
        JpaHenkel jpaHenkel = jpaRepo.findById(mcid).get();
        jpaHenkel.setCommonName(commonName);
        return new ResponseEntity<JpaHenkel>(jpaRepo.save(jpaHenkel), HttpStatus.OK);
    }


    public ResponseEntity<?> deleteRecord(String mcid) {
        JpaHenkel jpaHenkel = jpaRepo.findById(mcid).get();
        jpaRepo.delete(jpaHenkel);
        return new ResponseEntity<>("Deleted Chemical id " + mcid, HttpStatus.OK);
    }
}