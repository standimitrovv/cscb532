package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserDtoResponse login(LoginUserDto userDto, HttpServletResponse response) throws AuthenticationException {
        // find user by username
        User user = userRepository.findByUsername(userDto.getUsername()).get();

        // check if the user exists and if the password is correct
        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            // generate a token
            String token = generateJwtToken(user.getUsername());

            // set the token in the response header
            response.setHeader("Authorization", "Bearer " + token);

            // return dto
            return new UserDtoResponse(user.getId(), user.getUsername(), user.getEmail(), user.getUserType(), user.getEmployee(), user.getClient());
        } else {
            // handle invalid login credentials
            throw new AuthenticationException("Invalid username or password");
        }
    }

    public void register(RegisterUserDto userDto) throws AuthenticationException {
        // check if the username is already taken
        if (userRepository.findUserByUsername(userDto.getUsername()).isPresent()) {
            // if already taken
            throw new AuthenticationException("Username is already taken");
        }

        // encode the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserDto newUser = new UserDto(userDto.getUsername(), encodedPassword);

        User user = modelMapper.map(newUser, User.class);

        userRepository.saveAndFlush(user);
    }

    public UserDtoResponse setUserRole(int userId, UserType userType) {
        User user = userRepository.findById(userId).get();
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
