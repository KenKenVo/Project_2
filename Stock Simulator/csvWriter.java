import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class csvWriter
{

    public csvWriter()
    {

    }

    public void writeToCSV(List<Float> list, String title, String head) throws Exception
    {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(title+".csv"), StandardCharsets.UTF_16))) {
            String header = head;
            writer.write(header + "\n");
            for(Float i : list)
            {
                writer.write( i + "\n");
            }


        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
