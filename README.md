PROJECT GOALS: 

TestRail website functionality verification using an implemented set of positive & negative scenario UI/API tests.

Project requirements:

- JDK 17, Maven Build System (wrapper), Selenium, TestNG, 
Project build system: Maven

The following set of UI tests has been implemented:

- Positive scenario tests:
      1. Boundary values
      2. Pop-up window
      3. Dialog box display
      4. File upload & download 

- Negative scenario tests:
      1. Invalid credentials
      2. Data that exceeds permissible limits
      3. A test that reproduces any defect

A set of API tests:
      1. GET - 3 tests - NFE + 2 AFE
      2. POST

- Configured CI/CD (Jenkins) system for running tests and displaying reports

- Test Suites:
(TestNG.xml file with two configurations: Smoke + Regression Tests)