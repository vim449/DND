package DND;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
// Importing Apache POI classes
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class PdfParse {
    private String book = "";
    private String filepath = "";
    private File file;

    public PdfParse(String inputFilePath){
        this.filepath = inputFilePath;
    }

    public String returnString(){
        file = new File(filepath);
        BodyContentHandler contenthandler = new BodyContentHandler();
        
        try (// Create a file input stream
             // on specified path with the created file
            FileInputStream fstream = new FileInputStream(file)) {
            // Create an object of type Metadata to use
            Metadata data = new Metadata();

            // Create a context parser for the pdf document
            ParseContext context = new ParseContext();

            // PDF document can be parsed using the PDFparser
            // class
            PDFParser pdfparser = new PDFParser();

            // Method parse invoked on PDFParser class
            pdfparser.parse(fstream, contenthandler, data, context);
        } catch (IOException | TikaException | SAXException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            return contenthandler.toString();
        }
    }
}
