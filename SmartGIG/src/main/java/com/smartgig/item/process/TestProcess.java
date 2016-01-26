package com.smartgig.item.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.ecommerce.database.connection.MyEcommerceDBConnection;

import net.didion.jwnl.JWNLException;

public class TestProcess {
	public static void main(String[] args) throws SQLException, JWNLException, IOException {

		MyDBConnection db = new MyDBConnection();
		RemoveStopWords rStopWords = new RemoveStopWords();

//		 rStopWords.readStopWords();
		//

		PreprocessingEcommerce token = new PreprocessingEcommerce();
//		token.addItemNameToken();
		token.addItemBrandToke();
		
		
	
	}
}