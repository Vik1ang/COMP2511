# Tutorial 07

## A. Finding Patterns (15 minutes)

In groups, determine a possible pattern that could be used to solve each of the following problems:

* Sorting collections of records in different orders.

    > Strategy pattern. This what Java does with the `Collections.sort()` method. A `Comparator` can be provided to determine the order in which elements are sorted.

* Modelling a file system

    > Composite pattern. Both folders and files are filesystem entries. Files form the leaves, folders can contain files or other folders.

* Updating a UI component when the state of a program changes.

    > Observer pattern. If the state of the program is the subject and the UI an observer, the UI will be notified of any changes to the state and update accordingly.

* Parsing and evaluating arithmetic expressions.

    > Composite pattern. The composite pattern can be used to represent a parse-tree. An example of this is given in the code.

* Adjusting the brightness of a screen based on a light sensor.

    > Observer pattern. If the light sensor is the subject, the observer could be notified on all significant changes to the amount of light hitting the sensor and adjust the brightness of the screen accordingly.

## B. Composite Pattern (15 minutes)

> See solutions

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


## C. Design By Contract (15 minutes)

1. What is Design by Contract? 

2. Discuss briefly as a class how you have used Design by Contract already in previous courses.

> 1531 API & Frontend/backend, 1511/2521 using ADTs

3. Discuss how Design By Contract was applied in the Blackout assignment.

> Key things to discuss here:
>   * We specified the interface functions in `Blackout`, and so long as they matched that interface, they could implement however they chose
>   * We told them that they didn't have to account for invalid Satellite/Device IDs, and that no 2 devices would ever occupy the same position - examples of preconditions which they didn't have to account for (if we did, the behaviour of the ADT is undefined)

4. In the `calculator` code, specify a contract for each of the `compute` functions. Hint: for the trig functions, look at the interface of the `Math` functions in the Java documentation. Key edge cases to consider:
    * Dividing by zero
    * `tan(Math.PI / 2)`

5. Will you need to write unit tests for something that doesn't meet the preconditions? Explain why.

6. We will now make our code more defensive by throwing an exception on the two edge cases listed above. Define your own custom exception called `CalculatorException`, and change the code so that these edge cases cause this exception to be thrown. Modify your contract accordingly. If you have time, add tests for these conditions as well.

> When you update your postconditions, include the possible exception cases
> Note that exception cases are still cases for which the preconditions aren't met - this is the subject of a bit of debate, because technically exceptional behaviour is 'accounted for' and defined which means the preconditions (user of the ADT) don't have to worry about it, but also looking at it from a formal 'proving-things about programs' perspective, an input which causes an exception to be raised doesn't map to an output, which means it's not formally 'defined' behaviour. So what this essentially means is that an input that doesn't meet the 'correct' preconditions will cause an exception to be raised (in a defensive programming style) but the behaviour of the ADT is still defined. 

> From a library-writing perspective this sort of 'contract except I tell you where you went wrong you if you mess up' style of design is useful for 2 reasons, one is that it prevents weird stuff from happening that breaks everything, and the second is that it helps users of the ADT debug their code. Just like how when you enter jibberish on a date field in a web form it says 'error: invalid date' etc etc instead of a 500 Internal Server Error, or when you divide by 0 in Python or Java you get a ZeroDivisionError, rather than some message from the OS.