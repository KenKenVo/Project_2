
public class stockMain
{
    public static void main(String[] args) throws Exception
    {
        reader r = new reader();
        calculateData cd = new calculateData(r.CSVReader());
        csvWriter w = new csvWriter();
        w.writeToCSV(cd.calculateRSI(14), "rsi","rsi");
        w.writeToCSV(cd.smoothData(14),"MA","smoothData");

        stockBot sb = new stockBot(10000);
        //stockEvauluator 1 2 3 ,
        // 1 is buy lump sum at the beginning sell at the end of data set
        // 2 is buy and sell whether rsi is oversold/overbought and ma is lower/higher than current price
        // 3 is monthly dollar cost averaging, sell at the end of data set
        sb.stockEvauluator3(cd.getData(),14);
    }
}
