package com.walterjwhite.examples.spring.request;

import com.walterjwhite.web.servlet.request.Request;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableRetry
@Service
@RequiredArgsConstructor
public class RequestExportService {
  private final RequestRepository repo;
  private final EmailService emailService;

  @Value("${service.requests.email.to}")
  private String emailTo;

  @Scheduled(cron = "${service.requests.export.cron:3 3 3 * * *}")
  public void exportOldRequests() throws Exception {
    final List<Request> requests = repo.findAll();
    repo.truncate();

    emailService.send(emailTo, "requests", getMessageBody(requests));
  }

  private String getMessageBody(final List<Request> requests) {
    final StringBuilder sb = new StringBuilder();
    sb.append("id,timestamp,ip,userAgent,uri\n");
    for (Request request : requests) {
      sb.append(request.getId()).append(",");
      sb.append(request.getTimestamp()).append(",");
      sb.append(request.getIp()).append(",");
      sb.append(request.getUserAgent()).append(",");
      sb.append(request.getUri()).append("\n");
    }

    return sb.toString();
  }
}
