package com.mikoDataFinal.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLFileParser {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rahul","root","root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM rahul.mikodata");

			File outputFile = new File("output.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			String colName = "ID(PK) " + " Name" ;
			writer.write(colName);
			writer.newLine();
			String tl = "------------------" ;
			writer.write(tl);
			writer.newLine();
			

			while (rs.next()) {
				String column1 = rs.getString("column1");
				String column2 = rs.getString("column2");
				String line = column1 + "      " + column2;
				writer.write(line);
				writer.newLine();
			}
			writer.close();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
