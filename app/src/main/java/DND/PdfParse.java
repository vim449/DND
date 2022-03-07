package DND;

import java.io.IOException;
import java.util.Hashtable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import java.util.regex.*;

public class PdfParse {
    private String filepath = "";
    private Hashtable<String, Integer> classesTable = new Hashtable<>();
    private Hashtable<String, Integer> racesTable = new Hashtable<>();
    private Hashtable<String, Integer> equipmentTable = new Hashtable<>();

    public PdfParse(String inputFilePath) {
        this.filepath = inputFilePath;
    }

    public Hashtable<String, Integer> getClassesTable(int tableOfContentsPage) {

        // read in a page number from the pdf
        String pdfText = "";
        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            pdfText = text.getTextFromPage(tableOfContentsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // strip the string off all whitespace and periods
        pdfText = pdfText.replaceAll(" ", "");
        pdfText = pdfText.replaceAll("\\.", "");

        // find all the classes in the table of contents
        Pattern p1 = Pattern.compile("Classes(.*?)Chapter", Pattern.DOTALL);
        Matcher classes = p1.matcher(pdfText);

        // check to see if there is a match
        if (classes.find()) {
            // grabbing the string of all the classes from the pdf page text
            String classesString = classes.group(0);

            // create the regex for parsing the classes string
            Pattern p = Pattern.compile("([0-9]+)");
            String strings[] = classesString.split("\\n");

            // grab the last groups of numbers from the matched groups
            for (int i = 0; i < strings.length; i++) {
                Matcher matches = p.matcher(strings[i]);
                if (matches.find()) {
                    int pageNum = Integer.valueOf(matches.group(matches.groupCount()));

                    classesTable.put(strings[i].replaceAll(matches.group(matches.groupCount()), ""), pageNum);
                }
            }
        }
        return classesTable;
    }

    public Hashtable<String, Integer> getRacesTable(int tableOfContentsPage) {

        // read in a page number from the pdf
        String pdfText = "";
        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            pdfText = text.getTextFromPage(tableOfContentsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // strip the string off all whitespace and periods
        pdfText = pdfText.replaceAll(" ", "");
        pdfText = pdfText.replaceAll("\\.", "");

        // find all the classes in the table of contents
        Pattern p1 = Pattern.compile("Races(.*?)Chapter", Pattern.DOTALL);
        Matcher races = p1.matcher(pdfText);

        // check to see if there is a match
        if (races.find()) {
            // grabbing the string of all the classes from the pdf page text
            String racesString = races.group(0);

            // create the regex for parsing the classes string
            Pattern p = Pattern.compile("([0-9]+)");
            String strings[] = racesString.split("\\n");

            // grab the last groups of numbers from the matched groups
            for (int i = 0; i < strings.length; i++) {
                Matcher matches = p.matcher(strings[i]);
                if (matches.find()) {
                    int pageNum = Integer.valueOf(matches.group(matches.groupCount()));

                    racesTable.put(strings[i].replaceAll(matches.group(matches.groupCount()), ""), pageNum);
                }
            }
        }
        return racesTable;
    }

    public Hashtable<String, Integer> getEquipmentTable(int tableOfContentsPage) {

        // read in a page number from the pdf
        String pdfText = "";
        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            pdfText = text.getTextFromPage(tableOfContentsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // strip the string off all whitespace and periods
        pdfText = pdfText.replaceAll(" ", "");
        pdfText = pdfText.replaceAll("\\.", "");

        // find all the classes in the table of contents
        Pattern p1 = Pattern.compile("Equipment(.*?)Chapter", Pattern.DOTALL);
        Matcher equipment = p1.matcher(pdfText);

        // check to see if there is a match
        if (equipment.find()) {
            // grabbing the string of all the classes from the pdf page text
            String eString = equipment.group(0);

            // create the regex for parsing the classes string
            Pattern p = Pattern.compile("([0-9]+)");
            String strings[] = eString.split("\\n");

            // grab the last groups of numbers from the matched groups
            for (int i = 0; i < strings.length; i++) {
                Matcher matches = p.matcher(strings[i]);
                if (matches.find()) {
                    int pageNum = Integer.valueOf(matches.group(matches.groupCount()));

                    equipmentTable.put(strings[i].replaceAll(matches.group(matches.groupCount()), ""), pageNum);
                }
            }
        }
        return equipmentTable;
    }
}
