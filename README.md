# movie-theater

Redesigned movie-theater application. See high level <b>UML diagram</b> inside the movie-theater for better understanding of the new structure.

In addition of cleaning up the existing classess, added new classes to better separate and manage responsibilities. Recreated <b>JUnit test cases</b> from the ground up.


Description of <b>new</b> classes:

<b>Schedule (interface)</b> - a class implementing this interface is responsible for taking a list of movies and arranging the movies into a list of showings thus creating a schedule.

<b>SequentialScheduler</b> - implements Schedule. Shedules movies in sequential order. Takes a list of movies and turns it into a list of showings.

<b>TicketCounter</b> - accessed by Customer to make reservations. Responsible for making reservation based on PricePolicy.

<b>PricePolicy</b> - contains a regular price, plus can have 0 or more Discount implementations which are used to find discount price.

<b>Discount (abstract)</b> - abstract class to implement shared functionality of specific discounts if necesary. The class is extended by specific implementation of discount. There are total of 5 separte discount implementations.

<b>MovieType (enum)</b> - diffirentiates regular and special movie.

<b>TheaterException</b> - a checked expeption that is thrown in a number of places thoughout the application to highlight any violations of integrity.


# Original Problem Statement
# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format