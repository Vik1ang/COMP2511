package unsw.fileEditor;

public class EditorFactory implements AbstractFactory {

    @Override
    public Editor getInstance(String type) {
        Editor editor = null;
        if (type.equals("HTML Editor")) {
            editor = new HtmlEditor();
        } else if (type.equals("Text Editor")) {
            editor = new TextEditor();
        }
        return editor;
    }
}
