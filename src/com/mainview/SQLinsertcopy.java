package com.mainview;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SQLinsertcopy{
	
	private static Connection connect() {
		
        String url = "jdbc:sqlite:Group1Project.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
	public void insertRoomInfo( Integer extraBed, Integer person, int roomNo, String dateIN, String dateOUT, int checkInStatus, int checkOutStatus) throws SQLException {
	    
	    String insertRoomQuery = "INSERT INTO Reserved_Room(RoomNo, ExtraBeds, CheckInStatus, CheckOutStatus, CheckInDate, CheckOutDate, NoOfPeople ) VALUES(?,?,?,?,?,?,?)";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt2 = conn.prepareStatement(insertRoomQuery);)
            {	
        	
        		pstmt2.setInt(1, roomNo);
        		pstmt2.setInt(2, extraBed);
        		pstmt2.setInt(3, checkInStatus);
        		pstmt2.setInt(4, checkOutStatus);
	        	pstmt2.setString(5, dateIN);
		        pstmt2.setString(6, dateOUT);
		        pstmt2.setInt(7, person);
                pstmt2.execute();
                    
                System.out.println("Date in/out info added");
                
            } catch (SQLException e) { System.out.println(e.getMessage());}
	}    
	public void insertCID (int CID, String reservedTime, int totalPeople) {
		
		   String insertIDQuery = "INSERT INTO Reservation_Details (CustomerID, ReservedTime, TotalPeople) VALUES(?,?,?);";
		    
	        try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(insertIDQuery);)
	        {
		  	        pstmt.setInt(1, CID);
		  	        pstmt.setString(2, reservedTime);
		  	        pstmt.setInt(3, totalPeople);
	                pstmt.execute();
	                
	                System.out.println("CID added, reservedTime, totalPeople added");
	                
	        } catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	public void insertRID (int RID, int roomNO) {
		
		   String insertIDQuery = "UPDATE Reserved_Room SET ReservationID = ? WHERE	RoomNo = ?";
		    
	        try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(insertIDQuery);)
	        {
		  	        pstmt.setInt(1, RID);
		  	        pstmt.setInt(2, roomNO);
	                pstmt.execute();
	                
	                System.out.println("RID added");
	                
	        } catch (SQLException e) { System.out.println(e.getMessage()); }
		
	}
	public void insertCInfo(String name, String NRC, String Ph1, String Ph2, String Email ) {
		
		String insertCustomerQuery = "INSERT INTO Customer(CustomerName,NRC,PhoneNumber1,PhoneNumber2,Email) VALUES(?,?,?,?,?)";
		
	    
        try (Connection conn = this.connect();
            PreparedStatement pstmt1 = conn.prepareStatement(insertCustomerQuery);)
        {
	  	        pstmt1.setString(1, name);
	  	        pstmt1.setString(2, NRC);
	  	        pstmt1.setString(3, Ph1);
	  	        pstmt1.setString(4, Ph2);
	  	        pstmt1.setString(5, Email);
                pstmt1.execute();
                
                System.out.println("C info added");
                
        } catch (SQLException e) { System.out.println(e.getMessage()); }
		
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
	    	connect();
	    }

	}
        

	