// package com.walterjwhite.datastore.index;
// import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
// import com.walterjwhite.datastore.api.model.entity.AbstractEntity_;
// import java.util.HashSet;
// import java.util.Set;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// public class JPAIndexerWrapper {

//  protected String getIndex(AbstractEntity data) {
//    return (getIndexForEntityClass(data.getClass()));
//  }
//  protected String getIndexForEntityClass(final Class<? extends AbstractEntity> type) {
//    return (type.getName().toLowerCase());
//  }
//  protected String getIndex(final Class<? extends AbstractEntity_> type) {
//    return (type.getName().toLowerCase().replace("_", ""));
//  }
//  protected String getType(AbstractEntity data) {
//    return (getTypeForEntityClass(data.getClass()));
//  }
//  protected String getTypeForEntityClass(final Class<? extends AbstractEntity> type) {
//    return (type.getName());
//  }
//  protected String getType(final Class<? extends AbstractEntity_> type) {
//    return (type.getName().replace("_", ""));
//  }
//  protected String[] getIndexes(final Class<? extends AbstractEntity_>... types) {
//    final Set<String> indexes = new HashSet<>();
//    for (final Class<? extends AbstractEntity_> type : types) {
//      indexes.add(getIndex(type));
//    }
//    return (indexes.toArray(new String[indexes.size()]));
//  }
//  protected String[] getTypes(final Class<? extends AbstractEntity_>... types) {
//    final Set<String> sTypes = new HashSet<>();
//    for (final Class<? extends AbstractEntity_> type : types) {
//      sTypes.add(getType(type));
//    }
//    return (sTypes.toArray(new String[sTypes.size()]));
//  }
//  //    if (isIndexed()) return (doCall());
// }
