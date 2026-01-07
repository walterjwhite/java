package com.walterjwhite.property.impl.source;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.PropertyHelper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptedPropertySourceManager {
  protected final SecretService secretService;
  protected final Map<Class<? extends ConfigurableProperty>, PropertyValue>
      encryptedPropertyValueMap = new HashMap<>();

  protected void decryptProperties(
      final Map<Class<? extends ConfigurableProperty>, PropertyValue> propertyValueMap) {
    try {
      for (final Map.Entry<Class<? extends ConfigurableProperty>, PropertyValue> entry :
          encryptedPropertyValueMap.entrySet()) {
        final Class<? extends ConfigurableProperty> keyName = entry.getKey();
        final String encryptedValue = entry.getValue().getValue();

        if (encryptedValue == null) {
          throw new IllegalArgumentException("Encrypted value is null, unable to decrypt it.");
        }

        final String plaintextValue = decryptProperty(encryptedValue);
        if (plaintextValue == null) {
          throw new IllegalArgumentException(
              "Plaintext value is null, check the encrypted value is correct: " + keyName);
        }

        propertyValueMap.put(
            keyName, new PropertyValue(entry.getValue().getPropertyType(), plaintextValue));
      }
    } finally {
      encryptedPropertyValueMap.clear();
    }
  }

  protected void setSensitiveProperty(
      Class<? extends ConfigurableProperty> configurableProperty, @Sensitive final String value) {
    encryptedPropertyValueMap.put(
        configurableProperty,
        new PropertyValue(PropertyHelper.getPropertyValueType(configurableProperty), value));
  }

  @Sensitive
  protected String decryptProperty(final String propertyKey) {
    return secretService.get(propertyKey);
  }
}
