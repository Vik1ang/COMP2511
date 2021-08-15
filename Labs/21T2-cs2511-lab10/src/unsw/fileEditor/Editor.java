package unsw.fileEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public abstract class Editor {
    JFrame frame;
    JMenuBar menuBar;
    JToolBar toolBar;
    WindowAdapter windowAdapter;
    JDesktopPane pane;
    ActionListener listener;

    public void setFrameVisible(boolean value) {
        frame.setVisible(value);
    }
}
