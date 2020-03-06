package hotel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comp9321.assign2.*;

public class UserHandler {

	public void send_User_Bean(HttpServletRequest request, HttpServletResponse response, String forward)
			throws Exception {
		CookieHandler CookieHandler = new CookieHandler();
		Cookie[] cookies = request.getCookies();
		CookieBean cookieBean = CookieHandler.CookieReader(cookies);
		User_Bean user_frofile = Factory_DAO.GetUserDAOInstance()
				.FindById(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(cookieBean.getUsername()));
		request.setAttribute("update_user", user_frofile);
		request.getRequestDispatcher(forward).forward(request, response);

	}

	public void displayprofile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CookieHandler CookieHandler = new CookieHandler();
		Cookie[] cookies = request.getCookies();
		CookieBean cookieBean = CookieHandler.CookieReader(cookies);
		HttpSession session = request.getSession();
		int user_id = 0;

		try {
			user_id = Factory_DAO.GetUserDAOInstance().FindId_ByUsername(cookieBean.getUsername());
		} catch (Exception e) {
			request.getRequestDispatcher("Error404.jsp").forward(request, response);
			e.printStackTrace();
		}

		try {

			User_Bean user_frofile = Factory_DAO.GetUserDAOInstance().FindById(user_id);

			System.out.println("ID:" + user_id + "\n" + "password:" + user_frofile.getPassword() + "\n");
			if (CookieHandler.CookieBeanCheck(cookieBean.getUsername(), cookieBean.getFlag())) {
				session.setAttribute("user_name", user_frofile.getUser_name());
				session.setAttribute("nick_name", user_frofile.getNick_name());
				session.setAttribute("email", user_frofile.getEmail());
				session.setAttribute("last_name", user_frofile.getLast_name());
				session.setAttribute("first_name", user_frofile.getFirst_name());
				session.setAttribute("full_address", user_frofile.getFull_address());
				session.setAttribute("credit_card", user_frofile.getCredit_card());
				// session.setAttribute("bean", uBean);
				hotel.CookieHandler.addCookie(user_frofile.getUser_name(), response);
				request.getRequestDispatcher("Profile.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("Error404.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	public void send_details(User_Bean user_frofile, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user_name", user_frofile.getUser_name());
		session.setAttribute("nick_name", user_frofile.getNick_name());
		session.setAttribute("email", user_frofile.getEmail());
		session.setAttribute("last_name", user_frofile.getLast_name());
		session.setAttribute("first_name", user_frofile.getFirst_name());
		session.setAttribute("full_address", user_frofile.getFull_address());
		session.setAttribute("credit_card", user_frofile.getCredit_card());
		// session.setAttribute("bean", uBean);
		CookieHandler.addCookie(user_frofile.getUser_name(), response);
		request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

	}

	public boolean user_state_check(String username) throws Exception {

		User_Bean user_frofile = Factory_DAO.GetUserDAOInstance()
				.FindById(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(username));

		if (user_frofile.getUser_status().equals("true")) {
			return true;
		}
		return false;
	}

	public void ready_to_update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CookieHandler signin = new CookieHandler();
		Cookie[] cookies = request.getCookies();
		CookieBean user_cookie = signin.CookieReader(cookies);
		HttpSession session = request.getSession();
		int user_id = 0;
		try {
			user_id = Factory_DAO.GetUserDAOInstance().FindId_ByUsername(user_cookie.getUsername());

			User_Bean user_frofile = Factory_DAO.GetUserDAOInstance().FindById(user_id);
			session.setAttribute("user_name", user_frofile.getUser_name());
			session.setAttribute("nick_name", user_frofile.getNick_name());
			session.setAttribute("email", user_frofile.getEmail());
			session.setAttribute("last_name", user_frofile.getLast_name());
			session.setAttribute("first_name", user_frofile.getFirst_name());
			session.setAttribute("full_address", user_frofile.getFull_address());
			session.setAttribute("credit_card", user_frofile.getCredit_card());
			session.setAttribute("password", user_frofile.getPassword());
			// session.setAttribute("bean", uBean);
			CookieHandler.addCookie(user_frofile.getUser_name(), response);
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int user_id = Factory_DAO.GetUserDAOInstance().FindId_ByUsername(request.getParameter("user_name"));

		User_Bean update_user = Factory_DAO.GetUserDAOInstance().FindById(user_id);
		if (!request.getParameter("password").equals(request.getParameter("password_confirmation"))) {
			request.setAttribute("PasswordError", "Passwords are different!");
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
		} else {
			update_user.setNick_name(request.getParameter("nick_name"));
			update_user.setFirst_name(request.getParameter("first_name"));
			update_user.setLast_name(request.getParameter("last_name"));

			update_user.setEmail(request.getParameter("email"));
			update_user.setFull_address(request.getParameter("full_address"));
			update_user.setCredit_card(request.getParameter("credit_card"));
			update_user.setPassword(request.getParameter("password"));
			Factory_DAO.GetUserDAOInstance().UpdateUser(update_user);
		}

	}

	public void updateState(String username) throws Exception {
		User_Bean update_user = Factory_DAO.GetUserDAOInstance()
				.FindById(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(username));

		update_user.setUser_status("true");
		Factory_DAO.GetUserDAOInstance().UpdateUser(update_user);

	}

	/* register */
	public void register(HttpServletRequest request) throws Exception {
		User_Bean reg_user = new User_Bean();
		reg_user.setUser_name(request.getParameter("user_name"));
		reg_user.setNick_name(request.getParameter("nick_name"));
		reg_user.setFirst_name(request.getParameter("first_name"));
		reg_user.setLast_name(request.getParameter("last_name"));
		reg_user.setEmail(request.getParameter("email"));
		reg_user.setFull_address(request.getParameter("full_address"));
		reg_user.setCredit_card(request.getParameter("credit_card"));
		reg_user.setPassword(request.getParameter("password"));
		reg_user.setUser_type(1);
		reg_user.setUser_status("false");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		reg_user.setReg_date(format.format(new Date()));
		reg_user.setUser_id(Factory_DAO.GetUserDAOInstance().UserList().size() + 1);
		Factory_DAO.GetUserDAOInstance().AddUser(reg_user);
	}

	// public void sendEmail() {
	//
	// program_package sendMa = new program_package();
	//
	// sendMa.send_mail(1, "q439336387@gmail.com", "username", "QWERTYUIOP");
	//
	// }

	// private UserDao userDao;
	//
	// public void processregister(String email) {
	// UserBean user = new UserBean();
	// // user.getUser_id()
	// // int as = 567;
	// user.setUser_id(user.getUser_id());
	// user.setUsername("xiaoming");
	// user.setPassword("324545");
	// user.setEmail(email);
	// user.setRegisterTime(new Date());
	// user.setStatus(0);
	// user.setValidateCode(MD5Util.encode2hex(email));
	//
	// userDao.save(user);
	//
	// StringBuffer sb = new
	// StringBuffer("鐐瑰嚮涓嬮潰閾炬帴婵�娲昏处鍙凤紝48灏忔椂鐢熸晥锛屽惁鍒欓噸鏂版敞鍐岃处鍙凤紝閾炬帴鍙兘浣跨敤涓�娆★紝璇峰敖蹇縺娲伙紒</br>");
	// sb.append("<a
	// href=\"http://localhost:8080/springmvc/user/register?action=activate&email=");
	// sb.append(email);
	// sb.append("&validateCode=");
	// sb.append(user.getValidateCode());
	// sb.append("\">http://localhost:8080/springmvc/user/register?action=activate&email=");
	// sb.append(email);
	// sb.append("&validateCode=");
	// sb.append(user.getValidateCode());
	// sb.append("</a>");
	//
	// SendEmail.send(email, sb.toString());
	// System.out.println("鍙戦�侀偖浠�");
	//
	// }
	//
	// public void processActivate(String email, String validateCode) throws
	// ServiceException, ParseException {
	// UserBean user = userDao.find(email);
	// if (user != null) {
	// if (user.getStatus() == 0) {
	// Date currentTime = new Date();
	// currentTime.before(user.getRegisterTime());
	// if (currentTime.before(user.getLastActivateTime())) {
	// if (validateCode.equals(user.getValidateCode())) {
	// System.out.println("==sq===" + user.getStatus());
	// user.setStatus(1);
	// System.out.println("==sh===" + user.getStatus());
	// userDao.update(user);
	// } else {
	// throw new ServiceException("婵�娲荤爜涓嶆纭�");
	// }
	// } else {
	// throw new ServiceException("婵�娲荤爜宸茶繃鏈燂紒");
	// }
	// } else {
	// throw new ServiceException("閭宸叉縺娲伙紝璇风櫥褰曪紒");
	// }
	// } else {
	// throw new ServiceException("璇ラ偖绠辨湭娉ㄥ唽锛堥偖绠卞湴鍧�涓嶅瓨鍦級锛�");
	// }
	//
	// }
}