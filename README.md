# Project: Football Game


#### Class Game
  * Is the main Operator of Board, two Teams and Ball. Also, has a method runTurn() which implements action() randomly for two Teams and check if 
    + could be scored a goal, or 
    + delegate ball to a Player
  
#### Class Ball
  * Ball has a spot on the Board and two fields where defined
    + current Player who has now the ball and 
    + previous Player who is the last Player who has has the ball.
    + Also has feature that delegate to a Player where
      + firstly find him if is he close to the ball, or 
      + secondly do it randomly

#### Class Team
  * Represent a football team which includes its Players. Also, 
    + Run action method where for every Player of its Team implement randomly:
      + 35% method movement()
      + 35% method transfer()
      + 30% method specialMove()
      and then delegate to this player if he can take himself the ball.
    + Also,  can add and delete a Player.

#### Class Player
  * Represent a Football Player of a Team where 
    + can move into specific row [movement()],
    + can transfer the ball in target column [transfer()],	
    + can do a special move [abstract()].

#### Class Back
  * Represent the defender Player of Team where
    + can steal the ball from opponent Player [specialMove().

#### Class Forward
  * Represent the Attacker Player of a team where
    + can transfer the ball in the centre columns,
    + can move away from a team-mate if his has the ball.

#### Class Main
  * Represent the main method where 
    + Read Players from a the “players.txt” file.
    + Print board every time that implements runTurn method until
      + One Team score 7 goals or
      + Loop run over 10 rounds.
