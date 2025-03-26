# Live Football World Cup Score Board

This project is a Java-based library for tracking live football match scores during the World Cup. It provides functionality to start, update, and finish matches while maintaining a sorted scoreboard based on total score and start time.

## Features
- Start a new match with team names and start time.
- Update the score of an ongoing match.
- Finish a match and remove it from the scoreboard.
- Retrieve a summary of all ongoing matches, sorted by total score and start time.

## Notes
- In-memory storage: The scoreboard uses in-memory collections (e.g., List) to track matches. Data will be lost when the application stops.
- Match Order: The summary of matches is sorted primarily by total score. Matches with the same total score are sorted by the most recent start time.
- Score Updates: The solution assumes that only valid matches will be updated or finished. There is basic error handling in place to handle attempts to update or finish non-existent matches.
- Edge Cases: The implementation handles edge cases such as updating a non-existent match or finishing a match that doesn't exist, throwing appropriate exceptions with meaningful messages.

## Installation
1. Clone the repository:
   ```sh
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```sh
   cd scoreboard
   ```
3. Compile the project:
   ```sh
   javac -d bin src/com/scoreboard/*.java
   ```

## Running Tests
Run the JUnit tests:
```sh
mvn test
```
