package unsw.fileEditor;

public interface AbstractFactory {
    Editor getInstance(String type);
}
