
# $${\color{pink}Snake!}$$

<p align = "center">
<img src="https://github.com/mac-comp128-fa23/course-project-02_steph_tu_nadya_snake/assets/118240368/1d461417-8d17-4f70-b5c1-6e8ee1c837f4" width="597" />
</p>

***

### Made by Stephanie Miles, Tu Tran, and Nadya Konadu

## __Product Description__

Our team created a replica of the popular interactive Snake game. When the user runs the algorithm, the gameboard immediately pops up within a canvas window with the initial snake and food object generated inside the grid. 

The objective of Snake is to control a snake that moves around a two-dimensional playing field or grid. The snake starts as a short segment and must eat food items scattered throughout the playing area to grow longer. The primary challenge is to avoid running into the walls of the playing field and, more importantly, the snake's own tail. Players score points each time the snake eats a piece of food. The longer the snake, the higher the score. 

<p align = "center"> 
  <img width="597" alt="Screenshot 2023-12-02 at 2 56 40 PM" src="https://github.com/mac-comp128-fa23/course-project-02_steph_tu_nadya_snake/assets/118240368/b876f125-7a66-49d4-ab6a-aa6ec3af3c49">
</p>

***

> Technical Requirements: Java 17 and a Java-friendly IDE (we used VS Code) installed.

__Things to know:__

- The snake moves continuously in the direction that it is facing, so don't forget to hit another arrow key before it either runs into itself or the wall!
- The snake's length increases each time it consumes food, so your goal is to eat as much as you can!
- There are two rocks randomly generated within the board. If the snake runs into it, your score is decremented by 1 AND the snake loses a segment of its body.

__How to run the program:__

1. Open up the source code in an IDE.
2. Navigate to the main file (Game.Java)
3. Make sure the main method is present (if commented out, please uncomment it)
4. Hit the "run" button.

__How to play the game:__

1. Press the spaceboard on your keyboard to start the game.
2. Locate the food object placed randomly on the game board.
3. Control the snake via the arrow keys (up, right, down, left) towards the grid containing the food.
4. Collect as many pieces of food possible to grow your snake WITHOUT running into itself, hitting a rock, or running into the board's walls.

## __Known Issues:__

> Because there are so many things going on within the program, the window will sometimes freeze on macs after a few games are played (or if the snake gets super long).

## __Societal Impact:__

There are clear accessibility issues present in our program. The game heavily relies on the user being able to press the arrow, shift, and spacebar keys, so users that do not have the ability to use a keyboard will experience significant barriers when attempting to play our game. Similarly, the game contains no audio component and is entirely visual, besides the gameplay music (that has no effect on the actual game itself), so the visually impaired will also have difficulties playing this game. The game also relies on the player being able to see distinctions between colors, so someone who is color blind may experience some limitations when playing the game. If a user is color blind, it may be very hard to distinguish between the board, food, and snake as the only things that differentiates them is their cell color. 

## __Goals:__

Our team intends to create an exact replica of Snake. The project's core goals include successfully implementing all of the original game details (Snake needs to eat food, snake cannot run off the board, snake can't run into itself, etc.) as well as using what we learned this semester in Data Structures to do it. Peripheral goals include implementing an extra aspect into the game where the snake loses segments of itself after a certain amount of time without eating food. If there is enough time towards the end, we will also focus on upgrading the UI and user experience. The biggest goal for our team is to successfully showcase the skills and knowledge we have learned throughout CS128 in a self-driven project. 

## __Data Structures:__

### *Linked List Stack*

Our project's fundamental decision was to utilize a linked list implemented as a stack to represent the snake's body. The justification for this choice lies in the necessity for constant insertions and deletions at the end (tail) of the snake, aligning seamlessly with the Last-In-First-Out (LIFO) behavior of a stack. 

The dynamic nature of the snake's growth and shrinkage was pivotal in making the linked list stack the most suitable data structure. Unlike an alternative approach involving an array or a list of cells, where each element corresponds to a snake segment, the linked list's inherent ability to efficiently manage insertions and deletions at the head and tail was deemed crucial for the game's dynamics. For instance, in the addToTail method, a new cell is created and added to the tail of the snake. The time complexity of this operation is O(1), as it involves constant-time operations for creating a new cell, setting its previous reference, pushing it onto the stack, and updating the tail.

Similarly, in the removeFromTail method, the tail is updated to its previous cell, and the corresponding cell is removed from the stack. This operation also has a time complexity of O(1), as it involves constant-time operations of updating references and popping the top element from the stack. In contrast, had we chosen an array or list of cells, the time complexity for these operations could potentially have been O(n), where n is the snake's length, due to the need for shifting elements.

### *Two-Dimensional Array*

For our game board representation, we opted for a 2D array of Cell objects to manage the positions of the snake and food objects efficiently. The array provided constant-time access to any position, aiding in updating the game elements’ positions and checking for collisions. The use of a 2D array simplifies the indexing and retrieval of specific cells based on their row and column coordinates, which is crucial for the game logic.

An alternative data structure that we could have used is a linked list of cells, where each cell holds a reference to the next cell in the sequence. However, we opted against using a linked list in this context. Linked lists are advantageous for dynamic data structures where elements are frequently inserted or removed, but in our game board scenario, the size remains constant. 

In terms of time complexity for random access, the 2D array outperforms a linked list as they require traversing nodes sequentially whereas with the 2D array, you can directly access any element using row and column indices in constant time (O(1)). 

