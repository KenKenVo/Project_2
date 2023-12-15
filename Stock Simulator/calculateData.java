
import java.util.ArrayList;
import java.util.List;

public class calculateData
{

    public List<List<String>> getData() {
        return data;
    }

    private List<List<String>> data;
    public calculateData(List<List<String>> stockData)
    {
        //calculateRSI();
        this.data = stockData;
    }

    public List<Float> calculateRSI(int rsiPeriod)
    {
        float RSI;
        float RS;
        int N = rsiPeriod;

        List<Float> avgUList = new ArrayList<>();
        List<Float> avgDList = new ArrayList<>();

        ArrayList<Float> rsiList = new ArrayList<>();
        int counter = 0;
        int totalCounter = 0;
        // get avg D and U values
        for(int i = 0; i< data.size(); i++)
        {
            counter++;
            totalCounter++;

            if(i-1 > 0)
            {
                Float value = Float.parseFloat(data.get(i).get(4)) - Float.parseFloat(data.get(i-1).get(4));
                if(value > 0)
                {
                    avgUList.add(value);
                }
                else
                {
                    avgDList.add(Math.abs(value));
                }
                if(counter == N || totalCounter == data.size())
                {
                    counter = 0 ;
                    // avg the avgD values
                    float totalDValue = 0 ;
                    for(float x : avgDList)
                    {
                        totalDValue = totalDValue + x;
                    }

                    // avg the avgU values
                    float totalUValue = 0 ;
                    for(float o : avgUList)
                    {
                        totalUValue = totalUValue + o;
                    }

                    //avg the values
                    float dSMA = totalDValue/N;
                    float uSMA = totalUValue/N;

                    if(dSMA == 0 || uSMA == 0 )
                        break;

                    RS = uSMA / dSMA;
                    RSI = 100-100/(1+RS);
                    rsiList.add(RSI);

                    dSMA=0;
                    uSMA=0;
                    RS=0;
                    RSI=0;
                    totalDValue=0;
                    totalUValue=0;
                    avgDList.clear();
                    avgUList.clear();
                }
            }
        }

        return rsiList;
    }

    public List<Float>smoothData(int rsiPeriod)
    {
        int N = rsiPeriod;


        ArrayList<Float> sData = new ArrayList<>();
        float value = 0;

        int counter = 0;
        int totalCounter = 0;
        for(int i = 0; i< data.size(); i++)
        {
            counter++;
            totalCounter++;

            value = value + Float.parseFloat(data.get(i).get(4));
            if(counter == N-1 || totalCounter ==data.size())
            {
                sData.add(value/counter);
                counter = 0 ;
                value = 0;

            }
        }
        return sData;
    }

}
