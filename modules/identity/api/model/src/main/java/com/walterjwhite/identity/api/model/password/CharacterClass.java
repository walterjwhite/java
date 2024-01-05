package com.walterjwhite.identity.api.model.password;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

@Getter
@RequiredArgsConstructor
public enum CharacterClass {
  LowerCase(EnglishCharacterData.LowerCase),
  UpperCase(EnglishCharacterData.UpperCase),
  Numeric(EnglishCharacterData.Digit),
  Special(EnglishCharacterData.Special),
  Other(null) {
    @Override
    public CharacterRule get(final CharacterSetConfiguration characterSetConfiguration) {

      CharacterData specialChars =
          new CharacterData() {
            public String getErrorCode() {
              return "ERROR";
            }

            public String getCharacters() {
              return characterSetConfiguration.getCharacters();
            }
          };

      CharacterRule otherCharacterRule = new CharacterRule(specialChars);
      otherCharacterRule.setNumberOfCharacters(2);
      return otherCharacterRule;
    }
  };

  private final CharacterData characterData;

  public CharacterRule get(final CharacterSetConfiguration characterSetConfiguration) {
    CharacterRule characterRule = new CharacterRule(characterData);
    characterRule.setNumberOfCharacters(characterSetConfiguration.getNumber());

    return characterRule;
  }
}
