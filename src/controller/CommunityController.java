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

import model.CommunityDAO;
import model.domain.CommunityDTO;

@Controller
@RequestMapping("Community")
public class CommunityController {

	@Autowired
	public CommunityDAO comdao;

	//글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, @ModelAttribute CommunityDTO dto) throws SQLException{
		System.out.println("insert()----------");
		if(dto.getComContent() == null || dto.getComContent().trim().length() == 0 || dto.getComPw() == null || dto.getComPw().trim().length() == 0 || 
				dto.getComTitle() == null || dto.getComTitle().trim().length() == 0 || dto.getSubject() == null) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}

		comdao.write(dto);
		model.addAttribute("comNo", dto.getComNo());

		return "redirect:/read.jsp"; //상세 글 페이지로 이동
	}



	//읽기
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("comNo") int comNo) throws SQLException {
		System.out.println("view()------------"+comNo);

		CommunityDTO dto = comdao.view(comNo, true);

		if(dto == null) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		}else {
			model.addAttribute("dto", dto);
		}
		return "redirect:/read.jsp";
	}



	//목록 보기
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws SQLException{
		System.out.println("list()------------");

		model.addAttribute("list", comdao.list());
		return "redirect:/allView.jsp";
	}



	//수정화면(read.jsp에서 수정버튼 클릭시 실행되는 로직)
	@RequestMapping(value = "/updateform", method = RequestMethod.POST)
	public String updateForm(Model model, @RequestParam("comNo") int comNo) throws SQLException{
		System.out.println("updateForm()---------"+comNo);

		if(comNo == 0) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		}else {
			CommunityDTO dto = comdao.view(comNo, false);
			model.addAttribute("dto", dto);
		}
		return "update";
	}


	//수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute CommunityDTO dto) throws SQLException{
		System.out.println("update()---------");
		if(dto.getComContent() == null || dto.getComContent().trim().length() == 0 || dto.getComPw() == null || dto.getComPw().trim().length() == 0 || 
				dto.getComTitle() == null || dto.getComTitle().trim().length() == 0 || dto.getSubject() == null) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
		boolean result = comdao.update(dto);
		if(result) {
			model.addAttribute("comNo", dto.getComNo());
		}else {
			throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 틀렸습니다.");
		}

		return "redirect:/read.jsp"; //상세 글 페이지로 이동
	}
	
	
	
	//삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("comNo") int comNo, @RequestParam("comPw") String comPw) {
		System.out.println("delete()---------");
		if(comNo == 0 || comPw == null || comPw.trim().length() == 0) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
		boolean result = comdao.delete(comNo, comPw);
		if(!result) {
			throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 틀렸습니다.");
		}
		return "redirect:/list.jsp";
	}




	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(Exception.class)
	public String totalEx(Exception e, HttpServletRequest req) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace(); //개발자 관점에서 필요한 정보, 서버 콘솔창에만 출력

		req.setAttribute("errorMsg", e.getMessage());
		return "forward:/error.jsp"; 
	}


}
