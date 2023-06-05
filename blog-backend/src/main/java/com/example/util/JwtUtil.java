package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.example.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class JwtUtil {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    private static final String secret = "blog-backend";

    /**
     * 生成签名,5min后过期
     * @return 加密的token
     */
    public static String generateToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
//                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证 token
     * @param token 验证的 token 值
     * @return 用户名
     */
    public static boolean validateToken(HttpServletRequest request, String token) throws Exception {
            Verification verification = JWT.require(Algorithm.HMAC512(secret));
            JWTVerifier jwtVerifier = verification.build();
            DecodedJWT decodedJwt = jwtVerifier.verify(token);
            if(decodedJwt != null) {
                request.getSession().setAttribute("id", decodedJwt.getClaim("id"));
                request.getSession().setAttribute("username", decodedJwt.getClaim("username"));
                request.getSession().setAttribute("password", decodedJwt.getClaim("password"));
                return true;
            }
            return false;
    }
}