**Name: Mitran Andrei-Gabriel**

**Group: 323CA**

# OOP TV

### Description:

* The program works by using the Database and App classes in the Main class
  to simulate the website. After the Input class is used to collect all input
  from the input file, actions begin to be executed.
* The Database and App classes implement a Singleton design pattern. It is
  useful, since we only need one instance of each. They can simply be cleared
  and reused for the next input file.
***
* The User and Movie classes are used to store the information from the input.
  They possess extra fields, as well as multiple methods.
* They both implement the Builder design patterns. The thought process behind
  choosing this implementation is that in the future there may be fields that
  are optional for both premium users, and premium movies, if such movies are
  to be added.
***
* The Page class is used to simulate navigating through the pages. There is a
  subclass for each type of page.
* Although there is only one SeeDetails page, the flow of the program allows
  for this. Since the currentMoviesList is held by the app (combined with
  the fact that we can change to a different movie's SeeDetails page
  directly), this means that the currentMoviesList should not only hold the
  specific movie (or no movie if it fails), but all others as well. By
  switching back to the Movies page in case of failure, we make sure
  everything works well.
* Each page is also connected to itself.
* Pages do not have methods, they are merely used for simulating the website's
  flow.
***
* Each page is also Visitable by a Visitor that verifies whether a connection
  to another page exists or whether a feature can be executed while being on
  the page.
* The Visitors are called 'Verifiers'. They may have added functionalities in
  the future when it comes to checking that a user already exists for the
  login feature, however this would require more parameters in the 'verify'
  (visit) method.
***
* When executing a 'change page' action, a Verifier is used to make sure we
  can switch to said page.
* If the page is Logout, the user is logged out immediately (if the user has
  logged in).
* If the page is Movies, all movies are shown.
* If the page is SeeDetails, the specific movie is shown (if it can be
  accessed).
***
* When executing an 'on page' action, a different Verifier is used to ensure
  that the specified feature can be performed on the current page.
* When performing a 'login' or a 'register' action, the normal checks are
  performed. If everything is as it should be, the app changes its currentUser
  and currentMoviesList. Only the movies available in the user's country are
  visible for them, and as such, present in the currentMoviesList. 
* When performing a 'search' action, the app changes its currentMoviesList to
  match the search.
* When performing a 'filter' action, the app utilizes two methods: sort and
  contains.
* Sorting is done using the Strategy design pattern, which in turn is created
  by using a Factory design pattern. A sortStrategy is generated based on what
  the given SortInput (what field(s) is/are used for sorting, as well as
  whether it is done in the ascending or descending order is specified).
* The 'contains' method checks whether a movie contains everything specified
  in its fields (actors or genres).
* When buying tokens or buying a premium account actions are performed, the
  user returns whether the action was done successfully or not based on what
  their current balance is or how many tokens they have. The same concept
  applies for a 'purchase' action (if successful, the movie is added to the
  current user's purchasedMovies).
* For a 'watch' action, the movie is added to the current user's
  watchedMovies. The movie must have been purchased in advance.
* For a 'like' or a 'rate' action, besides the movie being added in the user's
  appropriate list, the movie's fields are also modified. The movie must have
  been watched beforehand. A rating cannot be higher than 5.

### Comments:
* Attempting to write multiple design patterns now may not have been useful.
  Hopefully, it will make writing the second part easier.
* If a standard user's numFreePremiumMovies were set to 0 instead of 15, the
  Builder pattern may have been more helpful.
