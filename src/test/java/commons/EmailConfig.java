package commons;

import java.io.File;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EmailConfig {

    public static void sendReportByEmail() {
        try {
            // 1. Lấy đường dẫn file ExtentReport.html ngay tại thư mục gốc dự án
            String reportPath = System.getProperty("user.dir") + File.separator + "ExtentReport.html";

            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(reportPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Automation Test Report");
            attachment.setName("ExtentReport.html");

            // 2. Cấu hình SMTP Gmail
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            
            // Xóa khoảng trắng trong App Password (viết liền 16 ký tự)
            email.setAuthenticator(new DefaultAuthenticator("mungnvph52815@gmail.com", "bbeforuqbhzqmkll"));
            email.setSSLOnConnect(true);

            // 3. Nội dung email
            email.setFrom("mungnvph52815@gmail.com", "AUTOMATION SYSTEM");
            email.setSubject("[AUTOMATION REPORT] Kết quả chạy Test Suite tự động");
            email.setMsg("Xin chào,\n\nBộ testcase đã chạy xong. Chi tiết xem trong file báo cáo đính kèm!\n\nTrân trọng.");
            email.addTo("mungnvph52815@gmail.com");

            // 4. Gửi mail
            email.attach(attachment);
            email.send();
            System.out.println("====== GỬI EMAIL BÁO CÁO THÀNH CÔNG ======");

        } catch (Exception e) {
            System.out.println("====== GỬI EMAIL THẤT BẠI ======");
            e.printStackTrace();
        }
    }
}