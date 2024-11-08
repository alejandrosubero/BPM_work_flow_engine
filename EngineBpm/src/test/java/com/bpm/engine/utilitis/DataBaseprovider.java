package com.bpm.engine.utilitis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.interfaces.RadomCode;
import com.bpm.engine.utility.SystemSate;

public class DataBaseprovider implements RadomCode {
	
	
	
	
	  public List<Role> roleRepositoryGetAll_ReturnRoles() {
	        Role role = Role.builder()
	                .name("Developer")
	                .codeRole("w23a")
	                .description("developer").build();
	        
	        Role role2 = Role.builder()
	                .name("DeveloperII")
	                .codeRole("w25a")
	                .description("Developer Senor").build();
	        
	        return Arrays.asList(role, role2);
	  }
	
	
	
	  private Stage getAStage(){
	        return Stage.builder()
	                .stageCode("stageCode")
	                .name("go_to_party")
	                .title("Go to party")
	                .type("human")
	                .dateCreate( new Date())
	                .tasks(Arrays.asList(this.getTask()))
	                .stages(new ArrayList<Stage>())
	                .roles(this.roleRepositoryGetAll_ReturnRoles())
	                .stageNumber(1)
	                .build();
	    }

	    private Task getTask(){
	        return  Task.builder().name("Task_test_1").title("Task Test 1").urlService( ".....xxxx....")
	                .taskUrl("htttp....iiiiuuu...")
	                .rulers(Arrays.asList(
	                        Ruler.builder().condition("APRUBE").action(1).build(),
	                        Ruler.builder().condition("CANCEL").action(2).build()
	                ))
	                .roles(roleRepositoryGetAll_ReturnRoles())
	                .type(TaskType.builder().type("Human").build())
	                .dateCreate(new Date())
	                .procesCode("procesCode")
	                .code("code")
	                .build();
	    }
	    

	    public Process getProcess(String name, String title, String user , Boolean global, Boolean visible){
	    	
	    	String code = this.generateCode("Process");
	    	
	        List<Stage> stages = Arrays.asList(this.getAStage());
	        return Process.builder()
	                .name(name)
	                .procesTitle(title)
	                .procesCode(code)
	                .userCreate(user)
	                .global(global)
	                .visible(visible)
	                .createDate(new Date())
	                .state(SystemSate.CREATE.toString())
	                .activo(true)
	                .stages(stages)
	                .roles(this.roleRepositoryGetAll_ReturnRoles())
	                .build();
	    }
	
	    
	    public List<Process> getAllProces(){
	    	
	    	 List<String> name = Arrays.asList("Brake", "Inventary", "Save");
	    	 
	    	 List<String> contex = Arrays.asList("Employee", "Buisnes", "Bank");
	    	 
	    	 List<String> union = Arrays.asList("_", "_");
	    	 
	    	 List<String> user = Arrays.asList("123434", "345967");
	    	
	    	 stringEnsamble(String... stringPaths);
	    	
	    	
	    }
	    
	    

}
