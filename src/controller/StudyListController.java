package controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.CommunityDAO;
import model.NoticeDAO;
import model.StudyGroupDAO;
import model.StudyListDAO;
import model.StudyMembersDAO;
import model.domain.StudyListsDTO;

@Controller
@RequestMapping("StdList")
public class StudyListController {
	
	@Autowired
	public StudyListDAO listdao;


	

	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model sessionData, StudyListsDTO sdto) throws SQLException {
		System.out.println("insert()메소드 호출 확인~^^");
		listdao.createStdList(sdto);
		sessionData.addAttribute("sdto", sdto);
		
		//가입 후엔 Group원 추가로 넘겨줌 - 그룹원 가입 url = /insert
		return "/StdGroup/insert";
	}
	
	//스터디 검색. Model에 JSONArray데이터 담은 후 res.jsp로 forward전송
	@RequestMapping(value = "search/{title}", method = RequestMethod.POST )
	public String serchRoom(Model model, @PathVariable String title) throws Exception {
		System.out.println("serchRoom()호출: " + title);
		try {
			List<StudyLists> rlist = StudyListDAO.serchRoom(title);
			//배열이 비어있으면 String으로 예외던지기
			System.out.println(rlist);
			if(rlist.isEmpty()) { 
				throw new NullPointerException();
			}
			JSONObject room = null;
			JSONArray rooms = new JSONArray();
			for(int i = 0; i < rlist.size(); i++) {
				room = new JSONObject();
				//room.put("방번호", rlist.get(i).getRoomNo()); 
				room.put("roomTitle", rlist.get(i).getRoomTitle()); 
				room.put("roomNo", rlist.get(i).getRoomNo());
				//후에 JSONArray에 담아서 json 배열로 만들기
				rooms.put(room);
			}
			//방 리스트를 데이터에 담아줌
			System.out.println("컨트롤러: " + rooms);
			model.addAttribute("data", rooms);
		}catch(JSONException s) {
			System.out.println("JSONException");
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		}catch(NullPointerException ne) { 
			System.out.println("JSONException");
			model.addAttribute("data", "검색된 방이 없습니다.");
			ne.printStackTrace();
		}
		return "list/main_res"; 
		//void, String, Object, ModelAndView 반환타입 : 데이터가 안넘아감
		//message: 'Request failed with status code 404', name: 'AxiosError', code: 'ERR_BAD_REQUEST', 
	}
}

