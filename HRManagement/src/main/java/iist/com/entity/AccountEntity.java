package iist.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity(name = "AccountEntity")
@Table(name = "account")
@Data
public class AccountEntity {

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "create_time", nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "last_login", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
}
