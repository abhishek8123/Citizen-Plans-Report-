package com.cashlink.pkg.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cashlink.pkg.entity.CitizenPlan;
import com.cashlink.pkg.repository.CitizenPlanRepository;
import com.cashlink.pkg.request.SearchRequest;
import com.cashlink.pkg.util.EmailUtils;
import com.cashlink.pkg.util.ExcelGenerator;
import com.cashlink.pkg.util.PdfGenerator;

import org.springframework.data.domain.Example;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repository;

	@Autowired
	private ExcelGenerator excel;

	@Autowired
	private PdfGenerator pdf;

	@Autowired
	private EmailUtils email;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = repository.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> planStatus = repository.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);

			entity.setPlanStartDate(localDate);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(endDate, formatter);

			entity.setPlanEndDate(localDate);
		}

		return repository.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = repository.findAll();
		File f = new File("Plans.xls");
		excel.gererate(response, plans, f);
		email.sendEmail("Citizen Plan Reports", "<h1>Citizen Plan Reports</h1>", "p123abhi@gmail.com", f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = repository.findAll();
		File f = new File("Plans.pdf");
		pdf.gererate(response, plans, f);
		email.sendEmail("Citizen Plan Reports", "<h1>Citizen Plan Reports</h1>", "p123abhi@gmail.com", f);
		f.delete();
		return true;
	}

}
