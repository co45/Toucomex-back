package com.Toucomex.Importation_Toucomex.excel.controller;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Models.ProduitCommande;
import com.Toucomex.Importation_Toucomex.Repositories.CommandeRepository;
import com.Toucomex.Importation_Toucomex.Repositories.FournisseurRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.listProduitCommandeRepository;
import com.Toucomex.Importation_Toucomex.Services.FournisseurService;
import com.Toucomex.Importation_Toucomex.excel.message.Message;
import com.Toucomex.Importation_Toucomex.excel.services.ExcelFileServices;
import com.Toucomex.Importation_Toucomex.excel.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/excel")
public class UploadFileRestAPIs {

    @Autowired
    ExcelFileServices fileServices;

    List files = new ArrayList();


    @PostMapping("/upload/fournisseur")
    public ResponseEntity uploadfsr(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileServices.storeFsr(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!" +e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/upload/client")
    public ResponseEntity uploadclt(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileServices.storeClt(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

//    @PostMapping("/upload/commande")
//    public ResponseEntity uploadCmd(@RequestParam("file") MultipartFile file) {
//
//        String message = "";
//        try {
//            fileServices.storeCmd(file);
//            files.add(file.getOriginalFilename());
//
//            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } catch (Exception e) {
//            message = "FAIL to upload " + file.getOriginalFilename() + "!" + e;
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//        }
//
//
//    }

    @PostMapping("/upload/produit")
    public ResponseEntity uploadPdt(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileServices.storePdt(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!" + e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/upload/titre")
    public ResponseEntity uploadTitre(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileServices.storeTitre(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!"+e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}