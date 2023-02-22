package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.StudyGroupDAO;
import model.domain.MyStudyDTO;
import model.domain.StudyGroupMembersDTO;

@Controller
@RequestMapping("StdGroup")
@SessionAttributes({ "id" }) // 저장될 값은 id
public class StudyGroupController {

	@Autowired
	public StudyGroupDAO groupdao;

	// 스터디 개설시 호스트 그룹원 추가
	@PostMapping(value = "/hostJoin/{roomNo}", produces = "application/json; charset=UTF-8")
	public String updateGroup(@ModelAttribute("id") String joinId, @PathVariable("roomNo") long roomNo) throws SQLException, Exception {
		
		groupdao.hostJoin(joinId, roomNo);

		return "redirect:/group/joinSuccessView.jsp";
	}
	
	//해당 방 가입
	@GetMapping(value = "/insert/{roomNo}", produces = "application/json; charset=UTF-8")
	public String insertGroup(@ModelAttribute("id") String joinId, @PathVariable("roomNo") long roomNo) throws SQLException, Exception {
		
		groupdao.joinGroup(joinId, roomNo);

		return "redirect:/StdGroup/enterRoom/" + roomNo;
	}

	//방번호로 그룹원 조회 후 roomView로 입장
	@GetMapping(value = "/enterRoom/{roomNo}", produces = "application/json; charset=UTF-8")
	public ModelAndView enterRoom(@PathVariable("roomNo") long roomNo) throws SQLException, Exception {
		ModelAndView mv = new ModelAndView();
		
		ArrayList<StudyGroupMembersDTO> allGroup = groupdao.getCustomers1(roomNo);
		System.out.println(allGroup);
		
		mv.addObject("allGroup", allGroup);
		mv.setViewName("group/roomView"); // WEB-INF/group/roomView.jsp

		return mv;
	}

	// 그룹 탈퇴(방장인 경우, 비방장인 경우).
	@PostMapping(value = "/delete/{roomNo}", produces = "application/json; charset=UTF-8")
	public String deleteGroup(@ModelAttribute("id") String deleteId, @PathVariable("roomNo") long roomNo) throws SQLException, Exception {
		
		int result = groupdao.delete(deleteId, roomNo);

		if (result == 1) { // 방 관리자 => list테이블에서 해당 방 번호 삭제
			return "forward:/StdList/deleteList/" + roomNo;
		}
		
		return "redirect:/group/deleteSucc.jsp";
	}

	// 내 스터디 클릭시 로그인한 회원의 방가입정보 나옴
	@GetMapping(value = "/mystudy", produces = "application/json; charset=UTF-8")
	public ModelAndView getMyStudy(Model sessionData, @ModelAttribute("id") String id) throws SQLException, Exception {
		System.out.println("getMyStudy " + id);
		ModelAndView mv = new ModelAndView();
		ArrayList<MyStudyDTO> all = groupdao.getMyStudy(id);
		System.out.println(all);
		mv.addObject("allData", all);
		mv.setViewName("/group/myStudy"); // WEB-INF/group/myStudy.jsp
		return mv;

	}

	// 예외처리
	@ExceptionHandler
	public String totalSQLEx(SQLException s, HttpServletRequest request) {
		request.setAttribute("errorMsg", s.getMessage());
		s.printStackTrace();
		return "group/error";
	}

	@ExceptionHandler
	public String totalEx(Exception e, HttpServletRequest request) {
		request.setAttribute("errorMsg", e.getMessage());
		e.printStackTrace();
		return "group/error";
	}

	// HttpSessionRequiredException
	@ExceptionHandler
	public String totalSession(HttpSessionRequiredException e, HttpServletRequest request) {
		System.out.println("예외처리 전담 Exception");
		request.setAttribute("errorMsg", "로그인 후 이용하실 수 있습니다.");
		e.printStackTrace();
		return "group/error";
	}

}
