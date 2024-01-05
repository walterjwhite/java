package com.walterjwhite.authentication.api;

import com.walterjwhite.authentication.api.model.command.*;

public interface IdentityManagementService {
  void changePassword(ChangePasswordCommand changePasswordCommand);

  void changeUsername(ChangeUsernameCommand changeUsernameCommand);

  void disableUser(DisableUserCommand disableUserCommand);

  void enableUser(EnableUserCommand enableUserCommand);

  void createUser(CreateUserCommand createUserCommand);

  void deleteUser(CreateUserCommand createUserCommand);
}
