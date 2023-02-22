package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import model.StudyMembersDAO;
import model.domain.StudyMembersDTO;
import model.domain.entity.StudyMembers;

@Controller
@RequestMapping("StdMembers")
@SessionAttributes({ "dto", "id" })
public class StudyMembersController {

	@Autowired
	public StudyMembersDAO memdao;

	// 로그인 화면으로 이동
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "login.jsp";
	}

	// 로그인
	// 세션에 로그인 한 회원의 전체 정보 데이터 저장하도록 수정필요 ->나중에 등급권한 설정..
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model sessionData, @RequestParam("id") String id, @RequestParam("password") String password)
			throws SQLException {

		boolean validate = memdao.loginMember(id, password);
		System.out.println("----" + validate);

		if (validate == true) { // 로그인성공
			System.out.println("id확인 " + id);
			sessionData.addAttribute("id", id); // 세션에 데이터 저장

			return "redirect:/main.jsp"; // 로그인 후 메인화면
		} else {
			return "redirect:/loginError.jsp"; // 에러메시지 창 띄우는걸로 수정하기
		}
	}

	// 로그인 상태 확인
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, Model sessionData) {

		System.out.println("로그인 확인");
		String id = (String) sessionData.getAttribute("id");
		return (String) session.getAttribute("id");
	}

	// 로그아웃 - 세션 삭제 후 로그인 전 메인화면으로 이동
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(SessionStatus sess) {

		System.out.println("로그 아웃  완료");
		sess.setComplete();
		sess = null;

		return "redirect:/main.html"; // 로그인 전 메인화면
	}

	// id 찾기 페이지 이동
	@RequestMapping(value = "/findIdForm", method = RequestMethod.GET)
	public String findIdForm() {
		return "auth/findId";
	}

	// id 찾기
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public ModelAndView findId(Model model, @RequestParam("email") String email, StudyMembersDTO dto)
			throws SQLException {

		ModelAndView mv = new ModelAndView();
		try {
			dto.setEmail(email);
			StudyMembers findMemId = memdao.findId(email);
			model.addAttribute("findMemId", findMemId);

			mv.setViewName("auth/findIdResult");
//			System.out.println(model.addAttribute("StudyMembers", findMemId));
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("msg", "오류가 발생되었습니다.");
		}

		return mv;
	}

	// pwd 찾기 페이지 이동
	@RequestMapping(value = "/findPwdForm", method = RequestMethod.GET)
	public String findPwdForm() {
		return "auth/findPwd";
	}

	// pwd 찾기
	@RequestMapping(value = "/findPwd", method = RequestMethod.POST)
	public ModelAndView findPwd(Model model, @RequestParam("id") String id, @RequestParam("email") String email,
			StudyMembersDTO dto) throws SQLException {

		ModelAndView mv = new ModelAndView();
		try {
			dto.setId(id);
			dto.setEmail(email);
			StudyMembers findMemPwd = memdao.findPwd(id, email);
			model.addAttribute("findMemPwd", findMemPwd);

			mv.setViewName("auth/findPwdResult");
//			System.out.println(model.addAttribute("StudyMembers", findPwd));
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("msg", "오류가 발생되었습니다.");
		}

		return mv;
	}

	// 회원가입 입력 폼
	// http://localhost/team2_studyroom/StdMembers/insertview
	@GetMapping(value = "/insertview", produces = "application/json; charset=UTF-8")
	protected ModelAndView memInsertView() throws SQLException {

		ModelAndView mv = new ModelAndView();
		System.out.println("insert() -----");

		mv.setViewName("auth/join");
		return mv;
	}

	// 회원 정보 등록 후 정보 보기
	// http://localhost/team2_studyroom/StdMembers/insert
	@PostMapping(value = "/insert", produces = "application/json; charset=UTF-8")
	protected ModelAndView memInsert(Model sessionData, StudyMembers dto) throws SQLException {
		ModelAndView mv = new ModelAndView();
		System.out.println("insert() -----");

		StudyMembers newmem = memdao.insertMember(dto);

		sessionData.addAttribute("dto", dto);
		mv.setViewName("auth/viewOne");

		return mv;
	}

	// 아이디 중복 체크 확인
	// http://localhost/team2_studyroom/StdMembers/checkOk?id=값
	@RequestMapping(value = "/checkOk", method = RequestMethod.GET)
	protected String idCheck(Model model, String id) throws Exception {
		System.out.println(id);

		boolean result = memdao.duplecateID(id);

		model.addAttribute("data", result);
		
		System.out.println("checkOk() -----" + result);

		return "main_res";	//WEB-INF/main_res.jsp
	}

	// 프로필 수정 페이지 이동
	@RequestMapping(value = "/updatepage", method = RequestMethod.GET)
	public ModelAndView updatePage(String id) throws SQLException {

		ModelAndView mv = new ModelAndView();

		mv.addObject("allData", memdao.getMember(id));
		mv.setViewName("auth/update");

		return mv;
	}

	// 프로필 수정 기능
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("id") String id, @RequestParam("nickname") String nickname,
			@RequestParam("email") String email, @RequestParam("password") String pw, @RequestParam("goal") String goal)
			throws SQLException {

		System.out.println("update() ----- " + id);

		memdao.memUpdate(id, email, goal, nickname, pw);

		return "auth/updateSuccess";
	}

	// 탈퇴 & 1명 회원 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String deleteId) throws SQLException {

		memdao.delete(deleteId);

		return "auth/deleteSuccess";
	}

	// 로그인 후 조회
	// http://localhost/team2_studyroom/WEB-INF/auth/viewOne.jsp
	@RequestMapping(value = "/viewOne2", method = RequestMethod.GET)
	public ModelAndView viewOne(@ModelAttribute("id") String id) throws SQLException {
		ModelAndView mv = new ModelAndView();
		StudyMembers members = memdao.getMember(id);
		System.out.println(members);
		mv.addObject("allData", members);
		mv.setViewName("auth/viewOne2");

		return mv;
	}

	// 관리자 화면으로 이동
	@RequestMapping(value = "/adPage", method = RequestMethod.GET)
	public String adMain() {
		return "auth/adPage";
	}

	// 관리자 - 전체회원조회
	// http://localhost:8080/team2_studyroom/StdMembers/allView
	// spring 코드로 변환 url - allView
	@RequestMapping(value = "/adAllView", method = RequestMethod.GET)
	public ModelAndView getMembers() throws SQLException {

		ModelAndView mv = new ModelAndView();

		mv.addObject("allData", memdao.getMembers());
		mv.setViewName("auth/adAllView");

		return mv;
	}

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	// http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) {
		System.out.println("예외 처리 전담");
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "forward:/error.jsp";
	}

}