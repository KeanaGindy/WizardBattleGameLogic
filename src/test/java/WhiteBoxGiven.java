import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WhiteBoxGiven {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Before
    public void setUp() throws Exception {
    	System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() throws Exception {
    	System.setOut(standardOut);
    }

    // simple attack test with test of experience after attack
    @Test
    public void equalDP() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.attack(wiz1, wiz2);

        assertEquals(wiz1.experience, 7);
        assertEquals(wiz2.experience, 7);
    }
    
    // simple play test for complete node coverage, checks experience after
    @Test
    public void playAllNodes() {

        GamePlay game = new GamePlay();
        
        game.play();
        
        for(int i = 0; i < game.Opponents.size(); i++) {
        	Character x = game.Opponents.get(i);
        	
        	if(x.getClass().getName() == new Barbarian().getClass().getName()) {
        		assertEquals(x.experience, 25);
        	} else if(x.getClass().getName() == new Bard().getClass().getName()) {
        		assertEquals(x.experience, 12);
        	} else if(x.getClass().getName() == new Druid().getClass().getName()) {
        		assertEquals(x.experience, 16);
        	} else if(x.getClass().getName() == new Ranger().getClass().getName()) {
        		assertEquals(x.experience, 32);
        	} else if(x.getClass().getName() == new Rogue().getClass().getName()) {
        		assertEquals(x.experience, 18);
        	} else if(x.getClass().getName() == new Wizard().getClass().getName()) {
        		assertEquals(x.experience, 12);
        		x.health = -20;
        	}
        }
        game.play();
        assertEquals(game.Opponents.size(), 5);
       
    }
    
 // play test for when speed of player and opponent is the same, edge case 1
    
    @Test
    public void playEdgeCase2() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.play();

        assertEquals(wiz1.experience, 7);
        assertEquals(wiz2.experience, 7);
        wiz1.health = 0;
        game.play();
        
        assertEquals(game.Opponents.size(),1);
    }
    
 // play test for when speed of player is greater than opponent, edge case 2
    
    @Test
    public void playEdgeCase1() {
        Character wiz = new Wizard();
        Character bard = new Bard();

        GamePlay game = new GamePlay(wiz, bard);
        game.play();

        assertEquals(wiz.experience, 8);
        assertEquals(bard.experience, 7);
        bard.health = 0;
        game.play();
        
        assertEquals(game.Opponents.size(),0);
    }
    
    // play test for when double damage occurs, checks experience
    @Test
    public void playDoubleDamage() {
        Character wiz = new Wizard();
        Character bard = new Bard();
        bard.health = 8;
        
        GamePlay game = new GamePlay(wiz, bard);
        game.play();

        assertEquals(wiz.experience, 11);
        assertEquals(bard.experience, 7);
        game.play();
        
        assertEquals(game.Opponents.size(),0);
    }
   
    //Normal test to see if addOpponent can add to a list of opponents
    @Test
    public void addOpponent() {
    	GamePlay game = new GamePlay();
    	Character wiz1 = new Wizard();
    	game.addOpponent(wiz1);
    	
    	assertEquals(game.Opponents.size(),7);
    }
   
  //Normal test to see if removeOpponent can remove an opponent from the list
    @Test
    public void removeOpponent() {
    	GamePlay game = new GamePlay();
    	game.removeOpponent(game.Opponents.get(0));
    	
    	assertEquals(game.Opponents.size(),5);
    }
    
   /**
    //Testing printing of character details
    @Test
    public void characterPrintNormal() {
        Character wiz1 = new Wizard();

        wiz1.printInfo();
        Assert.assertEquals("Class: " + wiz1.getClass().toString()+"\nLevel: "+ wiz1.level +"\nHealth: "+ wiz1.health+"\nExperience: "+ wiz1.experience+"\nProtection: "+wiz1.protection+"\nDamage: "+wiz1.damage+"\nSpeed: "+wiz1.speed+"\n\nPoints per Level: " +wiz1.pointsPerLevel, outputStreamCaptor.toString()
        	      .trim());
        
    }
    */

}