# AdventChess

## :bulb: About

AdventChess seeks to offer chess fans a nontraditional method of enjoying the game of chess. Whereas the traditional game of chess requires players to memorize openings, and end game scenarios, AdventChess offers an adventure game mode which randomizes the start state of each game. As a result, players need to improvise each and every game through handling variable number of pieces. This approach to the timeless game of Chess seeks to bring out the true strategic essence of the game.

The project includes a custom Chess Engine written in Java (with adventure mode features), tested using JUnit. The backend is built using Spring-Boot Websockets to handle the multiplayer nature of the game. The websockets facilitate bidirectional communication between the users and the server, ensuring smooth handling and validation of multiple simultaneous game states. Through asynchronous management of game queues and matchmaking, AdventChess implements advanced concurrency techniques to optimize performance and enhance user experience. Additionally, features like blocking queues and ping testing are implemented to prevent errors and ensure a seamless gaming experience. The frontend is built using Angular and tailwindcss, and offers an intuitive and response user experience. It is tested using Karma to ensure error free rendering of components.



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

#### Backend

* Java 21.0.2 -
Install for your OS from [here](https://jdk.java.net/21/)

* Maven 3.9.6 
  * Windows - 
Download from here https://maven.apache.org/download.cgi
  * Linux/macOS
  ```
  brew install maven
  ``` 

#### Frontend

* NodeJS 20.11.1 -
  Install for your OS from [here](Download from here https://maven.apache.org/download.cgi)

* Angular CLI
```
npm install -g @angular/cli
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```


## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds


## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details
