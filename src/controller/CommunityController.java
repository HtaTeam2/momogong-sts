package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import model.CommunityDAO;
import model.RecommendDAO;
import model.domain.CommunityDTO;
import model.domain.RecommendDTO;

@Controller
@RequestMapping("Community")
public class CommunityController {

	@Autowired
	public CommunityDAO comdao;

	@Autowired
	public RecommendDAO recommdao;

	// 글쓰기화면
	@RequestMapping(value = "/writeform", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String writeform() {
		return "forward:/comm/write.jsp";
	}

	// 입력
	@RequestMapping(value = "/write", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String write(Model model, @ModelAttribute CommunityDTO dto) throws Exception {
		if (dto.getComContent() == null || dto.getComContent().trim().length() == 0 || dto.getComPw() == null
				|| dto.getComPw().trim().length() == 0 || dto.getComTitle() == null
				|| dto.getComTitle().trim().length() == 0 || dto.getSubject() == null) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
		CommunityDTO dto2 = comdao.write(dto);
		model.addAttribute("dto", dto2);

		return "redirect:/Community/view/" + dto2.getComNo(); // 상세 글 페이지로 이동
	}

	// 읽기(추천기능 추가하기 전 연결했던 코드..)
	@RequestMapping(value = "/view/{comNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String view(Model model, @PathVariable long comNo) throws SQLException {

		CommunityDTO dto = comdao.view(comNo, true);

		if (dto == null) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		} else {
			model.addAttribute("dto", dto);
		}
		return "forward:/comm/read.jsp";
	}

	// 읽기(추천기능 추가하여 이렇게 수정하였지만.....결국 추천기능 구현 실패해서 쓸데없어짐 일단 여기로 연결은 해놓음)
	@RequestMapping(value = "/recommview/{comNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String recommview(Model model, @PathVariable long comNo) throws SQLException {
		CommunityDTO dto = comdao.view(comNo, true);
		RecommendDTO rdto = new RecommendDTO();

		if (dto == null) {
			throw new RuntimeException("게시물이 존재하지 않습니다.");
		} else {
			rdto.setCommid(dto.getComNo());
			rdto.setMemid(dto.getMemberid());
			model.addAttribute("recommcheck", recommdao.check(dto.getComNo(), dto.getMemberid()));// 추천상태=1, 추천안한상태=0
			model.addAttribute("dto", dto);
		}
		return "forward:/comm/read.jsp";
	}

	// 목록 보기
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String list(Model model) throws Exception {
		System.out.println("list()------------");

		model.addAttribute("list", comdao.list());
		return "forward:/comm/allView.jsp";
	}

	// 수정화면(read.jsp에서 수정버튼 클릭시 실행되는 로직)
	@RequestMapping(value = "/updateform", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String updateForm(Model model, @RequestParam("comNo") long comNo, @RequestParam("comPw") String comPw)
			throws SQLException {
		System.out.println("updateForm()---------" + comNo);

		if (comNo == 0 || comPw == null || comPw.trim().length() == 0) { // 입력 덜했을때
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		} else {// 일단입력은했을때
			CommunityDTO dto = comdao.view(comNo, false);

			if (dto.getComPw().equals(comPw)) {
				model.addAttribute("dto", dto);
			} else {
				throw new RuntimeException("비밀번호가 틀렸습니다.");
			}
		}
		return "forward:/comm/update.jsp";
	}

	// 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String update(Model model, @ModelAttribute CommunityDTO dto) throws SQLException {
		if (dto.getComContent() == null || dto.getComContent().trim().length() == 0 || dto.getComPw() == null
				|| dto.getComPw().trim().length() == 0 || dto.getComTitle() == null
				|| dto.getComTitle().trim().length() == 0 || dto.getSubject() == null) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
		boolean result = comdao.update(dto);
		if (result) {
			model.addAttribute("comNo", dto.getComNo());
		} else {
			throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 틀렸습니다.");
		}

		return "redirect:view/" + dto.getComNo();

	}

	// 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String delete(@RequestParam("comNo") long comNo, @RequestParam("comPw") String comPw) throws Exception {
		if (comNo == 0 || comPw == null || comPw.trim().length() == 0) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
		boolean result = comdao.delete(comNo, comPw);
		if (!result) {
			throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 틀렸습니다.");
		}
		return "redirect:list";
	}

	// 검색
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String search(Model model, @RequestParam String searchType, @RequestParam String searchText)
			throws Exception {

		if (searchText == null || searchText.trim().length() == 0) {
			throw new RuntimeException("검색어를 입력하세요.");
		}

		List<CommunityDTO> list = comdao.search(searchType, searchText);
		model.addAttribute("list", list);
		return "forward:/comm/allView.jsp";
	}

	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(Exception.class)
	public String totalEx(Exception e, HttpServletRequest req) {
		e.printStackTrace(); // 개발자 관점에서 필요한 정보, 서버 콘솔창에만 출력

		req.setAttribute("errorMsg", e.getMessage());
		return "forward:/comm/error.jsp";
	}

	// 이미지파일 업로드
	@RequestMapping(value = "fileUpload.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fileUpload(HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest multiFile)
			throws Exception {
		// Json 객체 생성
		JsonObject json = new JsonObject();
		// Json 객체 출력하기 위해 PrintWriter 생성
		PrintWriter printWriter = null;
		OutputStream out = null;
		// 파일을 가져오기 위해 MultipartHttpServletRequest의 getFile 메서드 사용
		MultipartFile file = multiFile.getFile("upload");
		// 파일이 비어있지 않고(비어있으면 null반환)
		if (file != null) {
			// 파일 사이즈가 0보다 크고, 파일이름이 공백이 아닐때
			if (file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if (file.getContentType().toLowerCase().startsWith("image/")) {
					try {
						// 파일 이름
						String fileName = file.getName();
						// 파일 내용
						byte[] bytes = file.getBytes();
						// 파일이 실제 저장되는 경로(/team2_studyroom/img)
						String uploadPath = req.getServletContext().getRealPath("/img");
						// 저장되는 파일에 경로 설정
						File uploadFile = new File(uploadPath);
						if (!uploadFile.exists()) {
							uploadFile.mkdirs();
						}
						// UUID : 사용고유성이 보장되는 id. 파일명 중복방지 랜덤생성
						fileName = UUID.randomUUID().toString();
						// 업로드 경로+파일이름을 줘서 데이터를 서버에 전송
						uploadPath = uploadPath + "/" + fileName;
						out = new FileOutputStream(new File(uploadPath));
						out.write(bytes);

						// 클라이언트에 추가
						printWriter = res.getWriter();
						res.setContentType("text/html");

						// 파일 연결되는 url 주소 설정
						String fileUrl = req.getContextPath() + "/img/" + fileName;
						// 생성된 json 객체를 이용해 파일 업로드+이름+주소를 ckEditor에 전송
						json.addProperty("uploaded", 1);
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);

						printWriter.println(json);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (out != null) {
							out.close();
						}
						if (printWriter != null) {
							printWriter.close();
						}
					}
				}
			}
		}
		return null;
	}

}
