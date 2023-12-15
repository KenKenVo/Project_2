import java.util.List;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.awt.Point;




public class csvWriter
{
    public csvWriter()
    {
    }


    public void writeToCSV(List<Point> pointList, String head, String title) throws Exception
    {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(title+".csv"), StandardCharsets.UTF_16))) {
            String header = head;
            writer.write(header + "\n");

            for(Point p : pointList)
            {
                writer.write((p.x) + ","+p.y + "\n");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
