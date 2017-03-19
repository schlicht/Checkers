# Checkers

## Getting Started
Programmed in Java.

You play the red pieces against the AI playing black.

### Input
Input each move as numbers representing each tile separated by a space. For example the input:
```
31 42
```
moves the red piece from the tile at row 3 column 1 to the tile at row 4 column 2.

## The AI
The AI use [min-max](https://en.wikipedia.org/wiki/Minimax) search with alpha-beta prunning to determine the next move. min-max is a depth first search algorithm with a max depth applied to zero sum games. [alpha-beta prunning](https://en.wikipedia.org/wiki/Alpha–beta_pruning) removes sections of the search space if it will not produce a better score than a previously found move. min-max search has a time complexity of O(b^d) (b is the number of possible moves and d is the number of turns ahead that is searched) but the best case improvement of alpha-beta prunning is performace is square rooting the search space. The score of a state is determined by the following heuristic:
```
Piece = 1
Piece is Promoted = 1
Piece is on back row = 1
Piece is on edge of Board = 1
Wins the Game = 1
```
The score is increased for red pieces and decremented for black pieces. For example, if there are 5 red 
pieces with 1 king and 4 on the back row, and 7 black pieces with no kings, 2 on the back row, and 3 on the edges, the score of the board would be -2.

# Use
The project is just meant as a fun way to improve my coding process and structure, but feel free to improve upon it if you'd like!
