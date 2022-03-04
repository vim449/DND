package DND;

import java.io.IOException;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

public class PdfParse {
    String filepath = "";

    public PdfParse(String inputFilePath) {
        this.filepath = inputFilePath;
    }

    public String reuturnObject() {
        String pdfText = "";
        try {
            PdfReader reader = new PdfReader(filepath);
            PdfTextExtractor text = new PdfTextExtractor(reader);
            pdfText = text.getTextFromPage(188);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pdfText);
        return pdfText;
    }
}
