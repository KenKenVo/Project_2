import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main
{
    public static void main(String[] args) throws Exception {
        csvWriter writer = new csvWriter();
        salter s = new salter();
        File csvfile = new File("pointList.csv");

        //creates salted list
        List<Point> saltedList = s.randomNumbersFromCSV(csvfile,-2,2);

        //creates smoothed list
        Integer windowVal = 9;
        smoother sm = new smoother();
        List<Point> smoothedList= sm.smoothSaltedList(saltedList, windowVal);

        //created plotted list
        plotter p = new plotter();
        List<Point> plottedList = p.createPlotList(100,0,50);

        writer.writeToCSV(smoothedList,"X,Y","smoothedList");
        writer.writeToCSV(saltedList,"X,Y","saltedList");
        writer.writeToCSV(plottedList,"X,Y","pointList");

    }
}
