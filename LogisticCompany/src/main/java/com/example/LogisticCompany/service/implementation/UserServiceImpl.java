package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.user.*;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.user.User;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.repository.UserRepository;
import com.example.LogisticCompany.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    // secret key for signing the JWT
    @Value("${jwt.secret}")
    private String jwtSecret;

    // token expiration time in milliseconds
    @Value("${jwt.expiration}")
    private long jwtExpiration;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    public UserLoginDtoResponse login(LoginUserDto userDto, HttpServletResponse response) throws AuthenticationException {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new AuthenticationException("User was not found"));

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            String token = generateJwtToken(user.getUsername());

            // set the token in the response header
            response.setHeader("Authorization", "Bearer " + token);

            UserLoginDtoResponse tempLoginDtoResponse = new UserLoginDtoResponse();

            tempLoginDtoResponse.setUsername(user.getUsername());
            tempLoginDtoResponse.setToken(token);
            tempLoginDtoResponse.setEmail(user.getEmail());
            tempLoginDtoResponse.setUserType(user.getUserType());

            return tempLoginDtoResponse;
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }

    public void register(RegisterUserDto userDto) throws AuthenticationException {
        // check if the username is already taken
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new AuthenticationException("Username is already taken");
        }

        // encode the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserDto newUser = new UserDto(userDto.getUsername(), encodedPassword);

        User user = modelMapper.map(newUser, User.class);

        userRepository.saveAndFlush(user);
    }

    public UserDtoResponse setUserRole(int userId, UserType userType) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setUserType(userType);
        userRepository.saveAndFlush(user);

        return modelMapper.map(user, UserDtoResponse.class);
    }

    private String generateJwtToken(String username) {
        // create an HMAC key from the secret key bytes
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // create a JWT token
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
