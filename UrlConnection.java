import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class UrlConnection{
    public String displayData(String url){
        String displayedOutput = null;
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("data.txt"));

            // Get the URL from the text field
            URL urlText = new URL(url);

            // Open a connection to the URL
            URLConnection connection = urlText.openConnection();

            // Read the contents of the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                displayedOutput = stringBuilder.append(line).append("\n").toString();
            }
            output.write(displayedOutput);
            reader.close();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return displayedOutput;
    } 

    public void clearTextFile(){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("data.txt"));
            // Read the contents of the URL         
            output.write("");
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void convertToCSV(String text) {
        try (FileWriter writer = new FileWriter("a CSV output.csv")) {
            String[] lines = text.split("\\n");

            for (String line : lines) {
                String[] fields = line.split("\\s+");
                StringBuilder csvLine = new StringBuilder();

                for (String field : fields) {
                    csvLine.append(field).append(",");
                }

                csvLine.deleteCharAt(csvLine.length() - 1);
                csvLine.append("\n");

                writer.write(csvLine.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToXML(String text) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<root>\n");

        String[] lines = text.split("\\n");
        for (String line : lines) {
            xmlBuilder.append("<line>")
            .append(line)
            .append("</line>\n");
        }

        xmlBuilder.append("</root>");

        try (FileWriter writer = new FileWriter("an XML output.xml")) {
            writer.write(xmlBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}