# cs56-games-blackjack

This is a current working version of a Blackjack game with a GUI. It can also be found at these links:

* [Archive page](https://foo.cs.ucsb.edu/cs56/issues/0000866/)
* [Mantis page](https://foo.cs.ucsb.edu/56mantis/view.php?id=866)

## Running the game

simply use the command:
```
ant run
```

![](http://i.imgur.com/rXE5Qe1.png)

## W16 final remarks
Most of the code you will be working on will be located in the BlackJackGui.java file. Things will be much easier if you can separate the code inside that file and put them into their own separate files because currently BlackJackGui.java is a very long file with literally every single frame the game uses. There are still some bugs in the game: ex.) When a player tries the "Add Money?" button. All the bugs are related to the GUI and not the game logic.

##### Recent changes
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

##### Possible improvements

* Online Multiplayer Blackjack - Using a server-client set up and java's built in socket libraries, create a blackjack game that two users can play on separate computers.
* Allow players to change the bet amount at the end of each round
* Allow a player to back out of the game
* Add in game statistics
* Save/load feature

##### Other former Blackjack projects
These are past projects which may have similar/missing features.

* [cs56_W12_492](https://foo.cs.ucsb.edu/cs56/issues/0000492/lab09b/)
* [cs56_W12_443](https://foo.cs.ucsb.edu/cs56/issues/0000443/)
* [cs56_S11_360](https://foo.cs.ucsb.edu/cs56/issues/0000360/)
* [Simple classes for PlayingCard, Deck, BlackJackHand](https://foo.cs.ucsb.edu/cs56/issues/0000215/)


## F16 final remarks
-------------------
##### We fixed the following issues:
* #37 - We implemented card sound effects
* #38 - Reimplemented go method so that new round doesn't create a new window each game
* #45 - Reimplemented rules tab closing function
* #47 - Readded green to background color menu
* #48 - Maintained background color between rounds
* #49 - Edited appearance of split button
* #53 - Reimplemented betting frame closing function
* #54 - Edited music playing functionality  

Currently the application runs well except for the split option. Most of the improvements needed are inside the BlackJackGui.java file. This class needs to be refactored - perhaps there is a design pattern that will work well. One notable bug in the Gui that has not been resolved is that the 'Hit' and 'Stay' buttons move around depending on which players turn it is. Possible paths of pursuit are as follows.  

##### TO-DO:
* Change build.xml file to take in two usernames. Currently, our folder is hardcoded into the file, so this might not work for you.
* Refactor BlackjackGui - This is the most important step to take for the next group. This one file is >1800 lines long and handles far too many tasks as is. Dividing this up with not only improve your understanding of the code but will help subsequent groups greatly.
* Fix Split Option - Currently, this option somewhat works for a single split, but subsequent hits after the split are not handled properly. In the code currently, Aces can have the value 1 or 11, so Hand and Second Hand values are stored in each player class to handle this case. Unfortunately, each split must handle its own two hands, which can grow large and needlessly complicated.
* Fix Button Shifting - The buttons for hitting and staying in the game hop around the screen. This might be more easily fixed after a code refactor.  


[Javadoc link here.](https://github.com/UCSB-CS56-F16/cs56-games-blackjack_javadoc_davidtsu_chavez95)

## F17 final remarks
------------------
##### What the code does:
* Currently the program steps through, in order, the rules gui, the welcome gui, the names gui, the bet gui, and the black jack gui. This control is done by the GuiController and GuiModel class. These classes control the user's movement between the gui's and control the data being passed between them. Once the blackjack gui is reached, the game of black can be played by interacting with the gui.

-What features could be Added:
* Current player should always be at the bottom of the screen, so the player cards should rotate when player turn changes. This might include refactoring.

##### What bugs exist:
* The amount that the player's pot contains changes incorrectly with the amount that the player bets. This can be seen by continuously losing by continually pressing the hit button for several iterations of the game.
* The player names tab in the menu bar takes opens up the names gui to read in player names, but doesn't update the blackjack gui with the proper names. This could also include a new feature to drop and add players dynamically as the game progresses.

##### What opportunities for refactoring:
* BlackjackGui can still be refactored further as it is still around 850 lines.
* The program should be structured based on the Model View Controller Pattern, but still has some way to go before it is entirely MVC based. Several variables and methods would be better in other files.
* There is a better way to implement GuiController, and thats by a programming method called wait and notify. Apparently this link has information about it. https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html

##### What advice do we have:
* You most likely want to start with the main.java, understand how GuiController and GuiModel is called and what they do. Then glance at the implementations of the rules, welcome, name, and bet guis to get an understanding of how the flow of the program works and how actaully running the application corresponds to what is happening with the xGui.java and the xGuiController.java code. From there you should play around with the blackjack game to understand the control flow of the game from a player's perspective. After that it might be best to start from the BlackjackGuiController.java run command and step through how the BlackjackGui is made and played so that you have a base and understanding of what needs to be refactored and where in the code those locations need to take place.
