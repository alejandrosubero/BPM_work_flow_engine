package com.bpm.engine.managers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.bpm.engine.models.InstanceAbstractionModel;



@Component
public class StackMemorylistas {

    private Queue<InstanceAbstractionModel> queueTask = new LinkedList<>();
    private Queue<InstanceAbstractionModel> queuePriorityTask = new LinkedList<>();
    
    // this map is for reference the instance, that referent is for tell to the system the referencia is in process.... no tocar 
    private Map<String,InstanceAbstractionModel> referenceWorking = new HashMap<>();
    
    

    public StackMemorylistas() {
    }

    public Boolean addTask(InstanceAbstractionModel instance, String type){
     Boolean isSave = false;
       if(instance != null){
           try{
               isSave = type.toLowerCase().equals("p")? queuePriorityTask.add(instance):queueTask.add(instance);
           }catch (IllegalStateException ei){
               isSave = false;
               // TODO: need do something else for control the memory problem... and discribe the queues
               ei.printStackTrace();
           }
       }
       return isSave;
    }


    public InstanceAbstractionModel getTask(String type){
        if(type != null && type.toLowerCase().equals("p")){
            return queuePriorityTask.poll();
        }else {
            return queueTask.poll();
        }
    }


    public  Boolean isEmptyQueueTask(String type){
        if(type != null && type.toLowerCase().equals("p")){
            return queuePriorityTask.isEmpty();
        }else {
            return queueTask.isEmpty();
        }
    }

    public Integer getSizeQueueTask(String type){
        Integer numberSize = type != null && type.toLowerCase().equals("p")? queuePriorityTask.size():queueTask.size();
        return numberSize;
    }

}
