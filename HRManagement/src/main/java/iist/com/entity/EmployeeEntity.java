package iist.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name = "employee")
@Table(name = "employee")
@Data
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Employee_id",nullable = false)
	private String employeeId;
	
	@Column(name = "first_name",nullable = false)
	private String firstName;
	
	@Column(name = "last_name",nullable = false)
	private String lastName;
	
	@Column(name = "phone_number",nullable = false)
	private String phoneNumber;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@Column(name = "birthday",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	@Column(name = "identity_card",nullable = false)
	private String identityCard;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private PositionEntity position;
	
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "create_time")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "update_by")
	private String updateBy;
	
	@Column(name = "update_time")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
}

