package hotel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;

import com.comp9321.assign2.*;

import bean.Row;

@WebServlet(urlPatterns = "/control", displayName = "ControlServlet")
public class ControlServlet extends HttpServlet {

	Date inTime = null;
	Date outTime = null;
	public static float money;
	public static int flag = 0;
	public static int flagBM = 0;
	ArrayList<Booking_Bean> booking_row = new ArrayList<Booking_Bean>();
	ArrayList<Row> addbooking_row = new ArrayList<Row>();
	ArrayList<Row> addbooking_row_tmp = new ArrayList<Row>();
	SimpleDateFormat def = new SimpleDateFormat("yyyy-MM-dd");
	private static final long serialVersionUID = 7649166041385978012L;

	public ControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String usercode = request.getParameter("usercode");
		String username = request.getParameter("user_name");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		CookieHandler CookieHandler = new CookieHandler();
		UserHandler UserHandler = new UserHandler();

		if (action == null && usercode == null) {
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		} else {

			if (usercode != null) {
				try {
					if (Factory_DAO.GetUserDAOInstance().FindId_ByUsername(usercode) != 0) {
						UserHandler.updateState(usercode);

						User_Bean update_user = (User_Bean) Factory_DAO.GetUserDAOInstance()
								.FindById(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(username));

						request.setAttribute("update_user", update_user);
						session.setAttribute("username", usercode);

						CookieHandler.addCookie(usercode, response);
						request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
					}
				} catch (Exception e2) {
				}
			} else {
				if (action.equals("pin")) {
					ArrayList<Row> rows = new ArrayList<Row>();

					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						//System.out.println("Error loading Mysql Driver!");
						e.printStackTrace();
					}
					try {
						Connection connection = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/COMP9321assignment2", "root", "1234");
						Statement statement1 = connection.createStatement();
						Statement statement2 = connection.createStatement();

						String pass = null;
						pass = request.getParameter("passto");

						program_package uni = new program_package();
						int repin = uni.DecryptionToPIN(pass);
						//System.out.println(repin);
						// int repin = 1234567;
						// int pin = Integer.parseInt(request.getRequestURI());
						int userid = -1;

						String username1 = null;
						ResultSet re = statement1.executeQuery("select * from booking_tbl where PIN = " + repin);
						String time = null;
						money = 0;
						int PINContent;
						try {
							PINContent = Integer.parseInt(request.getParameter("PINContent"));
						} catch (Throwable e) {
							PINContent = -1;
						}

						while (re.next()) {
							if (PINContent == repin) {
								Row row = new Row();
								ResultSet room = statement2.executeQuery(
										"select * from room_tbl where room_id = " + re.getInt("booking_room_id"));
								while (room.next()) {
									row.sethotel_name(room.getString("hotel_name"));
									row.setroom_type(room.getString("room_type"));
									row.setcity(room.getString("city"));
								}
								row.setBooking_id(re.getInt("booking_id"));
								row.setUser_id(re.getInt("user_id"));
								row.setBooking_room_id(re.getInt("booking_room_id"));
								try {
									outTime = def.parse(re.getString("check_out_date"));
									inTime = def.parse(re.getString("check_in_date"));
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								long days = 1 + (outTime.getTime() - inTime.getTime()) / (1000 * 60 * 60 * 24);
								money = money + days * re.getFloat("booking_confirm_price");
								row.setBooking_confirm_price(re.getFloat("booking_confirm_price") * days);
								row.setCheck_in_date(re.getString("check_in_date"));
								row.setCheck_out_date(re.getString("check_out_date"));
								row.setExtra_bed(re.getInt("extra_bed"));
								row.setPIN(re.getInt("PIN"));
								row.setBooking_time(re.getString("booking_time"));
								time = re.getString("check_in_date");
								userid = re.getInt("user_id");
								repin = re.getInt("PIN");
								rows.add(row);
							}
						}
						Statement statement3 = connection.createStatement();
						ResultSet user = statement3.executeQuery("select * from user_tbl where user_id = " + userid);
						while (user.next()) {
							username1 = user.getString("user_name");
						}
						if (rows.size() == 0 || rows == null) {
							response.sendRedirect("pindex.jsp?error=y");
							return;
						} else {
							Date now = new Date();
							Date currentTime = null;
							Date checkTime = null;
							try {
								currentTime = def.parse(def.format(now));
								checkTime = def.parse(time);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							long l = checkTime.getTime() - currentTime.getTime();
							long d = l / (24 * 60 * 60 * 1000);
							if (d < 2) {
								response.sendRedirect("pindex.jsp?error=t");
								return;
							} else {
								flag = 2;
								addbooking_row.addAll(rows);
								addbooking_row_tmp.addAll(rows);
								session.setAttribute("username1", username1);
								session.setAttribute("pins", repin);
								session.setAttribute("rows", rows);
								session.setAttribute("flag", flag);
								session.setAttribute("money", money);
								request.getRequestDispatcher("booking_detail.jsp").forward(request, response);
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//System.out.println("get data error!");
						e.printStackTrace();
					}
				} else if (action.equals("delete")) {
					int pins = (Integer) session.getAttribute("pins");
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/COMP9321assignment2", "root", "1234");
						Statement statement = connection.createStatement();
						String sql = "UPDATE booking_tbl SET PIN=0,user_id=0 WHERE PIN=" + pins;
						statement.executeUpdate(sql);
						statement.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addbooking_row.clear();
					addbooking_row_tmp.clear();
					flag = 0;
					ArrayList<Row> empty_rows = new ArrayList<Row>();
					session.setAttribute("rows", empty_rows);
					session.setAttribute("flag", flag);
					request.getRequestDispatcher("booking_detail.jsp").forward(request, response);

				} else if (action.equalsIgnoreCase("Add to Booking")) {
					int pins = (Integer) session.getAttribute("pins");
					String[] get_order = request.getParameterValues("adds");
					String[] String_extra_bed = request.getParameterValues("extra_bed");

					String check_in_data = request.getParameter("checkin_date");
					String check_out_data = request.getParameter("checkout_date");

					List<String> extra_bed = String_extra_bed != null ? Arrays.asList(String_extra_bed) : null;

					String login_user = (String) session.getAttribute("username");

					ArrayList<Booking_Bean> booking_List = new ArrayList<Booking_Bean>();

					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					addbooking_row.clear();
					addbooking_row.addAll(addbooking_row_tmp);
					try {
						for (String i : get_order) {
							Row booking_Node = new Row();
							Booking_Bean booking_Node1 = new Booking_Bean();
							String[] booking_info = i.split(" - ");
							int booking_room_id = Integer.parseInt(booking_info[0]);
							float booking_pay = Float.parseFloat(booking_info[1]);

							int is_Extra_Bed = 0;
							if (extra_bed != null) {
								is_Extra_Bed = extra_bed.contains(booking_info[0]) ? 1 : 0;
							}
							booking_Node.setBooking_id(0);
							booking_Node.setUser_id(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(login_user));
							booking_Node.setBooking_room_id(booking_room_id);
							booking_Node.setBooking_confirm_price(booking_pay);
							booking_Node.setCheck_in_date(check_in_data);
							booking_Node.setCheck_out_date(check_out_data);
							booking_Node.setExtra_bed(is_Extra_Bed);
							booking_Node.setPIN(pins);
							booking_Node.setService_status("booking");
							booking_Node.setcity(Factory_DAO.GetRoomDAOInstance().FindById(booking_room_id).getCity());
							booking_Node.setroom_type(
									Factory_DAO.GetRoomDAOInstance().FindById(booking_room_id).getRoom_type());
							booking_Node.sethotel_name(
									Factory_DAO.GetRoomDAOInstance().FindById(booking_room_id).getHotel_name());
							booking_Node.setBooking_time(dateFormat.format(now));
							addbooking_row.add(booking_Node);

							booking_Node1.setBooking_id(0);
							booking_Node1.setUser_id(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(login_user));
							booking_Node1.setBooking_room_id(booking_room_id);
							booking_Node1.setBooking_confirm_price(booking_pay);
							booking_Node1.setCheck_in_date(check_in_data);
							booking_Node1.setCheck_out_date(check_out_data);
							booking_Node1.setExtra_bed(is_Extra_Bed);
							booking_Node1.setPIN(pins);
							booking_Node1.setService_status("booking");
							booking_Node1.setBooking_time(dateFormat.format(now));
							booking_List.add(booking_Node1);
						}
						booking_row = booking_List;

					} catch (Exception e) {
						e.printStackTrace();
					}
					// System.out.println(addbooking_row.size() + " 1111 " +
					// addbooking_row_tmp.size());
					session.setAttribute("rows", addbooking_row);

					flag = 3;
					session.setAttribute("flag", flag);
					request.getRequestDispatcher("booking_detail.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("Confirm")) {

					flag = 2;
					for (Booking_Bean i : booking_row) {
						try {
							Factory_DAO.GetBookingDAOInstance().AddBooking(i);
							Factory_DAO.GetRoomDAOInstance().Booking_Room(i.getBooking_room_id());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					addbooking_row_tmp.clear();
					addbooking_row_tmp.addAll(addbooking_row);
					session.setAttribute("flag", flag);
					booking_row.clear();
					flagBM = 0;
					session.setAttribute("flagBM", flagBM);
					request.getRequestDispatcher("booking_detail.jsp").forward(request, response);

				} else if (action.equalsIgnoreCase("Signin")) {
					try {
						if (Factory_DAO.GetUserDAOInstance().FindLogin(username, password)) {
							if (UserHandler.user_state_check(username)) {
								CookieHandler.addCookie(username, response);
								session.setAttribute("username", username);
								request.getRequestDispatcher("Hello.jsp").forward(request, response);
								/*
								 * UserHandler.send_User_Bean(request, response,
								 * "Hello.jsp");
								 */
							} else {
								request.setAttribute("error", "Plseae check your email to finish register!");
								request.getRequestDispatcher("Index.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("error", "Invalid account or password!");
							request.getRequestDispatcher("Index.jsp").forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equalsIgnoreCase("Register")) {
					try {
						UserHandler.register(request);
						program_package sendMa = new program_package();

						sendMa.send_mail(1, request.getParameter("email"), username, username);

						session.setAttribute("Information", "Please check your Email and finish your register!");

					} catch (Exception e) {
						session.setAttribute("Information", "Sorry, your Email has already registered!");
					}
					request.getRequestDispatcher("Welcome.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("Profile")) {
					try {
						UserHandler.send_User_Bean(request, response, "Profile.jsp");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equalsIgnoreCase("Shopping_Cart")) {
					request.getRequestDispatcher("Shopping_Cart.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("search")) {

					Cookie[] cookies = request.getCookies();
					username = null;
					String flag = null;
					if (cookies != null) {
						for (Cookie CK : cookies) {
							if (CK.getName().equalsIgnoreCase("username")) {
								username = CK.getValue();
							}
							if (CK.getName().equalsIgnoreCase("flag")) {
								flag = CK.getValue();
							}
						}
					}

					if (username == null || flag == null) {
						request.setAttribute("message", "Please login, thanks.");
					} else {
						request.setAttribute("message", null);
						session.setAttribute("username", username);
						Cookie SiginName = new Cookie("username", username);
						Cookie StatusFlag = new Cookie("flag", "1");
						SiginName.setMaxAge(3600);
						StatusFlag.setMaxAge(3600);
						response.addCookie(SiginName);
						response.addCookie(StatusFlag);
					}

					String city = request.getParameter("citys");
					String check_in_data = request.getParameter("checkin_date");
					String check_out_data = request.getParameter("checkout_date");
					String room_number = request.getParameter("rooms");
					float search_maxprice;
					try {
						search_maxprice = Float.parseFloat(request.getParameter("maxprice"));
					} catch (Exception e) {
						search_maxprice = Float.POSITIVE_INFINITY;
					}

					ArrayList<Integer> target_roomID_List = new ArrayList<Integer>();
					ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
					ArrayList<Float> target_room_maxprice_List = new ArrayList<Float>();
					ArrayList<String> target_room_special_List = new ArrayList<String>();
					ArrayList<String> target_room_peak_List = new ArrayList<String>();

					try {
						target_roomID_List = Factory_DAO.GetRoomDAOInstance().FindId_ByCity(city);
					} catch (Exception e) {
						e.printStackTrace();
					}

					for (int i = 0; i < target_roomID_List.size(); i++) {
						try {
							if (!Factory_DAO.GetRoomDAOInstance().FindById(target_roomID_List.get(i)).getStatus()
									.contains("available")) {
								target_roomID_List.remove(i);
								i--;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					try {
						bookingList = Factory_DAO.GetBookingDAOInstance().FreeTimeBookingList(check_in_data,
								check_out_data);

						for (int i = 0; i < bookingList.size(); i++) {

							if (Factory_DAO.GetBookingDAOInstance().Check_Catch_Day(
									bookingList.get(i).getBooking_room_id(), check_in_data, check_out_data)) {
								bookingList.remove(i);
								i--;
							}

						}

						for (int i = 0; i < bookingList.size(); i++) {

							if (Factory_DAO.GetRoomDAOInstance().FindById(bookingList.get(i).getBooking_room_id())
									.getCity().contains(city)) {
								target_roomID_List.add(bookingList.get(i).getBooking_room_id());
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						for (int i = 0; i < target_roomID_List.size(); i++) {

							float maxprice = Factory_DAO.GetRoomDAOInstance().FindById(target_roomID_List.get(i))
									.getPrice();

							ArrayList<Deal_Bean> Special_List = Factory_DAO.GetDealDAOInstance()
									.Special_DealList(target_roomID_List.get(i), check_in_data);

							ArrayList<Deal_Bean> Peak_List = Factory_DAO.GetDealDAOInstance()
									.Peak_DealList(target_roomID_List.get(i), check_in_data);

							float discountprice = 0;
							float upprice = 0;

							if (Special_List != null && Special_List.size() != 0) {
								String temp_code = "";
								for (int j = 0; j < Special_List.size(); j++) {

									int percentage = Special_List.get(j).getPercentage();

									if (j != 0) {
										temp_code += " , ";
										discountprice = (float) (discountprice * percentage / 100.0);
									} else {
										discountprice = (float) (maxprice * percentage / 100.0);
									}

									temp_code += Special_List.get(j).getDeal_name() + " " + percentage + "%OFF: "
											+ Special_List.get(j).getStart_date() + " To "
											+ Special_List.get(j).getEnd_date();
								}
								target_room_special_List.add(temp_code);
							} else {
								target_room_special_List.add("-");
							}

							if (Peak_List != null && Peak_List.size() != 0) {
								String temp_code = "";
								for (int j = 0; j < Peak_List.size(); j++) {

									int percentage = Peak_List.get(j).getPercentage();

									if (j != 0) {
										temp_code += " , ";
										upprice = (float) (upprice * percentage / 100.0);
									} else {
										upprice = (float) (maxprice * percentage / 100.0);
									}

									temp_code += Peak_List.get(j).getDeal_name() + " " + percentage + "%UP: "
											+ Peak_List.get(j).getStart_date() + " To "
											+ Peak_List.get(j).getEnd_date();
								}
								target_room_peak_List.add(temp_code);
							} else {
								target_room_peak_List.add("-");
							}

							maxprice = maxprice + upprice - discountprice;

							if (search_maxprice >= maxprice) {
								target_room_maxprice_List.add(maxprice);
							} else {
								target_roomID_List.remove(i);
								target_room_special_List.remove(i);
								target_room_peak_List.remove(i);
								i--;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (target_roomID_List.size() >= Integer.parseInt(room_number)) {
						request.setAttribute("target_room", target_roomID_List);
						request.setAttribute("target_room_maxprice", target_room_maxprice_List);
						request.setAttribute("target_room_special_List", target_room_special_List);
						request.setAttribute("target_room_peak_List", target_room_peak_List);
					} else {
						request.setAttribute("target_room", null);
					}

					request.getRequestDispatcher("Search_Results.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("Save")) {

					try {
						UserHandler.updateProfile(request, response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					session.setAttribute("username", username);
					request.getRequestDispatcher("Hello.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("edit")) {
					try {
						UserHandler.ready_to_update(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equalsIgnoreCase("Logout")) {
					////////////////////////////////////////////////////////////////////////////////
					session.invalidate();
					Cookie[] cookies = request.getCookies();
					for (Cookie CK : cookies) {
						if (CK.getName().equalsIgnoreCase("username")) {
							CK.setMaxAge(0);
							CK.setValue("");
							response.addCookie(CK);
						}
						if (CK.getName().equalsIgnoreCase("flag")) {
							CK.setMaxAge(0);
							CK.setValue("0");
							response.addCookie(CK);
						}
					}

					request.getRequestDispatcher("Welcome.jsp").forward(request, response);
				} else if (action.equalsIgnoreCase("Add to Cart")) {
					String[] get_order = request.getParameterValues("adds");
					String[] String_extra_bed = request.getParameterValues("extra_bed");

					String check_in_data = request.getParameter("checkin_date");
					String check_out_data = request.getParameter("checkout_date");

					List<String> extra_bed = String_extra_bed != null ? Arrays.asList(String_extra_bed) : null;

					String login_user = (String) session.getAttribute("username");
					@SuppressWarnings("unchecked")
					ArrayList<Booking_Bean> booking_List = (session.getAttribute("BOOKING_List") == null)
							? new ArrayList<Booking_Bean>()
							: (ArrayList<Booking_Bean>) session.getAttribute("BOOKING_List");

					try {

						for (String i : get_order) {
							Booking_Bean booking_Node = new Booking_Bean();
							String[] booking_info = i.split(" - ");
							int booking_room_id = Integer.parseInt(booking_info[0]);
							float booking_pay = Float.parseFloat(booking_info[1]);

							int is_Extra_Bed = 0;
							if (extra_bed != null) {
								is_Extra_Bed = extra_bed.contains(booking_info[0]) ? 1 : 0;
							}

							booking_Node.setBooking_id(0);
							booking_Node.setUser_id(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(login_user));
							booking_Node.setBooking_room_id(booking_room_id);
							booking_Node.setBooking_confirm_price(booking_pay);
							booking_Node.setCheck_in_date(check_in_data);
							booking_Node.setCheck_out_date(check_out_data);
							booking_Node.setExtra_bed(is_Extra_Bed);
							booking_Node.setPIN(0);
							booking_Node.setService_status("booking");

							int re_flag = 0;
							for (Booking_Bean temp : booking_List) {
								if (temp.getBooking_room_id() == booking_Node.getBooking_room_id()) {
									re_flag = 1;
									break;
								}
							}

							if (re_flag == 0) {
								booking_List.add(booking_Node);
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					session.setAttribute("BOOKING_List", booking_List);
					request.getRequestDispatcher("Shopping_Cart.jsp").forward(request, response);
				} else if (action.contains("Update")) {

					String[] get_order = request.getParameterValues("remove_room");
					String[] String_extra_bed = request.getParameterValues("extra_bed");

					List<String> remove_order = get_order != null ? Arrays.asList(get_order) : null;
					List<String> extra_bed = String_extra_bed != null ? Arrays.asList(String_extra_bed) : null;

					@SuppressWarnings("unchecked")
					ArrayList<Booking_Bean> booking_List = (session.getAttribute("BOOKING_List") == null)
							? new ArrayList<Booking_Bean>()
							: (ArrayList<Booking_Bean>) session.getAttribute("BOOKING_List");

					for (Booking_Bean booking_Node : booking_List) {
						booking_Node.setExtra_bed(0);
					}

					if (extra_bed != null) {
						for (String extra_room_id : extra_bed) {
							for (int i = 0; i < booking_List.size(); i++) {
								if (extra_room_id.contains(String.valueOf(booking_List.get(i).getBooking_room_id()))) {
									booking_List.get(i).setExtra_bed(1);
									break;
								}
							}
						}
					}

					if (remove_order != null) {
						for (String remove_room_id : remove_order) {
							for (int i = 0; i < booking_List.size(); i++) {
								if (remove_room_id.contains(String.valueOf(booking_List.get(i).getBooking_room_id()))) {
									booking_List.remove(i);
									break;
								}
							}
						}
					}

					session.setAttribute("BOOKING_List", booking_List);
					request.getRequestDispatcher("Shopping_Cart.jsp").forward(request, response);
				} else if (action.contains("Check out")) {

					CookieHandler signin = new CookieHandler();
					Cookie[] cookies = request.getCookies();
					CookieBean cookieBean = signin.CookieReader(cookies);

					int user_id = 0;

					try {
						user_id = Factory_DAO.GetUserDAOInstance().FindId_ByUsername(cookieBean.getUsername());
					} catch (Exception e) {
						e.printStackTrace();
					}

					User_Bean user_frofile;
					try {
						user_frofile = Factory_DAO.GetUserDAOInstance().FindById(user_id);
						request.setAttribute("payment_user", user_frofile);
					} catch (Exception e) {
						e.printStackTrace();
					}

					request.getRequestDispatcher("Confirm_Profile.jsp").forward(request, response);

				} else if (action.equalsIgnoreCase("Cancel")) {
					flag = 2;
					// System.out.println(addbooking_row.size() + " 2222 " +
					// addbooking_row_tmp.size());
					session.setAttribute("rows", addbooking_row_tmp);
					session.setAttribute("flag", flag);
					booking_row.clear();
					flagBM = 0;
					session.setAttribute("flagBM", flagBM);
					request.getRequestDispatcher("booking_detail.jsp").forward(request, response);
				}

				else if (action.equalsIgnoreCase("add_room")) {

					String set_username = (String) request.getParameter("user_name");

					session.setAttribute("username", set_username);
					Cookie SiginName_temp = new Cookie("username", set_username);
					Cookie flag_temp = new Cookie("flag", "1");
					SiginName_temp.setMaxAge(3600);
					flag_temp.setMaxAge(3600);
					response.addCookie(SiginName_temp);
					response.addCookie(flag_temp);

					SimpleDateFormat DB_format = new SimpleDateFormat("yyyy-MM-dd");
					String now_date = DB_format.format(new Date());

					GregorianCalendar cal = new GregorianCalendar();
					cal.setTime(new Date());
					cal.add(GregorianCalendar.DATE, 1);
					String next_date = DB_format.format(cal.getTime());

					String city = "Sydney";
					String check_in_data = now_date;
					String check_out_data = next_date;
					String room_number = "1";

					request.setAttribute("message", null);
					float search_maxprice;
					try {
						search_maxprice = Float.parseFloat(request.getParameter("maxprice"));
					} catch (Exception e) {
						search_maxprice = Float.POSITIVE_INFINITY;
					}

					ArrayList<Integer> target_roomID_List = new ArrayList<Integer>();
					ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
					ArrayList<Float> target_room_maxprice_List = new ArrayList<Float>();
					ArrayList<String> target_room_special_List = new ArrayList<String>();
					ArrayList<String> target_room_peak_List = new ArrayList<String>();

					try {
						target_roomID_List = Factory_DAO.GetRoomDAOInstance().FindId_ByCity(city);
					} catch (Exception e) {
						e.printStackTrace();
					}

					for (int i = 0; i < target_roomID_List.size(); i++) {
						try {
							if (!Factory_DAO.GetRoomDAOInstance().FindById(target_roomID_List.get(i)).getStatus()
									.contains("available")) {
								target_roomID_List.remove(i);
								i--;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					try {
						bookingList = Factory_DAO.GetBookingDAOInstance().FreeTimeBookingList(check_in_data,
								check_out_data);

						for (int i = 0; i < bookingList.size(); i++) {

							if (Factory_DAO.GetBookingDAOInstance().Check_Catch_Day(
									bookingList.get(i).getBooking_room_id(), check_in_data, check_out_data)) {
								bookingList.remove(i);
								i--;
							}

						}

						for (int i = 0; i < bookingList.size(); i++) {

							if (Factory_DAO.GetRoomDAOInstance().FindById(bookingList.get(i).getBooking_room_id())
									.getCity().contains(city)) {
								target_roomID_List.add(bookingList.get(i).getBooking_room_id());
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						for (int i = 0; i < target_roomID_List.size(); i++) {

							float maxprice = Factory_DAO.GetRoomDAOInstance().FindById(target_roomID_List.get(i))
									.getPrice();

							ArrayList<Deal_Bean> Special_List = Factory_DAO.GetDealDAOInstance()
									.Special_DealList(target_roomID_List.get(i), check_in_data);

							ArrayList<Deal_Bean> Peak_List = Factory_DAO.GetDealDAOInstance()
									.Peak_DealList(target_roomID_List.get(i), check_in_data);

							float discountprice = 0;
							float upprice = 0;

							if (Special_List != null && Special_List.size() != 0) {
								String temp_code = "";
								for (int j = 0; j < Special_List.size(); j++) {

									int percentage = Special_List.get(j).getPercentage();

									if (j != 0) {
										temp_code += " , ";
										discountprice = (float) (discountprice * percentage / 100.0);
									} else {
										discountprice = (float) (maxprice * percentage / 100.0);
									}

									temp_code += Special_List.get(j).getDeal_name() + " " + percentage + "%OFF: "
											+ Special_List.get(j).getStart_date() + " To "
											+ Special_List.get(j).getEnd_date();
								}
								target_room_special_List.add(temp_code);
							} else {
								target_room_special_List.add("-");
							}

							if (Peak_List != null && Peak_List.size() != 0) {
								String temp_code = "";
								for (int j = 0; j < Peak_List.size(); j++) {

									int percentage = Peak_List.get(j).getPercentage();

									if (j != 0) {
										temp_code += " , ";
										upprice = (float) (upprice * percentage / 100.0);
									} else {
										upprice = (float) (maxprice * percentage / 100.0);
									}

									temp_code += Peak_List.get(j).getDeal_name() + " " + percentage + "%UP: "
											+ Peak_List.get(j).getStart_date() + " To "
											+ Peak_List.get(j).getEnd_date();
								}
								target_room_peak_List.add(temp_code);
							} else {
								target_room_peak_List.add("-");
							}

							maxprice = maxprice + upprice - discountprice;

							if (search_maxprice >= maxprice) {
								target_room_maxprice_List.add(maxprice);
							} else {
								target_roomID_List.remove(i);
								target_room_special_List.remove(i);
								target_room_peak_List.remove(i);
								i--;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (target_roomID_List.size() >= Integer.parseInt(room_number)) {
						request.setAttribute("target_room", target_roomID_List);
						request.setAttribute("target_room_maxprice", target_room_maxprice_List);
						request.setAttribute("target_room_special_List", target_room_special_List);
						request.setAttribute("target_room_peak_List", target_room_peak_List);
					} else {
						request.setAttribute("target_room", null);
					}

					flagBM = 1;
					session.setAttribute("flagBM", flagBM);
					request.getRequestDispatcher("Hello.jsp").forward(request, response);
				} else if (action.contains("Save & Pay")) {

					try {
						UserHandler.updateProfile(request, response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					session.setAttribute("username", username);

					@SuppressWarnings("unchecked")
					ArrayList<Booking_Bean> checkout_list = (ArrayList<Booking_Bean>) session
							.getAttribute("BOOKING_List");

					Boolean flag = true;
					try {
						for (Booking_Bean check_booking : checkout_list) {

							if (!Factory_DAO.GetBookingDAOInstance().isAvailable(check_booking)) {
								flag = false;
								break;
							}

						}

						if (flag) {

							Date now = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							int random_pin = (int) (1000000 + Math.random() * (9999999 - 1000000 + 1));
							ArrayList<Integer> PIN_List = Factory_DAO.GetBookingDAOInstance().GetPIN();

							while (PIN_List.contains(random_pin)) {
								random_pin = (int) (1000000 + Math.random() * (9999999 - 1000000 + 1));
							}

							for (Booking_Bean check_booking : checkout_list) {
								check_booking.setPIN(random_pin);
								check_booking.setBooking_time(dateFormat.format(now));
								Factory_DAO.GetBookingDAOInstance().AddBooking(check_booking);
								Factory_DAO.GetRoomDAOInstance().Booking_Room(check_booking.getBooking_room_id());
							}

							program_package sendmail = new program_package();
							String code = sendmail.EncryptByPIN(random_pin) + ". And your PIN is " + random_pin;

							String send_name = (String) session.getAttribute("username");

							String email_add = Factory_DAO.GetUserDAOInstance()
									.FindById(Factory_DAO.GetUserDAOInstance().FindId_ByUsername(username)).getEmail();

							sendmail.send_mail(2, email_add, send_name, code);

							session.setAttribute("Information",
									"Thanks for purchasing. We have sends an unique link to your email as an entrance to edit your order.");
							session.removeAttribute("BOOKING_List");
							request.getRequestDispatcher("Welcome.jsp").forward(request, response);
						} else {
							session.setAttribute("Information",
									"Sorry, your want list has already booking by others, please get a new start and your cart have already empty.");
							session.removeAttribute("BOOKING_List");
							request.getRequestDispatcher("Welcome.jsp").forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}