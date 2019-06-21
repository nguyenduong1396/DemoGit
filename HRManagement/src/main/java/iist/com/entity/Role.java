package iist.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name = "Role")
@Table(name = "Role")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_Id")
	private int roleId;
	
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "update_Time")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(name = "create_Time")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

}
