package pl.radekbonk.HADMHandlowcy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.radekbonk.HADMHandlowcy.service.ExcelService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class ExcelController {
	@Autowired
	private ExcelService excelService;
	
	@CrossOrigin
	@PostMapping(value = "/ankiety/generateExcel")
	@ResponseBody
	public String getExcel(HttpServletResponse response, @RequestParam(value = "data", required = false) String data) {
		String fileName = "excelRaport.xlsx";
		if (data != null) {
			//System.out.println(data);
		} else {
			System.out.println("Empty data");
		}
		return "http://83.13.11.217:8090/files/" + excelService.generateExcel(data);
	}

}
