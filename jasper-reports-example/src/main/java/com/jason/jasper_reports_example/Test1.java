package com.jason.jasper_reports_example;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;



public class Test1 {
	
	private JRDataSource createDataSource() {
		return new JREmptyDataSource(5);
	}
	
	public static void main(String[] args) {
		ArrayList<Bean> list = new ArrayList<Bean>();
		list.add(new Bean());

		
		JasperReportBuilder report = DynamicReports.report();
		report.columns(Columns.column("Customer Id", "id", DataTypes.stringType()),
				Columns.column("First Name", "first_name", DataTypes.stringType()),
				Columns.column("Last Name", "last_name", DataTypes.stringType()),
				Columns.column("Date", "date", DataTypes.dateType()))
				//page number on footer
				.title(Components.text("SimpleReportExample").setHorizontalAlignment(
						HorizontalAlignment.CENTER)).pageFooter(Components.pageXofY())
				.setDataSource(list);
				//new JRBeanCollectionDataSource(list);
		try {
			// show the report
			report.show();

			// export the report to a pdf file
			report.toPdf(new FileOutputStream("target/jasper-report.pdf"));
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
