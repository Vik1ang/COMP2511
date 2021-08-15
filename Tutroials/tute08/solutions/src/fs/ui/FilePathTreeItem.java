package fs.ui;

import fs.model.Expandable;
import fs.model.Inode;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * TreeItem's are the controller to the view TreeCell.
 */
public class FilePathTreeItem extends TreeItem<String> {
    private Inode inode;

    public FilePathTreeItem(Inode inode) {
        super(inode.getPath());
        this.inode = inode;

        this.setGraphic(new ImageView(new Image(
            ClassLoader.getSystemResourceAsStream("fs/resources/" + inode.getIcon(false)))));
        this.setValue(String.format("%s (%f KiB)", inode.getName(), inode.getFileSize() / 1024.0));

        addEventHandler(TreeItem.branchCollapsedEvent(), (ev) -> {
            ImageView iv = (ImageView) getGraphic();
            // in reality you would probably cache these images for performance reasons...
            iv.setImage(new Image(ClassLoader.getSystemResourceAsStream("fs/resources/" + inode.getIcon(false))));
        });

        if (inode instanceof Expandable) {
            this.addEventHandler(TreeItem.branchExpandedEvent(), (ev) -> {
                ImageView iv = (ImageView) getGraphic();
                // in reality you would probably cache these images for performance reasons...
                iv.setImage(new Image(ClassLoader.getSystemResourceAsStream("fs/resources/" + inode.getIcon(true))));

                // after expansion we may need to render some new cells
                for (Inode innerInode : ((Expandable)inode).expand()) {
                    getChildren().add(new FilePathTreeItem(innerInode));
                }
            });

            this.addEventHandler(TreeItem.childrenModificationEvent(), (ev) -> {
                this.setValue(String.format("%s (%f KiB)", inode.getName(), inode.getFileSize() / 1024.0));
            });
        }
    }

    @Override
    public boolean isLeaf() {
        return !(inode instanceof Expandable);
    }
}
