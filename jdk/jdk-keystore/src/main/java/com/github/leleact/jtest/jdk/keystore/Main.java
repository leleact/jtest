package com.github.leleact.jtest.jdk.keystore;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Enumeration;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {

        String fileName = args[0];
        String passwd = args[1];

        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(fileName), passwd.toCharArray());
        Enumeration<String> e = p12.aliases();
        while (e.hasMoreElements()) {
            String alias = e.nextElement();
            log.info("alias=[" + alias + "]");
            PrivateKey key = (PrivateKey) p12.getKey(alias, passwd.toCharArray());
            if (key == null) {
                log.info("alias[" + alias + "]=[NULL]");
            } else {
                log.info("alias[" + alias + "]=[" + key.toString() + "]");
            }
            log.info("=========================================");
            KeyStore.Entry entry = p12.getEntry(alias,
                                                new KeyStore.PasswordProtection(passwd.toCharArray()));
            if (entry == null) {
                log.info("entry[" + alias + "]=[NULL]");
            } else {
                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    KeyStore.PrivateKeyEntry entry1 = (KeyStore.PrivateKeyEntry) entry;
                    log.info(
                        "private entry[" + alias + "]=[" + entry1.getPrivateKey() + "], [" + entry1.getCertificate() + "]");
                    log.info("PrivateKey Algorithm:" + entry1.getPrivateKey().getAlgorithm());
                    log.info("PrivateKey Format:" + entry1.getPrivateKey().getFormat());
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
    }
}
