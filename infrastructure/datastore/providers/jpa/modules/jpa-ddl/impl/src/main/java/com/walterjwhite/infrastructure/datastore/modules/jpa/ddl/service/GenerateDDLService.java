package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.service;

import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property.ApplicationArtifactPath;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property.DDLAction;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property.OutputFilenameFormat;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property.TargetDatabase;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.EnumSet;
import jakarta.inject.Inject;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class GenerateDDLService {
  protected final String applicationArtifactPath;
  protected final TargetDatabase targetDatabase;
  protected final DDLAction ddlAction;
  protected final boolean debug;
  protected final String outputFilenameFormat;
  protected final ApplicationArtifactProcessor applicationArtifactProcessor;

  @Inject
  public GenerateDDLService(
      @Property(ApplicationArtifactPath.class) String applicationArtifactPath,
      @Property(TargetDatabase.class) TargetDatabase targetDatabase,
      @Property(DDLAction.class) DDLAction ddlAction,
      @Property(Debug.class) boolean debug,
      @Property(OutputFilenameFormat.class) final String outputFilenameFormat)
      throws MalformedURLException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {

    this.applicationArtifactPath = applicationArtifactPath;
    this.targetDatabase = targetDatabase;
    this.ddlAction = ddlAction;
    this.debug = debug;
    this.outputFilenameFormat = outputFilenameFormat;

    applicationArtifactProcessor = new ApplicationArtifactProcessor(applicationArtifactPath);
  }

  public void generateDDL() throws IOException {
    getExport()
        .execute(EnumSet.of(TargetType.SCRIPT), ddlAction.getAction(), getMetadataImplementor());
  }

  protected MetadataImplementor getMetadataImplementor() throws IOException {
    final MetadataSources metadata = getMetadataSources();
    for (Class entityClass : applicationArtifactProcessor.getEntityClasses()) {
      metadata.addAnnotatedClass(entityClass);
    }

    return (MetadataImplementor) metadata.buildMetadata();
  }

  protected MetadataSources getMetadataSources() {
    return new MetadataSources(
        new StandardServiceRegistryBuilder()
            .applySetting("hibernate.dialect", targetDatabase.name())
            .build());
  }

  protected SchemaExport getExport() throws IOException {
    SchemaExport export = new SchemaExport();

    export.setDelimiter(";");
    final String outputFile = getOutputFile(applicationArtifactProcessor);
    export.setOutputFile(outputFile);
    export.setFormat(true);

    return export;
  }

  protected String getOutputFile(ApplicationArtifactProcessor applicationArtifactProcessor)
      throws IOException {
    return String.format(
        outputFilenameFormat,
        applicationArtifactPath,
        applicationArtifactProcessor.getImplementationVersion(),
        targetDatabase.name().toLowerCase());
  }
}
