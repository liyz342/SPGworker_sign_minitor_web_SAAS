package com.wsminitor.hisexampleserver.config;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.StringUtils;
/*
* 这段代码是一个Java类，属于`com.wsminitor.hisexampleserver.config`包，主要提供了与用户认证相关的密码加密和匹配功能。这个类使用了Apache Shiro库，一个强大的安全框架，提供了认证、授权等功能。以下是对这个类的主要组成部分的解释：

1. **`generateSalt`方法**:
   - 这个方法用于生成随机的salt（盐值），用于密码加密过程中增加额外的熵，以提高密码的安全性。
   - 它接受一个整数参数`len`，表示生成的salt的长度。
   - 使用`SecureRandomNumberGenerator`来生成随机字节，然后将这些字节转换为十六进制字符串。

2. **`encryptPassword`方法（重载版本）**:
   - 这两个方法都用于加密密码。
   - 第一个`encryptPassword`方法是一个简化版本，使用默认的MD5算法和默认的迭代次数（1次）。
   - 第二个`encryptPassword`方法允许指定hash算法、密码、盐值以及迭代次数，提供了更灵活的加密选项。
   - 这两个方法都使用Shiro的`SimpleHash`类来执行实际的加密操作。

3. **`isPasswordMatch`方法**:
   - 这个方法用于验证用户输入的密码是否与存储的加密密码（userPass）匹配。
   - 它接受用户输入的密码、盐值和加密后的密码作为参数。
   - 使用MD5算法和指定的盐值来生成加密后的密码，并与用户提供的加密密码进行比较。

这个类的设计允许开发者在用户注册或更改密码时生成安全的加密密码，并在登录时验证用户输入的密码是否正确。使用盐值和迭代次数可以防止彩虹表攻击和字典攻击，提高密码存储的安全性。

在实际应用中，开发者可能会根据需要选择合适的加密方法和参数。例如，可能会选择更强的hash算法（如SHA-256或SHA-512），以及更多的迭代次数，以进一步提高安全性。此外，随着安全需求的变化和技术的发展，可能还会考虑使用更现代的密码存储方案，如bcrypt或scrypt。
* */
public class UserCredentialsMatcher {
    /**
     * 随机生成 salt 需要指定 它的字符串的长度
     *
     * @param len 字符串的长度
     * @return salt
     */
    public static String generateSalt(int len) {
        //一个Byte占两个字节
        int byteLen = len >> 1;
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(byteLen).toHex();
    }

    /**
     * 获取加密后的密码，使用默认hash迭代的次数 1 次
     *
     * @param hashAlgorithm hash算法名称 MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512、etc。
     * @param password      需要加密的密码
     * @param salt          盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String hashAlgorithm, String password, String salt) {
        return encryptPassword("MD5", password, salt, 2);
    }

    /**
     * 获取加密后的密码，需要指定 hash迭代的次数
     *
     * @param hashAlgorithm  hash算法名称 MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512、etc。
     * @param password       需要加密的密码
     * @param salt           盐
     * @param hashIterations hash迭代的次数
     * @return 加密后的密码
     */
    public static String encryptPassword(String hashAlgorithm, String password, String salt, int hashIterations) {
        SimpleHash hash = new SimpleHash(hashAlgorithm, password, salt, hashIterations);
        return hash.toString();
    }

    /**
     * 判断用户输入的密码是否正确
     * @param password 输入的密码
     * @param userPass 用户真正的密码
     */
    public static Boolean isPasswordMatch(String password, String  salt,String userPass){
        SimpleHash hash = new SimpleHash("MD5", password, salt, 2);
        return hash.toString().equals(userPass);
    }
}
