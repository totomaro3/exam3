import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
	
	/* 명령어 목록
	 *  "member join"
	 *  "member login"
	 *  "member logout"
	 *  "article list"
	 *  "article write"
	 *  "article detail"
	 *  "article modify"
	 *  "article delete"
	 */
	
	public static void main(String[] args) {
		
		System.out.println("==프로그램 시작==");
		
		List<Article> articles = new ArrayList<>();
		List<Member> members = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		articles.add(new Article(1,"제목1","내용1",11,3));
		articles.add(new Article(2,"제목2","내용1",22,2));
		articles.add(new Article(3,"제목3","내용1",33,2));
		
		System.out.println("테스트를 위한 게시글 데이터를 생성합니다");
		
		members.add(new Member(1,"test1","test1","김철수"));
		members.add(new Member(2,"test2","test2","김영희"));
		members.add(new Member(3,"test3","test3","홍길동"));
		
		System.out.println("테스트를 위한 회원 데이터를 생성합니다");
		
		int lastMemberId = 3;
		int lastArticleId = 3;
		
		Member loginMember = null;
		
		while(true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}
			
			else if(cmd.equals("member join")) {
				
				if(loginMember != null) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				
				String loginId = null;
				String loginPw = null;
				String loginPwConfirm = null;
				String name = null;
				boolean isfoundMember = false;
				
				while(true) {
					System.out.print("로그인 아이디 : ");
					loginId = sc.nextLine();
					
					if(loginId.length() == 0) {
						System.out.println("아이디를 입력해주세요");
						continue;
					}
					
					for(Member member : members) {
						if(loginId.equals(member.loginId)) {
							isfoundMember = true;
						}
					}
					
					if(isfoundMember) {
						System.out.println("이미 사용중인 아이디입니다");
						isfoundMember = false;
						continue;
					}
					break;
				}
				
				while(true) {
					System.out.print("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					
					if(loginPw.length() == 0) {
						System.out.println("비밀번호를 입력해주세요");
						continue;
					}
					
					System.out.print("로그인 비밀번호 확인 : ");
					loginPwConfirm = sc.nextLine();
					
					if(!loginPw.equals(loginPwConfirm)) {
						System.out.println("비밀번호를 확인해주세요");
						continue;
					}
					
					break;
				}
				
				while(true) {
					System.out.print("이름 : ");
					name = sc.nextLine();
					
					if(loginPw.length() == 0) {
						System.out.println("이름을 입력해주세요");
						continue;
					}
					
					break;
				}
				
				int id = ++lastMemberId;
				Member member = new Member(id,loginId,loginPw,name);
				
				members.add(member);
				
				System.out.println(member.id+"번 회원이 가입되었습니다");
			}
			
			else if(cmd.equals("member login")) {
				
				if(loginMember != null) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				
				String loginId = null;
				String loginPw = null;
				Member foundMember = null;
				
				System.out.print("로그인 아이디 : ");
				loginId = sc.nextLine();
				
				System.out.print("로그인 비밀번호 : ");
				loginPw = sc.nextLine();
				
				for(Member member : members) {
					if(member.loginId.equals(loginId)) {
						foundMember = member;
					}
				}
				
				if(foundMember == null) {
					System.out.println("일치하는 회원이 없습니다");
					continue;
				}
				
				if(!foundMember.loginPw.equals(loginPw)) {
					System.out.println("비밀번호가 일치하지 않습니다");
					continue;
				}
				
				loginMember = foundMember;
				System.out.println("로그인 성공! "+foundMember.name+"님 반갑습니다");
			}
			
			else if(cmd.equals("member logout")) {
				
				if(loginMember == null) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				
				loginMember = null;
				System.out.println("로그아웃 되었습니다");
			}
			
			else if(cmd.equals("article write")) {
				
				if(loginMember == null) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				int id = ++lastArticleId;
				Article article = new Article(id, title, body, 0, loginMember.id);
				
				articles.add(article);
				
				System.out.println("4번글이 생성되었습니다");
			}
			
			else if(cmd.equals("article list")) {
				
				String writerName = null;
				
				System.out.println("번호  //  제목   //  조회    //  작성자");
				for(int i=articles.size()-1;i>=0;i--) {
					Article article = articles.get(i); 
					for(Member member : members) {
						if(member.id == article.memberId) {
							writerName = member.name;
							System.out.printf("%d   //  %s   //  %d   //  %s\n",article.id,article.title,article.views,member.name);
									
						}
					}
				}
			}
			
			else if(cmd.startsWith("article detail")) {
				
				String[] splitCmd = cmd.split(" ");
				
				if(splitCmd.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				
				int id = (int) Integer.parseInt(splitCmd[2]);
				
				Article foundArticle = null;
				String writerName = null;
				
				for(Article article : articles ) {
					if(id == article.id) {
						foundArticle = article;
					}
				}
				
				for(Member member : members) {
					if(member.id == foundArticle.memberId) {
						writerName = member.name;
					}
				}
				
				foundArticle.views++;
				System.out.println("번호 : "+foundArticle.id);
				System.out.println("작성날짜 : "+foundArticle.regDate);
				System.out.println("수정날짜 : "+foundArticle.updateDate);
				System.out.println("작성자 : "+writerName);
				System.out.println("제목 : "+foundArticle.title);
				System.out.println("내용 : "+foundArticle.body);
				System.out.println("조회 : "+foundArticle.views);
				
			}
			
			else if(cmd.startsWith("article modify")) {
				
				if(loginMember == null) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				
				String[] splitCmd = cmd.split(" ");
				
				if(splitCmd.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				
				int id = (int) Integer.parseInt(splitCmd[2]);
				
				Article foundArticle = null;
				String writerName = null;
				
				for(Article article : articles ) {
					if(id == article.id) {
						foundArticle = article;
					}
				}
				
				if(foundArticle.memberId != loginMember.id) {
					System.out.println("권한이 없습니다");
					continue;
				}
				
				System.out.print("제목 : ");
				foundArticle.title = sc.nextLine();
				
				System.out.print("내용 : ");
				foundArticle.body = sc.nextLine();
				
				foundArticle.updateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
				System.out.println(foundArticle.id+"번 글이 수정되었습니다");
			}
			
			else if(cmd.startsWith("article delete")) {
				
				if(loginMember == null) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				
				String[] splitCmd = cmd.split(" ");
				
				if(splitCmd.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				
				int id = (int) Integer.parseInt(splitCmd[2]);
				
				Article foundArticle = null;
				String writerName = null;
				
				for(Article article : articles ) {
					if(id == article.id) {
						foundArticle = article;
					}
				}
				
				if(foundArticle.memberId != loginMember.id) {
					System.out.println("권한이 없습니다");
					continue;
				}
				
				articles.remove(foundArticle);
				System.out.println(foundArticle.id+"번 글이 삭제되었습니다");
			}
			else System.out.println("존재하지 않는 명령어입니다");
		}
	}

}
