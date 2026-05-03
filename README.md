# Commonwealth Savers Technical Exercise
### Author: Kyle Engler

## Clone the repo 
1. `git clone https://github.com/kyle-engler/CommonwealthSavers.git`
2. `cd CommonwealthSavers && git checkout main`

## How-to
We can run the unit tests in the project by:

    ./gradlew test

We can run the program in demo mode with console output by:

    ./gradlew run

## Source code overview
* `Account.java` is the home of the Account class and is responsible for handling all logic for balance transfers. 
* `AccountManager.java` is the entry point for the demo mode of the project. The function `runDemo()` can be used to quickly test features and verify the output in the console.
* `AccountTest.java` contains all the JUnit tests for Account.java