package hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

public class CookieHandler {

	// CookieCheck
	public boolean CookieBeanCheck(String username, int flag) {
		return !username.equalsIgnoreCase(null) && flag == 1;
	}

	public static void addCookie(String username, HttpServletResponse response) {
		Cookie SiginName = new Cookie("username", username);
		Cookie flag = new Cookie("flag", "1");
		SiginName.setMaxAge(3600);
		flag.setMaxAge(3600);
		response.addCookie(SiginName);
		response.addCookie(flag);

	}

	public boolean Cookie_check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		CookieBean cookieBean = CookieReader(cookies);

		if (cookieBean == null || CookieBeanCheck(cookieBean.getUsername(), cookieBean.getFlag())) {
			return true;
		}
		return false;

	}

	public CookieBean CookieReader(Cookie[] cookies) {
		CookieBean cookieBean = new CookieBean();
		if (cookies == null) {
			return null;
		} else {
			String usernmae = "";
			int flag = 0;
			for (Cookie ck : cookies) {
				if (ck.getName().equalsIgnoreCase("username")) {
					usernmae = ck.getValue();
				}
				if (ck.getName().equalsIgnoreCase("flag")) {
					flag = Integer.parseInt(ck.getValue());
				}
			}
			if (!usernmae.isEmpty() && flag == 1) {
				cookieBean.setUsername(usernmae);
				cookieBean.setFlag(flag);
			} else {
				cookieBean = null;
			}
		}
		return cookieBean;
	}

	public CookieBean RequestReader(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		CookieBean account = null;
		if (cookies == null) {
			return null;
		} else {
			String usernmae = "";
			int flag = 0;
			for (Cookie ck : cookies) {
				if (ck.getName().equalsIgnoreCase("usernmae")) {
					usernmae = ck.getValue();
				}
				if (ck.getName().equalsIgnoreCase("flag")) {
					flag = Integer.parseInt(ck.getValue());
				}
			}
			if (!usernmae.isEmpty() && flag == 1) {
				account = new CookieBean(usernmae, "", flag);
			}
		}
		return account;
	}
}