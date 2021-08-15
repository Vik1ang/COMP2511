package fs.ui;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));

        String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException x) {
            // just default to 'computer'
            hostName = "computer";
        }

        TreeItem<String> rootNode = new TreeItem<>(hostName,
                new ImageView(new Image(ClassLoader.getSystemResourceAsStream("fs/resources/computer.png"))));
        FileSystems.getDefault() //
                .getRootDirectories() //
                .forEach(name -> rootNode.getChildren().add(new FilePathTreeItem(name)));
        rootNode.setExpanded(true);

        // :(, java generics don't let us do this in a safe way so we need to just *trust* it's correct
        // the problem is because of type erasure no way to know what specific <?> we are looking at here.
        @SuppressWarnings("unchecked")
        TreeView<String> treeView = (TreeView<String>) root.lookup("#treeView");
        treeView.setRoot(rootNode);

        primaryStage.setTitle("File Browser");
        primaryStage.setScene(new Scene(root, 800, 300));
        primaryStage.show();
    }
}
