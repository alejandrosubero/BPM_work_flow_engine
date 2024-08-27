package com.bpm.engine.serviceImplement.jsomxml.run;


import com.bpm.engine.serviceImplement.jsomxml.services.JsontoXmlConverter;

public class GenerateXmlFromJsonString {

	public static void main(String[] args) {
		JsontoXmlConverter converter = new JsontoXmlConverter();
		converter.convertJsonToXml("ProcessModel", jsonString);
		
	}

	private static String jsonString ="{\r\n"
			+ "	\"name\": \"note_service\",\r\n"
			+ "	\"procesTitle\": \"Note Service internal\",\r\n"
			+ "	\"userCreate\": \"hx39075\",\r\n"
			+ "	\"global\": true,\r\n"
			+ "	\"visible\": false,\r\n"
			+ "	\"roles\": [\r\n"
			+ "		{\r\n"
			+ "			\"codeRole\": \"w23ab\",\r\n"
			+ "			\"description\": \"developer\",\r\n"
			+ "			\"name\": \"Develop\"\r\n"
			+ "		}\r\n"
			+ "	],\r\n"
			+ "	\"stages\": [\r\n"
			+ "		{\r\n"
			+ "			\"name\": \"note_go_to_party\",\r\n"
			+ "			\"title\": \"note for Go to party of enterprise\",\r\n"
			+ "			\"type\": \"human\",\r\n"
			+ "			\"roles\": [\r\n"
			+ "				{\r\n"
			+ "					\"codeRole\": \"w23a\",\r\n"
			+ "					\"description\": \"developer\",\r\n"
			+ "					\"name\": \"Developer\"\r\n"
			+ "				},\r\n"
			+ "				{\r\n"
			+ "					\"codeRole\": \"w23ab\",\r\n"
			+ "					\"description\": \"developer\",\r\n"
			+ "					\"name\": \"Develop\"\r\n"
			+ "				},\r\n"
			+ "				{\r\n"
			+ "					\"codeRole\": \"li#1\",\r\n"
			+ "					\"description\": \"project_Manager\",\r\n"
			+ "					\"name\": \"Project Manager\"\r\n"
			+ "				}\r\n"
			+ "			],\r\n"
			+ "			\"stageNumber\": 1,\r\n"
			+ "			\"stages\": [],\r\n"
			+ "			\"tasks\": [\r\n"
			+ "				{\r\n"
			+ "					\"name\": \"Task_test_note_1\",\r\n"
			+ "					\"title\": \"Task Test note service 1\",\r\n"
			+ "					\"roles\": [\r\n"
			+ "						{\r\n"
			+ "							\"codeRole\": \"li#1\",\r\n"
			+ "							\"description\": \"project_Manager\",\r\n"
			+ "							\"name\": \"Project Manager\"\r\n"
			+ "						}\r\n"
			+ "					],\r\n"
			+ "					\"rulers\": [\r\n"
			+ "						{\r\n"
			+ "							\"action\": \"0\",\r\n"
			+ "							\"condition\": \"APRUBE\"\r\n"
			+ "						},\r\n"
			+ "						{\r\n"
			+ "							\"action\": \"0\",\r\n"
			+ "							\"condition\": \"REJECTED\"\r\n"
			+ "						}\r\n"
			+ "					],\r\n"
			+ "					\"urlService\": \".....xxxx....\",\r\n"
			+ "					\"taskUrl\": \"htttp....iiiiuuu...\",\r\n"
			+ "					\"type\": {\r\n"
			+ "						\"type\": \"Human\"\r\n"
			+ "					}\r\n"
			+ "				}\r\n"
			+ "			]\r\n"
			+ "		},\r\n"
			+ "		{\r\n"
			+ "			\"name\": \"closed\",\r\n"
			+ "			\"title\": \"cloused\",\r\n"
			+ "			\"type\": \"automatic\",\r\n"
			+ "			\"roles\": [],\r\n"
			+ "			\"stageNumber\": 0,\r\n"
			+ "			\"stages\": [],\r\n"
			+ "			\"tasks\": [\r\n"
			+ "				{\r\n"
			+ "					\"name\": \"close_Finalized_flow\",\r\n"
			+ "					\"title\": \"Finalized flow\",\r\n"
			+ "					\"roles\": [],\r\n"
			+ "					\"rulers\": [\r\n"
			+ "						{\r\n"
			+ "							\"action\": \"999\",\r\n"
			+ "							\"condition\": \"APRUBE\"\r\n"
			+ "						}\r\n"
			+ "					],\r\n"
			+ "					\"urlService\": \"\",\r\n"
			+ "					\"taskUrl\": \"\",\r\n"
			+ "					\"type\": {\r\n"
			+ "						\"type\": \"automatic\"\r\n"
			+ "					}\r\n"
			+ "				}\r\n"
			+ "			]\r\n"
			+ "		}\r\n"
			+ "	]\r\n"
			+ "}";
	
	
}
