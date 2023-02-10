package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.CommunityDAO;
import model.NoticeDAO;
import model.StudyGroupDAO;
import model.StudyListDAO;
import model.StudyMembersDAO;

@Controller
@RequestMapping("Comunnity")
public class CommunityController {
	
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
	
}
