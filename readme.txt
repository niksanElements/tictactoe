users:
    niki
    alex
    benedict
    milan
    peter
    viki
    dani
    vasko
The passwords are the same as the name.


urls
game controller
/game/create - post - create new users
/game/list - get - get games to join
/game/join - post - join in game
/play/list - get - get player games
/game/{id} - get - get game properties

move controller
/move/create - post - create move
*/move/autocreate - get - auto create move 
/move/list - get - get movesInGame
/move/check - get - get player Move Position in game
/move/turn - get - is player turn

player controller 
/player/create - post - create new player
/player/players - get - get players
/player/logged - get - get logged players

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-javadoc-plugin</artifactId>
  <configuration>
    <doclet>org.umlgraph.doclet.UmlGraphDoc</doclet>
    <docletArtifact>
      <groupId>org.umlgraph</groupId>
      <artifactId>doclet</artifactId>
      <version>5.1</version>
    </docletArtifact>
    <additionalparam>-horizontal -attributes -enumconstants -enumerations -operations -types -visibility -inferrel -inferdep -hide java.* -inferrel -collpackages java.util.*</additionalparam>
    <show>public</show>
</configuration>
</plugin>
