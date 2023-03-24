import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member {
	int id;
	String regDate;
	String loginId;
	String loginPw;
	String name;
	
	Member (int id, String loginId, String loginPw, String name){
		this.id = id;
		this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
}
