package DND;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

import java.util.regex.*;

public class PdfParse {
    private static String filepath = "";

    public static void setFilepath(String f) {
        filepath = f;
    }
    public enum Info {
		// TODO: Im not sure what spell tier list is, but it might be able
		// to be added to this with a few changes
		Class("Classes(.*?)Chapter"),
		Race("Races(.*?)Chapter"),
		Equipment("Equipment(.*?)Chapter"),
		Spell("Spells(.*?)Appendix");
		public String searchRegex;
		private Info(String searchRegex) { this.searchRegex = searchRegex;}
	}

	public static Hashtable<String, Integer> getInfoPages(int tableOfContentsPage, Info info) {

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

        // Find all info matching
        Pattern p1 = Pattern.compile(info.searchRegex, Pattern.DOTALL);
        Matcher matches = p1.matcher(pdfText);

        // check to see if there is a match
        Hashtable<String, Integer> infoTable = new Hashtable<>();
        if (matches.find()) {
            // grabbing the string of all the matches from the pdf page text
            String matchesString = matches.group(0);

            // create the regex for parsing the matches string
            Pattern p = Pattern.compile("([0-9]+)");
            String strings[] = matchesString.split("\\n");

            // grab the last groups of numbers from the matched groups
            for (int i = 0; i < strings.length; i++) {
                Matcher matches = p.matcher(strings[i]);
                if (matches.find()) {
                    int pageNum = Integer.valueOf(matches.group(matches.groupCount()));

                    infoTable.put(strings[i].replaceAll(matches.group(matches.groupCount()), ""), pageNum);
                }
            }
        }
        String itemToRemove;
        switch(info){
            case Info.Class:
                itemToRemove = "Classes";
            case Info.Race:
                itemToRemove = "ChoosingaRace";
            case default:
                itemToRemove = "";
        }
        if (itemToRemove.length > 0){
            infoTable.remove(itemToRemove);
        }
        return infoTable;
    }

    public static Hashtable<String, Integer> getSpellPages(int tableOfContentsPage) {

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
        Hashtable<String, Integer> spellTable = new Hashtable<>();
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

    public static Vector<Vector<String>> getSpellTierList(int startingPageNum, String className) {
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

    public static Vector<Race> getRaceInfo(int startingPage, int stopingPage, String raceName) {
        Vector<Race> racesVector = new Vector<>();
        String pdfText = "";

        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            int pageNum = startingPage;
            while (pageNum < stopingPage) {
                pdfText += text.getTextFromPage(pageNum);
                pageNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern paraPattern = Pattern.compile("\\. $");
        Pattern sizePattern = Pattern.compile("^Size.  (.*?)$", Pattern.MULTILINE);
        Pattern speedPattern = Pattern.compile("^Speed.  (.*?)$", Pattern.MULTILINE);
        Pattern hightPattern = Pattern.compile("(?<=Age).*?([0-9]+)", Pattern.DOTALL);
        Pattern attributePattern = Pattern.compile("(?<=\\. [\\r\\n])(.*?\\.)");

        System.out.println(pdfText);
        return racesVector;
    }
    // TODO: make a function for class info
    // TODO: make a function for getting bacground
    // TODO: Make ka function for getting equitment stats(?<=\. [\r\n])(.*?\.)
}
