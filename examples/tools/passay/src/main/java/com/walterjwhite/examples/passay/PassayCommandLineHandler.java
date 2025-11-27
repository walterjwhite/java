package com.walterjwhite.examples.passay;

import com.walterjwhite.identity.api.model.password.CharacterClass;
import com.walterjwhite.identity.api.model.password.CharacterSetConfiguration;
import com.walterjwhite.identity.api.model.password.PasswordPolicy;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class PassayCommandLineHandler implements CommandLineHandler {


  @Override
  public void run(String... arguments) {
    final CharacterSetConfiguration[] characterSetConfiguration = new CharacterSetConfiguration[4];
    characterSetConfiguration[0] = new CharacterSetConfiguration(2, CharacterClass.LowerCase, null);
    characterSetConfiguration[1] = new CharacterSetConfiguration(2, CharacterClass.UpperCase, null);
    characterSetConfiguration[2] = new CharacterSetConfiguration(2, CharacterClass.Numeric, null);
    characterSetConfiguration[3] = new CharacterSetConfiguration(2, CharacterClass.Special, null);

    final PasswordPolicy passwordPolicy = new PasswordPolicy(8, 32, characterSetConfiguration);
  }
}
