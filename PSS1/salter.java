

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class salter
{
    public salter()
    {
    }

    // Method to generate random X,Y pairs by adding/subtracting a random number
    public List<Point> randomNumbersFromCSV(File csvFile, int rangeMin, int rangeMax)
    {
        double randomY;
        csvReader reader = new csvReader();
        List<Point> point = reader.CSVReader();
        for(Point p: point )
        {
            randomY= ThreadLocalRandom.current().nextInt(rangeMin,rangeMax);
            p.setLocation(p.getX(),p.getY()+randomY);
        }
        return point;
    }
}
