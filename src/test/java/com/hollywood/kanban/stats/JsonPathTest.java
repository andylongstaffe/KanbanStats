package com.hollywood.kanban.stats;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

public class JsonPathTest {

	String sampleJson;
	
	private String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result.toString();

	  }
	
	@Before
	public void setUp() throws Exception {
		sampleJson = getFile("sample.json");
	}

	@Test
	public void test() {
		System.out.println(sampleJson);
		List<String> output = JsonPath.read(sampleJson, "$..*[?(@.name=='AFD DEPS Beta Backlog 2')]");
		System.out.println(output);
		List<String> desc = JsonPath.read(output, "$..*.desc");
		System.out.println(desc);
		System.out.println(JsonPath.read(sampleJson, "$..*[?(@.name=='AFD DEPS Beta Backlog 2')].desc"));
		
	}
	
	@Test
	public void usingFilter() {
		Filter boardNameFilter = filter( 
			where("name").is("AFD DEPS Beta Backlog 2")	
		);
		List<String> output = JsonPath.read(sampleJson, "$..*[?]", boardNameFilter);
		System.out.println(output);
		
	}

}
