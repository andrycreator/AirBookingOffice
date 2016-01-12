package com.bionic.edu.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.service.AnalyticService;
import com.bionic.edu.service.AnalyticService.Result;

@Named
@Scope("session")
public class ReportBean implements Serializable {
	
	@Inject
	private AnalyticService analyticService;
	
	@Inject
	private SheduleView sheduleView;
	
	private static final long serialVersionUI = 3L;
	private Date start;
	private Date end;
	private String typeReport = "table";
	private String typeGroup = "date";
	private List<Result> listResult = new ArrayList<>();
	private double totalAmount;
	private long totalNumber;

	public ReportBean() {
	
	}
	
	public static Date asDate(LocalDate localDate) {
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public String showReport() {
		LocalDate ldStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ldEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		listResult.clear();
		
		if (typeGroup.equals("date")) {
			listResult = analyticService.getReportByDate(ldStart, ldEnd);
			if (typeReport.equals("shedule")) {
				sheduleView.setList(listResult);
				sheduleView.setStart(ldStart);
				sheduleView.setEnd(ldEnd);
				sheduleView.createLineModels();
			}
			
		} else {
			listResult = analyticService.getReportByDestinPlace(ldStart, ldEnd);
			if (typeReport.equals("shedule")) {
				sheduleView.setList(listResult);
				sheduleView.createPieModels();
			}
		}
		totalAmount = listResult.stream().mapToDouble(Result::getAmount).sum();
		totalNumber = listResult.stream().mapToLong(Result::getCount).sum();
		return "analytic?faces-redirect=true";
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}

	public String getTypeGroup() {
		return typeGroup;
	}

	public void setTypeGroup(String typeGroup) {
		this.typeGroup = typeGroup;
	}

	public List<Result> getListResult() {
		return listResult;
	}

	public void setListResult(List<Result> listResult) {
		this.listResult = listResult;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
	
}
