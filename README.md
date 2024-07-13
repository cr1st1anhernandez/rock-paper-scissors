# Rock-Paper-Scissors Game

This project implements a generalized rock-paper-scissors game in Java. It allows for an arbitrary number of moves specified as command line arguments, generates HMAC using SHA-256 for integrity, and provides a CLI interface for user interaction.

## Instructions

### Requirements

- Java Development Kit (JDK) version 7 or higher
- Maven (for building and managing dependencies)

### Building the Project

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/cr1st1anhernandez/rock-paper-scissors.git
   ```

2. Navigate to the project directory:

   ```bash
   cd rock-paper-scissors
   ```

3. Compile and package the project using Maven:

   ```bash
   mvn clean package
   ```

### Running the Game

After building the project, you can run the game using the generated JAR file.

1. Execute the JAR file with at least three non-repeating moves as command line arguments:

   ```bash
   java -jar target/rock-paper-scissors-1.0-SNAPSHOT.jar rock paper scissors
   ```

   Replace `rock`, `paper`, `scissors` with your desired moves.

2. Follow the prompts to interact with the game:
   - Enter your move based on the displayed menu.
   - View the HMAC key generated before making your move.
   - Compare results to determine the winner between your move and the computer's move.

### Help and Testing

To verify different scenarios and options:

- Use incorrect parameters (e.g., repeated moves, even number of moves) to ensure error handling:

  ```bash
  java -jar target/rock-paper-scissors-1.0-SNAPSHOT.jar rock paper rock
  ```

- Generate the help table with five parameters:

  ```bash
  java -jar target/rock-paper-scissors-1.0-SNAPSHOT.jar rock paper scissors lizard Spock
  ```

- Verify basic functionality with different numbers of moves (e.g., 3 and 7).

### Example Output

Here's an example of expected output during gameplay:

```
HMAC: 3Z2YxqsP7jo9qp6zi/sZ97EIbSkzmmmr3cPlId+0gyA=
Available moves:
1 - rock
2 - paper
3 - scissors
0 - exit
? - help
Enter your move: 2

Your move: paper
Computer move: rock
You win!
HMAC key: BD9BE48334BB9C5EC263953DA54727F707E95544739FCE7359C267E734E380A2
```

### Troubleshooting

- If you encounter issues with Maven or Java, ensure that your environment variables (`JAVA_HOME`, `PATH`) are correctly set.
- Double-check the command line arguments passed to the JAR file for correct format and sequence.

## Contributing

Feel free to fork this repository, make improvements, and submit pull requests. Suggestions and feedback are welcome!
