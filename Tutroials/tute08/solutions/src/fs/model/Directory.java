package fs.model;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A directory inode that contains multiple sub directories/files
 */
public class Directory extends Inode implements Expandable {
    private List<Inode> innerInodes = new ArrayList<>();

    public Directory(Path path) {
        super(path);
        this.innerInodes = new ArrayList<>();
    }

    public List<Inode> expand() {
        // an extension on this could do dynamic updates everytime you expand it
        if (innerInodes.size() == 0) {
            Path path = Paths.get(getPath());
            BasicFileAttributes attribs;
            try {
                attribs = Files.readAttributes(path, BasicFileAttributes.class);
                if (attribs.isDirectory()) {
                    DirectoryStream<Path> dir = Files.newDirectoryStream(path);
                    for (Path innerFile : dir) {
                        if (Files.isDirectory(innerFile)) {
                            innerInodes.add(new Directory(innerFile));
                        } else {
                            innerInodes.add(new File(innerFile));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return innerInodes;
        }
        return Arrays.<Inode>asList();
    }

    @Override
    public long getFileSize() {
        return super.getFileSize() + innerInodes.stream().mapToLong(i -> i.getFileSize()).sum();
    }

    @Override
    public String getIcon(boolean isExpanded) {
        return isExpanded ? "folder-open.png" : "folder.png";
    }

    @Override
    public String getName() {
        return Paths.get(getPath()).toString();
    }
}
