import io.basc.framework.env.Sys;

import java.awt.*;
import java.util.List;

public class smoother
{
    public smoother()
    {
    }

    public List<Point> smoothSaltedList(List<Point> saltedList,Integer windowVal)
    {
        List<Point> smoothedList = saltedList;
        List<Point> subList;

        for(int i=0; i<smoothedList.size();i++)
        {
            double total = 0;
            if(i-windowVal <= 0 && (i+windowVal+1)<=smoothedList.size())
            {
                subList= smoothedList.subList(0,i+windowVal+1);
                for(Point p : subList)
                {
                    total = total + p.y;
                }
                total = total / subList.size();
            }
            else if(i+windowVal > smoothedList.size() && i-windowVal-1>=0)
            {
                if(i-windowVal-1<=0)
                {
                    subList = smoothedList;
                }
                else
                {
                    subList= smoothedList.subList(i-windowVal-1,smoothedList.size());
                }
                for(Point p : subList)
                {
                    total = total + p.y;
                }
                total = total / subList.size();
            }
            else
            {
                if(i-windowVal<0)
                {
                    if(i+windowVal>=smoothedList.size())
                    {
                        subList= smoothedList;
                    }
                    else
                    {
                        subList= smoothedList.subList(0,i+windowVal+1);
                    }

                }
                else if(i+windowVal+1>smoothedList.size())
                {
                    subList= smoothedList.subList(i-windowVal,smoothedList.size());
                }
                else
                {
                    subList= smoothedList.subList(i-windowVal,i+windowVal+1);
                }
                for(Point p : subList)
                {
                    total = total + p.y;
                }
                total = total / subList.size();
            }
            smoothedList.get(i).setLocation(smoothedList.get(i).getX(),total);
        }
        return smoothedList;
    }
}
