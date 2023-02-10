package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.CommunityDAO;
import model.NoticeDAO;
import model.StudyGroupDAO;
import model.StudyListDAO;
import model.StudyMembersDAO;
import model.domain.StudyMembersDTO;

@Controller
@RequestMapping("StdMembers")
@SessionAttributes({ "dto" })
public class StudyMembersController {

	@Autowired
	public StudyMembersDAO memdao;
	@Autowired
	public StudyListDAO listdao;
	@Autowired
	public StudyGroupDAO groupdao;
	@Autowired
	public NoticeDAO notdao;
	@Autowired
	public CommunityDAO comdao;

	//회원가입
	@PostMapping(value = "/insert", produces = "application/json; charset=UTF-8")
	protected String memInsert(Model sessionData, StudyMembersDTO dto) throws SQLException {

		System.out.println("insert() -----");

		memdao.insertMember(dto);
		sessionData.addAttribute("dto", dto);

		return "forward:/auth/join.jsp";
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
