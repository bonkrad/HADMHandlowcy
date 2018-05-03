package pl.radekbonk.HADMHandlowcy.service;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.radekbonk.HADMHandlowcy.HadmHandlowcyApplication;
import pl.radekbonk.HADMHandlowcy.model.*;
import pl.radekbonk.HADMHandlowcy.repository.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class ExcelService {
    @Value(value = "classpath:static/raport.xlsx")
    private Resource resource;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MiscCallCenterRepository miscCallCenterRepository;
    @Autowired
    private MiscDeliveryRepository miscDeliveryRepository;
    @Autowired
    private MiscDrasRepository miscDrasRepository;
    @Autowired
    private MiscOrdersRepository miscOrdersRepository;
    @Autowired
    private NewClientMailRepository newClientMailRepository;
    @Autowired
    private NewClientSalonRepository newClientSalonRepository;
    @Autowired
    private NewClientServiceRepository newClientServiceRepository;
    @Autowired
    private NewClientTelephoneRepository newClientTelephoneRepository;
    @Autowired
    private OldClientMailRepository oldClientMailRepository;
    @Autowired
    private OldClientMeetingRepository oldClientMeetingRepository;
    @Autowired
    private OldClientTelephoneRepository oldClientTelephoneRepository;


    public ExcelService() {

    }

    private int[] serviceResult = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] serviceRating = {0, 0, 0, 0, 0};

    private int[] sellerResult = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] sellerRating = {0, 0, 0, 0, 0};

    private int[] sellerServiceResult = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] sellerServiceRating = {0, 0, 0, 0, 0};

    private int[] howResult = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public String generateWeeklyReport(int week, int year) throws IOException {
        String realPathToUploads = HadmHandlowcyApplication.getUploadPath();
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdir();
        }
        String fileName = getReportNameWeekly(week, year);
        FileOutputStream fileOutputStream = new FileOutputStream(realPathToUploads + fileName);

        List<User> userList = userRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        for (User user : userList) {
            String sheetName = user.getName() + user.getSurname();
            workbook.createSheet(sheetName);
            Sheet sheet = workbook.getSheet(sheetName);

            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);

            int rowNum = 0;

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Salon");
            row.getCell(0).setCellStyle(cellStyle);

            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy zaproponowano odkup auta?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy zaproponowano finansowanie?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Poziom zainteresowania?");
            row.getCell(4).setCellStyle(cellStyle);
            row.createCell(5).setCellValue("Skąd klient?");
            row.getCell(5).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientSalon> newClientSalonList = newClientSalonRepository.findByUserAndWeekAndYear(user, week, year);
            for (NewClientSalon newClientSalon : newClientSalonList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientSalon.isQuotation());
                row.createCell(1).setCellValue(newClientSalon.isCarRepurchase());
                row.createCell(2).setCellValue(newClientSalon.isFinance());
                row.createCell(3).setCellValue(newClientSalon.isStandard());
                row.createCell(4).setCellValue(newClientSalon.getInterest());
                row.createCell(5).setCellValue(newClientSalon.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Serwis");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy zaproponowano odkup auta?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy zaproponowano finansowanie?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Poziom zainteresowania?");
            row.getCell(4).setCellStyle(cellStyle);
            row.createCell(5).setCellValue("Skąd klient?");
            row.getCell(5).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientService> newClientServiceList = newClientServiceRepository.findByUserAndWeekAndYear(user, week, year);
            for (NewClientService newClientService : newClientServiceList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientService.isQuotation());
                row.createCell(1).setCellValue(newClientService.isCarRepurchase());
                row.createCell(2).setCellValue(newClientService.isFinance());
                row.createCell(3).setCellValue(newClientService.isStandard());
                row.createCell(4).setCellValue(newClientService.getInterest());
                row.createCell(5).setCellValue(newClientService.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Telefon");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy umówiono spotkanie?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Poziom zainteresowania?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Skąd klient?");
            row.getCell(4).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientTelephone> newClientTelephoneList = newClientTelephoneRepository.findByUserAndWeekAndYear(user, week, year);
            for (NewClientTelephone newClientTelephone : newClientTelephoneList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientTelephone.isQuotation());
                row.createCell(1).setCellValue(newClientTelephone.isMeeting());
                row.createCell(2).setCellValue(newClientTelephone.isStandard());
                row.createCell(3).setCellValue(newClientTelephone.getInterest());
                row.createCell(4).setCellValue(newClientTelephone.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Mail");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy wykonano odpowiedź w ciągu 4 godzin?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Poziom zainteresowania?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Skąd klient?");
            row.getCell(3).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientMail> newClientMailList = newClientMailRepository.findByUserAndWeekAndYear(user, week, year);
            for (NewClientMail newClientMail : newClientMailList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientMail.isResponse());
                row.createCell(1).setCellValue(newClientMail.isStandard());
                row.createCell(2).setCellValue(newClientMail.getInterest());
                row.createCell(3).setCellValue(newClientMail.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Spotkanie");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientMeeting> oldClientMeetingList = oldClientMeetingRepository.findByUserAndWeekAndYear(user, week, year);
            row.createCell(0).setCellValue(oldClientMeetingList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Telefon");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientTelephone> oldClientTelephoneList = oldClientTelephoneRepository.findByUserAndWeekAndYear(user, week, year);
            row.createCell(0).setCellValue(oldClientTelephoneList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Mail");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientMail> oldClientMailList = oldClientMailRepository.findByUserAndWeekAndYear(user, week, year);
            row.createCell(0).setCellValue(oldClientMailList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Call Center");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscCallCenter> miscCallCenterList = miscCallCenterRepository.findByUserAndWeekAndYear(user, week, year);
            int callCenterQuantity = 0;
            for (MiscCallCenter miscCallCenter : miscCallCenterList) {
                callCenterQuantity += miscCallCenter.getQuantity();
            }
            row.createCell(0).setCellValue(callCenterQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("DRAS");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscDras> miscDrasList = miscDrasRepository.findByUserAndWeekAndYear(user, week, year);
            int drasQuantity = 0;
            for (MiscDras miscDras : miscDrasList) {
                drasQuantity += miscDras.getQuantity();
            }
            row.createCell(0).setCellValue(drasQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Zamówienia");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscOrders> miscOrdersList = miscOrdersRepository.findByUserAndWeekAndYear(user, week, year);
            int ordersQuantity = 0;
            for (MiscOrders miscOrders : miscOrdersList) {
                ordersQuantity += miscOrders.getQuantity();
            }
            row.createCell(0).setCellValue(ordersQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Wydania");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscDelivery> miscDeliveryList = miscDeliveryRepository.findByUserAndWeekAndYear(user, week, year);
            int deliveryQuantity = 0;
            for (MiscDelivery miscDelivery : miscDeliveryList) {
                deliveryQuantity += miscDelivery.getQuantity();
            }
            row.createCell(0).setCellValue(deliveryQuantity);
            /*rowNum++;
            rowNum++;*/
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

        }

        try (FileOutputStream outputStream = new FileOutputStream(realPathToUploads + fileName)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileOutputStream.close();
        return realPathToUploads + fileName;

    }

    public String generateMonthlyReport(int month, int year) throws IOException {
        String realPathToUploads = HadmHandlowcyApplication.getUploadPath();
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdir();
        }
        String fileName = getReportNameMonthly(month, year);
        FileOutputStream fileOutputStream = new FileOutputStream(realPathToUploads + fileName);

        List<User> userList = userRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        for (User user : userList) {
            String sheetName = user.getName() + user.getSurname();
            workbook.createSheet(sheetName);
            Sheet sheet = workbook.getSheet(sheetName);

            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);

            int rowNum = 0;

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Salon");
            row.getCell(0).setCellStyle(cellStyle);

            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy zaproponowano odkup auta?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy zaproponowano finansowanie?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Poziom zainteresowania?");
            row.getCell(4).setCellStyle(cellStyle);
            row.createCell(5).setCellValue("Skąd klient?");
            row.getCell(5).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientSalon> newClientSalonList = newClientSalonRepository.findByUserAndMonthAndYear(user, month, year);
            for (NewClientSalon newClientSalon : newClientSalonList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientSalon.isQuotation());
                row.createCell(1).setCellValue(newClientSalon.isCarRepurchase());
                row.createCell(2).setCellValue(newClientSalon.isFinance());
                row.createCell(3).setCellValue(newClientSalon.isStandard());
                row.createCell(4).setCellValue(newClientSalon.getInterest());
                row.createCell(5).setCellValue(newClientSalon.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Serwis");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy zaproponowano odkup auta?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy zaproponowano finansowanie?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Poziom zainteresowania?");
            row.getCell(4).setCellStyle(cellStyle);
            row.createCell(5).setCellValue("Skąd klient?");
            row.getCell(5).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientService> newClientServiceList = newClientServiceRepository.findByUserAndMonthAndYear(user, month, year);
            for (NewClientService newClientService : newClientServiceList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientService.isQuotation());
                row.createCell(1).setCellValue(newClientService.isCarRepurchase());
                row.createCell(2).setCellValue(newClientService.isFinance());
                row.createCell(3).setCellValue(newClientService.isStandard());
                row.createCell(4).setCellValue(newClientService.getInterest());
                row.createCell(5).setCellValue(newClientService.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Telefon");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy przedstawiono ofertę?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy umówiono spotkanie?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Poziom zainteresowania?");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue("Skąd klient?");
            row.getCell(4).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientTelephone> newClientTelephoneList = newClientTelephoneRepository.findByUserAndMonthAndYear(user, month, year);
            for (NewClientTelephone newClientTelephone : newClientTelephoneList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientTelephone.isQuotation());
                row.createCell(1).setCellValue(newClientTelephone.isMeeting());
                row.createCell(2).setCellValue(newClientTelephone.isStandard());
                row.createCell(3).setCellValue(newClientTelephone.getInterest());
                row.createCell(4).setCellValue(newClientTelephone.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Nowy Klient Mail");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Czy wykonano odpowiedź w ciągu 4 godzin?");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue("Czy wykonany pełen standard TK?");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue("Poziom zainteresowania?");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue("Skąd klient?");
            row.getCell(3).setCellStyle(cellStyle);
            rowNum++;
            List<NewClientMail> newClientMailList = newClientMailRepository.findByUserAndMonthAndYear(user, month, year);
            for (NewClientMail newClientMail : newClientMailList) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(newClientMail.isResponse());
                row.createCell(1).setCellValue(newClientMail.isStandard());
                row.createCell(2).setCellValue(newClientMail.getInterest());
                row.createCell(3).setCellValue(newClientMail.getMarketing());
                rowNum++;
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Spotkanie");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientMeeting> oldClientMeetingList = oldClientMeetingRepository.findByUserAndMonthAndYear(user, month, year);
            row.createCell(0).setCellValue(oldClientMeetingList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Telefon");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientTelephone> oldClientTelephoneList = oldClientTelephoneRepository.findByUserAndMonthAndYear(user, month, year);
            row.createCell(0).setCellValue(oldClientTelephoneList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Własny Klient Mail");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<OldClientMail> oldClientMailList = oldClientMailRepository.findByUserAndMonthAndYear(user, month, year);
            row.createCell(0).setCellValue(oldClientMailList.size());
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Call Center");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscCallCenter> miscCallCenterList = miscCallCenterRepository.findByUserAndMonthAndYear(user, month, year);
            int callCenterQuantity = 0;
            for (MiscCallCenter miscCallCenter : miscCallCenterList) {
                callCenterQuantity += miscCallCenter.getQuantity();
            }
            row.createCell(0).setCellValue(callCenterQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("DRAS");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscDras> miscDrasList = miscDrasRepository.findByUserAndMonthAndYear(user, month, year);
            int drasQuantity = 0;
            for (MiscDras miscDras : miscDrasList) {
                drasQuantity += miscDras.getQuantity();
            }
            row.createCell(0).setCellValue(drasQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Zamówienia");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscOrders> miscOrdersList = miscOrdersRepository.findByUserAndMonthAndYear(user, month, year);
            int ordersQuantity = 0;
            for (MiscOrders miscOrders : miscOrdersList) {
                ordersQuantity += miscOrders.getQuantity();
            }
            row.createCell(0).setCellValue(ordersQuantity);
            rowNum++;
            rowNum++;

            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Wydania");
            row.getCell(0).setCellStyle(cellStyle);
            row.setRowStyle(cellStyle);
            rowNum++;
            row = sheet.createRow(rowNum);
            List<MiscDelivery> miscDeliveryList = miscDeliveryRepository.findByUserAndMonthAndYear(user, month, year);
            int deliveryQuantity = 0;
            for (MiscDelivery miscDelivery : miscDeliveryList) {
                deliveryQuantity += miscDelivery.getQuantity();
            }
            row.createCell(0).setCellValue(deliveryQuantity);
            /*rowNum++;
            rowNum++;*/

        }

        try (FileOutputStream outputStream = new FileOutputStream(realPathToUploads + fileName)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileOutputStream.close();
        return realPathToUploads + fileName;
    }

    public String getReportNameWeekly(int week, int year) {
        return "RaportHandlowcy-Tydzien-" + week + "-Rok-" + year + ".xlsx";
    }

    public String getReportNameMonthly(int month, int year) {
        return "RaportHandlowcy-Miesiac-" + month + "-Rok-" + year + ".xlsx";
    }

    public String generateExcel(String data) {
        JSONObject jsonArray = new JSONObject(data);
        System.out.println(jsonArray);
        Set<String> strings = jsonArray.keySet();
        for (String key : strings) {
            //System.out.println(key);
            //System.out.println(jsonArray.getJSONObject(key));
            JSONObject object = jsonArray.getJSONObject(key);
            //System.out.println(object.get("how"));
            addToResults(object);
        }
        System.out.println("\nService Result");
        for (int i : serviceResult) {
            System.out.print(i);
        }
        System.out.println("");
        for (int i : serviceRating) {
            System.out.print(i);
        }

        System.out.println("\n\nSeller Result");
        for (int i : sellerResult) {
            System.out.print(i);
        }
        System.out.println("");
        for (int i : sellerRating) {
            System.out.print(i);
        }

        System.out.println("\n\nSeller Service Result");
        for (int i : sellerServiceResult) {
            System.out.print(i);
        }
        System.out.println("");
        for (int i : sellerServiceRating) {
            System.out.print(i);
        }

        System.out.println("\n\nHow Result");
        for (int i : howResult) {
            System.out.print(i);
        }
        System.out.println("");

        ServletContext context = request.getServletContext();
        String realPathToUploads = context.getRealPath("/files/");

        if (!(new File(realPathToUploads)).exists()) {
            (new File(realPathToUploads)).mkdir();
        }

        String fileName = UUID.randomUUID() + ".xlsx";

        try {
            URL url = new URL("http://localhost:8090/raport.xlsx");
            File f = new File(context.getRealPath("/files/") + "temp.xlsx");
            FileUtils.copyURLToFile(url, f);
            FileInputStream file = new FileInputStream(f);
            Workbook wb = new XSSFWorkbook(file);
            Sheet sheet = wb.getSheet("Raport");
			/*Calendar calendar = Calendar.getInstance();
			String date = calendar.getTime().toString();*/
            //sheet.getRow(0).getCell(0).setCellValue(date);
            for (int i = 0; i < 10; i++) {
                sheet.getRow(i + 2).getCell(1).setCellValue(serviceResult[i]);
            }
            for (int i = 0; i < 5; i++) {
                sheet.getRow(i + 13).getCell(1).setCellValue(serviceRating[i]);
            }

            for (int i = 0; i < 10; i++) {
                sheet.getRow(i + 2).getCell(3).setCellValue(sellerResult[i]);
            }
            for (int i = 0; i < 5; i++) {
                sheet.getRow(i + 13).getCell(3).setCellValue(sellerRating[i]);
            }

            for (int i = 0; i < 10; i++) {
                sheet.getRow(i + 2).getCell(5).setCellValue(sellerServiceResult[i]);
            }
            for (int i = 0; i < 5; i++) {
                sheet.getRow(i + 13).getCell(5).setCellValue(sellerServiceRating[i]);
            }

            for (int i = 0; i < 10; i++) {
                sheet.getRow(i + 2).getCell(7).setCellValue(howResult[i]);
            }
            XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
            FileOutputStream outputStream = new FileOutputStream(realPathToUploads + fileName);
            wb.write(outputStream);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		/*Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Raport Ankiety");
		
		try (FileOutputStream outputStream = new FileOutputStream(realPathToUploads + fileName)) {
			try {
				wb.write(outputStream);
				wb.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

        System.out.println(realPathToUploads + fileName);
        for (int i = 0; i < 10; i++) {
            serviceResult[i] = 0;
            sellerResult[i] = 0;
            sellerServiceResult[i] = 0;
            howResult[i] = 0;
        }
        for (int i = 0; i < 5; i++) {
            serviceRating[i] = 0;
            sellerRating[i] = 0;
            sellerServiceRating[i] = 0;
        }
        return fileName;
    }

    private void addToResults(JSONObject object) {
        switch ((String) object.get("what")) {
            case "service":
                addToResults(serviceResult, serviceRating, object);
                break;
            case "seller":
                addToResults(sellerResult, sellerRating, object);
                break;
            case "sellerService":
                addToResults(sellerServiceResult, sellerServiceRating, object);
                break;
            case "0":
                addToResults(howResult, object);
                break;
        }
    }

    private void addToResults(int[] result, int[] rating, JSONObject object) {
        addToResults(result, object);

        switch ((int) object.get("rating")) {
            case 1:
                rating[0] = rating[0] + 1;
                break;
            case 2:
                rating[1] = rating[1] + 1;
                break;
            case 3:
                rating[2] = rating[2] + 1;
                break;
            case 4:
                rating[3] = rating[3] + 1;
                break;
            case 5:
                rating[4] = rating[4] + 1;
                break;
        }
    }

    private void addToResults(int[] result, JSONObject object) {
        switch ((String) object.get("how")) {
            case "google":
                result[0] = result[0] + 1;
                break;
            case "facebook":
                result[1] = result[1] + 1;
                break;
            case "web":
                result[2] = result[2] + 1;
                break;
            case "banner":
                result[3] = result[3] + 1;
                break;
            case "stand":
                result[4] = result[4] + 1;
                break;
            case "radio":
                result[5] = result[5] + 1;
                break;
            case "client":
                result[6] = result[6] + 1;
                break;
            case "driveBy":
                result[7] = result[7] + 1;
                break;
            case "fromFriend":
                result[8] = result[8] + 1;
                break;
            case "other":
                result[9] = result[9] + 1;
                break;
        }
    }
}
