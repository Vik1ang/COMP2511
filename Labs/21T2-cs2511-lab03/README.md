# Lab 03

### Due: Week 3 Sunday, 5pm

### Value: 2% of course mark

## Aims

* Apply design principles and refactoring theory to real examples
* Adapt existing systems to changes in requirements
* Design extensible and cohesive software solutions
* Practice creating UML Diagrams
* Practice writing integration and unit tests with JUnit
* Use coverage checkers on Java programs

## Setup

An individual repository for you for this lab has been created for you on the CSE GitLab server. You can find it at this URL (substituting z5555555 for your own zID):

[https://gitlab.cse.unsw.edu.au/COMP2511/21T2/students/z5555555/21T2-cs2511-lab03](https://gitlab.cse.unsw.edu.au/COMP2511/21T2/students/z5555555/21T2-cs2511-lab03)

**REMEMBER** to replace the zID below with your own.

`git clone gitlab@gitlab.cse.unsw.EDU.AU:COMP2511/21T2/students/z555555/21T2-cs2511-lab03.git`

## Lab 03 - Exercise - Extending Enrolments 🥨

Inside the `src/enrolment` package, there is some skeleton code for a university enrolment system. In this exercise, you are going to model the system using a UML diagram and extend the existing functionality of the solution.

### Requirements

* Students enrol in courses that are offered in particular semesters
* Students receive grades (pass, fail, etc.) for courses in particular semesters
* Courses may have pre-requisites (other courses) and must have credit point values
* For a student to enrol in a course, they must have passed all prerequisite courses
* Course offerings are broken down into multiple sessions (lectures, tutorials and labs)
* Sessions in a course offering for a particular semester have an allocated room and timeslot
* If a student enrols in a course, they must also enrol in some sessions of that course

### UML Diagram

Draw a complete UML diagram including all fields, methods and relationships. In deciding on the methods for the classes, you may wish to consider the [principle of least knowledge (Law of Demeter)](https://en.wikipedia.org/wiki/Law_of_Demeter).

### Implementation and Testing

Inside `EnrolmentTest.java`, there is a function `testIntegration` with a series of `TODO`s describing a simple use case written as an integration test.

You will need to:

1. Create stubs for your design outlined in the UML diagram
2. Write **unit** tests using JUnit for each of your methods. You should create a seperate test file for each class.
3. Complete the `TODO`s describing the **integration** test.
4. Implement the necessary components of the enrolment system. You can change the code in any way you feel is necessary.

Note that the provided code is not complete. For example, it does not check that students have passed all prerequisite courses before allowing enrolment in a course - so you should extend it to incorporate this requirement. You should similarly ensure that all of the above requirements in the bullet-points are incorporated into your solution.

After finishing the implementation, make sure your diagram is still consistent with the code. If you had to update your diagram, consider why.

### Coverage Checking

For this exercise, we require that your JUnit tests give at least 95% coverage on your code. In this course we will be using a coverage checker called **Gradle**. Gradle also allows you to see the results of your tests against your code, including test failures.  You can ignore all simple getters/setters (i.e. `this.x = x` and `return x;` sort of functions) and we'll just look at the coverage of your other functions.

Download the zip file from (download should start automatically): [https://gradle.org/next-steps/?version=5.4.1&format=bin](https://gradle.org/next-steps/?version=5.4.1&format=bin)

You should follow the installation instructions provided: [https://gradle.org/install/#manually](https://gradle.org/install/#manually)

For Linux users, note that you may have to edit the ~/.bashrc file to permanently change the PATH variable by appending the line:
export PATH=$PATH:/opt/gradle/gradle-5.4.1/bin

Note that Gradle 5.4.1 is the same version as on CSE machines. It has been chosen so that common syntax can be used for the test.gradle file to Jacoco coverage testing. We will run Gradle 5.4.1 and the provided test.gradle script to perform coverage checking of your submission for assignment 1, which will contribute towards your mark - so you should check the coverage checking command works on a CSE machine (command provided below under Running coverage checking). Note that Gradle and JUnit tests should be able to run on a CSE machine.

Then in the root directory of your repository run the following:

```bash
$ gradle test -b test.gradle
```

The coverage checking report will be in: [build/reports/jacoco/test/html/index.html](build/reports/jacoco/test/html/index.html)

The test report will be in: [build/reports/tests/test/index.html](build/reports/tests/test/index.html)

## Lab 03 - Challenge Exercise - Degree Distribution 📝 

In Australia, university degrees are allocated using a centralised preference system, where final high school exam marks are converted into a score in steps of 0.05 up to 99.95. A score of 99.00 indicates that the student's aggregated marks were better than 99% of their cohort.

Each degree has a certain number of available places, and accepts students until it is full. Students nominate up to nine degrees, ordered from first to last preference. They are considered in descending order of their marks, receiving an offer for the first degree in their preference list that still has available places. Each degree has a *cutoff mark*: the lowest score out of the students who received a place in that degree.

You will be given two JSON files, one containing degrees and one containing students. An example `degrees.json` is in the following format.

```javascript
[{"code": 422000, "name": "Bachelor of Arts","institution": "University of New South Wales", "places": 10},
{"code": 422300, "name": "Bachelor of Social Research and Policy","institution": "University of New South Wales", "places": 8},
{"code": 423600, "name": "Bachelor of Planning","institution": "University of New South Wales", "places": 10},
{"code": 511207, "name": "Bachelor of Arts (Media and Communications)","institution": "University of Sydney", "places": 2},
{"code": 511504, "name": "Bachelor of Commerce","institution": "University of Sydney", "places": 1},
{"code": 511795, "name": "Bachelor of Computer Science and Technology","institution": "University of Sydney", "places": 8}]
```

`students.json` is in the following format.

```javascript
[{"name": "Claudia Zingaro", "score": 84.50, "preferences": ["422300+2","511207"]},
{"name": "Ivan Connolly", "score": 91.00, "preferences": ["511207+5","511504"]},
{"name": "Jeffie Honaker", "score": 94.50, "preferences": ["511207","511504","511795"]},
{"name": "Floria Rozar", "score": 82.25, "preferences": ["422000","422300","511207","511504"]},
{"name": "Hyun Castleberry", "score": 83.15, "preferences": ["511795", "423600"]},
{"name": "Leland Acheson", "score": 81.15, "preferences": ["511207","422000"]},
{"name": "Wally Seppala", "score": 95.00, "preferences": ["511504"]},
{"name": "Cristi Authement", "score": 90.00, "preferences": ["511207"]},
{"name": "Yadira Millwood", "score": 83.15, "preferences": ["511795+2.5"]},
{"name": "Abram Bloomer", "score": 98.00, "preferences": ["511207","511795"]}]
```

Students' degree preferences are described in a semicolon-seperated list of unique degree codes ordered by preference. Preferences suffixed with `+n` indicate that the student has *flexible entry* for that degree and receives that many bonus marks when considered **for that degree only**. 

Bonuses may increase a score up to a maximum of 99.95. If the bonus pushes a score sbove the cutoff mark for a degree with no places available, the lowest scoring studenti is evicted to make way, and the degree cutoff is adjusted to the next lowest score (which may be the bonus-adjusted score of the new student). The evicted student is reconsidered for other degree as an appropriate for their score and any bonuses.

In `DegreeDistribution.java`, the `distribute` method takes in two parameters: the name of the degrees JSON file and the name of the students JSON file. and returns a JSON string of the format {"degrees": [...], "students": [...]}.

* The array of degrees is odered lexographically by their code. Each degree has a code, a cutoff, a vacancies boolean, and the number of students offered positions in the degree.
* A list of students, ordered by their original mark to 2 decimal places, with the degree code they have been offered.

If a student does not receive a degree place, or a degree has no cutoff, you should return `-` in the relevant location.

For all parts of this question that could result in tie breaking, including offers, eviction and final output, break any mark ties in ascending alphabetical order of student names. That is, if Amy and Zoe have the same effective score for a degree, Amy should be offered before Zoe, and Zoe should be evicted before Amy. 

Given the example files above, your program should produce the following:

```javascript
{"degrees": [
    {"code": 422000, "name": "Bachelor of Arts","institution": "University of New South Wales", "cutoff": 81.15, "offers": 2, "vacancies": true},
    {"code": 422300, "name": "Bachelor of Social Research and Policy","institution": "University of New South Wales", "cutoff": 86.50, "offers": 1, "vacancies": true},
    {"code": 423600, "name": "Bachelor of Planning","institution": "University of New South Wales", "cutoff": "-", "offers": 0, "vacancies": true},
    {"code": 511207, "name": "Bachelor of Arts (Media and Communications)","institution": "University of Sydney", "cutoff": 96.00, "offers": 2, "vacancies": false},
    {"code": 511504, "name": "Bachelor of Commerce","institution": "University of Sydney", "cutoff": 95.00, "offers": 1, "vacancies": false},
    {"code": 511795, "name": "Bachelor of Computer Science and Technology","institution": "University of Sydney", "cutoff": 83.15, "offers": 3, "vacancies": true}
],
"students": [
    {"name": "Abram Bloomer", "score": 98.00, "offer": 511207}, 
    {"name": "Wally Seppala", "score": 95.00, "offer": 511504}, 
    {"name": "Jeffie Honaker", "score": 94.50, "offer": 511795}, 
    {"name": "Ivan Connolly", "score": 91.00, "offer": 511207}, 
    {"name": "Cristi Authement", "score": 90.00, "offer": "-"}, 
    {"name": "Claudia Zingaro", "score": 84.50, "offer": 422300}, 
    {"name": "Hyun Castleberry", "score": 83.15, "offer": 511795}, 
    {"name": "Yadira Millwood", "score": 83.15, "offer": 511795}, 
    {"name": "Floria Rozar", "score": 82.25, "offer": 422000}, 
    {"name": "Leland Acheson", "score": 81.15, "offer": 422000}
]}
```

Within the two input files, you can assume that the degree codes will be unique, as are the names of students. The degree preference codes are guaranteed to be in the degrees JSON file.

Design and implement an object-oriented solution to this problem. You are given a near-complete suite of JUnit tests (note that coverage checking for problem has been disabled, which you can change if you like by editing lines 36 and 38 in `test.gradle`). 

**Extension Challenge**: Determine the edge case missing from the test suite, add it in and make sure your code passes.

Problem sourced from Grok Learning NCSS Challenge (Advanced), 2017 and adapted for Java.

## Submission

To submit, make a tag to show that your code at the current commit is ready for your submission using the command:

```bash
$ git tag -fa submission -m "Submission for Lab-03"
$ git push -f origin submission
```

Or, you can create one via the GitLab website by going to **Repository > Tags > New Tag**. 

We will take the last commit on your `master` branch before the deadline for your submission.
