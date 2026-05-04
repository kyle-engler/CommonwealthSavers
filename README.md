# Commonwealth Savers Technical Exercise
### Author: Kyle Engler

## Clone the repo 
1. `git clone https://github.com/kyle-engler/CommonwealthSavers.git`
2. `cd CommonwealthSavers && git checkout main`

## How-to

We can run the program in demo mode with console output by:

    ./gradlew run

We are also able to build the demo as a runnable Jar:

    ./gradlew clean build
    java -jar build/libs/CommonwealthSavers-1.0.jar

We can run the unit tests in the project by:

    ./gradlew test

## Source code overview
* `Account.java` is the home of the Account class and is responsible for handling all logic for balance transfers. 
* `AccountManager.java` is the Main Class of the project
  * It is the entry point for the demo mode of the project. The function `runDemo()` can be used to test features and verify
  the output in the console.
  * Contains helper functions, `transferEntireBalanceEvenly()` and `transferBalance()`, that can automatically create 
  accounts and perform transfers.
* `AccountTest.java` contains the JUnit tests for Account.java