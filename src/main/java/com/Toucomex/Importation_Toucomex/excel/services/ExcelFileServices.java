package com.Toucomex.Importation_Toucomex.excel.services;

import com.Toucomex.Importation_Toucomex.Models.*;
import com.Toucomex.Importation_Toucomex.Repositories.*;
import com.Toucomex.Importation_Toucomex.Services.FournisseurService;
import com.Toucomex.Importation_Toucomex.excel.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelFileServices {


    @Autowired
    FournisseurRepository fr;

    @Autowired
    CommandeRepository cmdr;
    @Autowired
    ProduitRepository pr;
    @Autowired
    titreRepository tr;
    @Autowired
    listProduitCommandeRepository pdtcmdr;

    // Store File Data to Database

    public void storeFsr(MultipartFile file) {
        try {
            List<Fournisseur> lstFsr = ExcelUtils.parseExcelFileFsr(file.getInputStream());
            // Save Fsr to DataBase
            fr.saveAll(lstFsr);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }




    public void storeCmd(MultipartFile file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            //---------------------
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Commande> lstCmd = new ArrayList<Commande>();


            int x;
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();


                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Commande cmd = new Commande();
                ProduitCommande pcmd= new ProduitCommande();
                ProduitCommandeId pci= new ProduitCommandeId();

                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if( cellIndex==0) {
                        cmd.setNumero(currentCell.getStringCellValue());
                    }else if ( cellIndex == 1 ) { // date
                        LocalDate localDate = LocalDate.parse(currentCell.getStringCellValue(), formatter);
                        cmd.setDate_cmd(localDate);
                        cmdr.save(cmd);
                        pci.setCommandeId(cmd.getID_cmd());
                    } else if ( cellIndex == 2)  { // fourniseur
                        cmd.setFsrc(fr.getFournisseurBynom(currentCell.getStringCellValue()));
                    }
                    cellIndex++;
                }

                    lstCmd.add(cmd);

                }


            // Close WorkBook
            workbook.close();


            //---------------------
            // Save cmd + pcmd  to DataBase
            cmdr.saveAll(lstCmd);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

//    public void storeCmd(MultipartFile file) throws IOException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//
//        try {
//            //----------------
//            Workbook workbook = new XSSFWorkbook(file.getInputStream());
//
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rows = sheet.iterator();
//
//            List<Commande> lstCmd = new ArrayList<Commande>();
//            List<ProduitCommande> lstPC= new ArrayList<ProduitCommande>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//
//                ProduitCommande pcmd = new ProduitCommande();
//
//
//                int cellIndex = 0;
//
//
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//                    String ncmd = currentCell.getStringCellValue();
//                    int c = cmdr.CommandeExists(ncmd);
//                    if (cellIndex == 0 && c == 0)//if commande n'existe pas // n cmd
//                    {
//                            Commande cmd = new Commande();
//                            cmd.setNumero(currentCell.getStringCellValue());
//                            pcmd.setCommande(cmd);
//                        } else if (cellIndex == 1 && c == 0) { // date cmd
//                            LocalDate localDate = LocalDate.parse(cellsInRow.next().getStringCellValue(), formatter);
//                            cmd.setDate_cmd(localDate);//Date
//                        } else if (cellIndex == 2 && c == 0) { // fournisseur
//                            cmd.setFsrc(fr.getFournisseurBynom(currentCell.getStringCellValue()));
//                        } else if (cellIndex == 3 && c == 0) { // produit
//
//                            }
//                            lstPC.add(pcmd);
//                            cellIndex++;
//                            }
//                        lstCmd.add(cmd);
//                        }
//
//
//
//
//
//            // Close WorkBook
//            workbook.close();
//            //----------------
//            // Save pcmd to DB
//            pdtcmdr.saveAll(lstPC);
//            // Save cmd to DataBase
//            cmdr.saveAll(lstCmd);
//        } catch (Exception e) {
//            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
//        }
//    }

    public List<Titre> parseExcelFileTitre(InputStream is) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Titre> lstTitre = new ArrayList<Titre>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Titre t = new Titre();


                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (cellIndex == 0) { // numero
                        t.setNum_t(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // code
                        t.setCode(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // date
                        LocalDate localDate = currentCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        t.setDate_t(localDate);
                    } else if (cellIndex == 3) { // montant
                        t.setMontant(currentCell.getNumericCellValue());
                    }else if (cellIndex == 4) { // fsr
                    t.setFournisseurTitre(fr.getFournisseurBynom(currentCell.getStringCellValue()));
                    }
                    cellIndex++;
                }
                lstTitre.add(t);
            }
            // Close WorkBook
            workbook.close();

            return lstTitre;

        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public void storeTitre(MultipartFile file) {
        try {
            List<Titre> lstT = parseExcelFileTitre(file.getInputStream());
            // Save Pdt to DataBase
            tr.saveAll(lstT);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public void storePdt(MultipartFile file) {
        try {
            List<Produit> lstPdt = ExcelUtils.parseExcelFilePdt(file.getInputStream());
            // Save Pdt to DataBase
            pr.saveAll(lstPdt);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    // Load Data to Excel File
    /*
    public ByteArrayInputStream loadFile() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        try {
            ByteArrayInputStream in = ExcelUtils.customersToExcel(customers);
            return in;
        } catch (IOException e) {}

        return null;
    }*/
}

