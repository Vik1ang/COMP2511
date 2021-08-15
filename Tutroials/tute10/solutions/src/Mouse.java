
public class Mouse implements ComputerComponent {
    private String name;

    public Mouse(String name) {
        this.name = name;
    }

	@Override
	public void accept(ComputerVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}