package fs.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilePathTreeItem extends TreeItem<String> {
    public static Image folderCollapseImage = new Image(
            ClassLoader.getSystemResourceAsStream("fs/resources/folder.png"));
    public static Image folderExpandImage = new Image(
            ClassLoader.getSystemResourceAsStream("fs/resources/folder-open.png"));
    public static Image fileImage = new Image(ClassLoader.getSystemResourceAsStream("fs/resources/text-x-generic.png"));

    private String fullPath;

    public String getFullPath() {
        return (this.fullPath);
    }

    private boolean isDirectory;

    public boolean isDirectory() {
        return this.isDirectory;
    }    

    @Override
    public boolean isLeaf() {
        return !isDirectory();
    }

    public FilePathTreeItem(Path file) {
        super(file.toString());
        this.fullPath = file.toString();

        // test if this is a directory and set the icon
        if (Files.isDirectory(file)) {
            this.isDirectory = true;
            this.setGraphic(new ImageView(folderCollapseImage));
        } else {
            this.isDirectory = false;
            this.setGraphic(new ImageView(fileImage));
        }

        // set the value
        if (!fullPath.endsWith(File.separator)) {
            // set the value (which is what is displayed in the tree)
            String value = file.toString();
            int indexOf = value.lastIndexOf(File.separator);
            if (indexOf > 0) {
                this.setValue(value.substring(indexOf + 1));
            } else {
                this.setValue(value);
            }
        }

        addEventHandler(TreeItem.<String>branchExpandedEvent(), (ev) -> {
            FilePathTreeItem source = (FilePathTreeItem) ev.getSource();
            if (source.isDirectory() && source.isExpanded()) {
                ImageView iv = (ImageView) source.getGraphic();
                iv.setImage(folderExpandImage);
            }
            try {
                if (source.getChildren().isEmpty()) {
                    Path path = Paths.get(source.getFullPath());
                    BasicFileAttributes attribs = Files.readAttributes(path, BasicFileAttributes.class);
                    if (attribs.isDirectory()) {
                        DirectoryStream<Path> dir = Files.newDirectoryStream(path);
                        for (Path innerFile : dir) {
                            FilePathTreeItem treeNode = new FilePathTreeItem(innerFile);
                            source.getChildren().add(treeNode);
                        }
                    }
                } else {
                    // if you want to implement rescanning a directory for changes this would be the
                    // place to do it
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        });

        this.addEventHandler(TreeItem.<String>branchCollapsedEvent(), (ev) -> {
            FilePathTreeItem source = (FilePathTreeItem) ev.getSource();
            if (source.isDirectory() && !source.isExpanded()) {
                ImageView iv = (ImageView) source.getGraphic();
                iv.setImage(folderCollapseImage);
            }
        });
    }
}
