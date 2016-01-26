package com.smartgig.database.connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.smartgig.constants.DBConstants;
import com.smartgig.database.dao.*;
import com.smartgig.database.dto.*;
import com.smartgig.facebook.dto.*;

public class MyDBConnection implements DBConstants {
	private Connection connection;
	private ResultSet res;
	private PreparedStatement prepStat;
	private Statement stat;

	public MyDBConnection() {
		try {
			Class.forName(DRIVER);// dynamic loading of driver
			connection = (Connection) DriverManager.getConnection(DATABASE_URL + DB_NAME, "root", "");
			stat = (Statement) connection.createStatement();
			stat.execute(CREATE_DB);
			stat.execute(USE_DB);

			stat.execute(CREATE_TB_USER);
			stat.execute(CREATE_TB_INTEREST);
			stat.execute(CREATE_TB_PERSONALITY);
			stat.execute(CREATE_TB_PERSONALITY_PERCENTAGE);
			stat.execute(CREATE_TB_ADMIN);
			stat.execute(CREATE_TB_CATEGORY);
			stat.execute(CREATE_TB_SUB_CATEGORY);
			stat.execute(CREATE_TB_PRODUCT);
			stat.execute(CREATE_TB_PRODUCT_LOGS);
			stat.execute(CREATE_TB_EVENT);
			stat.execute(CREATE_TB_CALENDAR);
			stat.execute(CREATE_TB_CALENDAR_EVENT);

			// insertCategory();
			// insertSubCategory();
			// insertSubCategory2();
			// insertAdmin();
			// insertProduct();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("MyDBConnection >>>> CONNECTION PROBLEMS t_t>>>> " + e.toString());
		}
	}

