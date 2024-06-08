package com.github.leleact.jtest.jdk.certificate;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CertificateInfoTest {

    @Disabled
    @Test
    public void getCertificateInfo() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

        String pfxFileName = "C:\\Users\\Lele\\Desktop\\czbdsf.pfx";
        String pfxPassword = "password";

        KeyStore ks = KeyStore.getInstance("pkcs12");
        ks.load(new FileInputStream(pfxFileName), pfxPassword.toCharArray());

        Enumeration<String> aliases = ks.aliases();

        String alias = null;
        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            System.out.println("alias[" + alias + "]");
            if (alias != null) break;
        }

        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        System.out.println("issuer  DN[" + certificate.getIssuerX500Principal().getName() + "]");
        System.out.println("subject DN[" + certificate.getSubjectX500Principal().getName() + "]");
        System.out.println("sigAlg  DN[" + certificate.getSigAlgName() + "]");
        System.out.println("sigAlgo DN[" + certificate.getSigAlgOID() + "]");
        System.out.println("type      [" + certificate.getType() + "]");
    }
}
