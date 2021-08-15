
public class ComputerVisitor {
    
    private boolean validated = false;

    public void visit(Keyboard keyboard) {
        System.out.println("Looking at keyboard " + keyboard + " which has " + keyboard.getNumKeys() + " keys.");
    }

    public void visit(Mouse mouse) {
        System.out.println("Looking at mouse " + mouse);
    }

    public void visit(Computer computer) {
        System.out.println("Looking at computer " + computer + " with memory " + computer.getMemory() + " GB.");
    }

    public void validate() {
        validated = true;
    }

    public boolean validated() {
        return validated;
    }

    public static void main(String[] args) {
        ComputerComponent computer = new Computer("Corelli", 500);
        ComputerComponent keyboard = new Keyboard("Mechanical keyboard");
        ComputerComponent mouse = new Mouse("Bluetooth mouse");
        ComputerVisitor visitor = new ComputerVisitor();

        computer.accept(visitor);
        visitor.validate();
        
        computer.accept(visitor);
        keyboard.accept(visitor);
        mouse.accept(visitor);
    }

}