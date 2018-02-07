package com.pointwest.java.utils;

public interface EmployeeQueries {

	public static final String VERIFY_USER = "SELECT first_name, last_name, role, username, password FROM employee"
			+ " WHERE binary username = ? AND binary password = ?";
	
	public static final String INFO_BY_ID = "SELECT e.emp_id, e.first_name, e.last_name, shift, s.bldg_id, "
			+ "s.floor_number, s.quadrant, s.row_number ,s.column_number, s.local_number as local, group_concat(p.proj_alias separator ' , ')  as proj_alias "
			+ "FROM employee e, project p, employee_project ep, employee_seat es, seat s "
			+ "WHERE e.emp_id = ep.employee_id AND ep.proj_alias = p.proj_alias "
			+ "AND e.emp_id = es.emp_id AND s.seat_id = es.seat_id AND e.emp_id like ? "
			+ "group by e.emp_id,s.bldg_id, s.floor_number,s.quadrant, s.row_number,s.column_number";
	
	public static final String INFO_BY_NAME = "SELECT e.emp_id, e.first_name, e.last_name, shift, s.bldg_id, "
			+ "s.floor_number, s.quadrant, s.row_number ,s.column_number, s.local_number as local, group_concat(p.proj_alias separator ' , ')  as proj_alias "
			+ "FROM employee e, project p, employee_project ep, employee_seat es, seat s "
			+ "WHERE e.emp_id = ep.employee_id AND ep.proj_alias = p.proj_alias "
			+ "AND e.emp_id = es.emp_id AND s.seat_id = es.seat_id AND CONCAT(e.first_name, e.last_name) like ? "
			+ "group by e.emp_id,s.bldg_id, s.floor_number,s.quadrant, s.row_number,s.column_number";

	public static final String INFO_BY_PROJECT = "SELECT e.emp_id, e.first_name, e.last_name, shift, s.bldg_id, "
			+ "s.floor_number, s.quadrant, s.row_number ,s.column_number, s.local_number as local, group_concat(p.proj_alias separator ' , ')  as proj_alias "
			+ "FROM employee e, project p, employee_project ep, employee_seat es, seat s "
			+ "WHERE e.emp_id = ep.employee_id AND ep.proj_alias = p.proj_alias "
			+ "AND e.emp_id = es.emp_id AND s.seat_id = es.seat_id AND p.proj_alias like ? AND NOT p.proj_alias = 'BogusProject'"
			+ "group by e.emp_id,s.bldg_id, s.floor_number,s.quadrant, s.row_number,s.column_number";
	
	public static final String INFO_BY_LOCATION = "SELECT e.emp_id, e.first_name, e.last_name, shift, s.bldg_id, "
			+ "s.floor_number, s.quadrant, s.row_number ,s.column_number, s.local_number as local, group_concat(p.proj_alias separator ' , ')  as proj_alias "
			+ "FROM employee e, project p, employee_project ep, employee_seat es, seat s "
			+ "WHERE e.emp_id = ep.employee_id AND ep.proj_alias = p.proj_alias "
			+ "AND e.emp_id = es.emp_id AND s.seat_id = es.seat_id AND s.bldg_id like ? and s.floor_number like ? " 
			+ "group by  e.emp_id order by quadrant,row_number,column_number asc";
	
	public static final String INFO_BY_LOCATION_AND_QUADRANT = "SELECT e.emp_id, e.first_name, e.last_name, shift, s.bldg_id, "
			+ "s.floor_number, s.quadrant, s.row_number ,s.column_number, s.local_number as local, group_concat(p.proj_alias separator ' , ')  as proj_alias "
			+ "FROM employee e, project p, employee_project ep, employee_seat es, seat s "
			+ "WHERE e.emp_id = ep.employee_id AND ep.proj_alias = p.proj_alias "
			+ "AND e.emp_id = es.emp_id AND s.seat_id = es.seat_id AND s.bldg_id like ? and s.floor_number like ? " 
			+ "AND s.quadrant like ? group by  e.emp_id order by quadrant,row_number,column_number asc";
	
	public static final String BUILDING_INFO = "SELECT  bldg_id,  group_concat(distinct floor_number separator ' , ') as floor_number, "
			+ "group_concat(distinct quadrant separator ' , ') as quadrant FROM plsdb.seat group by bldg_id";
	
	public static final String SEATPLAN_INFO = "SELECT * FROM plsdb.seat where bldg_id like ? and floor_number like ? order by row_number,quadrant asc";
	
}
