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

    public Vector<Vector<String>> getSpellTierList(int startingPageNum, String className) {
        // intialize variables
        String pdfText = "";
        int pageNum = startingPageNum - 50;
        String spellString = "";

        try {
            // Read in pdf and extract text
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);

            // expand the className so it matches with the text
            className = className.replace("", " ").trim();
            // complie the regex
            Pattern p = Pattern.compile(className + "  Spells(.*?)Spell", Pattern.DOTALL);
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
            spellString += "\n5"; // add some arbitray number so regex works
        }

        // preform some black magic regex to get a vector of vectors of leves of spells
        p = Pattern.compile("\\R(.*?)[0-9]", Pattern.DOTALL);
        matches = p.matcher(spellString);

        Vector<Vector<String>> spellList = new Vector<Vector<String>>(5);

        while (matches.find()) {
            String spells = "";
            spells = matches.group(1);
            Pattern p1 = Pattern.compile("^(.*?)\\R", Pattern.MULTILINE);
            Matcher matches1 = p1.matcher(spells);
            Vector<String> listOfSpells = new Vector<String>(5);
            String string = "";

            while (matches1.find()) {
                string = matches1.group(1);
                string = string.replace("  ", "HH"); // this is fixing the random spacing issue
                string = string.replace(" ", "");
                string = string.replace("HH", " ");
                string = string.trim();
                listOfSpells.add(string);
            }
            spellList.add(listOfSpells);
        }
        spellList.remove(0);
        return spellList;

    }

    public Hashtable<String, String> getSpellInfo(int startingPage, String spell) {

        String pdfText = "";
        Hashtable<String, String> spellInfo = new Hashtable<>();
        Pattern castingPattern = Pattern.compile("((?<=Casting  Time:).*[0-9]?)");
        Pattern rangePattern = Pattern.compile("((?<=Range:).*[0-9]?)");
        Pattern durationPattern = Pattern.compile("((?<=Duration:).*[0-9]?)");
        Pattern componentsPattern = Pattern.compile("((?<=Components:).*?(?=Duration))", Pattern.DOTALL);
        Pattern namePattern = Pattern.compile("(^[A-Z]\\s[a-z]+\\s[a-z].*?$).*?(?=^[1-9])",
                Pattern.MULTILINE | Pattern.DOTALL);

        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            pdfText = text.getTextFromPage(193);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Matcher castingMatch = castingPattern.matcher(pdfText);
        Matcher rangeMatch = rangePattern.matcher(pdfText);
        Matcher durationMatcher = durationPattern.matcher(pdfText);
        Matcher comMatcher = componentsPattern.matcher(pdfText);
        Matcher nameMatcher = namePattern.matcher(pdfText);

        while (nameMatcher.find() && comMatcher.find() && castingMatch.find() && rangeMatch.find()
                && durationMatcher.find()) {

            String pdfSpellName = nameMatcher.group(1).replace(" ", "").trim();
            String inputSpellName = spell.replace(" ", "").trim();

            if (pdfSpellName.equals(inputSpellName)) {
                System.out.println(nameMatcher.group(1) + " " + comMatcher.group(1));
                spellInfo.put("Name", nameMatcher.group(1));
                spellInfo.put("Casting Time", castingMatch.group(1));
                spellInfo.put("Range", rangeMatch.group(1));
                spellInfo.put("Components", comMatcher.group(1));
                spellInfo.put("Duration", durationMatcher.group(1));
            }

        }

        return spellInfo;
    }
}
