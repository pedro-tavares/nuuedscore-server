package com.nuuedscore.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Role Entity  
 *
 * An Authorization Role
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Role extends BaseDomain {

	private static final long serialVersionUID = 5570852700686895664L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "roles")
    private Collection<Person> people;
    @ManyToMany
    @JoinTable(
    	name = "roles_privileges", 
    	joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
    	inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Collection<Privilege> privileges;
    private String name;

    public Role(final String name) {
        super();
        this.name = name;
    }
    
}