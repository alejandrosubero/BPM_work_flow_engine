package com.bpm.engine.notification.componet;

import com.bpm.engine.models.InstanceDataInfoModel;
import com.bpm.engine.models.TemplateFlexibleDataModel;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.bpm.engine.utility.SystemSate.ASSIGNED;
import static com.bpm.engine.models.TemplateFlexibleDataModel.instanceTemplateFlexibleData;
import static com.bpm.engine.utility.SystemSate.*;

@Component
public class SystenTemplateFlexibleDataComponet {

    public  List<TemplateFlexibleDataModel>  getListBodyForTemplate(InstanceDataInfoModel instanceDataInfo){
        List<TemplateFlexibleDataModel> listTemplateFlexibleDataModel = new ArrayList<>();


        listTemplateFlexibleDataModel.add(
                instanceTemplateFlexibleData("@userName@", instanceDataInfo.getInstanceUserAssigned())
        );

        listTemplateFlexibleDataModel.add( this.getBodyForTemplate(
                instanceDataInfo.getState(),
                instanceDataInfo.getType(),
                instanceDataInfo.getInstanceTitle(),
                instanceDataInfo.getInstanceID().toString()));

        return listTemplateFlexibleDataModel;
    }


    private TemplateFlexibleDataModel getBodyForTemplate(String state, String Type, String instanceTitle, String instanceID){

        TemplateFlexibleDataModel templateFlexibleDataModel= null;

        if(state.equals(CREATE.name())) {
           String body =  Constants.CREATE;
           body = body.replace("@type@", Type);
           body = body.replace("@title@", instanceTitle);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(ASSIGNED.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(WAITING_FOR_ACTION.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(STARTED.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@",Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(RUNNING.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(IN_PROGRESS.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@",instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(COMPLETE.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(PAUSE.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(STOP.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(CANCELING.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(CANCEL.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        if(state.equals(ACTIVE.name())) {
            String body =  Constants.CREATE;
            body = body.replace("@type@", Type);
            body = body.replace("@title@", instanceTitle);
            body = body.replace("@instanceId@", instanceID);
            templateFlexibleDataModel = instanceTemplateFlexibleData("@body@", body);
            return templateFlexibleDataModel;
        }

        return null;
    }


}
