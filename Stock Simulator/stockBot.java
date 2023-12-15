import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stockBot
{
    private double bank;
    private Map<String,List<Float>> portfolio;

    public stockBot(double startAmount)
    {
        this.bank = startAmount;
        this.portfolio = new HashMap<String,List<Float>>();
    }

    public void addShares(String stockName,float priceToBuy, int amountToBuy)
    {
        if(!portfolio.containsKey(stockName))
        {
            ArrayList<Float> stocks = new ArrayList<>();

            for (int i = 0 ; i < amountToBuy; i++)
            {
                if(bank >= priceToBuy)
                {
                    bank = bank - priceToBuy;
                    stocks.add(priceToBuy);
                }
                else
                {
                    break;
                }
            }
           portfolio.put(stockName,stocks);
        }
        else
        {
            for(Map.Entry<String,List<Float>> entry : portfolio.entrySet())
            {
                String key = entry.getKey();
                if(stockName.equals(key) && bank >= (priceToBuy*amountToBuy))
                {
                    for (int i = 0 ; i < amountToBuy; i++)
                    {

                            entry.getValue().add(priceToBuy);
                            this.bank = bank - priceToBuy;
                    }
                }
                else
                {
                    System.out.println("Not enough funds");
                    break;
                }
            }
        }
    }

    public void sellShares(String stockName,Integer amountToSell,float currentStockPrice)
    {
        for(Map.Entry<String,List<Float>> entry : portfolio.entrySet())
        {
            String key = entry.getKey();
            List<Float> temp = entry.getValue();
            if(stockName.equals(key) )
            {
                if(amountToSell < temp.size())
                {
                    List<Float> afterSold = temp.subList(amountToSell,temp.size());
                    portfolio.put(stockName,afterSold);
                    this.bank = bank + currentStockPrice*amountToSell;
                }
                else
                {
                    System.out.println("Not enough Shares to sell!");
                }
            }
        }
    }

    public Integer getTotalShareAmount(String stockName)
    {
        int shareCount = 0;
        for(Map.Entry<String,List<Float>> entry : portfolio.entrySet())
        {
            String key = entry.getKey();
            List<Float> temp = entry.getValue();
            if(stockName.equals(key) )
            {
                shareCount = temp.size();
            }
        }
        return shareCount;
    }


    public void sellAllShares(String stockName,float currentStockPrice)
    {
        for(Map.Entry<String,List<Float>> entry : portfolio.entrySet())
        {
            String key = entry.getKey();
            List<Float> temp = entry.getValue();
            if(stockName.equals(key) )
            {
                if(temp.size() != 0)
                {
                    this.bank = bank + currentStockPrice*temp.size();
                    List<Float> afterSold = new ArrayList<>();
                    portfolio.put(stockName,afterSold);

                }
                else
                {
                    System.out.println("Not enough Shares to sell!");
                }
            }
        }
    }


    public void printPortfolio()
    {
        for(Map.Entry<String,List<Float>> entry : portfolio.entrySet())
        {
            float avg= 0;
            String stockName = entry.getKey();
            List<Float> stockList = entry.getValue();
            for(int i = 0 ; i<stockList.size(); i++)
            {
                avg =+ stockList.get(i);
            }

            double accountWorth = avg*stockList.size() + bank;
            System.out.println("Remaining Cash Balance: " + String.format("%.2f", bank));
            System.out.println("Account worth: " + String.format("%.2f", accountWorth));
            System.out.println(stockName + " : "
                    + stockList.size()+" shares");
            System.out.println("Average cost: " + avg);
        }
    }



    public int stockEvauluator(List<List<String>> stockData,int rsiPeriod)
    {
        calculateData cd = new calculateData(stockData);
        String stockName = "VOO";
        List<List<String>> data = cd.getData();
        List<List<String>> currData;
        int counter = rsiPeriod;
        double moneyToSpend;
        int amountToBuy = 0;
        float currPrice;
        int action = 0;

        for(int i = rsiPeriod; i <data.size(); i++ )
        {
            currData = data.subList(0,i+1);
            counter++;
            cd = new calculateData(currData);
            currPrice = Float.parseFloat(currData.get(currData.size()-1).get(4));
            List<Float> rsiValues = cd.calculateRSI(rsiPeriod);
            List<Float> maValues = cd.smoothData(rsiPeriod);


            System.out.println("Starting Balance: " + bank);
            System.out.println("Day " + counter);
            System.out.println(currData);
            System.out.println("RSI over "+rsiPeriod+ " Days " + rsiValues);
            System.out.println("MA over "+rsiPeriod+ " Days " + maValues);
            System.out.println(stockName + " current price: " + currPrice);


            //start of trading. Bot will trade 20%  account at closing price
            if(counter-1 == rsiPeriod)
            {
                moneyToSpend = bank;
                amountToBuy = (int) (moneyToSpend/ currPrice);
                //use 20% of account rounded down
                if(bank > amountToBuy* currPrice)
                {
                    System.out.println("Amount of stocks Bought: " + amountToBuy);
                    addShares(stockName,currPrice,amountToBuy);
                    action = 1 ;
                }
            }
            //exit holdings once we reach end of data
            if(counter == data.size())
            {
                System.out.println("End of Data, All shares are sold");
                sellAllShares(stockName,currPrice);
                action = -1;
            }
            printPortfolio();
            System.out.println("-------------------------------------------");
        }
        return action;
    }

    public int stockEvauluator2(List<List<String>> stockData,int rsiPeriod)
    {
        calculateData cd = new calculateData(stockData);
        String stockName = "VOO";

        List<List<String>> data = cd.getData();
        List<List<String>> currData;
        int counter = rsiPeriod;
        double moneyToSpend;
        int amountToBuy = 0;
        float currPrice;
        int action = 0;

        for(int i = rsiPeriod; i <data.size(); i++ )
        {
            currData = data.subList(0,i+1);
            counter++;
            cd = new calculateData(currData);
            currPrice = Float.parseFloat(currData.get(currData.size()-1).get(4));
            List<Float> rsiValues = cd.calculateRSI(rsiPeriod);
            List<Float> maValues = cd.smoothData(rsiPeriod);

            System.out.println("Starting Balance: " + bank);
            System.out.println("Day " + counter);
            System.out.println(currData);
            System.out.println("RSI over "+rsiPeriod+ " Days " + rsiValues);
            System.out.println("MA over "+rsiPeriod+ " Days " + maValues);
            System.out.println(stockName + " current price: " + currPrice);

            //buy if RSI is oversold under 31 and current price is below the moving average
            if(rsiValues.get(rsiValues.size()-1) <= 30  && maValues.get(maValues.size()-1) <= currPrice)
            {
                moneyToSpend= bank *.3;
                amountToBuy = (int) (moneyToSpend/ currPrice);
                if(bank > amountToBuy* currPrice)
                {
                    System.out.println("Amount of stocks Bought: " + amountToBuy);
                    addShares(stockName,currPrice,amountToBuy);
                    action = 1;
                }
                else
                {
                    System.out.println("Not enough funds to execute trade");
                }
            }
            // sell shares if RSI is overbought and current price is above the moving average
            else if(rsiValues.get(rsiValues.size()-1) >= 70 || maValues.get(maValues.size()-1) >= currPrice)
            {
                int sharesToSell = (int) (getTotalShareAmount(stockName)*.4);
                System.out.println("Amount of stocks sold: " +sharesToSell );
                sellShares(stockName, sharesToSell,currPrice);
                action = -1;
            }
            printPortfolio();
            System.out.println("-------------------------------------------");
        }
        return action;
    }

    public int stockEvauluator3(List<List<String>> stockData,int rsiPeriod)
    {
        calculateData cd = new calculateData(stockData);
        String stockName = "VOO";
        List<List<String>> data = cd.getData();
        List<List<String>> currData;
        int counter = rsiPeriod;
        double moneyToSpend;
        int amountToBuy = 0;
        float currPrice;
        int action = 0;

        for(int i = rsiPeriod; i <data.size(); i++ )
        {
            currData = data.subList(0,i+1);
            counter++;
            cd = new calculateData(currData);
            currPrice = Float.parseFloat(currData.get(currData.size()-1).get(4));
            List<Float> rsiValues = cd.calculateRSI(rsiPeriod);
            List<Float> maValues = cd.smoothData(rsiPeriod);


            System.out.println("Starting Balance: " + bank);
            System.out.println("Day " + counter);
            System.out.println(currData);
            System.out.println("RSI over "+rsiPeriod+ " Days " + rsiValues);
            System.out.println("MA over "+rsiPeriod+ " Days " + maValues);
            System.out.println(stockName + " current price: " + currPrice);


            //start of trading. Bot will trade 20%  account at closing price
            if(counter-1 == rsiPeriod)
            {
                moneyToSpend = bank*.2;
                amountToBuy = (int) (moneyToSpend/ currPrice);
                //use 20% of account rounded down
                if(bank > amountToBuy* currPrice)
                {
                    System.out.println("Amount of stocks Bought: " + amountToBuy);
                    addShares(stockName,currPrice,amountToBuy);
                    action = 1;
                }
            }
            //buy every 30th day at 20% of account
            else if(counter%30  == 0)
            {
                moneyToSpend= bank *.1;
                amountToBuy = (int) (moneyToSpend/ currPrice);
                if(bank > amountToBuy* currPrice)
                {
                    System.out.println("Amount of stocks Bought: " + amountToBuy);
                    addShares(stockName,currPrice,amountToBuy);
                    action = 1;
                }
                else
                {
                    System.out.println("Not enough funds to execute trade");
                }


            }
            //exit holdings once we reach end of data
            if(counter == data.size())
            {
                System.out.println("End of Data, All shares are sold");
                sellAllShares(stockName,currPrice);
                action = -1;
            }
            printPortfolio();
            System.out.println("-------------------------------------------");
        }
        return action;
    }
}
