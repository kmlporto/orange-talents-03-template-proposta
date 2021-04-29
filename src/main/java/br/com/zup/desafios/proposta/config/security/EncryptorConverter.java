package br.com.zup.desafios.proposta.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;


@Component
@Converter
public class EncryptorConverter implements AttributeConverter<String, String> {
    private static final String encryptType = "AES";
    private final Key key;
    private final Cipher cipher;

    public EncryptorConverter(@Value("${security.aes-secret}") String secret) throws Exception {
        key = new SecretKeySpec(secret.getBytes(), encryptType);
        cipher = Cipher.getInstance(encryptType);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try{
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }
}
