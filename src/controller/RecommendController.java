package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.RecommendDAO;
import model.domain.RecommendDTO;

@Controller
@RequestMapping("Recommend")
public class RecommendController {

	@Autowired
	public RecommendDAO recommdao;

	@RequestMapping(value = "/thup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String thup(Model model, @RequestParam Map<String, String> params) throws Exception {// @RequestBody long
																								// commid, @RequestParam
																								// String memid
//		if(commid == 0 || memid == null || memid.trim().length() == 0) {
//			throw new RuntimeException("잘못된 접근입니다. 새로고침 해주세요");
//		}
		RecommendDTO dto = recommdao.thup(Long.parseLong(params.get("commid")), params.get("memid"));
		model.addAttribute("dto", dto);

		return "redirect:/Community/view/" + dto.getCommid(); // 상세 글 페이지로 이동
	}

	// 추천취소
	@RequestMapping(value = "/thdown", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String thdown(Model model, @ModelAttribute long commid, @ModelAttribute String memid) throws Exception {
		if (commid == 0 || memid == null || memid.trim().length() == 0) {
			throw new RuntimeException("잘못된 접근입니다. 새로고침 해주세요");
		}
		RecommendDTO dto = recommdao.thdown(commid, memid);
		model.addAttribute("dto", dto);

		return "redirect:/Community/view/" + dto.getCommid(); // 상세 글 페이지로 이동
	}

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(Exception.class)
	public String totalEx(Exception e, HttpServletRequest req) {
		e.printStackTrace(); // 개발자 관점에서 필요한 정보, 서버 콘솔창에만 출력

		req.setAttribute("errorMsg", e.getMessage());
		return "forward:/comm/error.jsp";
	}

}
