package com.bpm.engine.serviceImplement.jsomxml.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlToJsonWithJackson {
	
	private String xml;
	
    public XmlToJsonWithJackson() {
	}


	public XmlToJsonWithJackson(String xml) {
		this.xml = xml;
	}


	public String XmlToJson() {
        String jsonString = "";
        try {
            // Crear XmlMapper y ObjectMapper
            XmlMapper xmlMapper = new XmlMapper();
            ObjectMapper jsonMapper = new ObjectMapper();

            // Convertir XML a JsonNode
            JsonNode node = xmlMapper.readTree(this.xml.getBytes());

            // Convertir JsonNode a String JSON
             jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return jsonString;
    }
}

