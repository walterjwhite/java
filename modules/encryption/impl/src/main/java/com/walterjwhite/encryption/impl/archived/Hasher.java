/*
package com.walterjwhite.remote.impl.service.encryption;

import com.walterjwhite.remote.impl.service.util.IOUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#Examples
@Stateless
public class Hasher {
 public String compute(final String data) throws NoSuchAlgorithmException, IOException {
   final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
   messageDigest.update(data.getBytes(StandardCharsets.ISO_8859_1));
   messageDigest.update(IOUtil.read(16, "/etc/remote/key"));
   messageDigest.update(IOUtil.read(16, "/etc/remote/iv"));
   final byte[] hash = messageDigest.digest();
   return (new String(Base64.encodeBase64String(hash)));
 }
}
*/
