package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.StudyGroupDAO;
import model.domain.MyStudyDTO;
import model.domain.StudyGroupMembersDTO;

@Controller
@RequestMapping("StdGroup")
@SessionAttributes({"id"}) //저장될 값은 id
public class StudyGroupController {
	
	@Autowired
	public StudyGroupDAO groupdao;
	
	//List나 검색결과에 특정방 클릭하는 순간 해당방에 가입 . 필요한 값 roomNo, 세션id
	//@RequestParam getParameter("roomNo")
	//방에 가입 -> 방번호만 넘겨서 해당 방 멤버(group) 전체 조회(ArrayList) -> 방 전체조회 후 뷰로 넘기기 
	@GetMapping(value = "/insert/{roomNo}", produces = "application/json; charset=UTF-8")
	public String insertGroup(@ModelAttribute("id") String joinId, @PathVariable("roomNo") long roomNo) throws SQLException, Exception{
		System.out.println(joinId + " insertGroup() 호출 "  + roomNo);
		groupdao.joinGroup(joinId, roomNo);
		System.out.println("가입완료");
		
		return "forward:/StdGroup/enterRoom/" + roomNo; 
	}
	
	//방번호로 그룹원 조회 후 roomView로 입장 
	@GetMapping(value = "/enterRoom/{roomNo}", produces = "application/json; charset=UTF-8")
	public ModelAndView enterRoom(@PathVariable("roomNo") long roomNo) throws SQLException, Exception{
		System.out.println("enterRoom" + roomNo);
		ModelAndView mv = new ModelAndView();
//		List allGroup = groupdao.getCustomers(roomNo);
		ArrayList<StudyGroupMembersDTO> allGroup = groupdao.getCustomers1(roomNo);
		System.out.println(allGroup.get(0).getNickname());
		mv.addObject("allGroup", allGroup);
		mv.setViewName("group/roomView"); //WEB-INF/group/roomView.jsp
		
		//채팅페이지 비동기로 연결..은 추후에
		//forward형식으로 넘어감
		return mv;
	}
	
	
	//그룹 탈퇴(방장인 경우, 비방장인 경우).
	@GetMapping(value = "/delete/{roomNo}", produces = "application/json; charset=UTF-8")
	public String deleteGroup(@ModelAttribute("id") String deleteId, @PathVariable("roomNo") long roomNo) throws SQLException, Exception{
		System.out.println("deleteGroup() " + deleteId);
		int result = groupdao.delete(deleteId, roomNo);
		
		if(result == 1) { //방 관리자 => list테이블에서 해당 방 번호 삭제
			return "StdList/list 방삭제 url";
		}
		//삭제 후에 삭제 성공페이지로 돌아감
		return "redirect:/group/deleteSucc.jsp";
	}
	
	//내 스터디 클릭시 로그인한 회원의 방가입정보 나옴
	@GetMapping(value = "/mystudy", produces = "application/json; charset=UTF-8")
	public ModelAndView getMyStudy(Model sessionData, @ModelAttribute("id") String id) throws SQLException{
		System.out.println("getMyStudy " + id);
		ModelAndView mv = new ModelAndView();
		ArrayList<MyStudyDTO> all = groupdao.getMyStudy(id);
		System.out.println(all);
		mv.addObject("allData", all);
		mv.setViewName("/group/myStudy"); //WEB-INF/group/myStudy.jsp
		return mv;
		
	}
	
	
	//예외처리
	@ExceptionHandler
	public String totalSQLEx(SQLException s, HttpServletRequest request) {
		System.out.println("예외처리 전담 SQLException");
		request.setAttribute("errorMsg", s.getMessage());
		s.printStackTrace();
		return "group/error";
	}
	
	@ExceptionHandler
	public String totalEx(Exception e, HttpServletRequest request) {
		System.out.println("예외처리 전담 Exception");
		request.setAttribute("errorMsg", e.getMessage());
		e.printStackTrace();
		return "group/error";
	}
	
}
