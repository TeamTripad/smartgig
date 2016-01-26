package com.smartgig.item.process;

import com.smartgig.constants.*;
import com.smartgig.database.connection.MyDBConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveStopWords implements DBConstants {
	ArrayList<String> stopwords = new ArrayList<>();
	MyDBConnection db = new MyDBConnection();

	public RemoveStopWords() {
	}

	public void readStopWords() throws IOException, SQLException {
		int a = 0;
		File inFile = new File(STOPWORDPATH);
		String line;
		MyDBConnection db = new MyDBConnection();
		if (!inFile.exists()) {
			System.out.println("Cannot find " + STOPWORDPATH);
			System.exit(0);
		}
		Scanner myFile = new Scanner(inFile);

		for (int i = 0; myFile.hasNext(); i = i + 1) {
//			 db.insertStopWords(myFile.nextLine());

		}

	}

}
