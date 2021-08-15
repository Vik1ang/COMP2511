package unsw.quaint.tools;

import javafx.scene.image.Image;
import unsw.quaint.strategies.IToolStrategy;

/**
 * Represents a 'tool' in the paint application such as
 * drawing a rectangle.
 * 
 * @author Braedon Wooding
 */
public final class Tool{
    private String icon;
    private String tooltip;
    private String name;
    private IToolStrategy strategy;

    public Tool(IToolStrategy strategy, String icon, String tooltip, String name) {
        this.icon = icon;
        this.tooltip = tooltip;
        this.name = name;
        this.strategy = strategy;
    }

    /**
     * Get the icon for this tool.
     */ 
    public Image getIcon() {
        return new Image(getClass().getResourceAsStream(icon));
    }

    /**
     * Get's the name for the tool.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the tooltip for this tool.
     */
    public String getTooltip() {
        return tooltip;
    }

    public IToolStrategy getToolActions() {
        return strategy;
    }
}
