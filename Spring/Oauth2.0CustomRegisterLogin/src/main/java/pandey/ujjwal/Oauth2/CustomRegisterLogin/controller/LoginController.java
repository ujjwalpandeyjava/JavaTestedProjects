package pandey.ujjwal.Oauth2.CustomRegisterLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pandey.ujjwal.Oauth2.CustomRegisterLogin.dto.UserLoginDTO;
import pandey.ujjwal.Oauth2.CustomRegisterLogin.service.DefaultUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private DefaultUserService userService;

//	@Autowired
//	private UserRepository userRepo;

	@ModelAttribute("user")
	public UserLoginDTO userLoginDTO() {
		return new UserLoginDTO();
	}

	@GetMapping
	public String login() {
		return "login";
	}

	@PostMapping
	public void loginUser(@ModelAttribute("user") UserLoginDTO userLoginDTO) {
		System.out.println("UserDTO" + userLoginDTO);
		UserDetails loadUserByUsername = userService.loadUserByUsername(userLoginDTO.getUsername());
		System.out.println(loadUserByUsername);
	}

}