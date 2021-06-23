This project consists of just one package - org.tenx.assignment and two classes Main.java and VersionComparison.java

The test cases are written using Junit framework in the test folder inside the class TestVersionComparison.

To run this project:

1. Clone this repositry
2. Import the project into any IDE
3. Run a maven clean install and then the Main class under org.tex.assignment package

Sample output:
Enter the 2 strings for comparison - one in each line below:
2.0.1-alpha+78GTH8
3.1
3.1 > 2.0.1-alpha+78GTH8

Process finished with exit code 0

For running the test cases - run maven test or maven install and the output of the test execution will appear on the console and the test report will be available under target/surefire-reports