# Name: 
leanft-selenium-java-add-leanft-to-existing-selenium-project

# Description
This project has two branches. 
- master is a branch that is a pure IntelliJ Selenium project against AOS.
- AddLeanFT is a branch that has modified the imports to allow working with LeanFT OIC

Note: If you run the default test, it does a purchase against the public AOS. The username/password in the code is a placeholder, and must be replaced with working credentials.

# Usage
Just clone the project, then use git to show how minor the differences are to enable LeanFT for Selenium. In IntelliJ project, just right click at the top level and comparet to branch.

There are a few import differences, and the AddLeanFT branch has two LeanFT specific objects. The more interesting is the tabletToSelect, which shows the LeanFT ByEach object constructor, which shows how easy it is to use complicated object constructors. It has a looping structure just to show that it works, randomly selecting from the available tablets.

The difference shows some white space differences and a few places were objects were declared differently. These can be ignored.
