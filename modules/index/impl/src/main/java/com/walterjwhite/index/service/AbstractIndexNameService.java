/*
package com.walterjwhite.index;

import com.walterjwhite.encryption.api.service.DigestService;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.service.IndexNameService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
public abstract class AbstractIndexNameService<
       EntityType extends Serializable, EntityId extends Serializable>
   implements IndexNameService<EntityType, EntityId> {
 protected final Map<EntityType, Index> entityTypeMap = new HashMap<>();
 protected final Map<Index, EntityType> indexNameMap = new HashMap<>();
 protected final DigestService digestService;
 public AbstractIndexNameService(DigestService digestService) throws IOException {
   this.digestService = digestService;
   read();
 }
 @Override
 public Index getIndex(EntityType entityType) {
   if (entityTypeMap.containsKey(entityType)) return (entityTypeMap.get(entityType));
   try {
     final String entityTypeString = getEntityTypeAsString(entityType);
     final String indexName =
         digestService.compute(entityTypeString.getBytes(Charset.defaultCharset()));
     // JPA: Class<? extends AbstractEntity>
     // CSV: String(Filename)
     indexNameMap.put(indexName, entityType);
     entityTypeMap.put(entityType, indexName);
     write();
     return (indexName);
   } catch (IOException | NoSuchAlgorithmException e) {
     throw new IllegalStateException("Unable to determine index name", e));
   }
 }
 protected abstract String getEntityTypeAsString(EntityType entityType);
 @Override
 public EntityType getEntityType(String indexName) {
   //    for (final String entityTypeString : indexNameMap.keySet()) {
   //      if (indexName.equals(indexNameMap.get(entityTypeString))) {
   //        return (getEntityTypeFromEntityTypeString(entityTypeString));
   //      }
   //    }
   if (!indexNameMap.containsKey(indexName))
     throw new IllegalStateException("Index Name:" + indexName + " is not mapped!"));
   return (indexNameMap.get(indexName));
 }
 protected abstract EntityType getEntityTypeFromEntityTypeString(final String entityTypeString);
 protected void write() throws IOException {
   if (indexNameMap.size() == 0) return;
   try (final FileWriter fileWriter = new FileWriter(new File(getIndexFilename()))) {
     for (final String indexName : indexNameMap.keySet()) {
       fileWriter.write(
           indexName + "=" + getEntityTypeAsString(indexNameMap.get(indexName)) + "\n");
     }
   }
 }
 protected void read() throws IOException {
   final File indexFile = new File(getIndexFilename());
   if (!indexFile.exists()) return;
   final List<String> lines = FileUtils.readLines(indexFile, Charset.defaultCharset());
   for (final String line : lines) {
     final String[] parts = line.split("=");
     final EntityType entityType = getEntityTypeFromEntityTypeString(parts[1]);
     indexNameMap.put(parts[0], entityType);
     entityTypeMap.put(entityType, parts[0]);
   }
 }
 private static final String getIndexFilename() {
   return (AbstractIndexNameService.class.getSimpleName() + ".index");
 }
}
*/
