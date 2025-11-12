package tn.esprit.spring.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
//vun import
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

 */

@Entity
@Table(name = "T_USER")
public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;   
	
	private String firstName;

	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@Enumerated(EnumType.STRING)
	Role role; 
	
	public User() {	}

	public User(String firstName, String lastName, Date dateNaissance, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}


	public User(Long id, String firstName, String lastName, Date dateNaissance, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateNaissance="
				+ dateNaissance + ", role=" + role + "]";
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lName) {
		this.lastName = lName;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}



/*
	//vun integrate the code here final test wedpiwwwwww

// === BEGIN Sonar-triggering demo code ===

	// S2068: hard-coded credentials / secrets
	private static final String PASSWORD = "P@ssw0rd!";      // flagged as hard-coded credential
	private static final String API_SECRET = "sk_live_ABC";  // flagged as hard-coded secret

	// S4790: weak hash algorithm (MD5) used for security
	public String md5OfLastName() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");  // weak hash
			byte[] digest = md.digest(
					(lastName == null ? "" : lastName).getBytes()
			);
			return Base64.getEncoder().encodeToString(digest);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	// S2245: predictable PRNG used for security token
	public String insecureActivationToken() {
		Random r = new Random(); // predictable PRNG
		byte[] buf = new byte[16];
		r.nextBytes(buf);
		return Base64.getEncoder().encodeToString(buf);
	}

	// S2077 / S3649 hotspot: dynamically formatted SQL (risk of SQLi if used)
// This just builds the string; if you actually execute it with JDBC/EntityManager,
// Sonar will raise the hotspot where it’s used.
	public String buildFindByNameQuery(String name) {
		return "SELECT id FROM T_USER WHERE firstName = '" + name + "'";
	}
// === END Sonar-triggering demo code ===*/


}
