package DND;

import com.aspose.pdf.*;

public class PdfParse {
    String filepath = "";

    public PdfParse(String inputFilePath) {
        this.filepath = inputFilePath;
    }

    public void reuturnObject() {
        // The path to the documents directory.
        String _dataDir = "C:/Users/thoma/Documents/Java Projects/DND/app/src/main/java/DND/";
        String filePath = _dataDir + "D&D 5e - Players Handbook.pdf";

        // Open document
        com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(filePath);

        // Create TextAbsorber object to extract text
        com.aspose.pdf.TextAbsorber textAbsorber = new com.aspose.pdf.TextAbsorber();

        // Accept the absorber for all the pages
        pdfDocument.getPages().accept(textAbsorber);

        // Get the extracted text
        String extractedText = textAbsorber.getText();
        try {
            java.io.FileWriter writer = new java.io.FileWriter(_dataDir + "extracted-text.txt", true);
            // Write a line of text to the file
            writer.write(extractedText);
            // Close the stream
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
