package com.bionic.edu.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.bionic.edu.service.AnalyticService.Result;

@Named
public class SheduleView implements Serializable {
	
	private static final long serialVersionUI = 3L;
	private LineChartModel modelLineAmount;
	private LineChartModel modelLineNumber;
	private PieChartModel modelPieAmount;
	private PieChartModel modelPieNumber;
	private List<Result> list;
    private LocalDate start;
	private LocalDate end;
	
	public SheduleView () {
		
	}
	 
    public void createLineModels() {
    	// first chart
		modelLineAmount = initLineAmountModel();
		modelLineAmount.setTitle("Revenue");
		modelLineAmount.setZoom(true);
		modelLineAmount.setAnimate(true);
		modelLineAmount.setLegendPosition("se");
	    Axis yAxisAmount = modelLineAmount.getAxis(AxisType.Y);        
	    	yAxisAmount.setLabel("Revenue, USD");
		DateAxis xAxisAmount = new DateAxis("Date");
	    	xAxisAmount.setTickAngle(-50);
		    xAxisAmount.setMin(start.minusDays(1).toString());
		    xAxisAmount.setMax(end.plusDays(1).toString());
		    xAxisAmount.setTickFormat("%d.%m.%Y");
	    modelLineAmount.getAxes().put(AxisType.X, xAxisAmount);
	    
	 // second chart
	    modelLineNumber = initLineNumberModel();
	    modelLineNumber.setTitle("Number");
	    modelLineNumber.setZoom(true);
	    modelLineNumber.setAnimate(true);
	    modelLineNumber.setLegendPosition("se");
	    Axis yAxisNumber = modelLineNumber.getAxis(AxisType.Y);        
	    	yAxisNumber.setLabel("Number");
		DateAxis xAxisNumber = new DateAxis("Date");
	    	xAxisNumber.setTickAngle(-50);
		    xAxisNumber.setMin(start.minusDays(1).toString());
		    xAxisNumber.setMax(end.plusDays(1).toString());
		    xAxisNumber.setTickFormat("%d.%m.%Y");
	    modelLineNumber.getAxes().put(AxisType.X, xAxisNumber);   
    }
     
    private LineChartModel initLineAmountModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Amount");
 
        for (Result result : list) {
    		series1.set(result.getLd().toString(), result.getAmount());
		}
	    model.addSeries(series1);
	    return model;
	}
    
    private LineChartModel initLineNumberModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Number");
 
        for (Result result : list) {
        	series1.set(result.getLd().toString(), result.getCount());
		}
	    model.addSeries(series1);
	    return model;
	}
    
    public void createPieModels() {
    	//first model
    	modelPieAmount = new PieChartModel();
    	for(Result result : list) {
    		modelPieAmount.set(result.getDestinPlace(), result.getAmount());
    	}
    	modelPieAmount.setTitle("Amount");
    	modelPieAmount.setLegendPosition("se");
    	modelPieAmount.setDiameter(250);
    	modelPieAmount.setShowDataLabels(true);
    	
    	//second model
    	modelPieNumber = new PieChartModel();
    	for(Result result : list) {
    		modelPieNumber.set(result.getDestinPlace(), result.getCount());
    	}
    	modelPieNumber.setTitle("Number");
    	modelPieNumber.setLegendPosition("se");
    	modelPieNumber.setDiameter(250);
    	modelPieNumber.setShowDataLabels(true);
    }
    
    public LineChartModel getModelLineAmount() {
        return modelLineAmount;
    }
    
	public LineChartModel getModelLineNumber() {
		return modelLineNumber;
	}
	
	public PieChartModel getModelPieAmount() {
		return modelPieAmount;
	}

	public PieChartModel getModelPieNumber() {
		return modelPieNumber;
	}

	public List<Result> getList() {
		return list;
	}
	
	public void setList(List<Result> list) {
		this.list = list;
	}
	
	public LocalDate getStart() {
		return start;
	}
	
	public void setStart(LocalDate start) {
		this.start = start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public void setEnd(LocalDate end) {
		this.end = end;
	}

}
