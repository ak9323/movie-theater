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
