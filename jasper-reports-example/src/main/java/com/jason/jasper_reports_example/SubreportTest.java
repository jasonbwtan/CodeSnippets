package com.jason.jasper_reports_example;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
public class SubreportTest {
    private StyleBuilder bold;
    private StyleBuilder centeredBold;
    private StyleBuilder columnTitleStyle;
    private StyleBuilder columnStyle;

    private JasperReportBuilder reportBuilder;

    private int numberOfSubReports = 2;
   public SubreportTest(int numberOfSubReports) {
         bold = Styles.style().bold();
         centeredBold = Styles.style(bold)
                 .setHorizontalAlignment(HorizontalAlignment.CENTER);
         columnTitleStyle = Styles.style(centeredBold)
                 .setBackgroundColor(Color.LIGHT_GRAY)
                 .setBorder(Styles.pen1Point());
         columnStyle = Styles.style()
                 .setHorizontalAlignment(HorizontalAlignment.LEFT)
                 .setLeftIndent(4)
                 .setBottomPadding(5);
         this.numberOfSubReports = numberOfSubReports;
         build();
   }

    public void build() {
           //Creating the subreport
           SubreportExpression subreportExpress = new SubreportExpression();
           SubreportBuilder subreport = Components.subreport(subreportExpress);
           subreport.setDataSource(new SubreportDataSourceExpression());
           reportBuilder = DynamicReports.report();
         
           //Formatting the subreports
           //Using default page break and setting the gap between subreports to 20
           reportBuilder.detail(subreport, Components.verticalGap(20));
           //Beginning each subreport on a new page
           //reportBuilder.detail(subreport, Components.pageBreak());
            //Only having a page break at a subreport
            //reportBuilder.detail(subreport, Components.pageBreak().setPrintWhenExpression(new PageBreakExpression()));
            //No page break
            //reportBuilder.ignorePagination();

            //Setting the page header (the title to be displayed on top of each page)
            reportBuilder.pageHeader(Components.text("Comprehensive Report").setStyle(centeredBold));
            reportBuilder.pageHeader(Components.text("Statistics of Market Development").setStyle(centeredBold));
            reportBuilder.pageHeader(Components.horizontalList(Components.text("Date: " + new Date().toString()),
             Components.filler().setFixedWidth(420),
             Components.text("Pay Attention Please")));

           //Setting the page footer
            reportBuilder.pageFooter(Components.horizontalFlowList().add(Components.text("Page "))
             .add(Components.pageNumber())
             .setStyle(bold));

           //Setting the number of subreports
           reportBuilder.setDataSource(new JREmptyDataSource(numberOfSubReports));

          try {
			reportBuilder.show();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
 
   private class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
         public JasperReportBuilder evaluate(ReportParameters reportParameters) {
                 int masterRowNumber = reportParameters.getReportRowNumber();
       
                 JasperReportBuilder report = DynamicReports.report();
                        report.columns(Columns.column("Col 1", "col1Data", DataTypes.stringType()),
                                                Columns.column("Col 2", "col2Data", DataTypes.stringType()));
                        report.setColumnTitleStyle(columnTitleStyle.setBottomBorder(Styles.penDashed()));
                       report.setColumnStyle(columnStyle);
                       report.highlightDetailEvenRows();

                 return report;
          }
   }

    private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
           //private ArrayList<DRDataSource> dataSourceArray;
   
           //public SubreportDataSourceExpression(ArrayList<DRDataSource> dataSourceArray){
                   // super();
                    // this.dataSourceArray = dataSourceArray;
           //}
   
           public JRDataSource evaluate(ReportParameters reportParameters) {
                   int masterRowNumber = reportParameters.getReportRowNumber();
                   DRDataSource dataSource = null;
                          dataSource = new DRDataSource("col1Data", "col2Data");
                          dataSource.add("Apple", "Sweet Fruit");
                          dataSource.add("Lemon", "Sour Fruit");
  
                   return dataSource;
                  // return dataSourceArray.get(masterRowNumber-1);
           }
    }

     private class PageBreakExpression extends AbstractSimpleExpression<Boolean> {
             public Boolean evaluate(ReportParameters reportParameters) {
                    //Only having a page break after the first subreport
                     if (reportParameters.getReportRowNumber() == 1) {
                             return true;
                     } else {
                             return false;
                     }
            }
     }
     public static void main(String[] args) {
//    	 ArrayList<PlanTestRecord> resultList = new ArrayList<PlanTestRecord>();
		new SubreportTest(2);
	}
}
