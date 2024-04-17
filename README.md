# AdventChess

## :bulb: About

AdventChess seeks to offer chess fans a nontraditional method of enjoying the game of chess. Whereas the traditional game of chess requires players to memorize openings, and end game scenarios, AdventChess offers an adventure game mode which randomizes the start state of each game. As a result, players need to improvise each and every game through handling variable number of pieces. This approach to the timeless game of Chess seeks to bring out the true strategic essence of the game.


## Technical

The project includes a custom Chess Engine written in Java (with adventure mode features), tested using JUnit. The backend is built using Spring-Boot Websockets to handle the multiplayer nature of the game. The websockets facilitate bidirectional communication between the users and the server, ensuring smooth handling and validation of multiple simultaneous game states. Through asynchronous management of game queues and matchmaking, AdventChess implements advanced concurrency techniques to optimize performance and enhance user experience. Additionally, features like blocking queues and ping testing are implemented to prevent errors and ensure a seamless gaming experience. The frontend is built using Angular and tailwindcss, and offers an intuitive and responsive user experience. It is tested using Karma to ensure error free rendering of components.


## Built With

* [Angular](https://angular.io/docs) - The web framework used
* [npm](https://nodejs.org/en/learn/getting-started/an-introduction-to-the-npm-package-manager) - The nodeJs package manager used for frontend
* [Karma](https://karma-runner.github.io/6.4/dev/public-api.html) - The frontend package used for testing
* [Maven](https://maven.apache.org/) - The backend dependency management
* [Java Spring Boot](https://spring.io/projects/spring-boot) - The backend websocket framework
* [JUnit](https://junit.org/junit5/docs/current/api/) - The backend package used for testing

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

#### Frontend

1. Clone the repository
2. Cd to frontend/AdventChess/src/app/shared/services
3. Edit the websocket.service.ts file by commenting out the current connection string and uncommenting the one on top and saving it. It should look like this:
```
...
  webSocketEndPoint = 'http://localhost:8080/wss';
  //  webSocketEndPoint = 'https://advent-chess-8bff396baa53.herokuapp.com/wss';
...
```
4. Cd back to frontend/AdventChess directory
5. Use npm to install the dependencies
```
npm install
```

6. Use the angular CLI to get the local development server running

```
ng serve
```
7. Terminal should show this message
```
   Initial chunk files | Names         |  Raw size
polyfills.js        | polyfills     |  83.60 kB | 
main.js             | main          |  52.67 kB | 
styles.css          | styles        |   7.01 kB | 

                    | Initial total | 143.28 kB
Application bundle generation complete. [1.300 seconds]
Watch mode enabled. Watching for file changes...
  ➜  Local:   http://localhost:4200/
  ➜  press h + enter to show help
```
8. Open the broswer of your choice and go to http://localhost:4200/


#### Backend

1. Clone the repository
2. Cd into the backend directory
3. Cd into backend/src/main/java/com/example/adventchess/config
4. Edit the WebSocketConfig.java file by commenting out the current connection string and uncommenting the one on top and saving it. It should look like this:
```
...
  private String url = "http://localhost:4200";
  //  private String url = "https://advent-chess.vercel.app";
...
```
5. Cd back to the root of the backend directory
6. Use mvn to install the dependencies
```
mvn clean install
```

7. Start the local Spring-Boot development server

```
mvn spring-boot:run
```
8. Terminal should show this message
```
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

2024-04-17T00:57:47.711-04:00  INFO 33198 --- [           main] c.e.a.MessagingStompWebsocketApplication : Starting MessagingStompWebsocketApplication using Java 21.0.2 with PID 33198 (/Users/jabirchowdhury/Projects/AdventChess/backend/target/classes started by jabirchowdhury in /Users/jabirchowdhury/Projects/AdventChess/backend)
2024-04-17T00:57:47.712-04:00  INFO 33198 --- [           main] c.e.a.MessagingStompWebsocketApplication : No active profile set, falling back to 1 default profile: "default"
2024-04-17T00:57:48.046-04:00  INFO 33198 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
...
...
2024-04-17T00:57:48.239-04:00  INFO 33198 --- [           main] o.s.m.s.b.SimpleBrokerMessageHandler     : Started.
2024-04-17T00:57:48.248-04:00  INFO 33198 --- [           main] c.e.a.MessagingStompWebsocketApplication : Started MessagingStompWebsocketApplication in 0.668 seconds (process running for 0.796)
```
9. The websocket connection url is http://localhost:8080
10. At this point the frontend and backend should be able to interact

## Running the tests

#### Frontend

1. Cd to frontend/AdventChess
2. Run tests using this command

```
ng test
```
3. Terminal should show these messages:
```
✔ Browser application bundle generation complete.
⠋ Generating browser application bundles...17 04 2024 01:18:49.665:WARN [karma]: No captured browser, open http://localhost:9876/
17 04 2024 01:18:49.680:INFO [karma-server]: Karma v6.4.2 server started at http://localhost:9876/
17 04 2024 01:18:49.681:INFO [launcher]: Launching browsers Chrome with concurrency unlimited
17 04 2024 01:18:49.684:INFO [launcher]: Starting browser Chrome
✔ Browser application bundle generation complete.
17 04 2024 01:18:52.476:INFO [Chrome 123.0.0.0 (Mac OS 10.15.7)]: Connected on socket 84zH6ZB3KiKb38NeAAAB with id 39466849
Chrome 123.0.0.0 (Mac OS 10.15.7): Executed 13 of 13 SUCCESS (0.105 secs / 0.083 secs)
```

#### Backend

1. Cd to the root of the backend
2. Run tests using this command

```
mvn test
```
3. Terminal should show these messages:
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.150 s -- in com.example.adventchess.ApplicationTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 84, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.006 s
[INFO] Finished at: 2024-04-17T01:14:14-04:00
[INFO] ------------------------------------------------------------------------
```

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details
