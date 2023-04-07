import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



@RunWith(Parameterized.class)
public class TDD {

    private Class<GamePlay> classUnderTest;

    @SuppressWarnings("unchecked")
    public TDD(Object classUnderTest) {
        this.classUnderTest = (Class<GamePlay>) classUnderTest;
    }

    /**
     * Defines classes to be tested.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {GamePlay.class}
        };
        return Arrays.asList(classes);
    }

    private GamePlay createGame() throws Exception {
        Constructor<GamePlay> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    GamePlay game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }

     
    //normal experience when healthy
    @Test
    public void dealtDamageNormalExperienceHealth100() {
        Wizard wiz = new Wizard();
        
        game.dealDamage(wiz);
        assertEquals(wiz.experience, 5);

        Barbarian bar = new Barbarian();
        game.dealDamage(bar);
        assertEquals(bar.experience, 10);
        
        Bard bard = new Bard();
        game.dealDamage(bard);
        assertEquals(bard.experience, 6);

        Druid dru = new Druid();
        game.dealDamage(dru);
        assertEquals(dru.experience, 7);

        Ranger ran = new Ranger();
        game.dealDamage(ran);
        assertEquals(ran.experience, 8);

        Rogue ro = new Rogue();
        game.dealDamage(ro);
        assertEquals(ro.experience, 5);
    }


    // Damage is not dealth because health is 0
    @Test
    public void dealtDamageHealth0() {
        Wizard wiz = new Wizard();
        wiz.health = 0;
        Barbarian bar = new Barbarian();
        bar.health = 0;
        Bard bard = new Bard();
        bard.health = 0;
        Druid dru = new Druid();
        dru.health = 0;
        Ranger ran = new Ranger();
        ran.health = 0;
        Rogue ro = new Rogue();
        ro.health = 0;
        
        assertEquals(game.dealDamage(wiz), 0);
        assertEquals(game.dealDamage(bar), 0);
        assertEquals(game.dealDamage(bard), 0);
        assertEquals(game.dealDamage(dru), 0);
        assertEquals(game.dealDamage(ran), 0);
        assertEquals(game.dealDamage(ro), 0);
    }

    // damage should be doubled because character health is less than 10
    @Test
    public void dealtDoubleDamageHealth8() {
        Wizard wiz = new Wizard();
        wiz.health = 8;
        Barbarian bar = new Barbarian();
        bar.health = 8;
        Bard bard = new Bard();
        bard.health = 8;
        Druid dru = new Druid();
        dru.health = 8;
        Ranger ran = new Ranger();
        ran.health = 8;
        Rogue ro = new Rogue();
        ro.health = 8;

        assertEquals(game.dealDamage(wiz), 10);
        assertEquals(game.dealDamage(bar), 20);
        assertEquals(game.dealDamage(bard), 12);
        assertEquals(game.dealDamage(dru), 14);
        assertEquals(game.dealDamage(ran), 16);
        assertEquals(game.dealDamage(ro), 10);
    }

    // Damage is not dealth because health is less than 0

    @Test
    public void dealtDamageHealthNegative3() {
        Wizard wiz = new Wizard();
        wiz.health = -3;
        Barbarian bar = new Barbarian();
        bar.health = -3;
        Bard bard = new Bard();
        bard.health = -3;
        Druid dru = new Druid();
        dru.health = -3;
        Ranger ran = new Ranger();
        ran.health = -3;
        Rogue ro = new Rogue();
        ro.health = -3;

        assertEquals(game.dealDamage(wiz), 0);
        assertEquals(game.dealDamage(bar), 0);
        assertEquals(game.dealDamage(bard), 0);
        assertEquals(game.dealDamage(dru), 0);
        assertEquals(game.dealDamage(ran), 0);
        assertEquals(game.dealDamage(ro), 0);
    }


    @Test
    public void dealtDamageExperienceZeroHealthNegative3() {
        Wizard wiz = new Wizard(); 
        wiz.health = -3;
        Barbarian bar = new Barbarian();
        bar.health = -3;
        Bard bard = new Bard();
        bard.health = -3;
        Druid dru = new Druid();
        dru.health = -3;
        Ranger ran = new Ranger();
        ran.health = -3;
        Rogue ro = new Rogue();
        ro.health = -3;

        game.dealDamage(wiz);
        assertEquals(wiz.experience, 0);
        game.dealDamage(bar);
        assertEquals(bar.experience, 0);
        game.dealDamage(bard);
        assertEquals(bard.experience, 0);
        game.dealDamage(dru);
        assertEquals(dru.experience, 0);
        game.dealDamage(ran);
        assertEquals(ran.experience, 0);
        game.dealDamage(ro);
        assertEquals(ro.experience, 0);
    }

    //Damage is greater than protection

    @Test
    public void takeDamageNormalProtectionCheckHealth() {
        Wizard wiz = new Wizard(); 
        game.takeDamage(wiz, 5);
        assertEquals(wiz.health, 96);
        
        Barbarian bar = new Barbarian();
        game.takeDamage(bar,11);
        assertEquals(bar.health, 99);
        
        Bard bard = new Bard();
        game.takeDamage(bard, 5);
        assertEquals(bard.health, 98);

        Druid dru = new Druid();
        game.takeDamage(dru, 5);
        assertEquals(dru.health, 99);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 10);
        assertEquals(ran.health, 98);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 8);
        assertEquals(ro.health, 98);
    }


    //Damage is greater than protection
    @Test
    public void takeDamageNormalProtectionCheckExperience() {
        Wizard wiz = new Wizard(); 
        game.takeDamage(wiz, 5);
        assertEquals(wiz.experience, 2);

        Barbarian bar = new Barbarian();
        game.takeDamage(bar, 11);
        assertEquals(bar.experience, 0);

        Bard bard = new Bard();
        game.takeDamage(bard, 5);
        assertEquals(bard.experience, 1);

        Druid dru = new Druid();
        game.takeDamage(dru, 5);
        assertEquals(dru.experience, 0);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 10);
        assertEquals(ran.experience, 1);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 8);
        assertEquals(ro.experience, 1);
    }



    //Damage greater than shield && health
    @Test
    public void takeDamageHealthBelowZeroCheckHealth() {
        Wizard wiz = new Wizard();
        game.takeDamage(wiz, 120);
        assertEquals(wiz.health, -19);
        
        Barbarian bar = new Barbarian();
        game.takeDamage(bar,120);
        assertEquals(bar.health, -10);

        Bard bard = new Bard();
        game.takeDamage(bard, 120);
        assertEquals(bard.health, -17);

        Druid dru = new Druid();
        game.takeDamage(dru, 120);
        assertEquals(dru.health, -16);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 120);
        assertEquals(ran.health, -12);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 120);
        assertEquals(ro.health, -14);
    }

    //Damage greater than shield && health
    @Test
    public void takeDamageHealthBelowZeroCheckExperience() {
        Wizard wiz = new Wizard(); 
        game.takeDamage(wiz, 120);
        assertEquals(wiz.experience, 59);

        Barbarian bar = new Barbarian();
        game.takeDamage(bar,120);
        assertEquals(bar.experience, 55);

        Bard bard = new Bard();
        game.takeDamage(bard, 120);
        assertEquals(bard.experience, 58);

        Druid dru = new Druid();
        game.takeDamage(dru, 120);
        assertEquals(dru.experience, 58);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 120);
        assertEquals(ran.experience, 56);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 120);
        assertEquals(ro.experience, 57);
    }

    //Damage same as shield.
    @Test
     public void takeDamageSameAsShieldCheckExperience() {
        Wizard wiz = new Wizard(); 
        game.takeDamage(wiz, 1);
        assertEquals(wiz.experience, 0);
        
        Barbarian bar = new Barbarian();
        game.takeDamage(bar,10);
        assertEquals(bar.experience, 0);

        Bard bard = new Bard();
        game.takeDamage(bard, 3);
        assertEquals(bard.experience, 0);

        Druid dru = new Druid();
        game.takeDamage(dru, 4);
        assertEquals(dru.experience, 0);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 8);
        assertEquals(ran.experience, 0);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 6);
        assertEquals(ro.experience, 0);
    }

    //Damage same as shield.
    @Test
    public void takeDamageSameAsShieldCheckHealth() {
        Wizard wiz = new Wizard(); 
        game.takeDamage(wiz, 1);
        assertEquals(wiz.health, 100);

        Barbarian bar = new Barbarian();
        game.takeDamage(bar,10);
        assertEquals(bar.health, 100);      

        Bard bard = new Bard();
        game.takeDamage(bard, 3);
        assertEquals(bard.health, 100);

        Druid dru = new Druid();
        game.takeDamage(dru, 4);
        assertEquals(dru.health, 100);

        Ranger ran = new Ranger();
        game.takeDamage(ran, 8);
        assertEquals(ran.health, 100);

        Rogue ro = new Rogue();
        game.takeDamage(ro, 6);
        assertEquals(ro.health, 100);
    }

    //Damage less than shield, check healing.
    @Test
    public void takeDamageLessThanShieldCheckHealth() {
        Wizard wiz = new Wizard(); 
        wiz.health = 94;
        Barbarian bar = new Barbarian();
        bar.health = 94;
        Bard bard = new Bard();
        bard.health = 94;
        Druid dru = new Druid();
        dru.health = 94;
        Ranger ran = new Ranger();
        ran.health = 94;
        Rogue ro = new Rogue();
        ro.health = 94;
 
        game.takeDamage(wiz, 0);
        assertEquals(wiz.health, 94);
        game.takeDamage(bar,5);
        assertEquals(bar.health, 96);
        game.takeDamage(bard, 2);
        assertEquals(bard.health, 94);
        game.takeDamage(dru, 2);
        assertEquals(dru.health, 95);
        game.takeDamage(ran, 4);
        assertEquals(ran.health, 96);
        game.takeDamage(ro, 3);
        assertEquals(ro.health, 95);
    }

    //Damage less than shield, check experience.
    @Test
    public void takeDamageLessThanShieldCheckExperience() {
        Wizard wiz = new Wizard(); 
        wiz.health = 94;
        Barbarian bar = new Barbarian();
        bar.health = 94;
        Bard bard = new Bard();
        bard.health = 94;
        Druid dru = new Druid();
        dru.health = 94;
        Ranger ran = new Ranger();
        ran.health = 94;
        Rogue ro = new Rogue();
        ro.health = 94;
 
        game.takeDamage(wiz, 0);
        assertEquals(wiz.experience, 1);
        game.takeDamage(bar,5);
        assertEquals(bar.experience, 5);
        game.takeDamage(bard, 2);
        assertEquals(bard.experience, 1);
        game.takeDamage(dru, 2);
        assertEquals(dru.experience, 2);
        game.takeDamage(ran, 4);
        assertEquals(ran.experience, 4);
        game.takeDamage(ro, 3);
        assertEquals(ro.experience, 3);
    }

    //Attack Normal, check Health of Opponent
    @Test
    public void attackNormalCheckHealthOpponent() {
        Wizard wiz = new Wizard(); 
        Rogue ro = new Rogue();

        game.attack(wiz, ro);
        assertEquals(ro.health, 100);
    
    }

    //Attack Normal, check Health of Character
    @Test
    public void attackNormalCheckHealthCharacter() {
        Wizard wiz = new Wizard(); 
        Rogue ro = new Rogue();

        game.attack(wiz, ro);
        assertEquals(wiz.health, 96);
    
    }

    //Attack Normal, check Experience of Opponent
    @Test
    public void attackNormalCheckExperienceOpponent() {
        Wizard wiz = new Wizard(); 
        Rogue ro = new Rogue();

        game.attack(wiz, ro);
        assertEquals(ro.experience, 6);
    
    }

    //Attack Normal, check Experience of Character
    @Test
    public void attackNormalCheckExperienceCharacter() {
        Wizard wiz = new Wizard(); 
        Rogue ro = new Rogue();

        game.attack(wiz, ro);
        assertEquals(wiz.experience, 7);
    
    }


    //Attack with Health at 1, check Health of Opponent (DAMAGE DOUBLE)
    @Test
    public void attackCheckHealth_OpponentHealth1() {
        Wizard wiz = new Wizard(); 
        wiz.health = 1;
        Rogue ro = new Rogue();
        ro.health = 1;

        game.attack(wiz, ro);
        assertEquals(ro.health, -3);
    
    }

    //Attack with Health at 1, check Health of Character (DAMAGE DOUBLE)
    @Test
    public void attackCheckHealth_CharacterHealth1() {
        Wizard wiz = new Wizard(); 
        wiz.health = 1;
        Rogue ro = new Rogue();
        ro.health = 1;

        game.attack(wiz, ro);
        assertEquals(wiz.health, 1);
    
    }

    //Attack with Health at 1, check Experience of Opponent (DAMAGE DOUBLE)
    @Test
    public void attackCheckExperience_OpponentHealth1() {
        Wizard wiz = new Wizard(); 
        wiz.health = 1;
        Rogue ro = new Rogue();
        ro.health = 1;

        game.attack(wiz, ro);
        assertEquals(ro.experience, 2);
    
    }

    //Attack with Health at 1, check Experience of Character (DAMAGE DOUBLE)
    @Test
    public void attackCheckExperience_CharacterHealth1() {
        Wizard wiz = new Wizard(); 
        wiz.health = 1;
        Rogue ro = new Rogue();
        ro.health = 1;

        game.attack(wiz, ro);
        assertEquals(wiz.experience, 5);
    
    }

    //Attack with Health at 50, check Health of Opponent 
    @Test
    public void attackCheckHealth_OpponentHealth50() {
        Wizard wiz = new Wizard(); 
        wiz.health = 50;
        Rogue ro = new Rogue();
        ro.health = 50;

        game.attack(wiz, ro);
        assertEquals(ro.health, 50);
    
    }

    //Attack with Health at 50, check Health of Character
    @Test
    public void attackCheckHealth_CharacterHealth50() {
        Wizard wiz = new Wizard(); 
        wiz.health = 50;
        Rogue ro = new Rogue();
        ro.health = 50;

        game.attack(wiz, ro);
        assertEquals(wiz.health, 46);
    
    }

    //Attack with Health at 50, check Experience of Opponent 
    @Test
    public void attackCheckExperience_OpponentHealth50() {
        Wizard wiz = new Wizard(); 
        wiz.health = 50;
        Rogue ro = new Rogue();
        ro.health = 50;

        game.attack(wiz, ro);
        assertEquals(ro.experience, 6);
    
    }

    //Attack with Health at 50, check Experience of Character
    @Test
    public void attackCheckExperience_CharacterHealth50() {
        Wizard wiz = new Wizard(); 
        wiz.health = 50;
        Rogue ro = new Rogue();
        ro.health = 50;

        game.attack(wiz, ro);
        assertEquals(wiz.experience, 7);
    
    }

    //Attack with Health at 0 for Character, check Health of Opponent 
    @Test
    public void attackCheckHealthOpponent_CharacterHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 0;
        Rogue ro = new Rogue();
        ro.health = 90;

        game.attack(wiz, ro);
        assertEquals(ro.health, 90);
    
    }

    //Attack with Health at 0 for Character, check Health of Opponent 
    @Test
    public void attackCheckHealthCharacter_CharacterHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 0;
        Rogue ro = new Rogue();
        ro.health = 90;

        game.attack(wiz, ro);
        assertEquals(wiz.health, 0);
    
    }

    //Attack with Health at 0 for Character, check Experience of Opponent 
    @Test
    public void attackCheckExperienceOpponent_CharacterHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 0;
        Rogue ro = new Rogue();
        ro.health = 90;

        game.attack(wiz, ro);
        assertEquals(ro.experience, 0);
    
    }

    //Attack with Health at 0 for Character, check Experience of Character
    @Test
    public void attackCheckExperienceCharacter_CharacterHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 0;
        Rogue ro = new Rogue();
        ro.health = 90;

        game.attack(wiz, ro);
        assertEquals(wiz.experience, 0);
    
    }

    //Attack with Health at 0 for Opponent, check Health of Opponent 
    @Test
    public void attackCheckHealthOpponent_OpponentHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 90;
        Rogue ro = new Rogue();
        ro.health = 0;

        game.attack(wiz, ro);
        assertEquals(ro.health, 0);
    
    }

    //Attack with Health at 0 for Opponent, check Health of Opponent 
    @Test
    public void attackCheckHealthCharacter_OpponentHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 90;
        Rogue ro = new Rogue();
        ro.health = 0;

        game.attack(wiz, ro);
        assertEquals(wiz.health, 90);
    
    }

    //Attack with Health at 0 for Character, check Experience of Opponent 
    @Test
    public void attackCheckExperienceOpponent_OpponentHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 90;
        Rogue ro = new Rogue();
        ro.health = 0;

        game.attack(wiz, ro);
        assertEquals(ro.experience, 0);
    
    }

    //Attack with Health at 0 for Character, check Experience of Character
    @Test
    public void attackCheckExperienceCharacter_OpponentHealth0() {
        Wizard wiz = new Wizard(); 
        wiz.health = 90;
        Rogue ro = new Rogue();
        ro.health = 0;

        game.attack(wiz, ro);
        assertEquals(wiz.experience, 0);
    
    }

    //Double Damage plus Killing Opponent
    @Test
    public void attackDoubleDamageKillOpponent_CheckHealthOpponent() {
        Barbarian bar = new Barbarian(); 
        bar.health = 9;
        Rogue ro = new Rogue();
        ro.health = 12;

        game.attack(bar, ro);
        assertEquals(ro.health, -2);
    }

    //Double Damage plus Killing Opponent
    @Test
    public void attackDoubleDamageKillOpponent_CheckHealthCharacter() {
        Barbarian bar = new Barbarian(); 
        bar.health = 9;
        Rogue ro = new Rogue();
        ro.health = 12;

        game.attack(bar, ro);
        assertEquals(bar.health, 9);
    
    }

    //Double Damage plus Killing Opponent
    @Test
    public void attackDoubleDamageKillOpponent_CheckExperienceOpponent() {
        Barbarian bar = new Barbarian(); 
        bar.health = 10;
        Rogue ro = new Rogue();
        ro.health = 12;

        game.attack(bar, ro);
        assertEquals(ro.experience, 11);
    }

    //Double Damage plus Killing Opponent
    @Test
    public void attackDoubleDamageKillOpponent_CheckExperienceCharacter() {
        Barbarian bar = new Barbarian(); 
        bar.health = 10;
        Rogue ro = new Rogue();
        ro.health = 12;

        game.attack(bar, ro);
        assertEquals(bar.experience, 5);
    }






}