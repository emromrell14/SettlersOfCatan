package JUnitTests;

import JUnitTests.ServerTests.*;

public class JUnitMain 
{
	public static void main(String[] args) 
	{
		org.junit.runner.JUnitCore.runClasses(
				ProxyTester.class,
				BuildingTester.class,
				DevCardsTester.class,
				JSONTester.class,
				ModelTester.class,
				PlayerTester.class,
				GameModelTester.class,
				PollerTester.class,
				RegisterHandlerTest.class,
				LoginHandlerTest.class,
				CreateGamesHandlerTest.class,
				JoinGamesHandlerTest.class,
				ResetHandlerTest.class,
				ModelHandlerTest.class,
				MovesHandlerTest.class
				);
		System.out.println("ALL TESTS PASSED!");
	}

}
