import java.math.BigDecimal;

public class StatsLibrary {
    public StatsLibrary()
    {

    }

    public double  normalDistribution(double x, double mean, double sd)
    {
         double tmp = 1 / sd
                 * Math.sqrt(2* Math.PI) *
                 Math.pow(Math.E,-((Math.pow((x-mean),2))/2*Math.pow(sd,2)));

         return tmp;
    }
}
