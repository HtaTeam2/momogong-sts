package controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.NoticeDAO;
import model.domain.NoticeDTO;

@Controller
@RequestMapping("Notice")
public class NoticeController {

	@Autowired
	public NoticeDAO notdao;

	
	// 글 등록
	@RequestMapping(value = "/insertNotice", method=RequestMethod.POST)
	public String insert(Model sessionData,NoticeDTO ndto) throws SQLException  {
		System.out.println("insert()");
		notdao.insertNotice(ndto);
		sessionData.addAttribute("ndto", ndto);
		return "forward:/view.jsp";
	}	
	
	// 글 삭제
	@RequestMapping(value = "/deleteNotice", method=RequestMethod.GET)
	public String delete(@RequestParam("noticeNo") Long noticeNo) throws SQLException {
		notdao.deleteNotice(noticeNo);
		return "redirect:list";  
	}	
	
	
	// 글 수정 (내용, 제목)
	@RequestMapping(value = "/updateNotice", method=RequestMethod.POST)
	public String update(@RequestParam("noticeContent") String NoticeContent, 
					     @RequestParam("NoticeTitle") String NoticeTitle,
					     @ModelAttribute("ndto") NoticeDTO ndto) throws SQLException{
		System.out.println("update() ---- " + ndto);
		
		ndto.setNoticeContent(NoticeContent);
		ndto.setNoticeTitle(NoticeTitle);	
			
		notdao.updateNotice(ndto);	
				
		return "forward:/updateSuccess.jsp";
	}
	
	// 수정 ui로
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @RequestParam("noticeNo") Long noticeNo) throws SQLException{
		if(noticeNo == 0) {
			throw new RuntimeException("존재하지 않은 게시물 입니다.");
		}else {
			NoticeDTO ndto = notdao.view(noticeNo, false);
			model.addAttribute("dto", ndto);
		}
		return "updateNotice";
	}
	
	// 목록 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws SQLException{
		System.out.println("list()------------");

		model.addAttribute("list", notdao.list());
		return "redirect:/view.jsp";
	}

	

	// 글 상세보기
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("noticeNo") long noticeNo) throws SQLException {
		System.out.println("view()"+noticeNo);

		NoticeDTO ndto = notdao.view(noticeNo, true);

		if(ndto == null) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		}else {
			model.addAttribute("dto", ndto);
		}
		return "redirect:/detail.jsp";
	}
	
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler
	public String totalEx(SQLException e) {  
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:error.jsp";
	}
	
	@ExceptionHandler
	public String totalEx2(NullPointerException e) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:error.jsp?";
	}
	
}
	

