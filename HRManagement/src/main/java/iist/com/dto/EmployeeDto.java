package iist.com.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description="All details about the Employee. ")
@Data
public class EmployeeDto {
	
	@NotBlank(message = "First name must be not blank!")
	@Pattern(regexp = "([a-zA-Z]+)", message = "First name must be charaters.")
	private String firstName;
	
	@NotBlank(message = "Last name must be not blank!")
	@Pattern(regexp = "([a-zA-Z\\s]+)", message = "First name must be charaters.")
	private String lastName;
	
	@NotBlank(message = "Phone number must be not blank!")
	@Pattern(regexp = "([0-9]{10})", message = "Phone number must be 10 number.")
	private String phoneNumber;
	
	
	@NotBlank(message = "DOB must be not blank!")
	@Pattern(regexp = "[0-1][0-9]\\/[0-3][0-9]\\/[1-2][0-9]{3}", message = "DOB must have format dd/MM/yyyy")
	private String birthday;
	
	@NotBlank(message = "Address must be not blank!")
	private String address;
	
	@NotBlank(message = "IdentityCard must be not blank!")
	private String identityCard;
	
	private String positionId;
}