	public void addUser(FacebookUserProfile profile) {
		// claire
		try {
			String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?)";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, profile.getUser_id());
			prepStat.setString(2, profile.getLast_name());
			prepStat.setString(3, profile.getFirst_name());
			prepStat.setString(4, profile.getAge() + "");
			prepStat.setString(5, profile.getBirthday());
			prepStat.setString(6, profile.getGender());
			prepStat.setString(7, profile.getEmail());
			prepStat.execute();
		} catch (Exception e) {
			System.out.println("Error in adding user: " + e.toString());
		}

	}

	public boolean tokenExists(String userID, String token) {
		// claire
		try {
			String sql = "SELECT interestId FROM interest WHERE token=? && fbId = ?";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, token);
			prepStat.setString(2, userID);
			res = prepStat.executeQuery();

			return res.next();

		} catch (Exception e) {
			System.out.println(e.toString() + "token exists");
		}
		return false;
	}

	public AcceptedTokenModel getToken(String userID, String token) {
		// claire
		AcceptedTokenModel t = new AcceptedTokenModel();
		try {
			String sql = "SELECT * FROM interest WHERE token=? && fbId=?";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, token);
			prepStat.setString(2, userID);
			res = prepStat.executeQuery();

			boolean found = res.next();
			if (found) {
				// result.next();
				t.setTokenID(res.getString("interestId"));
				t.setUserID(res.getString("fbId"));
				t.setWord(res.getString("token"));
				t.setPositiveCount(Integer.parseInt(res.getString("positiveCnt")));
				t.setNegativeCount(Integer.parseInt(res.getString("negativeCnt")));
			}
		} catch (Exception e) {
			System.out.println(e.toString() + "get token");
		}
		return t;
	}

	public void addToken(AcceptedTokenModel token, String userID) {
		// claire
		try {
			String sql = "insert into interest values(?,?,?,?,?);";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, "0");
			prepStat.setString(2, userID);
			prepStat.setString(3, token.getWord());
			prepStat.setString(4, token.getPositiveCount() + "");
			prepStat.setString(5, token.getNegativeCount() + "");
			prepStat.execute();
			// System.out.println("token inserted");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void updateToken(AcceptedTokenModel token) {
		// claire
		try {
			String sql = "UPDATE interest SET " + "positiveCnt='" + token.getPositiveCount() + "', " + "negativeCnt='"
					+ token.getNegativeCount() + "' " + "WHERE interestId='" + token.getTokenID() + "'";
			System.out.println(sql);
			stat.execute(sql);
			System.out.println("token updated in db");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean negativeWordExists(String word) {
		// claire
		try {
			String sql = "SELECT * FROM negativewords WHERE negativeword=?";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, word);
			res = prepStat.executeQuery();

			if (res.next()) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return false;

	}

	public boolean stopWordExists(String word) {
		// claire
		try {
			String sql = "SELECT * FROM stopwords WHERE stopword=?";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, word);
			res = prepStat.executeQuery();

			if (res.next()) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return false;

	}

	public boolean englishWordExists(String word) {
		// claire
		try {
			String sql = "SELECT * FROM englishwords WHERE englishword=?";
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, word);
			res = prepStat.executeQuery();

			if (res.next()) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return false;
	}

	// LAUREN
	public ArrayList<ProductKeyword> identifyDistinctTokens() {
		ArrayList<ProductKeyword> nonUpdatedList = new ArrayList<ProductKeyword>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_PRODUCT_KEYWORDS_STAT);
			prepStat.setString(1, "not updated");
			res = prepStat.executeQuery();
			while (res.next()) {
				ProductKeyword prodKey = new ProductKeyword(res.getInt("prodId"), res.getString("keyword"),
						res.getInt("frequency"));
				nonUpdatedList.add(prodKey);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return nonUpdatedList;
	}

	// LAUREN
	public int countItems() throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_COUNT_PRODUCT);
			res = prepStat.executeQuery();
			res.next();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return res.getInt(1);
	}

	// LAUREN
	public ArrayList<IDF> returnIDF() throws SQLException {
		ArrayList<IDF> idfList = new ArrayList<IDF>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_IDF);
			prepStat.setString(1, "not updated");
			res = prepStat.executeQuery();
			int frequency = 0;
			while (res.next()) {
				IDF idf = new IDF(res.getString("keyword"), res.getDouble("value"));
				idfList.add(idf);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return idfList;
	}

	// LAUREN
	public ArrayList<Product> getProduct() throws SQLException {
		ArrayList<Product> prodList = new ArrayList<Product>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_PRODUCT);
			res = prepStat.executeQuery();
			while (res.next()) {
				Product prod = new Product(res.getInt("prodId"), res.getString("prodName"));
				prodList.add(prod);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return prodList;
	}

	// LAUREN
	public ArrayList<InterestKeyword> getInterestKeywords() throws SQLException {
		ArrayList<InterestKeyword> interestList = new ArrayList<InterestKeyword>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_INTEREST);
			res = prepStat.executeQuery();
			while (res.next()) {
				InterestKeyword interest = new InterestKeyword(res.getString("fbId"), res.getString("keyword"),
						res.getInt("frequency"));
				interestList.add(interest);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return interestList;
	}

	// LAUREN
	public int returnKeywordFrequency(int prodId, String keyword) throws SQLException {
		int frequency = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_PRODUCT_KEYWORDS);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, keyword);
			res = prepStat.executeQuery();
			while (res.next()) {
				frequency = res.getInt("frequency");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return frequency;
	}

	// LAUREN
	public boolean isProductExisting(int prodId, String keyword) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_PRODUCT_KEYWORDS);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, keyword);
			res = prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return res.next();
	}

	// LAUREN
	public int countInterest(String fbId) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_COUNT_INTEREST);
			prepStat.setString(1, fbId);
			res = prepStat.executeQuery();
			res.next();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return res.getInt(1);
	}

	// LAUREN
	public double getIDF(String keyword) throws SQLException {
		double value = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_IDF_KEY);
			prepStat.setString(1, keyword);
			res = prepStat.executeQuery();
			while (res.next()) {
				value = res.getDouble("value");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return value;
	}

	// LAUREN
	public boolean isTF_IDF_Interest_Existing(String fbId, String keyword) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_TF_IDF_INTEREST);
			prepStat.setString(1, fbId);
			prepStat.setString(2, keyword);
			res = prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return res.next();
	}

	// LAUREN
	public void updateTF_IDF_Interest(String fbId, String keyword, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(UPDATE_TF_IDF_INTEREST);
			prepStat.setDouble(1, value);
			prepStat.setString(2, fbId);
			prepStat.setString(3, keyword);
			prepStat.execute();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public void updateIDF(String keyword) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(UPDATE_IDF);
			prepStat.setString(1, "updated");
			prepStat.setString(2, keyword);
			prepStat.execute();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public void insertTF_IDF_Interest(String fbId, String keyword, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(INSERT_TF_IDF_INTEREST);
			prepStat.setString(1, fbId);
			prepStat.setString(2, keyword);
			prepStat.setDouble(3, value);
			prepStat.execute();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public boolean isTF_IDF_Items_Existing(int prodId, String keyword) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_TF_IDF_ITEMS);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, keyword);
			res = prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return res.next();
	}

	// LAUREN
	public void updateTF_IDF_Items(int prodId, String keyword, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(UPDATE_TF_IDF_ITEMS);
			prepStat.setDouble(1, value);
			prepStat.setInt(2, prodId);
			prepStat.setString(3, keyword);
			prepStat.setString(4, "not updated");
			prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public void insertTF_IDF_Items(int prodId, String keyword, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(INSERT_TF_IDF_ITEMS);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, keyword);
			prepStat.setDouble(3, value);
			prepStat.setString(4, "not updated");
			prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public ArrayList<TF_IDF> getTF_IDF_items(int prodId) throws SQLException {
		ArrayList<TF_IDF> itemTF_IDFList = new ArrayList<TF_IDF>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_TF_IDF_ITEMS_ID);
			prepStat.setInt(1, prodId);
			res = prepStat.executeQuery();
			while (res.next()) {
				TF_IDF length = new TF_IDF(prodId, res.getString("keyword"), res.getDouble("value"));
				itemTF_IDFList.add(length);
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return itemTF_IDFList;
	}

	// LAUREN
	public void insertItemLength(int prodId, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(INSERT_ITEM_LENGTH);
			prepStat.setInt(1, prodId);
			prepStat.setDouble(2, value);
			prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public void insertInterestLength(String fbId, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(INSERT_INTEREST_LENGTH);
			prepStat.setString(1, fbId);
			prepStat.setDouble(2, value);
			prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public double returnTF_IDF_itemsVal(int prodId, String keyword) throws SQLException {
		double val = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_TF_IDF_ITEMS);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, keyword);
			res = prepStat.executeQuery();
			while (res.next()) {
				val = res.getDouble("value");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return val;
	}

	// LAUREN
	public ArrayList<TF_IDF> getTF_IDF_interest(String fbId) throws SQLException {
		ArrayList<TF_IDF> interestTF_IDFList = new ArrayList<TF_IDF>();
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_TF_IDF_INTEREST_ID);
			prepStat.setString(1, fbId);
			res = prepStat.executeQuery();
			while (res.next()) {
				TF_IDF length = new TF_IDF(fbId, res.getString("keyword"), res.getDouble("value"));
				interestTF_IDFList.add(length);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return interestTF_IDFList;
	}

	// LAUREN
	public double getItemLength(int prodId) throws SQLException {
		double value = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_ITEM_LENGTH);
			prepStat.setInt(1, prodId);
			res = prepStat.executeQuery();
			while (res.next()) {
				value = res.getDouble("value");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return value;
	}

	// LAUREN
	public double getInterestLength(String fbId) throws SQLException {
		double value = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_INTEREST_LENGTH);
			prepStat.setString(1, fbId);
			res = prepStat.executeQuery();
			while (res.next()) {
				value = res.getDouble("value");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return value;
	}

	// LAUREN
	public void insertCosSim(int prodId, String fbId, double value) throws SQLException {
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(INSERT_COS_SIM);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, fbId);
			prepStat.setDouble(3, value);
			prepStat.executeQuery();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// LAUREN
	public Product returnRankedItems(int prodId) throws SQLException {
		String prodName = null;
		float price = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_PRODUCT_ID);
			prepStat.setInt(1, prodId);
			res = prepStat.executeQuery();
			while (res.next()) {
				prodName = res.getString("prodName");
				price = res.getFloat("price");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		Product prod = new Product(prodName, price);
		return prod;
	}

	// LAUREN
	public ArrayList<CosSimilarity> returnItems(String fbId) throws SQLException {
		ArrayList<CosSimilarity> itemList = new ArrayList<CosSimilarity>();
		int frequency = 0;
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(SELECT_COS_SIM);
			prepStat.setString(1, fbId);
			res = prepStat.executeQuery();
			while (res.next()) {
				if (res.getDouble("value") != 0) {
					CosSimilarity cs = new CosSimilarity(res.getInt("prodId"), res.getDouble("value"));
					itemList.add(cs);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return itemList;
	}

	// TMAE
	public boolean isStopWord(String word) throws SQLException {
		String sql = "SELECT * FROM stopwords WHERE stopword=?;";
		prepStat = (PreparedStatement) connection.prepareStatement(sql);
		prepStat.setString(1, word);
		res = prepStat.executeQuery();

		return res.next();
	}

	public boolean isWordExisting(String word, int prodId) {
		String sql = "SELECT productId FROM product_keywords WHERE keyword=? AND productId=?;";
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setInt(1, prodId);
			prepStat.setString(2, word);
			res = prepStat.executeQuery();
			return res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
    
	// LAUREN
	public UserPersonality getUserPersonality(String fbId) {
		String sql = "SELECT * FROM personality_percentage where fbId=?";
		try {
			prepStat = (PreparedStatement) connection.prepareStatement(sql);
			prepStat.setString(1, fbId);
			res = prepStat.executeQuery();
			UserPersonality userPersonality = new UserPersonality(res.getDouble("musicLover"), res.getDouble("sportsFan"), res.getDouble("foodieWeight"), res.getDouble("fashionFiendWeight"), res.getDouble("bookWormWeight"), res.getDouble("outdoorEnthusiastWeight"), res.getDouble("musicLoverPercentage"), res.getDouble("sportsFanPercentage"), res.getDouble("foodiePercentage"), res.getDouble("fashionFiendPercentage"), res.getDouble("bookWormPercentage"), res.getDouble("outdoorEnthusiastPercentage"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
