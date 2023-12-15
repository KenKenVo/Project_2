import java.awt.*;
import java.io.File;
import java.util.List;

public class main
{
    public static void main(String[] args)
    {
        salter s = new salter();
        excelRW erw = new excelRW();
        File csvfile = new File("plottedList.csv");

        //creates salted list
        List<Point> saltedList = s.randomNumbersFromCSV(csvfile,-2,2);
        erw.createExcelSheet(saltedList, "test","salter");

        //creates salter chart
        XYLineChart_AWT chart = new XYLineChart_AWT("Salter", "Salter",saltedList,"Salter");
        chart.pack();
        chart.setVisible(true);

        //creates smoothed list
        Integer windowVal = 9;
        smoother sm = new smoother();
        List<Point> smoothedList= sm.smoothSaltedList(saltedList, windowVal);

        //creates smoothed chart
        XYLineChart_AWT chart2 = new XYLineChart_AWT("Smoother", "Smoother",smoothedList,"Smoother");
        chart2.pack();
        chart2.setVisible(true);

        //created plotted list
        plotter p = new plotter();
       List<Point> plottedList = p.createPlotList(100,0,50);
        erw.createExcelSheet(plottedList, "test","plotter");

        csvWriter writer = new csvWriter();
        writer.writeToCsv(smoothedList,"smoothedList");
        writer.writeToCsv(saltedList,"saltedList");
        writer.writeToCsv(plottedList,"plottedList");

        //creates Plotter chart
        XYLineChart_AWT chart3 = new XYLineChart_AWT("Plotter", "Plotter",plottedList,"Plotter");
        chart3.pack();
        chart3.setVisible(true);
    }
}
