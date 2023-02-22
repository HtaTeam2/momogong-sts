package controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.NoticeDAO;
import model.domain.NoticeDTO;
import model.domain.entity.Notice;

@Controller
@RequestMapping("Notice")
@SessionAttributes({ "id" })
public class NoticeController {
	@Autowired
	public NoticeDAO notdao;

	// 글 등록 폼
	@GetMapping(value = "/insertview", produces = "application/json; charset=UTF-8")
	public String writeform() {
		return "forward:/notice/writeform.jsp";
	}

	// 글등록
	@PostMapping(value = "/insertNotice", produces = "application/json; charset=UTF-8")
	public String write(Model model, @ModelAttribute Notice ndto) throws Exception {

		Notice notice = notdao.insertNotice(ndto);
		model.addAttribute("ndto", notice);

		return "redirect:/Notice/list";
	}

	// 글 삭제
	@RequestMapping(value = "/deleteNotice/{noticeNo}", method = RequestMethod.POST)
	public String delete(@RequestParam("noticeNo") Long noticeNo) throws SQLException {
		notdao.deleteNotice(noticeNo);

		return "redirect:/Notice/list";
	}

	// 글 수정 (내용, 제목)
	@RequestMapping(value = "/updateNotice/{noticeNo}", method = RequestMethod.GET)
	public String update(Notice ndto, Model model) throws SQLException {
		if (ndto.getNoticeNo() == 0) {
			throw new RuntimeException("존재하지 않은 게시물 입니다.");
		} else {
			NoticeDTO notice = notdao.view(ndto.getNoticeNo(), false);
			model.addAttribute("ndto", notice);
		}

		return "forward:/notice/update.jsp";
	}

	// 수정 ui로
	@RequestMapping(value = "/update/{noticeNo}", method = RequestMethod.POST)
	public String update(Model model, Notice ndto) throws SQLException {

		notdao.updateNotice(ndto);

		return "redirect:/notice/updateSuccess.jsp";
	}

	// 전체 목록 조회

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String list(Model model) throws Exception {

		model.addAttribute("list", notdao.list());
		return "forward:/notice/view.jsp";
	}

	// 글 상세보기
	@RequestMapping(value = "/view/{noticeNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String view(Model model, @PathVariable long noticeNo) throws SQLException {
		NoticeDTO ndto = notdao.view(noticeNo, true);
		if (ndto == null) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		} else {
			model.addAttribute("dto", ndto);
		}
		return "forward:/notice/detail.jsp";
	}

	// 이미지 첨부

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler
	public String totalEx(SQLException e) {
		e.printStackTrace();
		return "forward:error.jsp";
	}

	@ExceptionHandler
	public String totalEx2(NullPointerException e) {
		e.printStackTrace();
		return "forward:error.jsp?";
	}

}