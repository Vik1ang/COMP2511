# Tutorial 03

## A. Code Review & Questions

In your project groups, answer the following questions. 

1. Can you override a static method?

> No - the method is attached to the class, not the instance.

2. What is output by executing `A.f()` in the following?

    ```java
    public class A {
        public static void f() {
            C c = new C();
            c.speak();
            B b = c;
            b.speak();
            b = new B();
            b.speak();
            c.speak();
        }
    }


    public class B {
        public void speak() {
            System.out.println("moo");
        }
    }


    public class C extends B {
        public void speak() {
            System.out.println("quack");
        }
    }
    ```

    > The program will print out:
    > ```
    > quack
    > quack
    > moo
    > quack
    > ```

3. What is output by executing `A.f()` in the following?

    ```java
    public class A {
        public static void f() {
            B b1 = new B();
            B b2 = new B();
            b1.incX();
            b2.incY();
            System.out.println(b1.getX() + " " + b1.getY());
            System.out.println(b2.getX() + " " + b2.getY());
        }
    }

    public class B {
        private int x;
        private static int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void incX() {
            x++;
        }

        public void incY() {
            y++;
        }
    }
    ```

    > ```
    > 1 1
    > 0 1
    > ```

## B. Design Principles

### Part 1: Law of Demeter

In the `unsw.training` package there is some skeleton code for a training system. 

* Every employee must attend a whole day training seminar run by a qualified trainer
* Each trainer is running multiple seminars with no more than 10 attendees per seminar

In the TrainingSystem class there is a method to book a seminar for an employee given the dates on which they are available. This method violates the principle of least knowledge (Law of Demeter).

1. How and why does it violate this principle?

    > The `TrainingSystem` class extracts instances of `Seminar` from instances of `Trainer` and calls the methods of `Seminar`. Furthermore it extracts the start date from instances of `Seminar` and calls its methods. More informally, the `TrainingSystem` class is interacting with classes other than its "friends".

2. In violating this principle, what other properties of this design are not desirable?

    > * The design is needlessly tightly coupled as `TrainingSystem` is dependent on both `Trainer` and `Seminar`.
    > * `TrainingSystem` suffers from low cohesion as any change to the system requires a change to this class.
    > * The `Seminar` class has no control over the number attendees. It relies on `TrainingSystem` to ensure there are never more than 10. This makes `Seminar` hard to re-use as any future client has to ensure they don't exceed the maximum of 10 attendees. This is an example of poor encapsulation.

3. Refactor the code so that the principle is no longer violated. How has this affected other properties of the design?

    > * The design is no longer tightly coupled.
    > * Each of the classes now has a clear purpose in booking a training seminar, thus improving cohesion.
    > * The `Seminar` class itself is now responsible for ensuring that the number of attendees does not exceed 10. This is an example of a class maintaining its invariant. We'll come back to that when discussing programming by contract.

4. More generally, are getters essentially a means of violating the principle of least knowledge? Does this make using getters bad design?

    > Getters that return an object (as opposed to a primitive) likely only serve the purpose of letting clients invoke methods on that object, so a valid point can be made that getters can only be used as a means for violating the principle of least knowledge. A counter argument is that getters make classes more reusable. A client may need to do something with an object for which it has no method. In that case, getters can allow the client use the class in a way that was not originally foreseen, even if it does violate the principle of least knowledge.
    >
    > Another way in which a getter can be an example of bad design is in `Seminar` above. By having a `getAttendees()` method implemented as a simple getter, any client is able to add additional attendees to the list of attendees, potentially exceeding the maximum of 10. Unfortunately, Java does not offer any good solutions to this problem. Either `getAttendees()` has to create a copy of the list, or it can use `Collections.unmodifiableList(...)` to wrap the list up in a class that prevents any modification to the list. The former solution is inefficient as it needlessly copies data. The latter can be surprising to the client as the returned list still has an `add(...)` method, but it causes a runtime error every time it is used. Other languages resolve this problem by having proper immutable or read-only lists.

### Part 2: Liskov Substitution Principle

Look at the `OnlineSeminar` class. How does this violate the Liskov Substitution Principle?

> This class violates the Liskov Substitution Principle. Specifically a `Seminar` is defined as having a list of attendees, but `OnlineSeminar` does not require this. A client interacting with a `Seminar` would expect the seminar to be booked like any other. This is an example of classes having an IS-A relationship informally, but not a valid inheritance relationship when taking into account what the classes actually do and represent.

## C. Testing with JUnit

1. What is the difference between unit and integration testing?

> Unit testing: A single component
> Integration testing: Multiple components interacting

2. What does test-driven development look like in Java? 

> 1. Write the class/object declaration (the class and method headers, with parameters and types)
> 2. Write the test(s)
> 3. Run the test(s) (they'll fail of course)
> 4. Implement the methods
> 5. Pass the tests

3. Inside the `src/unsw` folder is `archaic_fs` and `test` that mocks a very simple file system and tests it respectively, you can see 3 already written in there.

    - This simulates a 'linux' like Inode system (arguably pretty badly but we'll extend on it next week to be better).  You don't have to understand how it works under the hood, since it mocks the typical linux commands.
    - Commands available;
        - `cd(path)`
            - Throws NoSuchFileExpception is a part of the path can't be found
        - `mkdir(path, createParentDirectories, ignoreIfExists)`
            - Throws FileNotFoundException if a part of the path can't be found and createParentDirectories is false
            - Throws FileAlreadyExistsException if the folder already exists and ignoreIfExists is false
        - `writeToFile(path, content, opts)`
            - Options are a EnumSet of FileWriteOptions i.e. `EnumSet.of(FileWriteOptions.APPEND, FileWriteOptions.CREATE)` the full set is: CREATE, APPEND, TRUNCATE, CREATE_IF_NOT_EXISTS
            - Throws FileNotFoundException if the file can't be found and no creation options are specified
            - Throws FileAlreadyExistsException if the file already exists and CREATE is true
        - `readFromFile(path)` returns the content for a given file.
            - Throws FileNotFoundException if the file can't be found

**Write at least 2 more unit tests and 1 integration test.**

More information on JUnit can be found [here](https://www.vogella.com/tutorials/JUnit/article.html).

4. Coverage testing!  After doing above you can check the coverage of your tests by running the command `$ gradle test -b test.gradle`

The output is under `build/reports/jacoco/test/html/index.html` you can open this in a browser.

## Installing Gradle

- Download the zip file from (download should start automatically): https://gradle.org/next-steps/?version=5.4.1&format=bin
- You should follow the installation instructions provided: https://gradle.org/install/#manually
  - For Linux users, note that you may have to edit the ~/.bashrc file to permanently change the PATH variable by appending the line: `export PATH=$PATH:/opt/gradle/gradle-5.4.1/bin`
- Note that Gradle 5.4.1 is the same version as on CSE machines. It has been chosen so that common syntax can be used for the test.gradle file to Jacoco coverage testing.
