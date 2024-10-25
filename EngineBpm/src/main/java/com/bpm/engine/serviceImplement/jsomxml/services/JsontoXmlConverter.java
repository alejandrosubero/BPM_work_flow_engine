package com.bpm.engine.serviceImplement.jsomxml.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;


public class JsontoXmlConverter {

	  private String jsonInputPath;
      private String xmlOutputPath;
      private String partialPathOutput = "src/main/resources/output/";
	
	public JsontoXmlConverter() {
		this.useDefaultConfigurationOfFile();
	}

	
	public JsontoXmlConverter(String jsonInputPath, String xmlOutputPath) {
		super();
		this.jsonInputPath = jsonInputPath;
		this.xmlOutputPath = xmlOutputPath;
	}


	private void useDefaultConfigurationOfFile() {
		this.jsonInputPath = "src/main/resources/json/createNewProcess.json";
		this.xmlOutputPath = partialPathOutput + "output.xml";
	}

	
    public void convertJsonToXml() {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.readTree(new File(this.jsonInputPath));
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(this.xmlOutputPath), jsonNode);
            System.out.println("Conversi�n completada. Archivo XML generado en: " + this.xmlOutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
   
    
    public void convertJsonToXml(String customRootName, String jsonString) {
        try {
          
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.readTree(jsonString);

            ObjectNode rootNode = jsonMapper.createObjectNode();
            rootNode.set(customRootName, jsonNode);

            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.writeValue(new File(xmlOutputPath), rootNode);

            System.out.println("Conversi�n completada. Archivo XML generado en: " + xmlOutputPath);
            
            ReplaceObjectNodeInFile.replaceObjectNodeInFile(xmlOutputPath, partialPathOutput +customRootName+".xml");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    
    public void convertJsonToXml(String customRootName) {
        try {
          
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.readTree(new File(jsonInputPath));

            ObjectNode rootNode = jsonMapper.createObjectNode();
            rootNode.set(customRootName, jsonNode);

            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.writeValue(new File(xmlOutputPath), rootNode);

            System.out.println("Conversi�n completada. Archivo XML generado en: " + xmlOutputPath);
            
            ReplaceObjectNodeInFile.replaceObjectNodeInFile(xmlOutputPath, partialPathOutput +customRootName+".xml");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
