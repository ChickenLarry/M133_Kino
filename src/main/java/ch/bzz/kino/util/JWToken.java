package ch.bzz.kino.util;

import ch.bzz.kino.service.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 * @description implementation of Json WEBToken
 */
public class JWToken {

    /**
     * builds the token
     * @param subject the token subject
     * @param duration the duration of this token in minutes
     * @param claims a map of claims
     *
     * @return JSON web token
     */
    public static String buildToken(
            String subject,
            int duration,
            Map<String, Object> claims) {
        String salt = Config.getProperty("salt");
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            String value = entry.getValue().toString();
            entry.setValue(encrypt(
                    value,
                    getJwtEncrypt(),
                    salt
            ));
        }

        try {
            byte[] keyBytes = Config.getProperty("jwtSecret").getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
            Date now = new Date();
            Date expiration = new Date(now.getTime() + duration * 60000);

        return Jwts.builder()
                .setIssuer("BookUltimate")
                .setSubject(encrypt(
                        subject,
                        getJwtEncrypt(),
                        salt
                ))
                .setClaims(claims)
                .setExpiration(expiration)
                .setIssuedAt(now)
                .signWith(secretKey)
                .compact();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @description reads all claims from the token
     * @param token  the token with all the claims
     *
     * @return a map of the claims
     */
    public static Map<String,String> readClaims(String token) {
        Map<String,String> claimMap = new HashMap<>();
        Jws<Claims> jwsClaims;
        byte[] keyBytes = Config.getProperty("jwtSecret").getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        String jwtKey = Config.getProperty("jwtKey");
        String salt = Config.getProperty("salt");
        try {
            jwsClaims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            String subject = decrypt(
                    jwsClaims.getBody().getSubject(),
                    jwtKey,
                    salt
            );
            claimMap.put("subject",subject         );

            for (Map.Entry<String, Object> entry : jwsClaims.getBody().entrySet()) {
                String value = decrypt(
                        entry.getValue().toString(),
                        jwtKey,
                        salt
                );
                claimMap.put(entry.getKey(), value);

            }

        } catch (JwtException ex) {
            ex.printStackTrace();
        }
        return claimMap;
    }

    /**
     * @param strToEncrypt  string to be encrypted
     * @return encrypted string
     *
     * @description encrypts the string
     */
    public static String encrypt(
            String strToEncrypt,
            String secret,
            String salt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    /**
     * @param strToDecrypt  string to be encrypted
     * @param secret  the secret key
     * @return encrypted string
     *
     * @description decrypts the string
     */
    public static String decrypt(
            String strToDecrypt,
            String secret,
            String salt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * @return the jwtKey
     *
     * @descriptiongets the jwtkey from the propierties
     */
    private static String getJwtEncrypt() {
        String rawKey = Config.getProperty("jwtKey");
        int multi8 = rawKey.length() / 8;
        return rawKey.substring(0, (multi8 * 8));
    }

}
