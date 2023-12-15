import org.apache.commons.csv.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class csvReader
{
    public csvReader()
    {
    }

    //csv file reader uses apache csv library
    public ArrayList<Point> readCSV(File file)
    {
        ArrayList<Point> pointList = new ArrayList<>();
        try
        {
            //parser for csv file
            CSVParser parser =
                    new CSVParser(new
                            FileReader(file),
                            CSVFormat.DEFAULT
                                    .withHeader("x","y"));
            //hashmap to store x,y pairs
            for(CSVRecord record : parser)
            {
                String x = record.get("x").replaceAll("\\p{C}", "");
                String y = record.get("y").replaceAll("\\p{C}", "");
                Point p = new Point(Integer.valueOf(x),Integer.valueOf(y));
                pointList.add(p);
            }
            parser.close();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex2)
        {
            ex2.printStackTrace();
        }
        return pointList;
    }
}
