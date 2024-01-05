package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.remote.api.model.message.Message;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Execute a sshCommand on a remote server. @NOTE: this requires (assumes) that public key
 * authentication is setup for the user. @NOTE: this should be unused because we should be running
 * the remote control API on that box (unless it is embedded).
 */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class SSHCommandMessage extends Message {}
