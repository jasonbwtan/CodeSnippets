package com.jason.jasper_reports_example;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JREmptyDataSource;

public class Test2 {
	public static void main(String[] args) {
		JasperReportBuilder report = DynamicReports.report();
		
		StyleBuilder bold = DynamicReports.stl.style().bold();
		StyleBuilder boldCenter = DynamicReports.stl.style().bold().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder leftJustify = DynamicReports.stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		StyleBuilder columnTitleStyle = DynamicReports.stl.style(boldCenter).setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
		
		//title
		TextFieldBuilder<String> title = DynamicReports.cmp.text("Dynamic report tutorial");
		title.setStyle(boldCenter);
		report.title(title);
		
		//columns
		TextColumnBuilder<String> firstNameColumn = Columns.column("First Name", "first_name", DataTypes.stringType());
		TextColumnBuilder<String> lastNameColumn = Columns.column("Last Name", "last_name", DataTypes.stringType());
		report.setColumnTitleStyle(columnTitleStyle);
		report.columns(firstNameColumn, lastNameColumn);

		//data
		report.setDataSource(new JREmptyDataSource(2));

		//render
		try {
			report.show();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	public static List<Bean> getData(){
		ArrayList<Bean> list = new ArrayList<Bean>();
		list.add(new Bean());
		list.add(new Bean());
		list.add(new Bean());
		return list;
	}
}
