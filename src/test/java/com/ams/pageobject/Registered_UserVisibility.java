package com.ams.pageobject;

import java.io.IOException;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;
import com.ams.testsetup.dataReader;

@RunWith(Parameterized.class)
public class Registered_UserVisibility extends SetUp {

	protected String name;
	protected String email;
	protected String destination;
	protected String Role;

	protected By table = By.cssSelector("table[class ='table table-striped']");
	protected By rows = By.tagName("tr");
	protected By cols = By.tagName("td");
	protected By logout = By.linkText("LOGOUT");
	
	public Registered_UserVisibility(String name,String email,String destination,String Role) {
		this.name =  name;
		this.email = email;
		this.destination = destination;
		this.Role = Role;
	}

	@Parameterized.Parameters
	public static Collection<String[]> testData() throws IOException {
		return dataReader.getTestData("./datasource/Registered_User.csv");
	}	
}