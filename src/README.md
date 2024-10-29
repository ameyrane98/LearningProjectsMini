-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primeService/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile primeService/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile primeService/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile primeService/src/build.xml run -Darg0=(server/client)

## you can run it in the following manner:

## Instruction to run Server:
ant -buildfile primeService/src/build.xml run -Darg0=server

## Instruction to run Client:
ant -buildfile primeService/src/build.xml run -Darg0=client

-----------------------------------------------------------------------
## Description:

We have used HashMap to store the Queries produce by clients. We have made the methods in AllPrimeQueries synchronized as all of the methods do the processing of accessing or updating the query produced by multiple clients. We have also implemented runnable interface so that we can later extend out classes. 
