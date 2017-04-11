package com.nerdapplabs.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import com.ams.testsetup.SetUp;
import au.com.bytecode.opencsv.CSVReader;


	public class TestDataReader{

	public TestDataReader(WebDriver driver) {
		 SetUp.driver = driver;
	 
	 }
	public TestDataReader() {
		
	}

	public List<String[]> readCsv(String fileName) throws IOException {
		List<String[]> dataSource = null;
		String filePath = "datasource/" + fileName;
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(filePath));
			String[] cell = null;
			while((cell= csvReader.readNext()) != null) {
				System.out.println(cell[0]);
				//for(int i = 0; i <= dataSource.size(); i++)      
			}
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} finally {
			csvReader.close();
		}

		return dataSource;

	}
	public static void main(String[] args) throws IOException {
		TestDataReader testDataReader = new TestDataReader();
		List<String[]> dataSource = testDataReader.readCsv("testData.csv");
		
	 

	}
	}

