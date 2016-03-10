package com.ywa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = true, unique = false)
	private String firstName;

	@NotEmpty
	@Size(min = 1, max = 100)
	@Column(nullable = true, unique = false)
	private String lastName;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false, unique = false)
	private String password;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(nullable = true, unique = false)
	private Boolean isEnabled;
	
	@JoinTable(name = "ACCOUNT_ROLE", joinColumns = @JoinColumn(name = "ACCOUNT_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Role> roles = new ArrayList<Role>();
}
