package TestNgClasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.Base;
import utilities.Methods;

//edited on bit bucket online
//edited local

public class LandingPageTest01 extends Base {

	@BeforeClass
	public void test00() {
		Base.openBrowser("chrome");
	}

	@Test(description = "Validate page title for landing page")
	public void test01() {

		ob.get("https://www.makemytrip.com/");
		Assert.assertTrue(Methods
				.compareTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday"));

		// ob.quit();
	}

	@Test(description = "Validate page title for landing page")
	public void test02() {

		ob.get("https://www.makemytrip.com/");
		Assert.assertTrue(Methods
				.compareTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday"));

		// ob.quit();
	}

	@Test(description = "Validate page title for landing page")
	public void test03() {

		ob.get("https://www.makemytrip.com/");
		Assert.assertTrue(Methods
				.compareTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday"));

	}

	@AfterClass
	public void test99() {
		ob.quit();
	}
}
