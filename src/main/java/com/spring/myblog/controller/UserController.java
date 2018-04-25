package com.spring.myblog.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myblog.domain.User;
import com.spring.myblog.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class UserController {
		
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, SessionStatus status) {
		logger.info("Welcome home! The client locale is {}.", locale);
		status.setComplete();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getUser(String userId, String userPw, HttpSession session)
	{
		System.out.println("userId = " + userId);
		System.out.println("userPw = " + userPw);
		//ModelAndView mav = new ModelAndView();
		//mav.setViewName("redirect:/");
		
		User loginUser = userService.login(userId, userPw); 
		if(loginUser != null)
		{
			System.out.println("notnull");
			session.setAttribute("loginUser", loginUser);
		}
		else
			System.out.println("null");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@RequestMapping(value="join")
	public String join(Model model)
	{
		User user = new User();
		model.addAttribute(new User());
		logger.info("ȸ������");
		
		return "join";
	}
	
	@RequestMapping(value="join_1")
	public String join_1(@ModelAttribute User user, HttpSession session)
	{
		logger.info("�����ϱ�1");
		System.out.println(session); 
		return "join2";
	}
	
	@PostMapping(value="user")
	public String join_2(@ModelAttribute User user, SessionStatus status)
	{
		logger.info(status.toString());
		userService.join(user); 
		logger.info("���ԿϷ�");
		// Session�� ��ϵ� user�� .setComplete()�� �����ϱ� �������� Sesiion ���ο� �����͸� �����ϰ� �ȴ�.
		// .setComplete()�� �����ϸ� Controller���� �����ص� SessionAttribute�� ��ϵ� form�� �ʱ�ȭ�ȴ�.
		status.setComplete();
		logger.info(status.toString());
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "userIdDuplicationCheck")
	@ResponseBody
	public String userIdDuplicationCheck(@ModelAttribute User user)
	{
		System.out.println(user.getUserId());
		//System.out.println(user.get("userId"));
		if(userService.userIdDuplicationCheck(user.getUserId()) == false || user.getUserId() == "")
		{
			System.out.println("0");
			return "0";
		}
		System.out.println("1111111");
		return "1";

	}
	
	@RequestMapping(value = "nickNameDuplicationCheck")
	@ResponseBody
	public String nickNameDuplicationCheck(@ModelAttribute User user)
	{
		System.out.println(user.getNickName());
		//System.out.println(user.get("userId"));
		if(userService.nickNameDuplicationCheck(user.getNickName()) == false || user.getNickName() == "")
		{
			System.out.println("0");
			return "0";
		}
		System.out.println("1111111");
		return "1";
	}
	
	@RequestMapping(value="profile")
	public String profle(Model model)
	{
		logger.info("�����ʼ���");
		model.addAttribute(new User());
		return "profile";
	}
	
	@RequestMapping(value = "profileSuccess")
	public String profileSuccess(@ModelAttribute User user, HttpSession session)
	{
		User updateUser = userService.get(user.getUserId());
		System.out.println("photo : " + user.getProfilePhoto());
		user.setUserPw(updateUser.getUserPw());
		userService.modify(user);
		session.setAttribute("loginUser", user);
//		System.out.println(updateUser.getUserName() + "  " + user.getUserName());
//		updateUser.setUserName(user.getUserName());
//		updateUser.setUserEmail(user.getUserEmail());
//		updateUser.setNickName(user.getNickName());
//		updateUser.setBlogName(user.getBlogName());
//		updateUser.setProfileIntro(user.getProfileIntro());
//		
		System.out.println(updateUser.getUserName() + "  " + user.getUserName());
		System.out.println(user.getUserId() + " ���� �Ϸ� ");

		return "redirect:/";
	}
	
	@RequestMapping(value = "blogSearch/{blogSearch}")
	@ResponseBody
	public List<User> blogSearch(@PathVariable("blogSearch") String blogSearch)
	{
		List<User> user = userService.blogSearch(blogSearch);
		System.out.println(user.size());
		return user;
	}
}
