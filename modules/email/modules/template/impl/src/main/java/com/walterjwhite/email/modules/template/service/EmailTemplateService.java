package com.walterjwhite.email.modules.template.service;

import com.walterjwhite.email.modules.template.model.EmailTemplate;
import com.walterjwhite.email.modules.template.model.EmailTemplateSendRequest;
import java.io.Serializable;
import java.util.Set;

public interface EmailTemplateService {
  Set<EmailTemplateSendRequest> render(EmailTemplate emailTemplate, Serializable... referenceData);
}
