package org.highmed.dsf.bpe.service;

import static org.highmed.dsf.bpe.ConstantsBase.BPMN_EXECUTION_VARIABLE_QUESTIONNAIRE_RESPONSE_COMPLETED;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.highmed.dsf.bpe.delegate.AbstractServiceDelegate;
import org.highmed.dsf.fhir.authorization.read.ReadAccessHelper;
import org.highmed.dsf.fhir.client.FhirWebserviceClientProvider;
import org.highmed.dsf.fhir.task.TaskHelper;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.fhir.context.FhirContext;

public class LogUserTaskResponse extends AbstractServiceDelegate
{
	private static final Logger logger = LoggerFactory.getLogger(LogUserTaskResponse.class);

	public LogUserTaskResponse(FhirWebserviceClientProvider clientProvider, TaskHelper taskHelper,
			ReadAccessHelper readAccessHelper)
	{
		super(clientProvider, taskHelper, readAccessHelper);
	}

	@Override
	protected void doExecute(DelegateExecution execution)
	{
		QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) execution
				.getVariable(BPMN_EXECUTION_VARIABLE_QUESTIONNAIRE_RESPONSE_COMPLETED);

		logger.info("Completed QuestionnaireResponse: {}",
				FhirContext.forR4().newXmlParser().encodeResourceToString(questionnaireResponse));
	}
}
