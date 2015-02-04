package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.DevCardType;
import models.*;

public class DevCardsTester {

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
		//should assert false because they're set to false in beginning
		assertFalse(monop.hasBeenPlayed());
		assertFalse(monu.hasBeenPlayed());
		assertFalse(rBuilder.hasBeenPlayed());
		assertFalse(soldier.hasBeenPlayed());
		assertFalse(yearOfPlenty.hasBeenPlayed());
		
		
		monop.setPlayed();
		monu.setPlayed();
		rBuilder.setPlayed();
		soldier.setPlayed();
		yearOfPlenty.setPlayed();
		
		//should now assert true after trigger change
		assertTrue(monop.hasBeenPlayed());
		assertTrue(monu.hasBeenPlayed());
		assertTrue(rBuilder.hasBeenPlayed());
		assertTrue(soldier.hasBeenPlayed());
		assertTrue(yearOfPlenty.hasBeenPlayed());
		
		
	}
	
	
	@Test
	public void testType() 
	{
		assertTrue(monop.type().equals(DevCardType.MONOPOLY));
		assertTrue(monu.type().equals(DevCardType.MONUMENT));
		assertTrue(rBuilder.type().equals(DevCardType.ROAD_BUILD));
		assertTrue(soldier.type().equals(DevCardType.SOLDIER));
		assertTrue(yearOfPlenty.type().equals(DevCardType.YEAR_OF_PLENTY));
		
	}

	@Test
	public void testNew()
	{
		monop.setNew();
		monu.setNew();
		rBuilder.setNew();
		
		assertFalse(monop.isNew());
		assertFalse(monu.isNew());
		assertFalse(rBuilder.isNew());
		assertTrue(soldier.isNew());
		assertTrue(yearOfPlenty.isNew());
		
	}
	
	
	@After
	public void tearDown()
	{
		return;
	}
	
	
}
