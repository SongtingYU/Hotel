package com.comp9321.assign2;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class program_package implements Serializable {

	private static final long serialVersionUID = -905694070443008672L;

	private char get_code() {
		return (char) (65 + Math.random() * (90 - 65 + 1));
	}

	private String mask(String code) {

		String re_code;

		if (code.equals("0")) {
			re_code = "D";
		} else if (code.equals("1")) {
			re_code = "M";
		} else if (code.equals("2")) {
			re_code = "S";
		} else if (code.equals("3")) {
			re_code = "F";
		} else if (code.equals("4")) {
			re_code = "G";
		} else if (code.equals("5")) {
			re_code = "C";
		} else if (code.equals("6")) {
			re_code = "H";
		} else if (code.equals("7")) {
			re_code = "J";
		} else if (code.equals("8")) {
			re_code = "N";
		} else if (code.equals("9")) {
			re_code = "K";
		} else if (code.equals("D")) {
			re_code = "0";
		} else if (code.equals("M")) {
			re_code = "1";
		} else if (code.equals("S")) {
			re_code = "2";
		} else if (code.equals("F")) {
			re_code = "3";
		} else if (code.equals("G")) {
			re_code = "4";
		} else if (code.equals("C")) {
			re_code = "5";
		} else if (code.equals("H")) {
			re_code = "6";
		} else if (code.equals("J")) {
			re_code = "7";
		} else if (code.equals("N")) {
			re_code = "8";
		} else {
			re_code = "9";
		}

		return re_code;
	}

	public int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public void send_mail(int email_type, String email_add, String username, String words) {

		SimpleEmail email = new SimpleEmail();
		String myUserName = "4th.hotel@gmail.com";
		String Password = "4thHotelpassword";

		StringBuffer sb = new StringBuffer("The mail is from 4th Hotel, ");

		if (email_type == 1) {

			sb.append("please click the URL to complete registration\n\n");
			sb.append("Dear " + username + "\n\n");
			sb.append("Thank you for choosing 4th Hotel, please click the URL below to complete your registration:\n");
			sb.append("http://localhost:8080/Hotel/control?usercode=");
			sb.append(words);
			sb.append("\n\n");
			sb.append("Yours sincerely.\n");

		} else if (email_type == 2) {

			sb.append("please do not reply.\n\n");
			sb.append("Dear " + username + "\n\n");
			sb.append("Thank you for choosing 4th Hotel, your booking is completed, please click the URL ");
			sb.append("below to manage your booking details:\n");
			sb.append("http://localhost:8080/Hotel/pindex.jsp?passto=");
			sb.append(words);
			sb.append("\nThe modification is only allowed 48 hours before you check in.\n\n");
			sb.append("Yours sincerely.\n");
		}

		try {
			email.setHostName("smtp.gmail.com");

			email.setAuthentication(myUserName, Password);
			email.setSSLOnConnect(true);
			email.setFrom(myUserName);
			if (email_type == 1) {
				email.setSubject("Register Confirmation");
			} else if (email_type == 2) {
				email.setSubject("Order Backup");
			}

			String s = sb.toString();

			email.setMsg(s);
			email.addTo(email_add);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

		return;
	}

	public String EncryptByPIN(int PIN_number) {

		String codeALL = "";
		String pos = String.valueOf(PIN_number);

		for (int i = 0; i < 25; i++) {

			if (i < 6) {
				codeALL += get_code();
			} else if (i < 19) {
				if (i % 2 == 0) {
					codeALL += mask(pos.substring(i / 2 - 3, i / 2 - 2));
				} else {
					codeALL += get_code();
				}
			} else {
				codeALL += get_code();
			}
		}

		return codeALL;
	}

	public int DecryptionToPIN(String code) {

		String real_PIN_code = "";
		System.out.println(code.length());
		if (code.length() == 25) {
			for (int i = 0; i < 25; i++) {

				if (i < 19 && i > 5) {
					if (i % 2 == 0) {
						real_PIN_code += mask(code.substring(i, i + 1));
					}
				}
			}
		}

		return Integer.parseInt(real_PIN_code);
	}
}
