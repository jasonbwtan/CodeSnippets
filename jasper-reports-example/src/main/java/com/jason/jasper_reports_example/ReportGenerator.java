package com.jason.jasper_reports_example;

import static net.sf.dynamicreports.report.builder.DynamicReports.grid;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportGenerator {
	public static String fontName = "Courier New";
	public static String outputPath = "target/jasper-report.pdf";
	public static void buildReport(Collection<?> collection) {
		JasperReportBuilder report = DynamicReports.report();

		StyleBuilder bold = DynamicReports.stl.style().bold();
		StyleBuilder boldCenter = DynamicReports.stl.style().bold()
				.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder leftJustify = DynamicReports.stl.style().setHorizontalTextAlignment(
				HorizontalTextAlignment.LEFT).setFontName(fontName);
		StyleBuilder rightJustify = DynamicReports.stl.style().setHorizontalTextAlignment(
				HorizontalTextAlignment.RIGHT).setFontName(fontName);
		StyleBuilder centerJustify = DynamicReports.stl.style().setHorizontalTextAlignment(
				HorizontalTextAlignment.CENTER).setFontName(fontName);
		StyleBuilder columnTitleStyle = DynamicReports.stl.style(centerJustify)
				.setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
		
		report.pageHeader(Components.text("Comprehensive Report").setStyle(centerJustify));
		report.pageHeader(Components.text("Statistics of Market Development").setStyle(centerJustify));
		report.pageHeader(Components.horizontalList(Components.text("Date: " + new Date().toString()).setStyle(leftJustify),
				Components.filler().setFixedWidth(420), Components.text("Domain").setStyle(rightJustify)));		
		
		
		
		
		TextColumnBuilder<String> col1 = Columns.column("Device", "device", DataTypes.stringType()).setWidth(20).setStyle(leftJustify);
		TextColumnBuilder<Integer> col2 = Columns.column("Expected plans", "expectedNumberOfPlans",
				DataTypes.integerType()).setWidth(9);
		TextColumnBuilder<Integer> col3 = Columns.column("Found plans", "foundNumberOfPlans",
				DataTypes.integerType()).setWidth(9);
		TextColumnBuilder<Integer> col4 = Columns.column("Passed", "passedMatchesCount",
				DataTypes.integerType()).setWidth(5);
		TextColumnBuilder<Integer> col5 = Columns.column("Failed", "failedMatchesCount",
				DataTypes.integerType()).setWidth(5);
		TextColumnBuilder<List> col6 = Columns.column("Passed Matches", "passedMatches", DataTypes.listType()).setWidth(26);
		TextColumnBuilder<List> col7 = Columns.column("Failed Matches", "failedMatches", DataTypes.listType()).setWidth(26);

		//ColumnGroupBuilder deviceGroup = grp.group(col1);
		// deviceGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(deviceGroup));
		//report.groupBy(deviceGroup);

		report.columns(col1, col2, col3, col4, col5, col6, col7);
		report.setColumnStyle(centerJustify);
		report.setColumnTitleStyle(columnTitleStyle);

		// title
		TextFieldBuilder<String> title = DynamicReports.cmp.text("Product Page Price Plan Report");
		title.setStyle(boldCenter);
		report.title(title);
		
		report.setDataSource(collection);
        report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		// new JRBeanCollectionDataSource(list);
		try {
			// show the report
			report.show();

			// export the report to a pdf file
			report.toPdf(new FileOutputStream(outputPath));
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		ArrayList<PlanTestRecord> resultList = new ArrayList<PlanTestRecord>();
//		resultList.add(new PlanTestRecord("Samsung Galaxy S6 Edge 256MB", 10, 10, 8, 2, Arrays.asList("foo"), Arrays
//				.asList("foo")));
//		resultList.add(new PlanTestRecord("iPhone 5", 10, 10, 8, 2, Arrays.asList("foo"), Arrays
//				.asList("foo")));
//		resultList.add(new PlanTestRecord("iPhone 6", 10, 10, 8, 2, Arrays.asList("foo", "asd", "asd",
//				"asdasd", "sds", "asdasdasdasdas", "asdasdasddas"), Arrays.asList("foo", "asdas")));
//		resultList.add(new PlanTestRecord("iPhone 6", 10, 10, 8, 2, Arrays.asList("foo"), Arrays
//				.asList("foo")));
//		resultList.add(new PlanTestRecord("iPhone 5", 99, 99, 99, 99, Arrays.asList("foo"), Arrays
//				.asList("foo")));
//
//		new ReportGenerator().buildReport(resultList);
//	}
}