## W18_proj00 ##
## Ray Muhlenkamp - raymuhlenkamp
## Saagar Parikh - saagarparikh

(a) A one to three player vegas blackjack game, complete with money mechanics and saved statistics.

(b) 
- As a player, I should be able to play a blackjack game, so that I may enjoy wasting precious work time.
- As a player, I can control the amount of money being bet, so that I can win virtual dollars.
- As a player, I should be able to start a game with one or two other people, so that I can play with my friends.
- As a player, I should be able to save my statistics, so that I can see how I have progressed (brag to my friends).

(c) The software does run, albeit extremely poorly. There are multiple bugs, primarily relating to the GUI, such as multiple windows being opened when only one is needed. You have the option to play with one, two, or three human controlled players, against each other/the dealer. Statistics are saved relating to your name. 

(d)
- As a player, I should be able to play against one or more AI controlled opponents, so that I can practice while my friends have fun without me.
- As a game, I should properly display information to make the UI more presentable (possibly look like a table - that would be neat)
- As a game, play multiple different background music tracks, and allow the player to choose, so that they don't have to listen to the same ambient casino music the whole time.

(e)
The README.md is well formatted and organized for this project. It does a good job of highlighting the current issues as well as what could be possibly implemented to improve the project. The only issue I can find is that it goes from the earliest quarter to the most recent. This can be somewhat misleading as you may assume that the first entry in the list is the most recent, but you have to read the entire README to find out the current state (though I do believe that is required, it is more a personal gripe for me).

(f)
The build.xml file seems to be well formatted. There is a lot more information in it than I am used to reading from the lectures, but all the targets seem to be well defined, and the descriptions are all there and useful. Everything seems good here.

(g)
There are more than enough issues for us to earn 1000 points for this project. Like way more.

(h)
We have not yet added any issues.

(i)
The code for this project is criminally unorganized. Essentially, the vast majority of the running of the program is contained within one class, BlackjackGui, and really should be better implemented (with constructors), let alone refactored into multiple classes. The code is massive, and not nearly organized enough. Essentially, I have no clue how to begin approaching this project, by nature of how convoluted the code base is. What I am planning on doing is going through the entire code, starting at Main.java, and following the construction of all the different objects throughout the class, and possibly draw a diagram mockup of the project at runtime, to better understand what classes/objects interact with. 

(j) 
There exists quite a lot of JUnit tests for this code, spread across multiple tests. They do only test the game logic and class innerworkings, however, that really is the only thing that can be tested in this manner. Any "tests" of the GUI more or less just involve playing the game, and seeing if anything doesn't seem to properly work. At this moment in time, according to the README.md, the game logic is more or less perfect, and it is simply the graphic implementation that has all the issues. Of course, if we were to add more features that require tests, we would implement those tests in a similar manner to the prior existing tests, using seperate ClassNameTest.java JUnit test files for each class that needs testing of that manner. All GUI testing will be done through gameplay testing, to see howw everything actually functions. 


