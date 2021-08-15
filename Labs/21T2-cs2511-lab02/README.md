# Lab 02

### Due: Week 2 Sunday, 5pm

### Value: 2% of course mark

## Change Log

None

## Aims

* Understand the Object-Oriented principles of abstraction, encapsulation and inheritance
* Design Object-Oriented solutions for simple problem domains
* Create UML diagrams
* Work with JSON in java

## Setup

An individual repository for you for this lab has been created for you:

https://gitlab.cse.unsw.edu.au/z5555555/21T2-cs2511-lab02

## Exercises

## Lab 02 - Exercise - Cars 🚗 

In this problem, we are going to continue the UML Diagram exercise from the tutorial. Your tutor will provide a pdf containing the diagram you worked on together.

### Requirements Version 1

A Car has one or more engines and a producer. The producer is a manufacturing company who has a brand name.  Engines are produced by a manufacturer and have a speed. There are only two types of engines within UNSW's cars:

* **Thermal Engines**, which have a default max speed of 114, although they can be produced with a different max speed, and the max speed can change to any value between 100 and 250.
* **Electrical Engines**, which have a default max speed of 180. This is the speed at which they are produced, and the max speed can change to any value that is divisible by 6.

Cars are able to drive to a particular location `x`, `y`.

Since UNSW is a world-leader in technology innovation, they want you to be able to model the behaviour of Time Travelling for *any* vehicle, and to model a time travelling car. A vehicle that travels in time *stays in the same location* but travels to a `LocalDateTime`.

### Requirements Version 2

In addition to the above which you did in the tutorial, you will need to model the following:

1. The Car also has an owner. The owner is the official 'owner of the car' on paper, who has a name, address and can own many cars. 

2. There are two new types of engines:

* **Nuclear Engines**, which has a default max speed of 223; the engine can be produced with a different max speed and can change to any number that is prime. Nuclear engines also have a nuclear energy value between 1 and 10 and are able to propel at their nuclear energy value.
* **Solar Engine**, which has a default max speed of 90, and the max speed can change to anything below 150. This is the speed at which they are produced.

3. In the innovation space, UNSW wants you to model flying for any vehicle. Flying constitutes driving, except to a location `x`, `y`, `z`. They also want you to model the following vehicles specifically:

* Planes, which are able to fly and contain a list of passengers' names
* Flying Cars (note that flying cars can still drive normally)
* Time Travelling Flying Cars

### Task

**You do not need to write any code for this exercise.**

Complete the UML diagram which models the domain. Think about the following OO design concepts:

* Abstraction
* Inheritance by extending classes
* Abstract classes
* Interfaces
* Has-a vs Is-a relationships (Composition vs Inheritance)

Your UML diagram will need to include:

* Getters and setters
* Constructors
* Aggregation/Composition relationships
* Cardinalities
* Inheritance/implementation relationships 

Submit your UML diagram in a file called `cars-design.pdf` in the root directory of this repository.

## Lab 02 - Exercise - Staff 🔱

Modify the class StaffMember (inside package `staff`) so that it satisfies the following requirements:

* Attributes to store the staff member’s name, salary, hire date, and end date.
* Appropriate constructors, getters, setters, and other methods you think are necessary.
* Overridden `toString()` and `equals(..)` methods. Use what you learnt from the tutorial, but make sure you understand what these methods are doing.
* Javadoc for all non-overriding methods and constructors.

Create a sub-class of `StaffMember` called `Lecturer` in the same package with the following requirements:

* An attribute to store the school (e.g. CSE) that the lecturer belongs to
* An attribute that stores the academic status of the lecturer (e.g A for an Associate Lecturer, B  for a Lecturer, and C for a Senior Lecturer)
* Appropriate getters and setters.
* An overriding `toString()` method that includes the school name and academic level.
* An overriding `equals(...)` method.
* Javadoc for all non-overriding methods and constructors.

Create a class `StaffTest` in the package `staff.test` to test the above classes. It should contain:

* A method `printStaffDetails(...)` that takes a `StaffMember` as a parameter and invokes the `toString()` method on it to print the details of the staff.
* A `main(...)` method that:

  * Creates a `StaffMember` with a name and salary of your choosing.
  * Creates an instance of `Lecturer` with a name, salary, school and academic status of your choosing.
  * Calls `printStaffDetails(...)` twice with each of the above as arguments.
  * Tests the `equals(..)` method of the two classes. Use [the documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object)) for `Object.equals(...)` as a guide for what you should test.
  * You do not need to write any JUnit tests for this exercise (though you can if you would like)

## Lab 02 - Challenge Exercise - Pineapple on Piazza 🍕

Welcome back to Piazza (even though Ed is a much better forum). This week we're going to finish off our implementation. We are going to make `Post` its own class, and implement functions which raise exceptions. If you didn't complete the Piazza challenge exercise last week then that's OK! The solution code can be found in the `solution` branch of the `lab01` repo once it's released to get you started.

Updated requirements:

**`Post`**

* A `Post` is created by a particular author;
* The author is able to edit the content, but other users cannot;
* Any user should be able to bump the upvotes, but only once per user.

**`Thread`**

* A `Thread` is created with a title, and a first post;
* The owner of the thread is the author of the first post;
* Any new user can add a new post to the thread;
* The thread owner can edit the title and tags, but other users cannot.

**`PiazzaForum`**

* The `Forum` contains a list of threads;
* Users can search for threads by tag;
* Users can search for posts by author.

Once again, there are a series of function stubs provided for you to implement with instructions in the JavaDoc.

There is also a class defined called `PermissionDeniedException` which you should `raise` whenever a user tries to perform an action (e.g. delete someone else's post) that they are not allowed to perform. You can throw (equivalent of `raise` in Python) this exception using `throw new PermissionDeniedException()`.

## Understanding Assignment Specification

Please read Assignment Specification carefully, and make sure that you properly understand the all tasks. If you have any queries or need any clarifications, you should ask your tutor during the lab time.

## Submission

To submit, make a tag to show that your code at the current commit is ready for your submission using the command:

```bash
$ git tag -fa submission -m "Submission for Lab-02"
$ git push -f origin submission
```

Or, you can create one via the GitLab website by going to **Repository > Tags > New Tag**.
We will take the last commit on your master branch before the deadline for your submission.
