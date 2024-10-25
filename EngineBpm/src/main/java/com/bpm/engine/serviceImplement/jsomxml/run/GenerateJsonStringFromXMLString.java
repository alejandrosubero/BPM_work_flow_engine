package com.bpm.engine.serviceImplement.jsomxml.run;


import com.bpm.engine.serviceImplement.jsomxml.services.GenerateJsonFromXml;

public class GenerateJsonStringFromXMLString {


	public static void main(String[] args) {
		GenerateJsonFromXml generate = new GenerateJsonFromXml();
		String jsonString = generate.generateFromFile("src/main/resources/output/" + "ProcessModel.xml");
		  System.out.println(jsonString);
	}
	
}
