package JUnitTests;

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
				PollerTester.class);
	}

}
