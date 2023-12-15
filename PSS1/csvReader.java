

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class csvReader
{
    public csvReader()
    {
    }

    public List<Point> CSVReader()
    {
        String file = "pointList.csv";
        String line;
        ArrayList<Point> pointList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            line = br.readLine();
            line = br.readLine();

            while (line != null)
            {
                String[] temp = line.split(",");
                String x = temp[0].replaceAll("\\p{C}", "");
                String y = temp[1].replaceAll("\\p{C}", "");
                Point p = new Point(Integer.valueOf(x),Integer.valueOf(y));
                pointList.add(p);
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(pointList);
        return pointList;
    }
}
