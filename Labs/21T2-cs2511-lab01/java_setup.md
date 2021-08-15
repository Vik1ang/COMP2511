## Setup - Java

### Java Introduction

Create a new branch called `java_exercises` to complete the following exercises. Remember to merge back into master when you are finished.

### Installing Java On Your Laptop

Navigate to https://www.oracle.com/java/technologies/javase-jdk11-downloads.html in your browser, and download and install the appropriate version of Java.

For Windows, you may need to add additional **PATH** variables for `javac` and `java`. To do this, use the commands

`where javac`

`where java`

Then add the output to a System Environment PATH variable. To do this, search `Path` in Cortana, and select `Environment Variables`

Such as below...

![Windows Cortana Search](win1.jpeg)

![Windows Cortana Searching for "advanced system settings" then clicking "View advanced system settings" option](win2.jpeg)

![Two dialogs.  The one on the left has a series of system variables at the bottom, finding the system variable named "Path" then clicking the "edit" button opens the second dialog which then indicates you should press the "New" button to create a new text input to enter a new path option, this should have both the java and javac paths added](win3.jpeg)

You should now be able to run the `java` and `javac` commands, to run Java Class files and compile Java files respectively.

### Compiling and running your first Java program at the command line

* At the terminal, to compile your java program HelloWorld.java invoke the Java compiler as follows: `javac HelloWorld.java`
* Once, your program has compiled successfully into Java bytecode, you should be able to see a file `HelloWorld.class` Next, to interpret and run the java program, invoke the Java VM byte code interpreter as follows:
`java HelloWorld`
* At the terminal, you should be able to see:
`Hello, Welcome to Java!` or `Hello, Welcome to merge conflicts!`

### Setting up VSCode on your machines and importing the HelloWorld.java

While we can compile and execute Java via the command line, it is generally easier to work with Java in an Integrated Development Environment (IDE). In this course, we will be using VSCode for this purpose. These steps assume you're using your own computer.

Navigate to https://code.visualstudio.com/download and download the appropriate installer for your machine.

Once you have installed VSCode, use the `code` command in a terminal to start VSCode in Ubuntu, or click the appropriate button to open it.

Once in VSCode, open the `Extensions` tab - you can do this using the icon on the left side of the screen or by using the shortcut **CTRL+Shift+X**

Within the `Extensions` tab, search for and install both the `Java Extension Pack` and `Code Runner`

Note that for these extensions to work you must have installed a Java JDK in the earlier instructions, and set any appropriate PATH variables in Windows.

Within VSCode, to open the project, navigate to **File** > **Open Folder** and select the **example** folder as the folder to open from your system (this folder is under the `src` folder, which is immediately under the root directory of this lab).

If you have correctly installed the Java Extensions Pack, clicking the `Run` link immediately above the main function in any file should run that file.

Examine the VSCode **Terminal** or **Output** terminals to see the output. Try adding print statements using `System.out.println` to help debug any issues in your programs.

Watch this video to assist with setting up in VSCode:

https://d2xnkjysn6lg7q.cloudfront.net/files/unswVideo/transcodes/1621514358073-lab01-javavscodesetup/70F87301-B968-11EB-ABC0A207E449CBD9/1621514358073-lab01-javavscodesetup-portalHigh-YouTube.mp4

**HINT:** Now would be a good time to commit your changes with git. Make sure your commit doesn't duplicate `HelloWorld.java`!
