package com.cashlink.pkg.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.cashlink.pkg.entity.CitizenPlan;
import com.cashlink.pkg.request.SearchRequest;
import com.cashlink.pkg.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;

	private void init(Model model) {
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
	
	@GetMapping("/")
	public String reportPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "report";
	}

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
		System.out.println(request);
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);

		init(model);
		return "report";
	}

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response, Model model) throws Exception {

		response.setContentType("application/actet-stream");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.xls");
		service.exportExcel(response);
//		boolean status = service.exportExcel(response);
//		if(status) {
//			model.addAttribute("msg","Excel Repost Sent to Your Email");
//		}else {
//			model.addAttribute("msg","Excel Not Sent to Your Email Sorry...try again");
//		}
//		init(model);
//		model.addAttribute("search", new SearchRequest());
//		return "report";
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response, Model model) throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.pdf");
		service.exportPdf(response);
//		if(status) {
//			model.addAttribute("msg","Excel Repost Sent to Your Email");
//		}else {
//			model.addAttribute("msg","Excel Not Sent to Your Email Sorry...try again");
//		}
//		init(model);
//		model.addAttribute("search", new SearchRequest());
//		return "report";
	}


}
