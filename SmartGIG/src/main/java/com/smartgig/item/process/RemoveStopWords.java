package com.smartgig.item.process;


import com.smartgig.ecommerce.constants.EcommerceDBConstants;
import com.smartgig.ecommerce.database.connection.MyEcommerceDBConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveStopWords implements EcommerceDBConstants {
	ArrayList<String> stopwords = new ArrayList<>();
	
	
	public RemoveStopWords() {
	}

	public void readStopWords() throws IOException, SQLException {

		File inFile = new File(STOPWORDPATH);
		MyEcommerceDBConnection db = new MyEcommerceDBConnection();
		if (!inFile.exists()) {
			System.out.println("Cannot find " + STOPWORDPATH);
			System.exit(0);
		}
		Scanner myFile = new Scanner(inFile);

		for (int i = 0; myFile.hasNext(); i = i + 1) {
			db.insertStopWords(myFile.nextLine());

		}

	}

}
