package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalDataReader;
import dataObjects.ProductData;
import pageObjects.HomePageObject;

public class HomeTestcase extends BaseTest{
	private HomePageObject homePage;
	
	@BeforeClass
	public void beforeMethod() {
		getBrowserDriver("chrome", "https://box8.vn/");
		homePage = new HomePageObject(driver);
	}
	
	@DataProvider(name = "getProductData") 
	public ProductData[] getProductData() {
		return GlobalDataReader.getJsonDataArray("NameProductData.json", ProductData[].class);
	}
	
	@Test
	public void TC_01_Click_Logo_Wrapper() {
		homePage.clickLogoWrapper();
	}

	@Test(dataProvider = "getProductData")
	public void TC_02_Search_Product(ProductData product) {
		homePage.searchProduct(product.getNameProduct());
		homePage.clickButtonSearch();
		
		String notificationResultProduct = homePage.displayResultSearchProduct();
		Assert.assertTrue(notificationResultProduct.contains("kết quả tìm kiếm phù hợp"));
	}
	
}
