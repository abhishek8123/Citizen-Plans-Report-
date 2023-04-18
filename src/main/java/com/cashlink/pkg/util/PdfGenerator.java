package com.cashlink.pkg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.cashlink.pkg.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {

	public void gererate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Citizen Plans", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		PdfPTable table = new PdfPTable(8);
		table.setSpacingBefore(15);
		table.addCell("ID");
		table.addCell("Citegen Name");
		table.addCell("plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");
		table.addCell("Gender");
		table.addCell("Benifery Amount");

		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if (null != plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != (plan.getPlanEndDate())) {
				table.addCell(plan.getPlanEndDate() + "");
			} else {
				table.addCell("N/A");
			}
			table.addCell(plan.getGender());
			if (null != plan.getBenifitAmt()) {
				table.addCell(plan.getBenifitAmt() + "");
			} else {
				table.addCell("N/A");
			}

		}
		document.add(table);
		document.close();

	}

}
