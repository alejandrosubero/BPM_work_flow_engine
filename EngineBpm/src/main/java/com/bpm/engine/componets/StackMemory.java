package com.bpm.engine.componets;

import com.bpm.engine.entitys.InstanceTask;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class StackMemory {

    private Queue<InstanceTask> queueTask = new LinkedList<>();
    private Queue<InstanceTask> queuePriorityTask = new LinkedList<>();

    public StackMemory() {
    }

    public Boolean addTask(InstanceTask instanceTask, String type){
     Boolean isSave = false;
       if(instanceTask != null){
           try{
               isSave = type.toLowerCase().equals("p")? queuePriorityTask.add(instanceTask):queueTask.add(instanceTask);
           }catch (IllegalStateException ei){
               isSave = false;
               // TODO: need do something else for control the memory problem... and discribe the queues
               ei.printStackTrace();
           }
       }
       return isSave;
    }


    public InstanceTask getTask(String type){
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
