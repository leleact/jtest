package com.lele.test.jdk.prikey;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Enumeration;

public class Main {

    //private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        String fileName = args[0];
        String passwd = args[1];

        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(fileName), passwd.toCharArray());
        Enumeration e = p12.aliases();
        while (e.hasMoreElements()) {
            String alias = (String) e.nextElement();
            System.out.println("alias=[" + alias + "]");
            PrivateKey key = (PrivateKey) p12.getKey(alias, passwd.toCharArray());
            if (key == null) {
                System.out.println("alias[" + alias + "]=[NULL]");
            } else {
                System.out.println("alias[" + alias + "]=[" + key.toString() + "]");
            }
            System.out.println();
            System.out.println();

            System.out.println("=========================================");
            KeyStore.Entry entry = p12.getEntry(alias,
                    new KeyStore.PasswordProtection(passwd.toCharArray()));
            if (entry == null) {
                System.out.println("entry[" + alias + "]=[NULL]");
            } else {
                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    KeyStore.PrivateKeyEntry entry1 = (KeyStore.PrivateKeyEntry) entry;
                    System.out.println(
                            "private entry[" + alias + "]=[" + entry1.getPrivateKey() + "], [" + entry1.getCertificate() + "]");
                    System.out.println("PrivateKey Algorithm:" + entry1.getPrivateKey().getAlgorithm());
                    System.out.println("PrivateKey Format:" + entry1.getPrivateKey().getFormat());
                    System.out.println("Type:" + entry1.getCertificate().getType());
                    System.out.println("PublicKey Algorithm:" + entry1.getCertificate().getPublicKey().getAlgorithm());
                    System.out.println("PublicKey Format:" + entry1.getCertificate().getPublicKey().getFormat());
                    System.out.println("chain len:" + entry1.getCertificateChain().length);
                } else if (entry instanceof KeyStore.TrustedCertificateEntry) {
                    KeyStore.TrustedCertificateEntry entry1 = (KeyStore.TrustedCertificateEntry) entry;
                    System.out.println(
                            "trust entry[" + alias + "]=[" + entry1.getTrustedCertificate() + "]");
                    System.out.println("Type:" + entry1.getTrustedCertificate().getType());
                    System.out.println(
                            "PublicKey Algorithm:" + entry1.getTrustedCertificate().getPublicKey().getAlgorithm());
                    System.out.println("PublicKey Format:" + entry1.getTrustedCertificate().getPublicKey().getFormat());
                }
                System.out.println();
                System.out.println();
            }
        }
    }
}
