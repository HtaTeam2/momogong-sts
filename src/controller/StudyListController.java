package controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.StudyListDAO;
import model.domain.entity.StudyLists;

@Controller
@RequestMapping("StdList")
@SessionAttributes({ "id" })
public class StudyListController {

	@Autowired
	public StudyListDAO listdao;

	// 스터디 생성
	@RequestMapping(value = "/insertList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String insert(Model model, @ModelAttribute("id") String hostId, @RequestParam("roomTitle") String roomTitle,
			@RequestParam("category") String category, @RequestParam("roomDesc") String roomDesc,
			@RequestParam("maxMem") int maxMem, @RequestParam("roomPw") String roomPw) throws Exception {
		String host = listdao.hostCheck(hostId);
		if (host != null) {
			model.addAttribute("errorMsg", "더 이상 스터디를 생성할 수 없습니다.");
			return "lists/error"; // WEB-INF/lists/error.jsp
		}

		StudyLists lists = listdao.insertList(roomTitle, hostId, category, roomDesc, maxMem, roomPw);
		model.addAttribute("sdto", lists);

		// 가입 후엔 Group원 추가로 넘겨줌 - 그룹원 가입 url = /insert
		return "forward:/StdGroup/hostJoin/" + lists.getRoomNo();
	}

	// 스터디 삭제
	@RequestMapping(value = "/deleteList/{roomNo}", method = RequestMethod.POST)
	public String delete(@PathVariable("roomNo") long roomNo) throws Exception {
		System.out.println("delete()메소드 호출 확인");

		listdao.deleteList(roomNo);

		return "redirect:/group/deleteSucc.jsp"; // 삭제 후 성공페이지로 넘어감
	}

	// 스터디 정보 수정
	@RequestMapping(value = "/updatelist", method = RequestMethod.POST)
	public String update(Model model, @RequestParam("roomNo") long roomNo, @RequestParam("roomTitle") String roomTitle,
			@RequestParam("roomDesc") String roomDesc, @RequestParam("roomPw") String roomPw,
			@RequestParam("category") String category) throws Exception {

		boolean result = false;

		System.out.println("update() 확인----" + roomTitle);
		// 리턴값 boolean으로 수정
		result = listdao.updateList(roomNo, roomTitle, roomDesc, roomPw, category);

		if (result == false) {
			model.addAttribute("errorMsg", "업데이트에 실패했습니다. 잠시 후 다시 시도해주십시오.");
			return "group/error"; // WEB-INF/group/error.jsp
		}

		return "lists/updatesuccessView"; // WEB-INF/lists/updatesuccessView.jsp

	}

	// 스터디 수정버튼 후 아이디 체크
	@RequestMapping(value = "/updatecheck", method = RequestMethod.POST)
	public String updateCheck(Model model, long roomNo, @ModelAttribute("id") String id) throws Exception {

		System.out.println("updateCheck() 확인----" + roomNo);
		// 리턴값 boolean으로 수정해서 true면 수정 아니면 에러페이지 가도록
		StudyLists lists = listdao.updateCheck(roomNo, id);

		if (lists != null) {
			model.addAttribute("lists", lists);
			return "forward:/lists/stdListUpdate.jsp";
		}
		model.addAttribute("errorMsg", "해당 방의 수정 권한이 없습니다.");
		return "group/error"; // WEB-INF/group/error.jsp
	}

	// 스터디 하나 정보보기
	@RequestMapping(value = "/oneRoom/{roomNo}", method = RequestMethod.GET)
	public String oneRoom(Model model, @PathVariable("roomNo") long roomNo) throws Exception {

		System.out.println("oneRoom() 확인----" + roomNo);

		StudyLists lists = listdao.oneRoom(roomNo);

		if (lists != null) {
			model.addAttribute("lists", lists);
			return "lists/roomView";
		}
		model.addAttribute("errorMsg", "해당 방이 존재하지 않습니다.");
		return "group/error"; // WEB-INF/group/error.jsp
	}

	// 리스트
	@RequestMapping(value = "/allList", method = RequestMethod.GET)
	public String allList(Model model) throws Exception {
		System.out.println("allList() 확인----");
		try {
			List<StudyLists> rlist = listdao.allList();
			// 배열이 비어있으면 String으로 예외던지기
			if (rlist.isEmpty()) {
				throw new NullPointerException();
			}
			JSONObject room = null;
			JSONArray rooms = new JSONArray();
			for (int i = 0; i < rlist.size(); i++) {
				room = new JSONObject();
				// room.put("방번호", rlist.get(i).getRoomNo());
				room.put("roomTitle", rlist.get(i).getRoomTitle());
				room.put("roomNo", rlist.get(i).getRoomNo());
				// 후에 JSONArray에 담아서 json 배열로 만들기
				rooms.put(room);
			}
			// 방 리스트를 데이터에 담아줌
			System.out.println("컨트롤러: " + rooms);
			model.addAttribute("data", rooms);
		} catch (JSONException s) {
			System.out.println("JSONException");
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		} catch (NullPointerException ne) {
			System.out.println("JSONException");
			model.addAttribute("data", "검색된 방이 없습니다.");
			ne.printStackTrace();
		}

		return "main_res";
		// void, String, Object, ModelAndView 반환타입 : 데이터가 안넘아감
		// message: 'Request failed with status code 404', name: 'AxiosError', code:
		// 'ERR_BAD_REQUEST',
	}

	// 스터디 검색. Model에 JSONArray데이터 담은 후 res.jsp로 forward전송
	@RequestMapping(value = "search/{title}", method = RequestMethod.POST)
	public String serchRoom(Model model, @PathVariable String title) throws Exception {
		System.out.println("serchRoom()호출: " + title);
		try {
			List<StudyLists> rlist = listdao.serchRoom(title);
			// 배열이 비어있으면 String으로 예외던지기
			System.out.println(rlist);
			if (rlist.isEmpty()) {
				throw new NullPointerException();
			}
			JSONObject room = null;
			JSONArray rooms = new JSONArray();
			for (int i = 0; i < rlist.size(); i++) {
				room = new JSONObject();
				// room.put("방번호", rlist.get(i).getRoomNo());
				room.put("roomTitle", rlist.get(i).getRoomTitle());
				room.put("roomNo", rlist.get(i).getRoomNo());
				// 후에 JSONArray에 담아서 json 배열로 만들기
				rooms.put(room);
			}
			// 방 리스트를 데이터에 담아줌
			System.out.println("컨트롤러: " + rooms);
			model.addAttribute("data", rooms);
		} catch (JSONException s) {
			System.out.println("JSONException");
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		} catch (NullPointerException ne) {
			System.out.println("NullPointerException");
			model.addAttribute("data", "검색된 방이 없습니다.");
			ne.printStackTrace();
		}

		return "main_res";
		// void, String, Object, ModelAndView 반환타입 : 데이터가 안넘아감
		// message: 'Request failed with status code 404', name: 'AxiosError', code:
		// 'ERR_BAD_REQUEST',
	}

	// 예외처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(SQLException.class)
	public String tatalEx(SQLException e, HttpServletRequest req) { // 예외중 SQLException만 처리하는 핸들러 메소드
		System.out.println("예외처리전담");
		e.printStackTrace();

		req.setAttribute("errorMasg", e.getMessage());

		return "forward:/error.jsp"; // /가 없을 경우
	}

	@ExceptionHandler(NullPointerException.class)
	public String tatalEx2(NullPointerException e) { // 예외중 NullPointerException만 처리하는 핸들러 메소드
		System.out.println("예외처리전담");
		e.printStackTrace();
		return "forward:/error.jsp";
	}
}
