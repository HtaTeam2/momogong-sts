package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
import model.domain.entity.StudyMembers;


@RestController
@RequestMapping("StdMembers")
@SessionAttributes({"dto", "id"})
public class StudyMembersController {

	@Autowired
	public StudyMembersDAO memdao;

	
	//로그인 화면으로 이동
	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public String login() {
		return "login.html";
	}
	
	//로그인
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(Model sessionData, @RequestParam("id") String id, @RequestParam("pw") String password) throws SQLException {
		
		boolean validate = memdao.loginMember(id, password);
		System.out.println("----"+validate);
		
		if(validate == true) { //로그인성공
			System.out.println("id확인 "+id);
			sessionData.addAttribute("id", id);  //세션에 데이터  저장
			return "redirect:/main.jsp"; //로그인 후 메인화면
		}else {
			return "redirect:/login.html"; //에러메시지..... => view단에서	
		}
		
	}
	
	//로그인 상태 확인
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, Model sessionData) {
		System.out.println("로그인 확인");
		String id = (String) sessionData.getAttribute("id");
		return (String) session.getAttribute("id");
		
	}
	
	//로그아웃 -  세션 삭제 후 로그인 전 메인화면으로 이동
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String login(SessionStatus sess) {
		
		System.out.println("로그 아웃  완료");
		sess.setComplete();
		sess = null;
		
		return "redirect:/main.html"; //로그인 전 메인화면			
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
	
	
	
	
	//회원가입 입력 폼 
	//http://localhost/team2_studyroom/StdMembers/insertview
	@GetMapping(value = "/insertview", produces = "application/json; charset=UTF-8")
	protected ModelAndView memInsertView() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		System.out.println("insert() -----");
		
		mv.setViewName("auth/join");   
		return mv;
	}
	
	//회원 정보 등록 후 정보 보기
	//http://localhost/team2_studyroom/StdMembers/insert
	@PostMapping(value = "/insert", produces = "application/json; charset=UTF-8")
	protected ModelAndView memInsert(Model sessionData, StudyMembers dto) throws SQLException {
		ModelAndView mv = new ModelAndView();
		System.out.println("insert() -----");

		StudyMembers newmem = memdao.insertMember(dto);
		
		sessionData.addAttribute("dto", dto);
		mv.setViewName("auth/viewOne"); 
		
		return mv;
	}
	
	//아이디 중복 체크 화면 띄우기
	//http://localhost/team2_studyroom/StdMembers/check
	@GetMapping(value = "/check", produces = "application/json; charset=UTF-8")
	protected ModelAndView idCheckView() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		System.out.println("check() -----");
		
		mv.setViewName("auth/idCheckForm");   
		return mv;
	}
	
	//아이디 중복 체크 확인 -- 에러
	//http://localhost/team2_studyroom/StdMembers/checkOk
	@GetMapping(value = "/checkOk", produces = "application/json; charset=UTF-8")
	protected ModelAndView idCheck() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		System.out.println("checkOk() -----");
		
		mv.setViewName("auth/idCheckProc");   
		return mv;
	}
	
	
	//프로필 수정 
	@RequestMapping(value = "/updatepage", method = RequestMethod.GET)
	public ModelAndView updatePage(String id) throws SQLException {

		ModelAndView mv = new ModelAndView();

		mv.addObject("allData", memdao.getMember(id));
		mv.setViewName("auth/update");

		return mv;

	}
	

	
	//탈퇴 --
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String deleteId) throws SQLException {
		
		memdao.delete(deleteId);
		
		return "redirect:/auth/deleteSuccess"; 
	}

	
	//로그인 후 조회
	//http://localhost/team2_studyroom/WEB-INF/auth/viewOne.jsp
	@RequestMapping(value = "/viewOne2", method = RequestMethod.GET)
	public ModelAndView viewOne(String id) throws SQLException {

		ModelAndView mv = new ModelAndView();

		mv.addObject("allData", memdao.getMember(id));
		mv.setViewName("viewOne2");

		return mv;

	}
	
	
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler
	public String totalEx(SQLException e) {  // 예외중 SQLException 만 처리 하는 핸들러 메소드
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:error.jsp";
	}

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	//http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "forward:/error.jsp";
	}

}
