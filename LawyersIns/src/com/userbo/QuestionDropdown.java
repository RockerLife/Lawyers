
package com.userbo;

import com.util.InetLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.managemetadata.metadata.IDropDown;
import com.osi.ws.client.objects.Questions;
import com.osi.ws.client.objects.SecureOneWSSoap11BindingStub;
import com.util.IContext;
import com.util.SystemProperties;


public class QuestionDropdown implements IDropDown
{
	private static final InetLogger logger = InetLogger.getInetLogger(QuestionDropdown.class);

	public List getDropDownData(IContext ctx, String field)throws Exception
	{
		List questionsList = new ArrayList();
		
		try{
				
			String endPointUrl = null;		
			endPointUrl = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".webserviceurl.SecureOne");
						
			if(endPointUrl == null)
				return null;		
			
			logger.debug("Going to get questions ---");
			
			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY, endPointUrl);
			
			Questions[] questions = bindingStub.getAllQuestions("");
			if(questions == null)
				return null;
			
			Questions  question = null;
			for(int i=0; i<questions.length; i++)
			{
				question = questions[i];
				Map mapPrivilege = new HashMap();
				mapPrivilege.put("ChallengeQuesID", question.getQuesId().toString()) ;
				mapPrivilege.put("ChallengeQuesDesc", question.getQues());	
				questionsList.add(mapPrivilege);					
			}
			
			logger.debug("Questions has been got---");
			
		}catch (Exception e) {
					
			LawyersUtils.populateError(ctx, "AccountEmail", "Please contact 1-877-569-4111 for assistance");
			logger.debug("Questions has been got---");
		}
			
		return questionsList;
	}
	

}

