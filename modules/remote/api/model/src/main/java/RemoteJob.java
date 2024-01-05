/*
import com.walterjwhite.remote.api.model.Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class RemoteJob {
   protected final Client client;
   protected final QueueService queueService;
   protected final RemoteConfiguration remoteConfiguration;
}
ClientIdentifierService clientIdentifierService,
     Client client
 protected final ClientIdentifierService clientIdentifierService;
 protected final Client client;
   this.clientIdentifierService = clientIdentifierService;
   this.client = client;
protected void updateRecipients(AbstractMessage message)
     throws IOException, NoSuchAlgorithmException {
   for (Client client : message.getRecipients()) {
     if (client.getIdentifier() == null) {
       client.setIdentifier(clientIdentifierService.get(client.getUuid()));
     }
   }
 }
 protected void updateSender(AbstractMessage message)
     throws IOException, NoSuchAlgorithmException {
   if (message.getSender().getIdentifier() == null) {
     message.getSender().setIdentifier(clientIdentifierService.get(client.getUuid()));
   }
 }
public String computeMessageToken(AbstractMessage message)
     throws IOException, NoSuchAlgorithmException {
   ByteArrayOutputStream baos = new ByteArrayOutputStream();
   baos.write(client.getInitializationVector());
   baos.write(client.getIdentifier().getBytes(Charset.defaultCharset()));
   baos.write(client.getPublicIPAddress().getBytes(Charset.defaultCharset()));
   baos.write(message.getDateCreated().toString().getBytes(Charset.defaultCharset()));
   return (digestService.computeSignature(baos.toByteArray()));
 }
if (validate) validateMessage(message);
   updateRecipients(message);
   updateSender(message);
   return (message);
protected void validateMessage(AbstractMessage message)
     throws IOException, NoSuchAlgorithmException {
   final String expectedToken = computeMessageToken(message);
   if (message.getToken() == null) {
     throw new IllegalStateException("Token is null, but it should be populated."));
   }
   if (!message.getToken().equals(expectedToken)) {
     throw new IllegalStateException("Token does not match expected token."));
   }
 }
TODO: refactor this
   updateMessageDetails(message);
protected void updateMessageDetails(AbstractMessage message)
     throws IOException, NoSuchAlgorithmException {
   message.setDateSent(LocalDateTime.now());
   message.setSender(client);
   message.setToken(messageHelperService.computeMessageToken(message));
 }
if (message.getRecipients() == null || message.getRecipients().isEmpty()) {
     queueService.write(new Queue("all", QueueType.All), encryptedContents);
   } else {
     for (Client recipient : message.getRecipients()) {
       queueService.write(new Queue(recipient.getIdentifier(), QueueType.Self),
encryptedContents);
     }
   }
message.setToken(messageHelperService.computeMessageToken(message));
*/
