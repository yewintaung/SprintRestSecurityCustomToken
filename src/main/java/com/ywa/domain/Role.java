package com.ywa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "roleName", nullable = false, unique = true)
    private String roleName;
	
	public Role(final String roleName) {
		this.roleName = roleName;
	}
	 
}
