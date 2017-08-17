package com.aem.advanced.core.workflow;
 
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
 
import org.osgi.framework.Constants;
  
/**
 * Sample dynamic participant step that determines the participant based on a path given as argument.
 */
@Component
@Service
 
public class MyDynamicParticipant implements ParticipantStepChooser {
     
    @Property(value = "An example implementation of a dynamic participant chooser.")
    static final String DESCRIPTION = Constants.SERVICE_DESCRIPTION; 
    @Property(value = "Adobe")
    static final String VENDOR = Constants.SERVICE_VENDOR;
    @Property(value = "Dynamic Participant Chooser Process")
    static final String LABEL=ParticipantStepChooser.SERVICE_PROPERTY_LABEL;
  
    private static final String TYPE_JCR_PATH = "JCR_PATH";
	 private static final Logger logger = LoggerFactory.getLogger(MyDynamicParticipant.class);
  
    public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException 
	{
		logger.info("################ Inside the SampleProcessStepChooserImpl GetPcarticipant ##########################");
		
    	
    //	logger.info( "print here !!" + workflowSession.toString());
    //	logger.info( "print hereonly" + String.valueOf(workflowSession));



        WorkflowData workflowData = workItem.getWorkflowData();
        if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
            String path = workflowData.getPayload().toString();
            String pathFromArgument = args.get("PROCESS_ARGS", String.class);
            if (pathFromArgument != null && path.startsWith(pathFromArgument)) {
                return "admin";
            }
        }
        return "administrators";
    }
}