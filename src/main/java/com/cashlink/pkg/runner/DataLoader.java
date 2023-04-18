package com.cashlink.pkg.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cashlink.pkg.entity.CitizenPlan;
import com.cashlink.pkg.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repository.deleteAll();
		// To insert data into database
		
		// Cash Plans 
		CitizenPlan cp1=new CitizenPlan();
		cp1.setCitizenName("Vinoth");
		cp1.setGender("Male");
		cp1.setPlanName("Cash");
		cp1.setPlanStatus("Approved");
		cp1.setPlanStartDate(LocalDate.now());
		cp1.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp1.setBenifitAmt(5000.00);;
		
		CitizenPlan cp2=new CitizenPlan();
		cp2.setCitizenName("Maria");
		cp2.setGender("Fe-Male");
		cp2.setPlanName("Cash");
		cp2.setPlanStatus("Denied");
		cp2.setDenialReason("New Employment");
		
		CitizenPlan cp3=new CitizenPlan();
		cp3.setCitizenName("Zafer");
		cp3.setGender("Male");
		cp3.setPlanName("Cash");
		cp3.setPlanStatus("Terminated");
		cp3.setTerminatedDate(LocalDate.now());
		cp3.setTerminationRsn("New Job");
		
		// Food Plans 
		CitizenPlan cp4=new CitizenPlan();
		cp4.setCitizenName("David");
		cp4.setGender("Male");
		cp4.setPlanName("Food");
		cp4.setPlanStatus("Approved");
		cp4.setPlanStartDate(LocalDate.now());
		cp4.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp4.setBenifitAmt(6000.00);
		
		CitizenPlan cp5=new CitizenPlan();
		cp5.setCitizenName("Mindy");
		cp5.setGender("Fe-Male");
		cp5.setPlanName("Food");
		cp5.setPlanStatus("Denied");
		cp5.setDenialReason("New Employment");
		
		CitizenPlan cp6=new CitizenPlan();
		cp6.setCitizenName("Usha");
		cp6.setGender("Fe-Male");
		cp6.setPlanName("Food");
		cp6.setPlanStatus("Terminated");
		cp6.setTerminatedDate(LocalDate.now());
		cp6.setTerminationRsn("New Job");
		
		// Medical Plans 
		CitizenPlan cp7=new CitizenPlan();
		cp7.setCitizenName("Nadhiaya");
		cp7.setGender("Fe-Male");
		cp7.setPlanName("Medical");
		cp7.setPlanStatus("Approved");
		cp7.setPlanStartDate(LocalDate.now());
		cp7.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp7.setBenifitAmt(7000.00);
		
		CitizenPlan cp8=new CitizenPlan();
		cp8.setCitizenName("Anirban");
		cp8.setGender("Male");
		cp8.setPlanName("Medical");
		cp8.setPlanStatus("Denied");
		cp8.setDenialReason("New Job");
		
		CitizenPlan cp9=new CitizenPlan();
		cp9.setCitizenName("Raghu");
		cp9.setGender("Male");
		cp9.setPlanName("Medical");
		cp9.setPlanStatus("Terminated");
		cp9.setTerminatedDate(LocalDate.now());
		cp9.setTerminationRsn("Rental Income");
		
		// Employment Plans 
		CitizenPlan cp10=new CitizenPlan();
		cp10.setCitizenName("Nandini");
		cp10.setGender("Fe-Male");
		cp10.setPlanName("Employment");
		cp10.setPlanStatus("Approved");
		cp10.setPlanStartDate(LocalDate.now());
		cp10.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp10.setBenifitAmt(8000.00);
		
		CitizenPlan cp11=new CitizenPlan();
		cp11.setCitizenName("Selva");
		cp11.setGender("Male");
		cp11.setPlanName("Employment");
		cp11.setPlanStatus("Denied");
		cp11.setDenialReason("New Job");
		
		CitizenPlan cp12=new CitizenPlan();
		cp12.setCitizenName("Bilal");
		cp12.setGender("Male");
		cp12.setPlanName("Employment");
		cp12.setPlanStatus("Terminated");
		cp12.setTerminatedDate(LocalDate.now());
		cp12.setTerminationRsn("Rental Income");
		
        List<CitizenPlan> allCitizenPlans = Arrays.asList(cp1,cp2,cp3,cp4,cp5,cp6,cp7,cp8,cp9,cp10,cp11,cp12);
		
		repository.saveAll(allCitizenPlans);
		
		System.out.println("All records are successfully inserted to DB through DataLoader(OneTime)");
	}

}
