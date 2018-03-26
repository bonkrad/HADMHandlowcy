package pl.radekbonk.HADMHandlowcy.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Component
public class ExcelService {
	
	@Autowired
	private HttpServletRequest request;
	
	public ExcelService() {
	
	}
	
	private int[] serviceResult = {0,0,0,0,0,0,0,0,0,0};
	private int[] serviceRating = {0,0,0,0,0};
	
	private int[] sellerResult = {0,0,0,0,0,0,0,0,0,0};
	private int[] sellerRating = {0,0,0,0,0};
	
	private int[] sellerServiceResult = {0,0,0,0,0,0,0,0,0,0};
	private int[] sellerServiceRating = {0,0,0,0,0};
	
	private int[] howResult = {0,0,0,0,0,0,0,0,0,0};
	
	public String generateExcel(String data) {
		JSONObject jsonArray = new JSONObject(data);
		System.out.println(jsonArray);
		Set<String> strings = jsonArray.keySet();
		for (String key:strings) {
			//System.out.println(key);
			//System.out.println(jsonArray.getJSONObject(key));
			JSONObject object = jsonArray.getJSONObject(key);
			//System.out.println(object.get("how"));
			addToResults(object);
		}
		System.out.println("\nService Result");
		for(int i:serviceResult) {
			System.out.print(i);
		}
		System.out.println("");
		for (int i:serviceRating) {
			System.out.print(i);
		}
		
		System.out.println("\n\nSeller Result");
		for(int i:sellerResult) {
			System.out.print(i);
		}
		System.out.println("");
		for(int i:sellerRating) {
			System.out.print(i);
		}
		
		System.out.println("\n\nSeller Service Result");
		for(int i:sellerServiceResult) {
			System.out.print(i);
		}
		System.out.println("");
		for(int i:sellerServiceRating) {
			System.out.print(i);
		}
		
		System.out.println("\n\nHow Result");
		for(int i:howResult) {
			System.out.print(i);
		}
		System.out.println("");
		
		ServletContext context = request.getServletContext();
		String realPathToUploads = context.getRealPath("/files/");

		if (!(new File(realPathToUploads)).exists()) {
			(new File(realPathToUploads)).mkdir();
		}
		
		String fileName = UUID.randomUUID() + ".xlsx";
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Raport Ankiety");
		
		try(FileOutputStream outputStream = new FileOutputStream(realPathToUploads + fileName)) {
			try {
				wb.write(outputStream);
				wb.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(realPathToUploads + fileName);
		return fileName;
	}
	
	private void addToResults(JSONObject object) {
		switch((String)object.get("what")) {
			case "service":
				addToResults(serviceResult,serviceRating,object);
				break;
			case "seller":
				addToResults(sellerResult,sellerRating,object);
				break;
			case "sellerService":
				addToResults(sellerServiceResult,sellerServiceRating,object);
				break;
			case "0":
				addToResults(howResult,object);
				break;
		}
	}
	
	private void addToResults(int[] result, int[] rating, JSONObject object) {
		addToResults(result, object);
		
		switch((int) object.get("rating")) {
			case 1:
				rating[0]=rating[0]+1;
				break;
			case 2:
				rating[1]=rating[1]+1;
				break;
			case 3:
				rating[2]=rating[2]+1;
				break;
			case 4:
				rating[3]=rating[3]+1;
				break;
			case 5:
				rating[4]=rating[4]+1;
				break;
		}
	}
	
	private void addToResults(int[] result, JSONObject object) {
		switch((String) object.get("how")) {
			case "google":
				result[0]=result[0]+1;
				break;
			case "facebook":
				result[1]=result[1]+1;
				break;
			case "web":
				result[2]=result[2]+1;
				break;
			case "banner":
				result[3]=result[3]+1;
				break;
			case "stand":
				result[4]=result[4]+1;
				break;
			case "radio":
				result[5]=result[5]+1;
				break;
			case "client":
				result[6]=result[6]+1;
				break;
			case "driveBy":
				result[7]=result[7]+1;
				break;
			case "fromFriend":
				result[8]=result[8]+1;
				break;
			case "other":
				result[9]=result[9]+1;
				break;
		}
	}
}
