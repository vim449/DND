package DND.GUI;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class Gui extends Application {
    String[] books;

    public static void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("title");
        StackPane layout = new StackPane();

        // Button button = new Button("Click Me");
        // layout.getChildren().add(button);
        // button.setOnAction(e -> System.out.println("Clicked"));

        getBooks(primaryStage);
        System.out.println(Arrays.toString(books));

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getBooks(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        // apply the getPath function on each File, then cast the list ot an array
        books = files.stream().map(File::getPath).collect(Collectors.toList()).toArray(String[]::new);
    }
}
