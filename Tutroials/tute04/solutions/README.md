# Tutorial 04

## A. Requirements Analysis Revision (20 minutes)

### Part 1 (5 minutes)

a. Discuss the structure of this user story as a class, and create some acceptance criteria (Given/When/Then and Yes/No).

**As someone who uses GitLab, I want to be able to see all of my repositories currently 'active' so that I don't have to sift through old repos to find my current projects.**

b. What are story points? See [this Atlassian guide](https://www.atlassian.com/agile/project-management/estimation) for more information.

> * Estimation of the length of time a task will take
* Uses fibonacci numbering to make estimations

c. How do we prioritise user stories and epic stories? See [this guide](https://www.productplan.com/learn/prioritize-product-backlog/) for more information.

> * High priority - we MUST have this, or else we don't have a product
* Medium priority - useful and make the product stand out, but not absolutely vital
* Low priority - nice to have, but aren't essential

### Part 2

In groups, create user stories and acceptance criteria for the following requirements. Structure these stories under at least one epic story.  You are only going to do 2 out of the many user stories below to demonstrate this (the tutorial answers will have them all).

As a creatively minded individual I want the ability to create beautiful art like the following french duck.

![Le Quack](lequack.png)

To accomplish this I need the following, you have to

- The ability to draw shapes such as rectangles and ovals, you should be able to give shapes constant aspect ratios by holding shift while dragging
- The ability to select a region then perform operations on it, regions are selected via a box
  - You can delete a region with ctrl + d
  - You can paste a region with ctrl + v, this won't clear the old region and will just paste it (i.e. copy + paste)

Other features that exist that you don't need to write a user story for are:

- The ability to paint using a brush like tool
- The ability to remove mistakes
- The ability to save my picture out to a location of my choosing
- The ability to load an image onto the canvas by clicking the image load tool, selecting the image you want to insert, then clicking where you want to insert it.
  - After placing the image it should return to just the simple cursor.
- The ability to change colours using a colour wheel for the strokes and shapes
- A new pipette tool that appears next to the colour selector that lets you pick the stroke/fill colour based upon the canvas.

For your reference, all the above user stories are implemented in the application specified in the tute.

In case you wish to run the application to understand how these user stories interact you will need to install JavaFX if running locally, these instructions are specified in the lab.

> Answers:

* Epic “POC/MVP - Quaint Shapes” or "Implement Shapes" are both acceptable here.
* User Stories for Shapes (Rect/Oval)
  * U: 1. As a user I want the ability to draw shapes by dragging out a region so I can make more interesting art
    * AC: 1. A rectangle tool (with image "rect.png") and oval tool (with image "oval.png") should be added
    * AC: 2. Clicking on the tool, should activate a 'shape' mode which will then begin showing a preview of the shape when I begin to drag (dashed outline)
    * AC: 3. On finishing the drag, the shape should be placed onto the canvas
    * AC: 4. Pressing escape at any point during the process prior to finishing the drag should end the shape drawing and not output anything to the canvas
  * U: 2. As a user I want the ability to drag shapes with a constant ratio of width/height
    * AC: 1. In the case of a constant ratio width = height = min(width, height)
    * AC: 2. Pressing 'shift' while dragging any sort of shape should activate this
    * AC: 3. Letting go of shift should revert to the normal shape drawing mode
    * AC: 4. The preview of the shape should also have a constant ratio
* User Stories for region selecting
  * U: 1. As a user I want the ability to select a boxed region and manipulate it so I can make changes to my art without having to erase it
    * AC: 1. A box select tool (with image "box-select.png") should be added
    * AC: 2. On clicking the tool, shape mode should be activated and a preview of my region should be shown when dragging out the select
    * AC: 3. On finishing the drag, the selected region should be shown via a dashed outline
    * AC: 4. Pressing escape at any point during the process prior to or after dragging should stop the dragging operation and no changes should be applied to the canvas, including the dashed outline of selection
    * AC: 5. Pressing Ctrl + d should delete the region, clearing it out from canvas
    * AC: 6. Pressing Ctrl + v should paste the region at the current cursor location
    * AC: 7. By clicking on the region I should be able to drag it around to change it's location

## B. Patterns - Strategy and State Pattern (20 minutes)

Currently the above specifications have been implemented into a paint application called `quaint`.  It uses strategy patterns to handle various different tools as well as a state pattern to handle the current state of the canvas.

Your task is to investigate the [UML here](Quaint.pdf) and talk about how you would implement the following feature;

- When using the image tool; clicking and dragging should allow you to change the size / position of where the image is placed

Talk about the benefits of having such a state diagram at 'scale' and how state / strategy interact, what their similarities are and what their differences are.

> Answer:

Break down the problem, go through how state / strategy are able to abstract away the concept of dragging.

In this case we could do the following to reuse state logic as much as possible;

- Change ImagePlaceStrategy into a IShapeStrategy, this will automatically make it create a ShapeState with no code needed here.  You'll have to change the code slightly but roughly it'll be something like;

```java
package unsw.quaint.strategies;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import unsw.quaint.state.State;
import unsw.quaint.tools.Tool;

public class ImagePlaceStrategy implements IShapeStrategy {
    private Image img;

    public static Tool GetTool() {
        return new Tool(new ImagePlaceStrategy(), "../imgs/img.png", "Place some images", "Image");
    }

    @Override
    public void addWidgets(State state, VBox box) {
        // it's a 'little bit clunky' that we are loading the image when we add widgets
        // but in reality this is more of a 'initialiseTool' state, that just so happens to often
        // add widgets / little pieces.

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Files", ".png"));
        File file = fileChooser.showOpenDialog(box.getScene().getWindow());
        if (file != null) {
            img = new Image(file.toURI().toString());
        }
    }

    @Override
    public void dragDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width,
            double height, boolean isGhost) {
        context.drawImage(img, topLeftX, topLeftY, width, height);
    }
}
```

As you can see it's very 'little' code and we have a vastly different shape, this is hopefully good enough to demonstrate the power of state and strategy working in tandem.

#### Alternatively, if you want to make things simpler

Go through this UML diagram: https://lucid.app/lucidchart/invitations/accept/inv_e57fd5e4-dbad-4846-8078-e2018a1fdd5b?viewport_loc=-494%2C49%2C2782%2C1568%2C0_0 to explain the State and Strategy Patterns.

Then brainstorm one more state and strategy and add it to the UML, or work on the above activity implementing images.

## C. Low-Fidelity User Interface Design (15 minutes)

Look at the UI we've provided and discuss as a class:
* What's good about it?
* What could be better?

Your tutor will discuss what you should consider when designing a low-fidelity design for a frontend, using this as an example. This will help you with Milestone 1 of the project.

> Discussion points from the Image
> * Menu bar
> * Header
> * Alignment of elements
> * Use of drop downs/number fields
> * Lots of space to draw!
> * Simple colour scheme

> Other things to discuss - these ones aren't visible in the image, but are when you run the frontend and will still be important for the **user interactions** section of their frontend design in the project
> * Highlighting when something is selected
> * Movement of shapes when drawing (e.g. oval)

![Le Quack](wireframe.png)
