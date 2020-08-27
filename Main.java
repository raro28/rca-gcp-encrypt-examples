import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class Main {

    private static String encrypt(String key, String str) throws Exception {
        PublicKey pk = KeyFactory
            .getInstance("RSA")
            .generatePublic(new X509EncodedKeySpec(Base64
                .getDecoder()
                .decode(Files
                    .readString(Paths.get(key))
                    .replace("-----BEGIN PUBLIC KEY-----\n", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PUBLIC KEY-----", ""))));

        //https://cloud.google.com/kms/docs/encrypt-decrypt-rsa#encrypt_data
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        OAEPParameterSpec oaepParams =
            new OAEPParameterSpec(
                "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.ENCRYPT_MODE, pk, oaepParams);

        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(String.format("Encrypt %s with %s\n\n%s", args[1], args[0], encrypt(args[0], args[1])));
    }
}