package com.id.perpus.common;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationFilter {

   static String apiKey = Constanta.SALT;
	

	public static TokenModel createJWT(String id, String issuer, String subject, long ttlMillis) {
		TokenModel model = new TokenModel();
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

			JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
					.signWith(signatureAlgorithm, signingKey);

			if (ttlMillis >= 0) {
				long expMillis = nowMillis + ttlMillis;
				Date exp = new Date(expMillis);
				builder.setExpiration(exp);
			}

			model.setToken(builder.compact());
			model.setStatus(true);
			model.setIss(issuer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
		
	}

	public static boolean verifyJWT(String jwt) throws Exception{
		TokenModel model = new TokenModel();
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey)).parseClaimsJws(jwt).getBody();

		Date exp = claims.getExpiration();
		Date now = new Date();
		
		if(exp.getTime() > now.getTime()){
			model.setToken(jwt);
			model.setIss(claims.getIssuer());
			model.setStatus(true);
		}else{
			model.setStatus(false);
		}

		return model.isStatus();
	}
	
	public static boolean apiCheck(HttpServletRequest request){
		try {
			String token = (String) request.getSession().getAttribute("token");
			return verifyJWT(token);
		} catch (Exception e) {
			return false;
		}
	}

}
