package fs.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class File extends Inode {
    public File(Path path) {
        super(path);
    }

    @Override
    public String getIcon(boolean isExpanded) {
        // would be quite easy to have many images for this file here
        return "text-x-generic.png";
    }

    @Override
    public String getName() {
        return Paths.get(getPath()).getFileName().toString();
    }
}
