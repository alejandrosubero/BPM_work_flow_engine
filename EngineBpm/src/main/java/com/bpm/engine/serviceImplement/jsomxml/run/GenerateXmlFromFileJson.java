package com.bpm.engine.serviceImplement.jsomxml.run;


import com.bpm.engine.serviceImplement.jsomxml.services.JsontoXmlConverter;

public class GenerateXmlFromFileJson {
	
	public static void main(String[] args) {
		JsontoXmlConverter converter = new JsontoXmlConverter();
		converter.convertJsonToXml("ProcessModel");
	}


}
