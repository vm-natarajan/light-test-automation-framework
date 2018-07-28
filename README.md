# Light-Test-Automation-Framework

# Introduction

This README provides the information and guidance for scripting, execution and maintenance of the automated test scripts using this automation framework developed for any digital Automation using Selenium WebDriver 2.0. This has been developed by following using design patterns such as PageObject and PageFactory Models

# Pre-requisite

The user is expected to have basic knowledge on Automation Testing. Having a working knowledge of Selenium, Java, Maven and TestNG knowledge will be an added advantage.

# Tool Stack in the Framework

Framework/Library                       | Usage Description                                                     | Version
--------------------------------------- | ----------------------------------------------------------------------| -------
Selenium WebDriver                      | Java API which controls Web browser and emulate user interactions     | 3.8.1 
TestNG Framework                        | It provides features for organizing and running tests in a flexible   | 6.8
                                          manner    
Maven                                   | Dependencies and Build Management                                     | 4.0.0
Extent Reports                          | Open source Java library which provides API for reporting 
                                        | selenium-based tests                                                  | 3.0.6
POI                                     | Open source Java library which gives API's for reading MS EXCEL files | 3.16

----------------------------------------------------------------------------------------------------------------------------

# Directory and Packages Hierarchy

The source directory mainly contains packages to distinguish test methods, page objects and common utilities. All other resources such as driver exe files, test data source will be placed inside the resource director. Maven POM XML, TestNG XML and configuration properties are placed at the root of the project directory.

Directory/File                                 | Description                                                    
---------------------------------------------- | ------------------------------------------------------------------------
src/test/java/pages                            | Java package that holds page objects and page methods
src/test/java/tests                            | Java package that holds Java class files grouped under functionality, which
                                               | has TestNG test methods annotated with @Test
src/test/java/support                          | A collection of Java class files which provide features for reading data from 
                                               | external files, generating test reports etc.
master_suite.xml                               | TestNG suite file that controls the execution flow
pom.xml                                        | Maven POM file that maintains the dependencies and helps in build process
TestSummaryReport.html                         | HTML report that has details on recent automated test execution
history                                        | The directory where history of previous execution report will be maintained
confirgurations.properties                     | Any test parameters can be configured if it needs to be. This is required       
                                               | only if we have to override any existing configurations

----------------------------------------------------------------------------------------------------------------------------

# CI/CD

Framework is built in such a way that it can easily integrate with Jenkins to enable Continuous Integration.  Below is the config file attached for the job created for Automated Testing. 


# Test Script Maintenance

•	Any changes in the application functionalities can be remediated by modifying or creating methods under all impacted page classes, keeping all other pages unchanged.

•	Test failures due to changes in the web element properties can be handled by updating properties of the them in Page classes. 
