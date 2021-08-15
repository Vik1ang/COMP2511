# Tutorial 2

## A. Code Review

Your tutor will provide you a link or open up the `src/shapes`, look at the `Shape` and `Rectangle` classes.

In groups, analyse the classes to answer the following questions:

1. What is the difference between `super` and `this`?
2. What about `super(...)` and `this(...)`?
4. What are static fields and methods?

> `super`: The instance of the super class
`this`: The instance of this class (like self in python)
`super(...)` Runs the respective method in the super class
`'this(...)'` Runs the respective method in this class with the given parameters (used for method overloading)

## B. Access Modifiers & Packages

In the code in the `src/access` package, answer the questions marked `TODO`.

<img src='https://media.geeksforgeeks.org/wp-content/cdn-uploads/Access-Modifiers-in-Java.png' />

## C. UML Diagrams

In this problem, we are going to create an Object-Oriented domain model for a system with the following requirements. 

With success in student projects like Sunswift and Redback, UNSW have decided that they would like to build a system that can show all of the student-built and other cars that they have in order to showcase to prospective students interested in STEM and attract students from other universities. They have asked you, as a designer to produce a model for what this system will look like. 

### Requirements

A Car has one or more engines and a producer. The producer is a manufacturing company who has a brand name.  Engines are produced by a manufacturer and have a speed. There are only two types of engines within UNSW's cars:

* **Thermal Engines**, which have a default max speed of 114, although they can be produced with a different max speed, and the max speed can change to any value between 100 and 250.
* **Electrical Engines**, which have a default max speed of 180. This is the speed at which they are produced, and the max speed can change to any value that is divisible by 6.

Cars are able to drive to a particular location `x`, `y`.

Since UNSW is a world-leader in technology innovation, they want you to be able to model the behaviour of Time Travelling for *any* vehicle, and to model a time travelling car. A vehicle that travels in time *stays in the same location* but travels to a `LocalDateTime`.

**Create a UML diagram which models the domain**.

During the lab, you will build on this UML diagram to incorporate further requirements.

> Example Solution: https://lucid.app/lucidchart/04828d06-1e75-4dad-9682-7d67113f4e46/view?page=0_0#

## D. JavaDoc

Within the `src` directory, create a new package called `employee`.

Create an `Employee` class which has private fields for an employee's name and salary and appropriate getters, setters, and constructors. Document the class with [JavaDoc](https://www.oracle.com/au/technical-resources/articles/java/javadoc-tool.html).

## E. Basic Inheritance & Polymorphism

* How many constructors should the class have? What arguments should they take?

    > Only one. If we don't define a constructor Java automatically generates one that takes no arguments. It makes little sense to have an employee with no name or salary, so the constructor should take the name and salary as arguments.

Create a `Manager` class that is a subclass of `Employee` and has a field for the manager's hire date.

> See code

* What constructors are appropriate?

    > Because we are inheriting from Employee, Java forces us to write a constructor that calls `super(...)`, so we have to write at least one. In this case, it makes sense to have a constructor that takes the name, salary and hire date, but you could also argue there should be one that just takes the name and salary and sets the hire date to the current day. It depends on context of how the class will be used whether you want the former, the latter, or both constructors.

* Is appropriate to have a getter for the hire date? What about a setter?

    > One can assume that if the hire date is stored it is information that will be needed at some point, so a getter is necessary. However, unlike the name or salary, it is unlikely that the hire date will be updated, so a setter would only serve to break that reasonable assumption about the class.

Override the `toString()` method inherited from `Object` in both classes.

> See code

* What should the result of `toString()` contain?

    > The [documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#toString--) for the `toString()` method states that it should return a string that "textually represents" the object. In this case, it should contain the name, salary and hire date (in the case of `Manager`), but also the runtime class of the object.

* Does the method in `Manager` call the one in `Employee`?

    > In order to satisfy the above requirement and not introduce unnecessary repetition, the superclass method must be called.
