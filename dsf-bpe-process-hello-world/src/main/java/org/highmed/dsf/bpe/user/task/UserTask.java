package org.highmed.dsf.bpe.user.task;

import org.highmed.dsf.bpe.listener.AbstractUserTaskListener;
import org.highmed.dsf.fhir.authorization.read.ReadAccessHelper;
import org.highmed.dsf.fhir.client.FhirWebserviceClientProvider;
import org.highmed.dsf.fhir.organization.OrganizationProvider;
import org.highmed.dsf.fhir.questionnaire.QuestionnaireResponseHelper;
import org.highmed.dsf.fhir.task.TaskHelper;

public class UserTask extends AbstractUserTaskListener
{
	public UserTask(FhirWebserviceClientProvider clientProvider, OrganizationProvider organizationProvider,
			QuestionnaireResponseHelper questionnaireResponseHelper, TaskHelper taskHelper,
			ReadAccessHelper readAccessHelper)
	{
		super(clientProvider, organizationProvider, questionnaireResponseHelper, taskHelper, readAccessHelper);
	}
}
