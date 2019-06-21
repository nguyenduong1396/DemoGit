package iist.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "UserRoleEntity")
@Table(name="user_Role")
@Data
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id",nullable = false)
	private String userId;
	
	@Column(name="role_id",nullable = false)
	private int roleId;
	
}
