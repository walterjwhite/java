Contact form feature

How it works

- GET /contact shows the contact form (name, email, subject, message).
- POST /contact validates the input and sends an email via Spring's JavaMailSender.

Configuration

- Configure SMTP with Spring Boot properties (for example in application-local.properties):
  - spring.mail.host
  - spring.mail.port
  - spring.mail.username
  - spring.mail.password
  - spring.mail.properties.mail.smtp.auth
  - spring.mail.properties.mail.smtp.starttls.enable

- Optional properties:
  - app.contact.to: destination address for contact messages. If not set, will use app.contact.from or spring.mail.username.
  - app.contact.from: from address for outgoing messages. If not set, JavaMailSender's default (spring.mail.username) will be used.

Testing locally

- You can use a local SMTP testing server (mailcatcher, MailHog) and point spring.mail.host/port at it.
- Run the app with the profile that loads the properties, e.g.:

```bash
mvn -DskipTests package
java -Dspring.profiles.active=local -jar target/spring-rest-example-<version>.jar
```


