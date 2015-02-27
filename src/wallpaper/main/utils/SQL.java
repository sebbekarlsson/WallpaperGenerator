package wallpaper.main.utils;

import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;

public class SQL {
	public static SimpleMySQLResult query(String query){
		SimpleMySQLResult result;
		SimpleMySQL mysql = new SimpleMySQL();
		mysql.connect("localhost", "root", "root");
		mysql.SelectDB("artist");
		result = mysql.Query(query);
		
		return result;
	}
}
