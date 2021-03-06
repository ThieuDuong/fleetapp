package com.fleet.thieuduong.fleetapp.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fleet.thieuduong.fleetapp.models.Employee;
import com.fleet.thieuduong.fleetapp.services.CountryService;
import com.fleet.thieuduong.fleetapp.services.EmployeeService;
import com.fleet.thieuduong.fleetapp.services.EmployeeTypeService;
import com.fleet.thieuduong.fleetapp.services.JobTitleService;
import com.fleet.thieuduong.fleetapp.services.StateService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeTypeService employeeTypeService;

	@Autowired
	private JobTitleService jobTitleService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@GetMapping("/employees")
	public String getEmployees(Model model) {
		model.addAttribute("employees", employeeService.getEmployees());
		model.addAttribute("employeetypes", employeeTypeService.getEmployeeTypes());
		model.addAttribute("jobtitles", jobTitleService.getJobTitles());
		model.addAttribute("countries", countryService.getCountries());
		model.addAttribute("states", stateService.getStates());
		return "Employee";
	}

	@PostMapping(value = "/employee/addNew")
	public String addNewEmployee(Employee employee) {
		System.out.println("employee photo: " + employee.getPhoto());
//		System.out.println("multipartFile: " + multipartFile);
//		employeeService.saveEmployee(employee);
//		try {
//			filesStorageService.save(multipartFile);
//		} catch (Exception e) {
//			System.err.println("Cannot save file");;
//		}
		return "redirect:/employees";
	}

	@RequestMapping("/employee/findById")
	@ResponseBody
	public Optional<Employee> getEmployeeById(int id) {
		return employeeService.getEmployeeById(id);
	}

	@RequestMapping(value = "/employee/edit", method = { RequestMethod.PUT, RequestMethod.GET })
	public String updateEmployee(Employee Employee) {
		employeeService.saveEmployee(Employee);
		return "redirect:/employees";
	}

	@RequestMapping(value = "/employee/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteEmployee(int id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}

	@GetMapping("/employees/exportToExcel")
	public String exportCountriesToExcel(Model model) throws IOException {
		DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime dateTimeNow = LocalDateTime.now();

		Workbook workbook = new XSSFWorkbook();

		// Create sheet name "Countries"
		Sheet sheet = workbook.createSheet("Employees");

		// Create column
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		sheet.setColumnWidth(16, 5000);
		sheet.setColumnWidth(17, 5000);
		sheet.setColumnWidth(18, 5000);
		sheet.setColumnWidth(19, 5000);
		sheet.setColumnWidth(20, 5000);

		Row headerRow = sheet.createRow(0);

		// Format Header Style
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		XSSFFont fontHeader = ((XSSFWorkbook) workbook).createFont();
		fontHeader.setFontName("Arial");
		fontHeader.setFontHeightInPoints((short) 14);
		headerStyle.setFont(fontHeader);

		// Tạo ra cell dòng 1 cột 1
		Cell headerCell00 = headerRow.createCell(0);
		headerCell00.setCellValue("ID");
		headerCell00.setCellStyle(headerStyle);

		// Tạo ra cell dòng 1 cột 2
		Cell headerCell01 = headerRow.createCell(1);
		headerCell01.setCellValue("Name");
		headerCell01.setCellStyle(headerStyle);

		// Tạo ra cell dòng 1 cột 3
		Cell headerCell02 = headerRow.createCell(2);
		headerCell02.setCellValue("Capital");
		headerCell02.setCellStyle(headerStyle);

		// Tạo ra cell dòng 1 cột 4
		Cell headerCell03 = headerRow.createCell(3);
		headerCell03.setCellValue("Code");
		headerCell03.setCellStyle(headerStyle);

		// Tạo ra cell dòng 1 cột 5
		Cell headerCell04 = headerRow.createCell(4);
		headerCell04.setCellValue("Continent");
		headerCell04.setCellStyle(headerStyle);

		// Tạo ra cell dòng 1 cột 6
		Cell headerCell05 = headerRow.createCell(5);
		headerCell05.setCellValue("Nationality");
		headerCell05.setCellStyle(headerStyle);

		// Format Row Style
		CellStyle rowStyle = workbook.createCellStyle();
		rowStyle.setWrapText(true);
		rowStyle.setAlignment(HorizontalAlignment.CENTER);

		XSSFFont fontRow = ((XSSFWorkbook) workbook).createFont();
		fontRow.setFontName("Arial");
		fontRow.setFontHeightInPoints((short) 12);
		rowStyle.setFont(fontRow);

		// Import data to cell
		for (int i = 0; i < countryService.getCountries().size(); i++) {
			Row row = sheet.createRow(i + 1);

			// Country ID
			Cell cell = row.createCell(0);
			cell.setCellValue(countryService.getCountries().get(i).getId());
			cell.setCellStyle(rowStyle);

			// Country Name
			cell = row.createCell(1);
			cell.setCellValue(countryService.getCountries().get(i).getDescription());
			cell.setCellStyle(rowStyle);

			// Country Capital
			cell = row.createCell(2);
			cell.setCellValue(countryService.getCountries().get(i).getCapital());
			cell.setCellStyle(rowStyle);

			// Country Code
			cell = row.createCell(3);
			cell.setCellValue(countryService.getCountries().get(i).getCode());
			cell.setCellStyle(rowStyle);

			// Country Continent
			cell = row.createCell(4);
			cell.setCellValue(countryService.getCountries().get(i).getContinent());
			cell.setCellStyle(rowStyle);

			// Country Nationality
			cell = row.createCell(5);
			cell.setCellValue(countryService.getCountries().get(i).getNationality());
			cell.setCellStyle(rowStyle);
		}

		File currDir = new File("\\F:\\FleetApp_Excel_Export\\Country_Export\\");
		System.out.println("current direction: " + currDir);
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length()) + "\\Country_Export_"
				+ datetimeFormat.format(dateTimeNow) + ".xlsx";
		System.out.println("fileLocation: " + fileLocation);

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();

		return "redirect:/countries";
	}
}
