package dataObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Bổ sung thêm cái này để hỗ trợ đọc Excel/JSON sau này
@AllArgsConstructor // Bổ sung thêm cái này để tạo nhanh dữ liệu qua Constructor
public class UserIncorrectDataRegister {
	private String lastname;
	private String firstname;
	private String email;
	private String password;
	private String description;
}
