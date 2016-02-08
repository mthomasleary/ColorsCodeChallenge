Welcome to the Code Challenge for Matthew Leary

======== What this is ========
This is a demo. Some of the technologies used in this project are overkill, given the assignment.
However, this is a demo of what I can do as a full stack engineer, not a stand alone app.

Server-side:
The server is a Jetty server with a REST-ful API.
    o The server uses Jersey to define API endpoints.
    o The server uses Jackson to serialize and deserialize JSON
The server persists data using mySQL with JDBC.

UI-side
The application uses Angular to better separate model, view and controller.
The application also defines an Angular directive specific to this project.
In addition, the application makes API calls to get colors for characters.
    o This was not entirely necessary but demonstrates my capability to both define and use an API.
The application accepts alpha, numeric and special characters, including whitespace.
    o Lowercase letters are considered different than capital letters.

======== What You Need ========
Git   - but if you're here, you probably know that already
Java  - 1.7 or newer
Maven - 3.3
mySQL - with user/password root/root, running on port 3306

======== Usage ========
To install or update after changes, run:
    mvn clean install

To run, use:
    mvn jetty:run
Then go to:
    http://localhost:8080
