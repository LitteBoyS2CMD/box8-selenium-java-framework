package dataObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Bổ sung thêm cái này để hỗ trợ đọc Excel/JSON sau này
@AllArgsConstructor // Bổ sung thêm cái này để tạo nhanh dữ liệu qua Constructor
public class UserDataLogin {
    private String email;
    private String password;
}
