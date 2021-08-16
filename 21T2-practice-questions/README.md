COMP2511 Practice Questions
====================================

The following questions are practice questions. They may not be representative of the style or difficulty of the questions in the exam. There may be questions in the Final Exam of a different style and structure to these examples. To get a better idea of the structure/style of questions in the exam, please refer to the [Final Exam Structure](https://www.cse.unsw.edu.au/~cs2511/21T2/SampleExam/).

* * * * *

Multiple Choice Practice Questions
----------------------------------

<mark>**Please note that the topics covered in the final exam may be different to the topics covered in these practice questions. Also, the number of questions for each section may vary.**</mark>

### Example 1

Which of the following design patterns are used to dynamically add/change functionality at run-time:

Select one or more:

- Composite Pattern

- Decorator Pattern

- Abstract Factory Pattern

- Observer Pattern

- Builder Pattern

- Template Pattern

### Example 2

Which of the following statements is/are true?

Select one or more:

- The adapter class maps/joins functionality of two different types/interfaces and offers additional functionality.

- The decorator design pattern does not satisfy Open-Closed Principle.

- Tree structures are normally used to implement Composite Patterns.

- Graph structures are normally used to represent Builder Patterns.

### Example 3

For the Template Pattern, which of the following statements is/are true?

Select one or more:

- Template Method lets subclasses redefine an algorithm, keeping certain steps invariants.

- Subclasses of the Template Method can redefine only certain parts of a behaviour without changing the algorithm's structure.

- A subclass calls the operations of a parent class and not the other way around.

- Template pattern works on the object level, letting you switch behaviours at runtime

### Example 4

Which of the following statements is/are true?

Select one or more:

- In Java, errors (like OutOfMemoryError, VirtualMachineError, etc.) are Checked Exceptions.

- The Java IO makes use of the strategy pattern.

- Pre-conditions in an inherited overridden method must be stronger.

- All other choices are incorrect.

### Example 5

The Factory Method design pattern solves problems like:

Select one or more:

- How can an object be created so that subclasses can redefine which class to instantiate?

- How can a class defer instantiation to its superclass?

- How can the way an object is created be changed at run-time?

- How can object creation that is distributed across multiple classes be centralized?

### Example 6

An online camping store, sells different kinds of camping equipment. Items selected by the customer are added to a shopping cart. If an item is not available, a user can request an email notification when that item is available. Which of the following patterns would be useful to design this scenario? Select the most suitable pattern.

Select one:

- Strategy Pattern

- Decorator Pattern

- Template Pattern

- Visitor Pattern

- Observer Pattern

- Builder Pattern

### Example 7

In the composite pattern, not placing child-related operations in the component interface does what?

Select one:

- Prioritises type safety over uniformity

- Prioritises uniformity over type safety

- Prioritises polymorphism over uniformity

- Prioritises efficiency over type safety

### Example 8

Which of the following statements is/are correct?

Select one or more:

- Encapsulate what does not vary is a key design principle.

- Polymorphism requires multiple inheritance.

- Favour inheritance over composition is a key design principle.

- A subclass can offer more behaviour than its super class.

### Example 9

Suppose the following classes/interfaces are defined:

- `public interface Car {...}`

- `public class SportsCar implements Car {...}`

- `public interface FamilyCar extends Car {...}`

- `public abstract class CityCar implements FamilyCar {...}`

Which of the following instantiations/statements are valid?

Select one or more:

- `FamilyCar c = new Car(...);`

- `FamilyCar c = new SportsCar(...);`

- `FamilyCar c = new CityCar(...);`

- None of the other three choices are correct.

### Example 10

For generic types in Java, which of the following is/are incorrect?

Select one or more:

- `List<Integer>` is a subtype of `List<Object>`.

- `List<?>` matches `List<Object>` and `List<Integer>`.

- The wildcard `< ? extends Foo >` matches Foo and any subtype of Foo, where Foo is any type.

- The wildcard `< ? extends Foo >` matches Foo and any super type of Foo, where Foo is any type.

### Example 11

Consider a vending machine application that dispenses products when the proper combination of coins is deposited. Which of the following design patterns is suitable for such a vending machine application?

Select one:

- Strategy Pattern

- Composite Pattern

- Visitor Pattern

- State Pattern

Short-Answer Practice Questions
-------------------------------

<mark>**Please note that the topics covered in the final exam may be different to the topics covered in these practice questions. Also, the number of questions for each section may vary.**</mark>

### Example 1

Give the most appropriate design pattern for the following situations. You must briefly justify your answer.

**(A)** You are implementing a health application that monitors heart rate of a patient. If the heart rate of a patient is out of the required range, the application needs to inform all the relevant doctors and nurses. Doctors and nurses responsible for a patient may change over time.

**(B)** A user bought an application that reads data in JSON format, and displays results on a web page. Later, the user realised that one of their data sources is in XML format. Unfortunately, the user does not have access to the source code of the application, so it is not possible to change the application.

* * * * *

### Example 2

Briefly explain the important differences between the Decorator Pattern and the Builder Pattern, as discussed in the lectures.

* * * * *

### Example 3

Briefly explain the important differences between the Factory Method and Abstract Factory Method Patterns, as discussed in the lectures.

* * * * *

Programming Practice Questions
------------------------------

<mark>**Please note that the topics covered in the final exam may be different to the topics covered in these practice questions. Also, the number of questions for each section may vary.**</mark>

### Example 1 (Design, 20T3 Final Exam)

Consider a hotel booking system with the following requirements.

-   Hotels consist of a set of numbered rooms. Hotels have a name (e.g. "Meriton Suites Sydney"). Hotels are added automatically when the first room for that hotel is added.
-   Hotel Managers can add a room to their hotel. Rooms have a room number, and size (small, medium or large).
-   Customers can request, change and cancel bookings.
-   Booking requests have a request number (each booking request has a unique request number), starting date, checkout date, and request for 1 room of a single size (small, medium or large).
-   Booking changes have the request number of the booking to be changed, and the new starting date, checkout date, and request for 1 room of a single size (small, medium or large).
-   A booking/change request is either granted in full or is completely rejected by the system (a request cannot be partially fulfilled).
-   Customers can cancel a booking by providing the request number of the booking.
-   Hotel Managers can display the occupancy of all rooms at the specified hotel in order of room creation, then booking start date.

To remove any ambiguity, booking requests and changes are fulfilled as follows: each hotel is checked (in order of definition) to determine whether the room request can be satisfied, and if so, the first available room (again in order of definition in the input) is assigned to the booking request. The system should not try to fulfil requests by allocating medium rooms when a small is requested, or by reassigning rooms to different bookings or hotels to create vacancies, etc.

For a booking's checkout date, another booking can start on the same date - so if you have a booking for a room starting on the 3rd January with checkout date of 5th January, the earliest date you can start another booking for this room is the 5th January.

The Java package for this question is accessible via [this link](ProgrammingExample1/src/programmingexample1).

Given the above problem specification, your task is to write a solution with skeleton classes. They need only contain:

-   Fields
-   Stub methods
-   Brief comments describing what the methods do, and what the fields and classes represent

Additionally, in a file *programmingExample1.txt*, write a brief rationale explaining your design choices, examples of adhering to design principles taught in the course, and a justification for any Design Patterns you may use in your solution.

**Your code must compile, but does not need to execute. Your main class should be HotelBookingSystem (the *main* method may be empty).**

Your methods may be stubs (non-working methods with fake return values so the code compiles), for example:

```java
public int getNumDays(){

    return 0;

}
```

You do not need to supply any tests.

Your design should be for **backend** classes (no frontend). You do not need to take into account authentication/authorization.

Where there are ambiguities, you may decide how to resolve them (for example, you may choose the type for a booking **id** - *String* and *int* are both acceptable).

### Example 2 (Design, 20T2 Final Exam)

Consider an airline booking system with the following requirements.

-   Passengers have schedules that involve at least one and maybe several flights.
-   Each flight has a departure date/time and an arrival date/time.
-   Flights are identified by a name (e.g. QF1) that may be repeated for different days/times.
-   Flights have a number of seats in several sections: first, business and economy class.
-   Each flight in each passenger's schedule includes the class of seat for that flight.
-   Each flight has a seat allocated for each of its passengers
-   The seat allocated to a passenger on a flight must match the seat class in the schedule. Passengers may book, cancel or update flights and seat allocations in their schedule.

The (currently empty) Java package for this question is accessible via [this link](ProgrammingExample2/src/programmingexample2).

Place all classes and interfaces you create for this question in that package (**don't** create subpackages).

Given the above problem specification, and considering carefully how you distinguish between a flight and a particular occurrence of that flight, write a solution with skeleton classes. They need only contain:

-   Fields
-   Method signatures (with brief comments describing what they do)

They do not need to contain method bodies nor any tests.

Additionally, in a file *programmingExample2.txt*, write a brief rationale explaining your design choices, examples of adhering to design principles taught in the course, and a justification for any Design Patterns you may use in your solution.

### Example 3 (Generics, 20T3 Final Exam)

The Java package for this question is accessible via [this link](ProgrammingExample3/src/programmingexample3).

The interface `Hamper<E>` is for hampers that can handle elements of a generic type E. A hamper is similar to a set but allows for duplicate elements. A "hamper" in real life is a basket of gifts full of lots of nice things, e.g. fruits, chocolates, toys, etc...

Complete all methods marked TODO in `ArrayListItemHamper`, `CreativeHamper` and `FruitHamper`. The class `ArrayListItemHamper` uses an `ArrayList` of `Count<E>` to track the count of each element. Pay careful attention to the contract and documentation in `Hamper`, as well as the invariants in `ArrayListItemHamper`, `CreativeHamper`, and `FruitHamper` to make sure your implementation is correct. Make sure that your solution successfully passes the tests in `TestHamper`.

`CreativeHamper` is a hamper for which the price of the hamper is $10 more than the sum of the prices of the items inside it. A `CreativeHamper` which contains 5 or more items, must have at least 2 toy cars (at least 1 must be a premium toy car), and at least 2 fruits. When adding items to a `CreativeHamper`, if this condition cannot be adhered to, the items should not be added.

`FruitHamper` is a hamper of fruits for which the price of the hamper is 25% more than the sum of the prices of the fruits inside it (if the result of this is not an integer, the result should be rounded up to the nearest dollar). A `FruitHamper` which contains 6 or more fruits, must have at least 2 avocados, and at least 2 apples. When adding items to a `FruitHamper`, if this condition cannot be adhered to, the items should not be added.

Note that all currency values in this question and the starter code are in Australian dollars.

The tests used in automarking will be much more extensive than the JUnit tests provided in the dryrun and starter code. Thus, you should test your code thoroughly according to the details in this specification and the requirements/conditions outlined in the starter code.

You should not rely on any modifications to code other than in the files `ArrayListItemHamper.java`, `CreativeHamper.java` and `FruitHamper.java`

Note that, for your implementation of the `sum` method in `ArrayListItemHamper`, `FruitHamper`, and `CreativeHamper`, it is acceptable to create a new `ArrayListItemHamper` to contain the elements from the summation and return this.

### Example 4 (Generics, 20T2 Final Exam)

The Java package for this question is accessible via [this link](ProgrammingExample4/src/programmingexample4).

The interface `Bag<E>` is for bags that can handle elements of a generic type E. A bag is similar to a set but allows for duplicate elements. Complete all methods marked TODO in `ArrayListBag`. The class uses an `ArrayList` of `Count<E>` to track the count of each element. Pay careful attention to the contract and documentation in `Bag`, as well as the invariant in `ArrayListBag`, to make sure your implementation is correct. Make sure that your solution successfully passes the tests in `TestBag`.

### Example 5 (Pattern Implementation, 20T3 Final Exam)

The Java package for this question is accessible via [this link](ProgrammingExample5/src/programmingexample5).

For a quick reference, the interfaces/classes from the package are listed below. Note that, most of them are partially implemented.

```
Circle    ShapeColourAreaVisitor ShapeVisitable
Rectangle ShapeColourVisitor     ShapeVisitor
Shape     ShapeGroup             Triangle
```

In this question, you must use the Visitor pattern (as discussed in the lectures) to implement your solution. Make sure that your solution successfully passes the given JUnit tests. Please note that we will use additional tests to extensively test your solution.

#### Tasks:

Using the Visitor pattern (as discussed in the lectures):

1.  Complete the interface `ShapeVisitor` for the classes that implement `ShapeVisitable` in the package `programmingexample5`.
2.  Implement the required method `accept` in the classes `Circle`, `Rectangle`, `Triangle` and `ShapeGroup`.
3.  Implement the visitor class `ShapeColourVisitor` that sets the colour of circles to red, rectangles to green, and triangles to blue.
4.  Implement the visitor class `ShapeColourAreaVisitor` that can be used to calculate total area covered by shapes of a given colour.

The tests in `ShapeVisitorTest` will guide you in completing these. You should ensure your solution passes these tests. Note that you **must** use the visitor pattern (as discussed in lectures) for the above tasks. Otherwise, you will not be awarded marks for this question, even if you pass some/all the tests provided.

### Example 6 (Pattern Implementation, 20T2 Final Exam)

The Java package for this question is accessible via [this link](ProgrammingExample6/src/programmingexample6).

The class `Product` represents a product in an online store with a price and weight. The weight is used to calculate the shipping cost. Using the decorator pattern (as discussed in lectures):

1.  Implement `DiscountDecorator` that discounts the price of a product by a given percentage. Multiple discounts are applied cumulatively (e.g. if 20% discount on $100 gives a price of $80, a further discount of 20% would give a price of $64).
2.  Implement `FreeShippingDecorator` that makes shipping free for products over a given price and under a given weight. Note that, if this decorator is applied to a discounted product, whether or not it qualifies for free shipping depends on the *discounted* price.

The tests in `TestProduct` will guide you in completing these. You should ensure your solution passes these tests. Note that you **must** use the decorator pattern (as discussed in lectures) for the above two tasks (`DiscountDecorator` and `FreeShippingDecorator`). Otherwise, you will not be awarded marks for this question, even if you pass some/all the tests provided.

### Example 7 (Refactoring, 20T2 Final Exam)

The Java project containing code you'll need to modify for this question is accessible via [this link](ProgrammingExample7/src/programmingexample7).

Analyse the classes `Employee`, `Engineer`, `SalesMan` and `SalesHistory`. The source code in these classes suffer from various code smells. Applying suitable refactoring techniques, modify the Java source files to address each of the following code smells. You MUST include brief comments in your code to identify the smells and explain the refactoring techniques used.

Code smells to address:

-   Refused Bequest
-   Duplicated code
-   Excessive coupling between classes
-   Lazy class

Note: the lecture notes on Code Smells are available here: ['Code Smell Lecture'](https://webcms3.cse.unsw.edu.au/static/uploads/course/COMP2511/19T2/fb0104a296f7f4cd16b4f6e762218f0d871761be462655ab7e04f9894cb79ade/week08_CodeSmell.pdf).

* * * * *

End

* * * * *

