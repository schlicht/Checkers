# Checkers

##Prerequisites
Programmed in Java.

##How To Play

### Start Game
Start the game by using the command:
java classes/CheckersGame
That will automatically start a game where you will play the red pieces against the AI playing black.

### Input
Input each move as numbers representing each tile separated by a space. For example the input:
```
31 42
```
moves the red piece from the tile at row 3 column 1 to the tile at row 4 column 2.

## The AI
The AI uses alpha-beta prunning to determine the next move. alpha-beta prunning is a depth first search algorithm with a 
max depth applied to zero sum games. The algorithm prunes sections of the search space if it will not produce a greater 
score than a previously found move. The algorithm typical square roots the search space. The score of a state is determined
by the following heuristic:
```
Piece = 1
Piece is Promoted = 1
Piece is on back row = 1
Piece is on edge of Board = 1
Wins the Game = 1
```
The score is increase by the amounts above for red pieces and decremented for black pieces. For example, if there are 5 red 
pieces, and 7 black pieces.
