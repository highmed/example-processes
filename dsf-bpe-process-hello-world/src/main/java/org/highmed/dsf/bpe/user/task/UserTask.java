package org.highmed.dsf.bpe.user.task;

import org.highmed.dsf.bpe.listener.AbstractUserTaskListener;
import org.highmed.dsf.fhir.authorization.read.ReadAccessHelper;
import org.highmed.dsf.fhir.client.FhirWebserviceClientProvider;
import org.highmed.dsf.fhir.organization.OrganizationProvider;
import org.highmed.dsf.fhir.questionnaire.QuestionnaireResponseHelper;

public class UserTask extends AbstractUserTaskListener
{
	public UserTask(FhirWebserviceClientProvider clientProvider, OrganizationProvider organizationProvider,
			QuestionnaireResponseHelper questionnaireResponseHelper, ReadAccessHelper readAccessHelper)
	{
		super(clientProvider, organizationProvider, questionnaireResponseHelper, readAccessHelper);
	}
}
