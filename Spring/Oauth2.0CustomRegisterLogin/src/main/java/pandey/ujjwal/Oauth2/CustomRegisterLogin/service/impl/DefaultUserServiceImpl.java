package pandey.ujjwal.Oauth2.CustomRegisterLogin.service.impl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pandey.ujjwal.Oauth2.CustomRegisterLogin.dao.RoleRepository;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.dao.UserRepository;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.dto.UserRegisteredDTO;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.model.Role;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.model.User;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.service.DefaultUserService;

@Service
public class DefaultUserServiceImpl implements DefaultUserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password.");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public User save(UserRegisteredDTO userRegisteredDTO) {
		Role role = roleRepo.findByRole("USER");
		User user = new User();
		user.setEmail(userRegisteredDTO.getEmail_id());
		user.setName(userRegisteredDTO.getName());
		user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
		user.setRole(role);
		return userRepo.save(user);
	}

}