package com.nuuedscore.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nuuedscore.refdata.RefGender;
import com.nuuedscore.refdata.RefLearningPersonality;
import com.nuuedscore.refdata.NuuEdScoreConstants;
import com.nuuedscore.refdata.RefPersonStatus;
import com.nuuedscore.repository.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Person Entity  
 *
 * Not called User because USER is reserved word in many SQLs
 * Its also arguably nicer to call a character a Person rather than a User
 * 
 * Implements Spring Security Core UserDetails
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@ToString
@Entity
public class Person extends BaseDomain implements UserDetails {
		
	private static final long serialVersionUID = 3411907887968056444L;
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	@Column(nullable=false, name = "first_name")
	private String firstName; 
	@Column(nullable=false, name = "last_name")
	private String lastName; 
	//@Column(nullable=false)
	private String username; 
	@Column(nullable=false)
    private String email;
	@Column(nullable=false)
    private String password;
	@Enumerated(EnumType.STRING)
	private RefGender refGender;
	@Enumerated(EnumType.STRING)
	@Column(name = "learning_personality")
	private RefLearningPersonality refLearningPersonality;
	@Column(name = "phone_number")
    private String phoneNumber;
	private String organization;
	@Column(name = "class_room_code")
	private String classRoomCode;
	@Column(name = "devicen_token")
	private String devicenToken;
	@Column(name = "type_device")
	private String typeDevice;
	private Date birthday;
	private String school;
	@Column(name = "create_by_id")
	private Integer createById;
	@Enumerated(EnumType.STRING)
    private RefPersonStatus status;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    @ManyToMany 
    @JoinTable( 
    	name = "persons_roles", 
        joinColumns = @JoinColumn(
        name = "person_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
        name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;
    
    public Person(String email) {
    	this("", "", email, NuuEdScoreConstants.DEFAULT_PASSWORD);
    }
    
    public Person(String firstName, String lastName, String email, String password) {
    	this(
    		null, // id
    		firstName, // firstName
    		lastName, // lastName 
    		"", // username,
    		email,
    		password,
    		RefGender.NEUTRAL, // gender
    		RefLearningPersonality.TODO_LEARNING_PERSONALITY, // learningPersonality
    		"", // phoneNumber
    		"", // organization
    		"", //classRoomCode
    		"", // devicenToken
    		"", // typeDevice
    		null, // birthday
    		"", // school
    		0, // createById 
    		RefPersonStatus.ACTIVE, // status
    		null, // created_on
    		null, // updated_on
    		null // roles
    	);
    }

    @Transient
    public boolean isNew() {
    	return this.id == null;
    }

    @Transient
    public boolean hasPassword() {
    	return (this.password != null && (!this.password.isBlank() || !password.isEmpty()));
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(this.roles.toString()));
        return roles;		
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
 	}

	/*
	 * LifeCycle
	 */
	@PrePersist
	public void prePersist() {
	    log.info("prePersist...");
	    //if (this.username == null) {
	    	this.username = this.email;
	    //}
	    if (this.refGender == null) {
	    	this.refGender = RefGender.NEUTRAL;
	    }
	    if (this.refLearningPersonality == null) {
	    	this.refLearningPersonality = RefLearningPersonality.TODO_LEARNING_PERSONALITY;
	    }
	    if (this.status == null) {
	    	this.status = RefPersonStatus.ACTIVE;
	    }	    
	    this.setCreatedOn(LocalDateTime.now());
	}

	@PreUpdate
	public void logUserUpdateAttempt() {
	    log.info("preUpdate...");
	    this.setUpdatedOn(LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Person [id=" + id 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", username=" + username
				+ ", email=" + email + ", password=" + password 
				+ ", refGender=" + refGender
				+ ", refLearningPersonality=" + refLearningPersonality 
				+ ", phoneNumber=" + phoneNumber
				+ ", organization=" + organization 
				+ ", classRoomCode=" + classRoomCode 
				+ ", devicenToken=" + devicenToken 
				+ ", typeDevice=" + typeDevice 
				+ ", birthday=" + birthday 
				+ ", school=" + school
				+ ", createById=" + createById 
				+ ", status=" + status 
				+ ", createdOn=" + createdOn 
				+ ", updatedOn= "+ updatedOn 
				//+ ", roles=" + roles 
				+ "]";
	}
}

