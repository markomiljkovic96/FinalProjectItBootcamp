# FinalProjectItBootcamp
Version: 1.0.0

FinalProjectItBootcamp is used for testing functionalities

Target application URL is: https://petstore.octoperf.com/

Target browsers: Chrome, Firefox, Edge

Browsers version: Chrome(77.0.3865.90), Firefox(74.0), Edge(80.0.361.109)

Run test suite: testng.xml Total tests run: 27

Used libraries:

    TestNG automation testing framework
    Selenium portable framework for testing web applications
    Apache POI library for manipulating various file formats

JavaDoc is located in doc folder.

The following website functionalities are tested:

    -left navigate menu
    -quick launch menu
    -main menu
    -register( all corect data, without data in one or more cells) 
    -login
    -logout
    -add to cart
    -calculating values in cart
    -entering data into input fields
    -select from drowdown menu
    -checkbox functionality
    -basic table checks (column values, links in a table)

This project contains 3 packages:

    pages
    tests
    utils
    All packages are in the src folder.

Package pages contains 6 classes:

    HomePage
    PetstoreMenuPage
    RegistrationPage
    SigninPage
    CartPage
    StoreItemPage
    
    
Package tests contains 5 classes:

    HomePageTest
    PetstoreMenuTest
    RegistrationPageTest
    SigninTest
    CartTest

Package utils contains 1 class:

   ExcelUtils

Folder data contains 1 file:

    pet-store-data (it contains information for completing the form)
