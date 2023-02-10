package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.StudyGroupDAO;
import model.domain.StudyGroupMembersDTO;

@Controller
@RequestMapping("StdGroup")
@SessionAttributes({"roomNo", "id"})
public class StudyGroupController {
	
	@Autowired
	public StudyGroupDAO groupdao;
	
	//List에서 특정방 클릭하는 순간 해당방 List가 들어가야 함 
	@PostMapping(value = "/insert", produces = "application/json; charset=UTF-8")
	public ModelAndView insertGroup(Model sessionData, @ModelAttribute("id") String joinId, @RequestParam("roomNo") long roomNo) throws SQLException, Exception{
		ModelAndView mv = new ModelAndView();
		groupdao.joinGroup(joinId, roomNo);
		//방번호 저장해서 전체 그룹원 찾을 수 있도록 함
		mv.addObject("roomNo", roomNo);
		mv.setViewName("group/roomView"); //WEB-INF/group/roomView.jsp
		
		//채팅페이지 비동기로 연결
		//forward형식으로 넘어감
		return mv; // WEB-INF/group/roomView.jsp
	}
	
	//그룹원 조회 후 roomView로 입장
	@GetMapping(value = "/group", produces = "application/json; charset=UTF-8")
	public ModelAndView selectGroup(Model sessionData, @RequestParam("roomNo") long roomNo) throws SQLException, Exception{
		System.out.println("getMyStudy " + roomNo);
		ModelAndView mv = new ModelAndView();
		ArrayList<StudyGroupMembersDTO> allGroup = groupdao.getCustomers(roomNo);
		
		mv.addObject("allGroup", allGroup);
		mv.setViewName("group/roomView"); //WEB-INF/group/roomView.jsp
		
		//채팅페이지 비동기로 연결..은 추후에
		//forward형식으로 넘어감
		return mv;
	}
	
	
	//그룹원 삭제(방장인 경우, 비방장인 경우)
	@PostMapping(value = "/group", produces = "application/json; charset=UTF-8")
	public String deleteGroup(Model sessionData, @ModelAttribute("id") String deleteId, @RequestParam("roomNo") long roomNo) throws SQLException, Exception{
		System.out.println("deleteGroup() " + deleteId);
		int result = groupdao.delete(deleteId, roomNo);
		
		if(result == 1) { //방 관리자 => list테이블에서 해당 방 번호 삭제
		  	sessionData.addAttribute("roomNo", roomNo);
			return "StdList/lists테이블 방삭제url";
		}
		//삭제 후에는 main페이지로 돌아감
		return "redirect:로그인 후 main페이지.html";
	}
	
	//내 스터디 클릭시 로그인한 회원의 방가입정보 나옴
	@GetMapping(value = "/mystudy", produces = "application/json; charset=UTF-8")
	public ModelAndView getMyStudy(Model sessionData, @ModelAttribute("id") String id) throws SQLException{
		System.out.println("getMyStudy " + id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("allData", groupdao.getMyStudy(id));
		mv.setViewName("group/myStudy"); //WEB-INF/group/myStudy.jsp
		return mv;
		
	}
	
	
	//예외처리
	@ExceptionHandler
	public String totalSQLEx(SQLException s) {
		System.out.println("예외처리 전담");
		return "group/error";
	}
	
	@ExceptionHandler
	public String totalEx(Exception e) {
		System.out.println("예외처리 전담");
		return "group/error";
	}
	
}
