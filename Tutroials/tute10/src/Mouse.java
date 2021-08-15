
public class Mouse implements ComputerComponent {
    private String name;

    public Mouse(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}