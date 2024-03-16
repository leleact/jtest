package com.github.leleact.jtest.crypto.nsa;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * SM2 Test
 *
 * @author leleact
 * @see <a href="https://github.com/JulongChain/julongchain/blob/master/src/main/java/org/bcia/julongchain/csp/gm/dxct/sm2/SM2.java">SM2 Example</a>
 * @since 2022-04-29
 */
@Slf4j
public class SM2Tests {
    @Test
    public void signAndVerifyTest() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        kpg.initialize(sm2Spec, new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),
            new BouncyCastleProvider());
        signature.initSign(privateKey);
        byte[] plainText = "Hello world".getBytes(StandardCharsets.UTF_8);
        signature.update(plainText);
        byte[] signatureValue = signature.sign();
        log.info("signatureValue: {}", Hex.toHexString(signatureValue));

        signature.initVerify(publicKey);
        signature.update(plainText);
        log.info("signature verify result: {}", signature.verify(signatureValue));
    }

    @Test
    public void keyPair1Test() {
        int length;
        byte[] privateKey;
        byte[] publicKey;
        do {
            ECKeyPairGenerator gen = new ECKeyPairGenerator();
            X9ECParameters secNameCurves = SECNamedCurves.getByName("secp256k1");
            ECDomainParameters ecParams = new ECDomainParameters(secNameCurves.getCurve(), secNameCurves.getG(),
                secNameCurves.getN(), secNameCurves.getH());
            ECKeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(ecParams, new SecureRandom());
            gen.init(keyGenParam);
            AsymmetricCipherKeyPair kp = gen.generateKeyPair();
            ECPrivateKeyParameters ecPriKParam = (ECPrivateKeyParameters) kp.getPrivate();
            ECPublicKeyParameters ecPubKParam = (ECPublicKeyParameters) kp.getPublic();
            privateKey = ecPriKParam.getD().toByteArray();
            publicKey = ecPubKParam.getQ().getEncoded(false);
            length = ecPriKParam.getD().toByteArray().length;
        } while (length != 32);
        log.info("privateKey: {}", Hex.toHexString(privateKey));
        log.info("publicKey: {}", Hex.toHexString(publicKey));
    }

    @Test
    public void genKeyPair2Test() {
        X9ECParameters secNameCurves = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters ecParams = new ECDomainParameters(secNameCurves.getCurve(), secNameCurves.getG(),
            secNameCurves.getN(), secNameCurves.getH());
        ECKeyGenerationParameters ecKeyGenerationParameters = new ECKeyGenerationParameters(ecParams,
            new SecureRandom());
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        keyPairGenerator.init(ecKeyGenerationParameters);
        AsymmetricCipherKeyPair kp = keyPairGenerator.generateKeyPair();
        ECPrivateKeyParameters ecPriKParam = (ECPrivateKeyParameters) kp.getPrivate();
        ECPublicKeyParameters ecPubKParam = (ECPublicKeyParameters) kp.getPublic();
        BigInteger privateKey = ecPriKParam.getD();
        ECPoint publicKey = ecPubKParam.getQ();
        log.info("privateKey: {}", Hex.toHexString(privateKey.toByteArray()));
        log.info("publicKey: {}", Hex.toHexString(publicKey.getEncoded(false)));
    }
}
