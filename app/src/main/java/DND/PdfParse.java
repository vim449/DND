package DND;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import java.util.regex.*;

public class PdfParse {
    private String filepath = "";
    private Hashtable<String, Integer> classesTable = new Hashtable<>();
    private Hashtable<String, Integer> racesTable = new Hashtable<>();
    private Hashtable<String, Integer> equipmentTable = new Hashtable<>();
    private Hashtable<String, Integer> spellTable = new Hashtable<>();

    public PdfParse(String inputFilePath) {
        this.filepath = inputFilePath;
    }

    public Hashtable<String, Integer> getClassesPages(int tableOfContentsPage) {

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

    public Hashtable<String, Integer> getRacesPages(int tableOfContentsPage) {

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

    public Hashtable<String, Integer> getEquipmentPages(int tableOfContentsPage) {

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

    public Hashtable<String, Integer> getSpellPages(int tableOfContentsPage) {

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
        Pattern p1 = Pattern.compile("Spells(.*?)Appendix", Pattern.DOTALL);
        Matcher spells = p1.matcher(pdfText);

        // check to see if there is a match
        if (spells.find()) {
            // grabbing the string of all the classes from the pdf page text
            String spellString = spells.group(0);

            // create the regex for parsing the classes string
            Pattern p = Pattern.compile("([0-9]+)");
            String strings[] = spellString.split("\\n");

            // grab the last groups of numbers from the matched groups
            for (int i = 0; i < strings.length; i++) {
                Matcher matches = p.matcher(strings[i]);
                if (matches.find()) {
                    int pageNum = Integer.valueOf(matches.group(matches.groupCount()));

                    spellTable.put(strings[i].replaceAll(matches.group(matches.groupCount()), ""), pageNum);
                }
            }
        }
        return spellTable;
    }

    public Vector<String> getSpellTierList(int startingPageNum, String className) {
        // intialize variables
        String pdfText = "";
        int pageNum = startingPageNum;
        String spellString = "";
        Vector<String> spellList = new Vector<String>(2);

        try {
            // Read in pdf and extract text
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);

            // expand the className so it matches with the text
            className = className.replace("", " ").trim();
            // complie the regex
            Pattern p = Pattern.compile(className + "  Spells(.*?)Spells", Pattern.DOTALL);
            Matcher matches;
            boolean keepGoing = true;

            // loop to keep reading in information untill found a match
            do {
                pdfText += text.getTextFromPage(pageNum); // grab text from the page
                matches = p.matcher(pdfText); // try to match it
                // if found a match exit the loop and store the matched string
                if (matches.find()) {
                    spellString = matches.group(0);
                    keepGoing = false;
                }
                pageNum++; // increment to the next page
            } while (keepGoing);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // start to parse the spells string

        // take the end off of the string it is not needed
        Pattern p = Pattern.compile("\\R(.*)$");
        Matcher matches = p.matcher(spellString);
        if (matches.find()) {
            spellString = spellString.replace(matches.group(0), "");
        }

        // preform some black magic regex to get a vector of vectors of leves of spells
        p = Pattern.compile("\\R(.*?)[0-9]", Pattern.DOTALL);
        matches = p.matcher(spellString);
        while (matches.find()) {
            for (int i = 0; i < matches.groupCount(); i++) {
                System.out.println(matches.group(i)); // TODO: add this to a vector of vectors @yomas000
            }
        }

        // System.out.println(spellString);
        return spellList;
    }
}
