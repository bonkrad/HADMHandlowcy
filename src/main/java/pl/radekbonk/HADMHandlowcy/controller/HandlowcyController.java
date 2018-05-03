package pl.radekbonk.HADMHandlowcy.controller;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radekbonk.HADMHandlowcy.model.*;
import pl.radekbonk.HADMHandlowcy.repository.*;
import pl.radekbonk.HADMHandlowcy.service.ExcelService;
import pl.radekbonk.HADMHandlowcy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/")
public class HandlowcyController {
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
    @Autowired
    private UserService userService;
    @Autowired
    private ExcelService excelService;

    @GetMapping(value = "/raport")
    public String raport() {
        return "raport";
    }

    @PostMapping(value = "/weekReport", params = {"week", "year"})
    public void weekReport(@RequestParam(value = "week") int week, @RequestParam(value = "year") int year, HttpServletResponse response) {
        String fileName = excelService.getReportNameWeekly(week,year);
        try {
            InputStream is = new FileInputStream(excelService.generateWeeklyReport(week,year));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            IOUtils.copy(is,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/monthReport", params = {"month", "year"})
    public void monthReport(@RequestParam(value = "month") int month, @RequestParam(value = "year") int year, HttpServletResponse response) {
        String fileName = excelService.getReportNameMonthly(month,year);
        try {
            InputStream is = new FileInputStream(excelService.generateMonthlyReport(month,year));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            IOUtils.copy(is,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/newClientSalon")
    public String newClientSalon(Model model, NewClientSalon client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        newClientSalonRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/newClientService")
    public String newClientService(Model model, NewClientService client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        newClientServiceRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/newClientTelephone")
    public String newClientTelephone(Model model, NewClientTelephone client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        newClientTelephoneRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/newClientMail")
    public String newClientMail(Model model, NewClientMail client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        newClientMailRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/oldClientMeeting")
    public String oldClientMeeting(Model model, OldClientMeeting client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        oldClientMeetingRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/oldClientTelephone")
    public String oldClientTelephone(Model model, OldClientTelephone client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        oldClientTelephoneRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/oldClientMail")
    public String oldClientMail(Model model, OldClientMail client, HttpServletRequest request) {
        client.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        client.setUser(user);

        oldClientMailRepository.save(client);
        return "redirect:/";
    }

    @PostMapping(value = "/callCenter")
    public String callCenter(Model model, MiscCallCenter misc, HttpServletRequest request) {
        misc.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        misc.setUser(user);

        miscCallCenterRepository.save(misc);
        return "redirect:/";
    }

    @PostMapping(value = "/dras")
    public String dras(Model model, MiscDras misc, HttpServletRequest request) {
        misc.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        misc.setUser(user);

        miscDrasRepository.save(misc);
        return "redirect:/";
    }

    @PostMapping(value = "/orders")
    public String orders(Model model, MiscOrders misc, HttpServletRequest request) {
        misc.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        misc.setUser(user);

        miscOrdersRepository.save(misc);
        return "redirect:/";
    }

    @PostMapping(value = "/delivery")
    public String delivery(Model model, MiscDelivery misc, HttpServletRequest request) {
        misc.setTimestamp();
        User user = userService.findByUsername(request.getRemoteUser());
        misc.setUser(user);

        miscDeliveryRepository.save(misc);
        return "redirect:/";
    }
}
