package J_test;

import J_models.Game;
import J_models.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class  GameTest {

    Game game;

    @Before
    public void setup(){
        game = new Game(
                new Team("Gryffindor","oliver","harry",new String[]{"Angellina","ginna","katie"}),
                new Team("Slytherin","vinny","draco",new String[]{"Briget","harper","malcom"})
        );

    }

    @Test
    public void getPlaceholderTest() {
        assertEquals("chaser", game.getPlaceholder("<chaser> gets the next pass"));
    }

    @Test
    public void replacePlaceholderTest(){
        assertEquals("Katie gets the next pass",game.replacePlaceholder("<chaser> gets the next pass","chaser","Katie"));
    }

    @Test
    public void quaffleScoreTest(){
        Team team = game.getTeam("Gryffindor");
        game.quaffleScore(team);
        game.quaffleScore(team);
        assertEquals(game.getQuafflePoints()*2,game.getScore(team));

    }

    @Test
    public void catchSnitchTest(){
        Team team = game.getTeam("Gryffindor");
        game.catchSnitch(team);
        assertEquals(Game.getSnitchPoints(),game.getScore(team));
    }
}
