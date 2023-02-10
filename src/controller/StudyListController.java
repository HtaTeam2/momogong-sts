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
@RequestMapping("camstudy")
public class StudyListController {
	
	@Autowired
	public StudyMembersDAO memdao;
	@Autowired
	public StudyListDAO listdao;
	@Autowired
	public StudyGroupDAO groupdao;
	@Autowired
	public NoticeDAO notdao;
	@Autowired
	public CommunityDAO comdao;
	
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model sessionData, StudyListsDTO sdto) throws SQLException {
		System.out.println("insert()메소드 호출 확인~^^");
		listdao.createStdList(sdto);
		sessionData.addAttribute("sdto", sdto);
		
		//가입 후엔 Group원 추가로 넘겨줌 - 그룹원 가입 url = /insert
		return "/StdGroup/insert";
	}
	
	
	
}
