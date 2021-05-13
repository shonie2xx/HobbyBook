package fontys.its3.hobbybook.controller;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.ERole;
import fontys.its3.hobbybook.domain.Role;
import fontys.its3.hobbybook.repositories.AccountRepository;
import fontys.its3.hobbybook.repositories.RoleRepository;
import fontys.its3.hobbybook.security.jwt.TokenUtil;
import fontys.its3.hobbybook.security.payload.request.LoginRequest;
import fontys.its3.hobbybook.security.payload.request.SignupRequest;
import fontys.its3.hobbybook.security.payload.response.JwtResponse;
import fontys.its3.hobbybook.security.payload.response.MessageResponse;
import fontys.its3.hobbybook.security.service.UserDetailsImpl;
import fontys.its3.hobbybook.security.service.UserDetailsServiceImpl;
import fontys.its3.hobbybook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountService accountService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(path = "/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse (jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> saveUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if (accountRepo.existsAccountByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (accountRepo.existsAccountByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Account user = new Account(signUpRequest.getUsername(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getAge(),
                signUpRequest.getGender());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        accountService.createAccount(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
