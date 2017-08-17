package com.aem.advanced.core.workflow;
 
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;

//Resource resolver
import org.apache.sling.api.resource.ResourceResolverFactory ; 
import org.apache.sling.api.resource.ResourceResolver; 
import org.apache.sling.api.resource.Resource; 
import com.day.cq.wcm.api.Page; 
//Event Handler
/*import org.osgi.service.event.EventHandler;
import org.osgi.service.event.Event;	
import org.apache.sling.discovery.TopologyEvent;
import org.apache.sling.discovery.TopologyEventListener;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
*/


import javax.jcr.Session;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;

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

//-----------------------------------  TASK  ------------------------------------------------------------------






		//----------------------------------------------------------------------------------------
public class MyDynamicParticipant implements ParticipantStepChooser {
     
    @Property(value = "An example implementation of a dynamic participant chooser.")
    static final String DESCRIPTION = Constants.SERVICE_DESCRIPTION; 
    @Property(value = "Adobe")
    static final String VENDOR = Constants.SERVICE_VENDOR;
    @Property(value = "Dynamic Participant Chooser Process")
    static final String LABEL=ParticipantStepChooser.SERVICE_PROPERTY_LABEL;
  
    private static final String TYPE_JCR_PATH = "JCR_PATH";
	 private static final Logger logger = LoggerFactory.getLogger(MyDynamicParticipant.class);

//	 @Property(value=com.adobe.granite.workflow.event.WorkflowEvent.EVENT_TOPIC)
 //   static final String EVENT_TOPICS = "event.topics";



  
    public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException 
	{
		String participant="007";


	//	logger.info( "EVENT GET PROPERTY!!! ===> " + Event.getProperty());
		//--------------------------------------------------------------------------------------------------------
		logger.info("################ Inside the SampleProcessStepChooserImpl GetPcarticipant ##########################");
		//WORKFLOWSESSION
//		logger.info( "workflowSession is !!" + workflowSession.toString());
    	//WORKITEM
//		logger.info( "workItem is " + String.valueOf(workItem));
//ARGS
//		logger.info( "Args is " + String.valueOf(args));

		//----------------------------------------------------------LOOP START

		if(workItem.getTimeEnded()!=null)
		{
			logger.info( "HELLO THIS IS !!! ===> " + workItem.getCurrentAssignee());
		
		
		}
//logger.info("HELLO THIS IS 007 AKSHAY2 !!!!! ");
		//-------------------------------------------------------------LOOP END





		//current assignee-------------------------------------------------------------------
	// logger.info( "GetcurrentAssignee ===> " + workItem.getCurrentAssignee());

		//Time started
	//	logger.info("GETTIME STARTED ===> " + workItem.getTimeStarted());

		// Time Ended
	//	logger.info("GETTIME ENDED ===> " + workItem.getTimeEnded());

		//----------------------------------------------------------------------------------------

		//USER NAME
//		ResourceResolver resourceResolver=null;
		//UserManager userManager = resourceResolver.adaptTo(UserManager.class);
//Session session = resourceResolver.adaptTo(Session.class);
//logger.info("UserNAME= "+session.getUserID());
	
		// BORDER 
//		UserManager userManager=null;

//		String myID = workItem.getId() ; 
     //Set the Map that is used to pass values to other workflow steps 
  //  workItem.getWorkflowData().getMetaDataMap().put("MyIDkey", myID);
     
    
    //logger.info("*** In Step 2 - this value was sets " + myID);

		// resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
     //  Session adminSession = resourceResolver.adaptTo(Session.class);
       //UserManager userManager = resourceResolver.adaptTo(UserManager.class);
       // User user = (User) userManager.getAuthorizable(Session.getUserID());
	//	logger.info("user.getID().."+user.getID());

		//logger.info("UserNAME="+session.getUserID());

       WorkflowData workflowData = workItem.getWorkflowData();
		//workflow data
		//logger.info("Workflowdata is ## ="+ workflowData.getWorkflowData().toString());
		//getpayload
//		logger.info("GetPayload ="+workflowData.getPayloadType().toString());

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