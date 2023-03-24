import java.time.*;
import java.time.format.DateTimeFormatter;

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	int views;
	int memberId;
	
	Article(int id, String title, String body, int views, int memberId){
		this.id = id;
		this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
		this.updateDate = regDate;
		this.title = title;
		this.body = body;
		this.views = views;
		this.memberId = memberId;
	}
}
