package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.NoticeDAO;
import model.domain.NoticeDTO;

@Controller
@RequestMapping("Notice")
public class NoticeController {

	@Autowired
	public NoticeDAO dao;

	
	// 글 등록
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String insert(Model sessionData,NoticeDTO ndto) throws SQLException  {
		dao.insert(ndto);
		sessionData.addAttribute("ndto", ndto);
		return "forward:/notice/view.jsp";
	}	
	
	// 글 삭제
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("noticeNo") Long noticeNo) throws SQLException {
		dao.delete(noticeNo);
		return "redirect:allView";  
	}	
	
	// 글 수정
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public String update(@RequestParam("noticeContent") String NoticeContent, 
					     @RequestParam("NoticeTitle") String NoticeTitle,
					     @ModelAttribute("ndto") NoticeDTO ndto) throws SQLException{
		System.out.println("update() ---- " + ndto);
		
		ndto.setNoticeContent(NoticeContent);
		ndto.setNoticeTitle(NoticeTitle);	
			
		dao.update(ndto);	
				
		return "forward:/notice/updateSuccess.jsp";
	}
	
	// 목록 조회
	@RequestMapping(value="/allView", method = RequestMethod.GET)
	public ModelAndView getNotice() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
			
		mv.addObject("allData", dao.getNotice());
		mv.setViewName("list");
		
		return mv;
		
	}
	
	// 글 상세보기 ..... 모르겠어 ... 
//	@RequestMapping(value="/detail", method = RequestMethod.GET)
//	public String detail(){
//		
//		ModelAndView mv = new ModelAndView();
//			
//		mv.addObject("allData", dao.getNotice());
//		mv.setViewName("list");
		
		
		
	//}
	
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(SQLException.class)
	public String totalEx(SQLException e, HttpServletRequest req) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		
		req.setAttribute("errorMsg", e.getMessage());
		return "forward:/notice/error.jsp";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String totalEx2(NullPointerException e) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:/notice/error.jsp";
	}
	
}
	

