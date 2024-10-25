package com.bpm.engine.serviceImplement.jsomxml.services;

import java.io.IOException;

public class GenerateJsonFromXml {


	public GenerateJsonFromXml() {}


	@SuppressWarnings("finally")
	public String generateFromFile(String xmlPath) {
		
		String jsonString = "";
		try {
			String xml = FileToString.readFileToString(xmlPath);
			jsonString = this.generate(xml);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return jsonString;
		}
	}
	
	
	public String generateFromString(String xml) {
		return this.generate(xml);
	}
	
	
	private String generate(String xml) {
		XmlToJsonWithJackson XmlToJson = new XmlToJsonWithJackson(xml);
		return  XmlToJson.XmlToJson();
	}
	
}
