public interface ComputerComponent {
    /**
     * Accepts a visitor.
     * @param visitor
     */
    public void accept(ComputerVisitor visitor);
}


