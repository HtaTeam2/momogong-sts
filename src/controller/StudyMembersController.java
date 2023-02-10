package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import model.StudyMembersDAO;
import model.domain.StudyMembersDTO;


@RestController
@RequestMapping("StdMembers")
@SessionAttributes({"dto", "id"})
public class StudyMembersController {

	@Autowired
	public StudyMembersDAO memdao;


	//회원가입
	@PostMapping(value = "/insert", produces = "application/json; charset=UTF-8")
	protected String memInsert(Model sessionData, StudyMembersDTO dto) throws SQLException {

		System.out.println("insert() -----");

		memdao.insertMember(dto);
		sessionData.addAttribute("dto", dto);

		return "forward:/auth/join.jsp";
	}
	
	//로그인
	/* 1.유효한 user인 경우
	 * 	1-1.id가 admin(관리자)인 경우 
	 * 	- 세션에 id 데이터 저장
	 * 	- 관리자 메인(adLoginView)화면으로 redirect 이동
	 * 	1-2.일반 회원일 경우
	 * 	- 세션에 id 데이터 저장
	 * 	- 일반 로그인 후 메인(loginView)화면으로 redirect 이동
	 * 	
	 * 2.무효한 user라면 로그인 웹페이지로 이동 
	 * 
	 * http://localhost:8080/team2_studyroom/StdMembers/login
	 * 	- ?id=admin&pw=admin
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(Model sessionData, @RequestParam String id, @RequestParam String pw) throws SQLException {
		
		boolean validate = memdao.getMemberInfo(id, pw);
		//System.out.println("----"+validate);
		
		if(validate == true && id.equals("admin")) { //관리자
			//System.out.println("id확인 "+id);
			sessionData.addAttribute("id", id);  //세션에 데이터  저장
			return "redirect:/adLoginView.jsp";
		}else if(validate == true && !id.equals("admin")) {//관리자X = 회원
			//System.out.println("id확인 "+id);
			sessionData.addAttribute("id", id);  //세션에 데이터  저장
			return "redirect:/loginView.jsp";
		}else {
			return "redirect:/login.html";			
		}
		
	}
	
	//로그아웃 -  세션 삭제 후 login.html로 이동
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String login(SessionStatus sess) {
		
		System.out.println("로그 아웃  완료");
		sess.setComplete();
		sess = null;
		
		return "home.html";			
	}
	
	
	//전체회원조회
	//http://localhost:8080/team2_studyroom/StdMembers/allView
	//spring 코드로 변환 url - allView 
	@RequestMapping(value="/allView", method = RequestMethod.GET)
	public ModelAndView getStdMembers() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
			
		mv.addObject("allData", memdao.getMembers());
		mv.setViewName("auth/list");
		
		return mv;
		
	}
	
	
	//관리자 정보 수정(update)
	@RequestMapping(value = "/updateAd", method=RequestMethod.POST)
	public String updateAdmin(@RequestParam("password") String pw, 
					     @RequestParam("email") String email,
					     @ModelAttribute("dto") StudyMembersDTO dto) throws SQLException{
		System.out.println("update() ---- " + dto);
		
		dto.setPassword(pw);
		dto.setEmail(email);	
			
		memdao.update(dto);	
				
		return "forward:/auth/adUpdateSuccess.jsp";
	}
	
	
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler
	public String totalEx(SQLException e) {  // 예외중 SQLException 만 처리 하는 핸들러 메소드
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:error.jsp";
	}
	
	
	
//	@Autowired
//	public StudyListDAO listdao;
//	@Autowired
//	public StudyGroupDAO groupdao;
//	@Autowired
//	public NoticeDAO notdao;
//	@Autowired
//	public CommunityDAO comdao;
	
	@PostMapping(value = "/auth/register", produces = "application/json; charset=UTF-8")
	protected String deptInsert() {
		
//		memdao.insertMember();
		
		return "회원가입성공";
	}
	
	//탈퇴
	//http://localhost/team2_studyroom/WEB-INF/auth/deleteSuccess.jsp
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String deleteId) throws SQLException {
		memdao.delete(deleteId);
		return "redirect:auth/deleteSuccess"; 
	}
	
	//수정
	//http://localhost/team2_studyroom/WEB-INF/auth/update.jsp
	//http://localhost/team2_studyroom/WEB-INF/auth/updateSuccess.jsp
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("nickname") String nickname, @RequestParam("email") String email, @RequestParam("password") String pw, @RequestParam("goal") String goal,
			@ModelAttribute("dto") StudyMembersDTO dto) throws SQLException {

		System.out.println("update() ----- " + dto);
		
		dto.setNickname(nickname);
		dto.setEmail(email);
		dto.setPassword(pw);
		dto.setGoal(goal);
		

		memdao.update(dto);

		return "forward:/auth/updateSuccess.jsp";
	}
	
	//조회
	//http://localhost/team2_studyroom/WEB-INF/auth/viewOne.jsp
	@RequestMapping(value = "/viewOne", method = RequestMethod.GET)
	public ModelAndView viewOne(String id) throws SQLException {

		ModelAndView mv = new ModelAndView();

		mv.addObject("allData", memdao.getMember(id));
		mv.setViewName("viewOne");

		return mv;

	}

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	//http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "forward:/auth/error.jsp";
	}

	@ExceptionHandler
	public String totalEx2(NullPointerException e) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:/auth/error.jsp";
	}
}