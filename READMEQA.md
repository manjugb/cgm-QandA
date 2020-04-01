# QuestionAndAnswer

# Code src/main/java
  -added DataAccess hanlding class(IncorrectResultSizeDataAccessException) this would handle the exception from negative cases like with    invalid data while submit querry into into database using flush and saveflush
  -added the comments what the class and methods do, this would helpul to new people to understand the sin betterway.
# tests src/test/java
  -added the comments what the class and methods do, this would helpul to new people to understand the in betterway.
  -modified some of the existing test methods and code

# Features covered with tests
  
Therefore the following restrictions apply:
  - A Question is a String with max 255 chars
  - An Answer is a String with max 255 chars
  - A Question can have multiple answers (like bullet points)
  - If the user asks a question it has to be exactly the same as entered – no “best match”.
  - If the user asks a question which is not stored yet the program should print “the answer to life, universe and everything is 42” according to “The hitchhikers guide to the Galaxy”
  - If the user asks a question which is  stored the program should print all answers to that question. Every Answer in a separate line
  - adding a question looks like: 
    - <question>? “<answer1>” “<answer2>” “<answerX>”
    - Char “?” is the separator between question and answers
    - Every Question needs to have at least one answer but can have unlimited answers all inside of char “
  
# Features vs Tests covered
  -Added testscripts with positive cases
  -Added testscripts with negative cases
  -Added Exception handling for negative cases to make sure that tests run with out issues
  -added Assertions against with injected values to make that tests run with out issues.

# Getting Started

# Prerequisites
  -What things you need to install the software and how to install them java and maven needs to be install before running this maven project http://maven.apache.org/install.html https://www.java.com/en/

# Command Line:
download this project unzip and save into local folder where maven and java installed on window command line go to folder run mvn clean test

# Ecliplse:
Install spring-boot from eclipse market to run from spring-boot
Note:Make sure M2E Intalled in Eclipse,but this should come while installed eclipse Download project from this repository 1.open Eclipse create workspace 2.File->Open->Import->Existing Maven Project and click on next 3.successfully import 4.Right Click on Project->Run as->Maven Test

# Jenkins Steps Build:

1.Click on New job 2.Write Desc and choose maven project(note:maven project is not availble, try install from manage jenkins -> Manage Plugins) 3.Go to build option enter root path with pom.xml 4.save and run build


   
