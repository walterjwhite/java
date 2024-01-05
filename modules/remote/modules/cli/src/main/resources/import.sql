-- setup callables for each resource type
-- when a job is retrieved with the given entity type, that callable will be created
INSERT INTO EntityType (id, className) VALUES ('1977615988', 'com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatMessage');
INSERT INTO EntityType (id, className) VALUES ('-1113511677', 'com.walterjwhite.remote.plugins.shell.ServiceMessage');
INSERT INTO JobExecutor (id, name) VALUES ('-1113511677', 'com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatMessageHandlerService');
INSERT INTO JobExecutor (id, name) VALUES ('149328878', 'com.walterjwhite.remote.plugins.shell.ServiceMessageHandlerService');
INSERT INTO EntityJobExecutor (id, entityType_id) VALUES ('-1113511677', '1977615988');
INSERT INTO EntityJobExecutor (id, entityType_id) VALUES ('149328878', '-1113511677');

INSERT INTO Service (id, name, version) VALUES ('93080436', 'argus', 0);
INSERT INTO Service (id, name, version) VALUES ('97823', 'bro', 0);
INSERT INTO Service (id, name, version) VALUES ('754680663', 'chronyd', 0);
INSERT INTO Service (id, name, version) VALUES ('95025423', 'cupsd', 0);
INSERT INTO Service (id, name, version) VALUES ('-1359333102', 'minidlna', 0);
INSERT INTO Service (id, name, version) VALUES ('-693594312', 'postgresql-10', 0);
INSERT INTO Service (id, name, version) VALUES ('-314484063', 'privoxy', 0);
INSERT INTO Service (id, name, version) VALUES ('3539804', 'sshd', 0);
INSERT INTO Service (id, name, version) VALUES ('-293117307', 'unbound', 0);
INSERT INTO Service (id, name, version) VALUES ('-1870403137', 'NetworkManager', 0);

--INSERT INTO Client (id) VALUES ('98a654cf4');
--INSERT INTO Client (id) VALUES ('33813d14d');

INSERT INTO NetworkDiagnosticTest (id, name, description, fqdn) VALUES ('-1916761515', 'google.com', '', 'google.com');
INSERT INTO NetworkDiagnosticTest (id, name, description, fqdn) VALUES ('971135797', 'yahoo.com', '', 'yahoo.com');
INSERT INTO NetworkDiagnosticTest (id, name, description, fqdn) VALUES ('-273608267', 'weather.com', '', 'weather.com');
INSERT INTO NetworkDiagnosticTest (id, name, description, fqdn) VALUES ('-1089452075', 'pnc.com', '', 'pnc.com');
INSERT INTO NetworkDiagnosticTest (id, name, description, fqdn) VALUES ('862687829', 'discovercard.com', '', 'discovercard.com');

INSERT INTO Provider (id, name, description, providerType) VALUES ('amazonsqs', 'com.walterjwhite.queue.providers.amazon.sqs.service.AmazonSQSQueueService', 'amazon sqs', 'Queue');
INSERT INTO Provider (id, name, description, providerType) VALUES ('amazons3', 'com.walterjwhite.file.providers.amazon.service.AmazonS3FileStorageService', 'amazon s3', 'FileStorage');
INSERT INTO Provider (id, name, description, providerType) VALUES ('googlepubsub', 'google pub/sub', 'google cloud queue', 'Queue');
INSERT INTO Provider (id, name, description, providerType) VALUES ('googlecloudstorage', 'google cloud storage', 'google cloud storage', 'FileStorage');

COMMIT;
