package JUnitTests;

import static org.junit.Assert.*;
import models.Building;
import models.Index;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.BuildingType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildingTester 
{

	//this tester also includes the index testing.
	//after all is done, Building, Index, VertexLocation, HexLocation, and Vertex Direction will all be tested
	
	private Index indexZero;
	private Index indexOne;
	private Building buildingZero;
	private Building buildingSettle;
	private Building buildingCity;
	private VertexLocation locationZero;
	private HexLocation hexZero = new HexLocation(1,1);
	private HexLocation hexOne = new HexLocation(2,2);
	private VertexLocation locationOne;
	private VertexLocation locationTwo;
	
	
	@Before
	public void initialize() 
	{
		try 
		{
			indexZero = new Index(0);
			indexOne = new Index(1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		locationZero = new VertexLocation(hexZero, VertexDirection.NorthEast);
		locationOne = new VertexLocation(hexZero, VertexDirection.SouthWest);
		locationTwo = new VertexLocation(hexOne, VertexDirection.East);
		buildingZero = new Building(indexZero, locationZero);
		buildingCity = new Building(indexZero, locationOne);
		buildingSettle = new Building(indexOne, locationTwo);
		
		buildingCity.setBuildingTypeToCity();
		
		
	}
	
	
	
	
	@Test
	public void testBuildings() 
	{
		//System.out.println("\nBUILDING OBJECTS AND MEMBERS TEST:\n");
		//testings buildingType
		//System.out.print("Testing building types");
		assertTrue(buildingZero.buildingType().equals(BuildingType.SETTLEMENT));
		//System.out.print(" . ");
		assertTrue(buildingSettle.buildingType().equals(BuildingType.SETTLEMENT));
		//System.out.print(" . ");
		assertTrue(buildingCity.buildingType().equals(BuildingType.CITY));
		//System.out.println(" - PASSED");
		
		//System.out.print("Testing building location");
		//testing buildingLocation
		assertTrue(buildingZero.location().equals(locationZero));
		//System.out.print(" . ");
		assertFalse(buildingCity.location().equals(locationZero));
		//System.out.print(" . ");
		assertTrue(buildingSettle.location().equals(locationTwo));
		//System.out.print(" . ");
		//testing building owner
		assertTrue(buildingZero.owner().equals(indexZero));
		//System.out.print(" . ");
		assertFalse(buildingCity.owner().equals(indexOne));
		//System.out.print(" . ");
		assertTrue(buildingSettle.owner().equals(indexOne));
		//System.out.println(" - PASSED");		
	}

	
	
	
	
	
	@After
	public void tearDown()
	{
		return;
	}
	
	
}
