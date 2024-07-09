package J_test;


import J_models.Team;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TeamTest {
    @Test
    public void hasNullTest(){
        String[] chasers = new String[]{null,"Ginny","Katie"};
        assertTrue(Team.hasNull(chasers));
    }

    @Test
    public void hasBlank(){
        String[] chasers = new String[]{"    ","Ginny","Katie"};
        assertTrue(Team.hasBlank(chasers));
    }
}
