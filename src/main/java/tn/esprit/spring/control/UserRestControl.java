package tn.esprit.spring.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserRestControl {

	@Autowired
	IUserService userService;

	// 🚨 Fake secret for Gitleaks test (ne jamais utiliser en prod)
	String awsSecretKey = "AKIA1234567890FAKEKEY";


	@GetMapping("/retrieve-all-users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}

	@GetMapping("/retrieve-user/{user-id}")
	public User retrieveUser(@PathVariable("user-id") String userId) {
		return userService.retrieveUser(userId);
	}

	@PostMapping("/add-user")
	public User addUser(@RequestBody User u) {
		User user = userService.addUser(u);
		return user;
	}

	@DeleteMapping("/remove-user/{user-id}")
	public void removeUser(@PathVariable("user-id") String userId) {
		userService.deleteUser(userId);
	}

	@PutMapping("/modify-user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	// 🚨 Vulnérabilité volontaire pour test DevSecOps (Injection SQL)
	// URL : http://localhost:8080/user/unsafe-login?username=admin'--&password=anything
	@GetMapping("/unsafe-login")
	public String unsafeLogin(@RequestParam String username, @RequestParam String password) {
		String result = "Utilisateur non trouvé";
		try {
			// Mauvaise pratique : concaténation directe de variables dans la requête SQL
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM USERS WHERE username='" + username + "' AND password='" + password + "'"
			);

			if (rs.next()) {
				result = "Connexion réussie pour " + username;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
