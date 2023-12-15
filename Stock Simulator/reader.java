import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class reader
{
    public reader()
    {
    }

    public ArrayList<List<String>> CSVReader()
    {
        String file = "VOO.csv";
        String line;
        ArrayList<List<String>> data = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            line = br.readLine();
            line = br.readLine();

            while (line != null)
            {
                List<String> parts =
                        Arrays.asList(line.split(","));
                data.add(parts);
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return data;
    }
}
