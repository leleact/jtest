package com.github.leleact.jtest.jdk.keystore;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

@Slf4j
public class Pkcs12Tests {

    @Test
    public void readPrivateKeyTest() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException, InvalidKeySpecException {
        String pfxPath = "D:\\a.pfx";
        String passwd = "000000";
        String base64Pri = null;
        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(pfxPath), passwd.toCharArray());
        Enumeration e = p12.aliases();
        while (e.hasMoreElements()) {
            String alias = (String) e.nextElement();
            log.info("alias=[{}]", alias);
            PrivateKey key = (PrivateKey) p12.getKey(alias, passwd.toCharArray());
            if (key == null) {
                log.info("alias[{}] private key null", alias);
            } else {
                log.info("alias[{}}]=[{}}]", alias, key.toString());
            }
            KeyStore.Entry entry = p12.getEntry(alias,
                                                new KeyStore.PasswordProtection(passwd.toCharArray()));
            if (entry == null) {
                log.info("entry[" + alias + "]=[NULL]");
            } else {
                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    KeyStore.PrivateKeyEntry entry1 = (KeyStore.PrivateKeyEntry) entry;
                    log.info(
                        "private entry[" + alias + "]=[" + entry1.getPrivateKey() + "], [" + entry1.getCertificate() + "]");
                    log.info("serialNumber: {}", ((X509Certificate)entry1.getCertificate()).getSerialNumber());
                    log.info("certificate base64 encode: [{}]", new String(Base64.getEncoder().encode(entry1.getCertificate().getEncoded())));
                    log.info("PrivateKey Algorithm:" + entry1.getPrivateKey().getAlgorithm());
                    log.info("PrivateKey Format:" + entry1.getPrivateKey().getFormat());
                    base64Pri = new String(Base64.getEncoder().encode(entry1.getPrivateKey().getEncoded()));
                    log.info("Private Key base64: [{}]", base64Pri);
                    log.info("Type:" + entry1.getCertificate().getType());
                    log.info("PublicKey Algorithm:" + entry1.getCertificate().getPublicKey().getAlgorithm());
                    log.info("PublicKey Format:" + entry1.getCertificate().getPublicKey().getFormat());
                    log.info("chain len:" + entry1.getCertificateChain().length);
                } else if (entry instanceof KeyStore.TrustedCertificateEntry) {
                    KeyStore.TrustedCertificateEntry entry1 = (KeyStore.TrustedCertificateEntry) entry;
                    log.info(
                        "trust entry[" + alias + "]=[" + entry1.getTrustedCertificate() + "]");
                    log.info("Type:" + entry1.getTrustedCertificate().getType());
                    log.info(
                        "PublicKey Algorithm:" + entry1.getTrustedCertificate().getPublicKey().getAlgorithm());
                    log.info("PublicKey Format:" + entry1.getTrustedCertificate().getPublicKey().getFormat());
                }
            }
        }

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64Pri));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        log.info("algorithm: {}", privateKey.getAlgorithm());
    }
}
