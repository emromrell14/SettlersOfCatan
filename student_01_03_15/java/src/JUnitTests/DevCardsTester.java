package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.DevCardType;
import models.*;

public class DevCardsTester 
{

	private Monopoly monop;
	private Monument monu;
	private RoadBuild rBuilder;
	private Soldier soldier;
	private YearOfPlenty yearOfPlenty;
	
	
	//tests all types of Dev Cards
	
	@Before
	public void initialize() 
	{
		monop = new Monopoly();
		monu = new Monument();
		rBuilder = new RoadBuild();
		soldier = new Soldier();
		yearOfPlenty = new YearOfPlenty();
	}
	
	
	@Test
	public void testPlayed() 
	{
		//System.out.println("\nDEV CARD ALREADY PLAYED TEST:\n");
		
		//System.out.print("Testing where each dev card has not been played");
		//should assert false because they're set to false in beginning
		assertFalse(monop.hasBeenPlayed());
		//System.out.print(" . ");
		assertFalse(monu.hasBeenPlayed());
		//System.out.print(" . ");
		assertFalse(rBuilder.hasBeenPlayed());
		//System.out.print(" . ");
		assertFalse(soldier.hasBeenPlayed());
		//System.out.print(" . ");
		assertFalse(yearOfPlenty.hasBeenPlayed());
		//System.out.println(" - PASSED");
		
		
		monop.setPlayed();
		monu.setPlayed();
		rBuilder.setPlayed();
		soldier.setPlayed();
		yearOfPlenty.setPlayed();
		
		//System.out.print("Testing after each type of dev card been played");

		//should now assert true after trigger change
		assertTrue(monop.hasBeenPlayed());
		//System.out.print(" . ");
		assertTrue(monu.hasBeenPlayed());
		//System.out.print(" . ");
		assertTrue(rBuilder.hasBeenPlayed());
		//System.out.print(" . ");
		assertTrue(soldier.hasBeenPlayed());
		//System.out.print(" . ");
		assertTrue(yearOfPlenty.hasBeenPlayed());
		//System.out.println(" - PASSED");		
	}
	
	
	@Test
	public void testType() 
	{
		//System.out.println("\nDEV CARD TYPE TEST:\n");
		assertTrue(monop.type().equals(DevCardType.MONOPOLY));
		//System.out.print(" . ");
		assertTrue(monu.type().equals(DevCardType.MONUMENT));
		//System.out.print(" . ");
		assertTrue(rBuilder.type().equals(DevCardType.ROAD_BUILD));
		//System.out.print(" . ");
		assertTrue(soldier.type().equals(DevCardType.SOLDIER));
		//System.out.print(" . ");
		assertTrue(yearOfPlenty.type().equals(DevCardType.YEAR_OF_PLENTY));
		//System.out.print(" - PASSED");
	}

	@Test
	public void testNew()
	{
		//System.out.println("\nDEV CARD NEW TEST:\n");

		monop.setNew(false);
		monu.setNew(false);
		rBuilder.setNew(false);
		
		assertFalse(monop.isNew());
		//System.out.print(" . ");
		assertFalse(monu.isNew());
		//System.out.print(" . ");
		assertFalse(rBuilder.isNew());
		//System.out.print(" . ");
		assertTrue(soldier.isNew());
		//System.out.print(" . ");
		assertTrue(yearOfPlenty.isNew());
		//System.out.print(" - PASSED");
		
	}
	
	
	@After
	public void tearDown()
	{
		return;
	}
	
	
}
