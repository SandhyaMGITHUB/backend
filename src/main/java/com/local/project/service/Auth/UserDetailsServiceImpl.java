package com.local.project.service.Auth;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.local.project.jwts.MessageResponse;
import com.local.project.model.Auth.User;
import com.local.project.repository.Auth.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return UserDetailsImpl.build(user);
	}

	/*fetch all users except admin*/
	public List<User> listAllUsers(){
		Integer roleid = 1;
//	    return userDao.getAdminList(roleName);
		return userRepository.findAllUsers(roleid);
	}	
	
	/*fetch  users based on role*/
	public User listUsers(int roleName){
//	    return userDao.getAdminList(roleName);
		return userRepository.findUsers(roleName);
	}
	
	
	/*forgot password*/
	public String forgotPassword(String email) {

		Optional<User> userOptional = userRepository.findByEmail(email);

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());
		user = userRepository.save(user);

		return user.getToken();
	}

	/*reset password*/
	public ResponseEntity<?>  resetPassword(String email,String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByToken(token));

		if (!userOptional.isPresent()) {
//			return ResponseEntity.ok(new MessageResponse("Invalid Token"));
			return ResponseEntity.ok(new MessageResponse("Link Expired"));
//			return "Invalid Token";
		}
		
		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return ResponseEntity.ok(new MessageResponse("Link Expired"));
//			return "Token Expired";

		}

		User user = userOptional.get();
		
		user.setPassword(password);
		user.setToken(null);
		user.setTokenCreationDate(null);
		
		userRepository.save(user);
		
		
//		return "Your password successfully updated.";
		return ResponseEntity.ok(new MessageResponse("Password has been Reset Succesfully"));
	}

	/*Token generation for reset password link*/
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	/*checking expiration time of password reset link*/
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	public List<User> getRelationsHead(int roleName) {
		// TODO Auto-generated method stub
		return userRepository.getRelationsHead(roleName);
	}
		
}

