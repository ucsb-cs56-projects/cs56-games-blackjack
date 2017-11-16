Ryan Kirkpatrick and Ryan Lorica

### List of things that can be done (no particular order):

* Make this todo list once complete into issues.
* Add manifest for jar compilation
* Separate each of the guis. There are ones for the rules, the welcome, player
names, initial bets, and the actual game
* Idea for possibly setting players or similar objects as observers to a main
subject class that would control the flow of the game from player to player.
* Do composition for a lot of the gui elements like menubar
* Music stops looping after a while
* Current player should be displayed at the bottom without any notice of the
other players. After one player finishes, another should rotate to the front,
and start their turn. Also, we need to implement it in such a way that the player
class can affect these positions.
* Closing the naming players Gui doesn't stop the program from running. This can
be seen, because the music continues to play.
* Clean up the case statements of that are caused by number of players. These look very redundant, and they should be able to be refactored. There is also a block in the NameGui that can also be fixed.
* Make renaming with the menubar work properly.
* Create a main controller or something of sort to hold consistent variables, such as numPlayers, playerNames, and amountBet.
* The way players and player positions are handled needs an overhaul of refactoring.
