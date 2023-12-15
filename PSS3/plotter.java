
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class plotter
{
    //using example y=mx+b
    public plotter()
    {
    }

    public List<Point> createPlotList(int range, int plotLowerBounds, int plotUpperBounds)
    {
        ArrayList<Point> pointList= new ArrayList<>();
        for(int i = 1; i < range; i++)
        {
            int m = ThreadLocalRandom.current().nextInt(0,3);
            int b = ThreadLocalRandom.current().nextInt(0,3);
            int y = m*i+b;
            Point p = new Point(i,y);
            pointList.add(p);
        }
        return pointList.subList(plotLowerBounds,plotUpperBounds);
    }

}
