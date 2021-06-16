
package com.Toucomex.Importation_Toucomex.excel.utils;


import com.Toucomex.Importation_Toucomex.Models.*;
import com.Toucomex.Importation_Toucomex.Repositories.CommandeRepository;
import com.Toucomex.Importation_Toucomex.Repositories.FournisseurRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.listProduitCommandeRepository;
import com.Toucomex.Importation_Toucomex.Services.FournisseurService;
import com.Toucomex.Importation_Toucomex.Services.ProduitService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {



    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    public static List<Client> parseExcelFileClient(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Client> lstClt = new ArrayList<Client>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Client clt = new Client();


                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (cellIndex == 0) { // code
                        clt.setCode(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // libelle
                        clt.setLibelle(currentCell.getStringCellValue());
                    }
                    cellIndex++;
                }

                lstClt.add(clt);
            }
            // Close WorkBook
            workbook.close();

            return lstClt;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }

    }




//    public static List<Commande> parseExcelFileCmd(InputStream is) {
//
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//        Fournisseur fr = new Fournisseur();
//
//
//            Workbook workbook = new XSSFWorkbook(is);
//
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rows = sheet.iterator();
//            List<ProduitCommande> lpc = new ArrayList<ProduitCommande>();
//            List<Commande> lstCmd = new ArrayList<Commande>();
//            List<Produit> lstPdt = new ArrayList<Produit>();
//
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                Commande cmd = new Commande();
//                Produit produit = new Produit();
//                ProduitCommande pc = new ProduitCommande();
//
//                int cellIndex = 0;
//
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    if (cellIndex == 0) {
//                        Boolean exists =false;
//                        if (exists == false) {
//                            String n = currentCell.getStringCellValue();
//                            cmd.setNumero(currentCell.getStringCellValue());// n°cmd
//                            LocalDate localDate = LocalDate.parse(cellsInRow.next().getStringCellValue(), formatter);
//                            cmd.setDate_cmd(localDate);//Date
//                            //toFix
//                            Fournisseur fsr = fs.findByName("MEDTRONIC");
//                            System.out.println(fsr);
//                            cmd.setFsrc(fsr);//fournisseur
//                            while (rows.hasNext()) {
//                                while (cellsInRow.hasNext()) {
//                                    currentCell = cellsInRow.next();
//                                    if (cellIndex == 0 && currentCell.getStringCellValue() == n) {
//                                        currentCell = cellsInRow.next();
//                                        if (cellIndex == 3) { // code pdt
//                                            pc.setCommande(cmd);
//                                            //toFix
//                                            produit=pr.findProduitsByReference(currentCell.getStringCellValue());
//                                            pc.setProduit(produit);
//
//                                        } else if (cellIndex == 5) { // quantite
//                                            pc.setQuantite(Integer.parseInt(currentCell.getStringCellValue()));
//
//                                        } else if (cellIndex == 6) { // prix devise
//                                            pc.setPrix(Float.parseFloat(currentCell.getStringCellValue()));
//                                        }
//                                    }
//                                    lpc.add(pc);
//                                }
//                                pcr.saveAll(lpc);
//                            }
//                        }
//                        cellIndex++;
//                    }
//                    lstCmd.add(cmd);
//                }
//            }
//
//             Close WorkBook
//            workbook.close();
//
//            return lstCmd;
//
//    }

    public static List<Produit> parseExcelFilePdt(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Produit> lstPdt = new ArrayList<Produit>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Produit pdt = new Produit();


                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (cellIndex == 0) { // REF PRODUIT
                        pdt.setReference(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // LIBELLE
                        pdt.setLibelle_pdt(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // FAMILLE
                        pdt.setFamille(currentCell.getStringCellValue());
                    } else if (cellIndex == 3) { // GAMME
                        pdt.setGamme(currentCell.getStringCellValue());
                    } else if (cellIndex == 4) { // THERAPIE
                        pdt.setTherapie(currentCell.getStringCellValue());
                    } else if (cellIndex == 5) { // NOM COMMERCIAL
                        pdt.setNom_commercial(currentCell.getStringCellValue());
                    } else if (cellIndex == 6) { // CODENGP
                        pdt.setCode_ngp(currentCell.getStringCellValue());
                    } else if (cellIndex == 7) { // DESIGNATION
                        pdt.setDesignation(currentCell.getStringCellValue());
                    }
                    cellIndex++;
                }

                pdt.setProduitCommandes(null);
                lstPdt.add(pdt);
            }

            // Close WorkBook
            workbook.close();

            return lstPdt;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static List<Fournisseur> parseExcelFileFsr(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Fournisseur> lstFsr = new ArrayList<Fournisseur>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Fournisseur fr = new Fournisseur();


                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (cellIndex == 0) { // Code
                        fr.setCode_fsr(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // Libelle
                        fr.setLibelle_fsr(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // Addresse
                        fr.setAdresse_fsr(currentCell.getStringCellValue());
                    } else if (cellIndex == 3) { // Swift
                        fr.setSwift(currentCell.getStringCellValue());
                    } else if (cellIndex == 4) { // nom_fsr
                        fr.setNom(currentCell.getStringCellValue());
                    }
                    cellIndex++;
                }
                fr.setCommandeFournisseur(null);
                fr.setFournisseurTitre(null);

                lstFsr.add(fr);
            }

            // Close WorkBook
            workbook.close();

            return lstFsr;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static boolean isExcelFile(MultipartFile file) {

        if (!EXCELTYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

}
