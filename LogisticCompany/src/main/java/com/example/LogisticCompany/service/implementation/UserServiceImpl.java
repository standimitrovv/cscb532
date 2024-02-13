package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.person.BasePersonDtoResponse;
import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDto;
import com.example.LogisticCompany.dto.user.UserLoginDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.user.User;
import com.example.LogisticCompany.repository.ClientRepository;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.repository.UserRepository;
import com.example.LogisticCompany.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    // secret key for signing the JWT
    @Value("${jwt.secret}")
    private String jwtSecret;

    // token expiration time in milliseconds
    @Value("${jwt.expiration}")
    private long jwtExpiration;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(16);
        this.modelMapper = new ModelMapper();
    }

    public UserLoginDtoResponse login(LoginUserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Incorrect password");
        }

        String token = generateJwtToken(user.getUsername());

        UserLoginDtoResponse tempLoginDtoResponse = new UserLoginDtoResponse();

        tempLoginDtoResponse.setId(user.getId());
        tempLoginDtoResponse.setUsername(user.getUsername());
        tempLoginDtoResponse.setToken(token);
        tempLoginDtoResponse.setEmail(user.getEmail());
        tempLoginDtoResponse.setUserType(user.getUserType());

        Client client = clientRepository.findClientByUserId(user.getId());
        if(client != null){
            BasePersonDtoResponse tempPerson = new BasePersonDtoResponse();
            tempPerson.setId(client.getId());
            tempPerson.setEmail(client.getEmail());
            tempPerson.setFullName(client.getFullName());
            tempPerson.setPhoneNumber(client.getPhoneNumber());

            tempLoginDtoResponse.setPerson(tempPerson);
        }

        Employee employee = employeeRepository.findEmployeeByUserId(user.getId());
        if(employee != null){
            BasePersonDtoResponse tempPerson = new BasePersonDtoResponse();
            tempPerson.setId(employee.getId());
            tempPerson.setEmail(employee.getEmail());
            tempPerson.setFullName(employee.getFullName());
            tempPerson.setPhoneNumber(employee.getPhoneNumber());
            tempLoginDtoResponse.setPerson(tempPerson);
        }

        return tempLoginDtoResponse;
    }

    public void register(RegisterUserDto userDto) throws AuthenticationException {
        // check if the username is already taken
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new AuthenticationException("Username is already taken");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserDto tempUser = new UserDto();
        tempUser.setPassword(encodedPassword);
        tempUser.setUsername(userDto.getUsername());
        tempUser.setEmail(userDto.getEmail());

        userRepository.saveAndFlush(modelMapper.map(tempUser, User.class));
    }

    private String generateJwtToken(String username) {
        // create an HMAC key from the secret key bytes
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
