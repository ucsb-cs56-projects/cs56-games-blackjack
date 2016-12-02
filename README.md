# cs56-games-blackjack

This is a current working version of a Blackjack game with a GUI. It can also be found at these links:

* [Archive page](https://foo.cs.ucsb.edu/cs56/issues/0000866/)
* [Mantis page](https://foo.cs.ucsb.edu/56mantis/view.php?id=866)

project history
===============
```
 W14 | andrewberls 5pm | ericpalyan | (jstaahl) Blackjack game
```

## Running the game

simply use the command:
```
ant run
```

![](http://i.imgur.com/rXE5Qe1.png)

## Recent changes
* Improved Javadoc
* Refactored files and directories to appropriately reflect current quarter (Winter 2014)
    * Previous: edu.ucsb.cs56.s13.Blackjack
    * Current: edu.ucsb.cs56.projects.game.blackjack
* Removed JWS references
* Added more JUnit tests
* Removed uneccessary build.xml targets (github handles those for us)
* Added Currency & Betting
* Added How To Play
* Added Double Down
* Cleaned up code and added comments
* Cleaned up and improved the GUI

## Possible improvements

* Online Multiplayer Blackjack - Using a server-client set up and java's built in socket libraries, create a blackjack game that two users can play on separate computers.
* Allow players to change the bet amount at the end of each round
* Allow a player to back out of the game
* Add in game statistics
* Save/load feature

## Other former Blackjack projects

These are past projects which may have similar/missing features. 

* [cs56_W12_492](https://foo.cs.ucsb.edu/cs56/issues/0000492/lab09b/)
* [cs56_W12_443](https://foo.cs.ucsb.edu/cs56/issues/0000443/)
* [cs56_S11_360](https://foo.cs.ucsb.edu/cs56/issues/0000360/)
* [Simple classes for PlayingCard, Deck, BlackJackHand](https://foo.cs.ucsb.edu/cs56/issues/0000215/)



W16 final remarks
-------------------
Most of the code you will be working on will be located in the BlackJackGui.java file. Things will be much easier if you can separate the code inside that file and put them into their own separate files because currently BlackJackGui.java is a very long file with literally every single frame the game uses. There are still some bugs in the game: ex.) When a player tries the "Add Money?" button. All the bugs are related to the GUI and not the game logic.

F16 final remarks
-------------------
This quarter, we fixed the following issues:
* #37 - We implemented card sound effects
* #38 - Reimplemented go method so that new round doesn't create a new window each game
* #45 - Reimplemented rules tab closing function
* #47 - Readded green to background color menu
* #48 - Maintained background color between rounds
* #49 - Edited appearance of split button
* #53 - Reimplemented betting frame closing function
* #54 - Edited music playing functionality
Currently the application runs well except for the split option. Most of the improvements needed are inside the BlackJackGui.java file. This class needs to be refactored - perhaps there is a design pattern that will work well. One notable bug in the Gui that has not been resolved is that the 'Hit' and 'Stay' buttons move around depending on which players turn it is. Possible paths of pursuit are as follows.
TO-DO:
* Refactor BlackjackGui - This is the most important step to take for the next group. This one file is >1800 lines long and handles far too many tasks as is. Dividing this up with not only improve your understanding of the code but will help subsequent groups greatly.
* Fix Split Option - Currently, this option somewhat works for a single split, but subsequent hits after the split are not handled properly. In the code currently, Aces can have the value 1 or 11, so Hand and Second Hand values are stored in each player class to handle this case. Unfortunately, each split must handle its own two hands, which can grow large and needlessly complicated.
* Fix Button Shifting - The buttons for hitting and staying in the game hop around the screen. This might be more easily fixed after a code refactor.
