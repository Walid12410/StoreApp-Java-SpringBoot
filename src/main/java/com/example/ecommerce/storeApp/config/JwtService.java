package com.example.ecommerce.storeApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service // Marks this class as a Spring Bean (managed by Spring container)
public class JwtService {

    // 🔐 This is your secret key for signing and verifying JWTs. It should stay secure!
    private static final String SECRET_KEY = "jZO8YQ6IWBvh7Bd4geJwEByf09mXszie";

    // ✅ Extracts the username (subject) from the token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // ✅ Generic method to extract any claim from the JWT using a resolver function
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token); // get all claims first
        return claimsResolver.apply(claims); // apply the resolver (e.g., get subject, expiration, etc.)
    }

    // ✅ Generate a JWT token for the given user with no extra claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    // ✅ Generate a JWT with optional extra claims
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims) // add optional data inside the token
                .setSubject(userDetails.getUsername()) // set username as subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // token issue time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // expires after 24 minutes
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // sign the token with the secret key
                .compact(); // build and return the final token
    }

    // ✅ Checks if a token is valid: same username and not expired
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // ❌ Checks if the token is expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // ✅ Extracts the expiration date from the token
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // ✅ Parses the token and extracts all claims using the signing key
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignInKey()) // set key to validate token
                .build()
                .parseClaimsJws(token)
                .getBody(); // return all the data (claims) inside the token
    }

    // ✅ Converts the secret key string to a Key object usable for signing
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // decode the secret string
        return Keys.hmacShaKeyFor(keyBytes); // generate secure key for HMAC SHA-256
    }

}
