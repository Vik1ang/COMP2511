# Tutorial 07

## A. Assignment Feedback

Your tutor will go through common points of feedback for the Assignment.

## B. Finding Patterns

In groups, determine a possible pattern that could be used to solve each of the following problems:

* Sorting collections of records in different orders.
* Modelling a file system.
* Updating a UI component when the state of a program changes.
* Parsing and evaluating arithmetic expressions.
* Adjusting the brightness of a screen based on a light sensor.

Then pick one and start to think about potential entities and draw up a rough UML diagram

## C. Composite Pattern

Inside `src/calculator`, use the Composite Pattern to write a simple calculator that evaluates an expression. Your calculator should be able to:

* Add two expressions
* Subtract two expressions
* Multiply two expressions
* Divide two expressions
* Sine of an expression
* Cosine of an expression
* Tangent of an expression

There should be a `Calculator` class as well which can be passed in an expression, and calculate that expression.

Design a solution, create stubs, write failing unit tests, then implement the functions.

## D. Design By Contract

1. What is Design by Contract? 

2. Discuss briefly as a class how you have used Design by Contract already in previous courses.

3. Discuss how Design By Contract was applied in the Blackout assignment.

4. In the `calculator` code, specify a contract for each of the `compute` functions. Hint: for the trig functions, look at the interface of the `Math` functions in the Java documentation. Key edge cases to consider:

    * Dividing by zero
    * `tan(Math.PI / 2)`

5. Will you need to write unit tests for something that doesn't meet the preconditions? Explain why.

6. We will now make our code more defensive by throwing an exception on the two edge cases listed above. Define your own custom exception called `CalculatorException`, and change the code so that these edge cases cause this exception to be thrown. Modify your contract accordingly. If you have time, add tests for these conditions as well.

