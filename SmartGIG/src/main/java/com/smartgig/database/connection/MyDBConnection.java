package com.smartgig.database.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.smartgig.constants.DBConstants;
import com.smartgig.database.dao.Calendar;
import com.smartgig.database.dao.CalendarEvent;
import com.smartgig.database.dao.Event;
import com.smartgig.database.dao.Interest;
import com.smartgig.database.dao.Personality;
import com.smartgig.database.dao.PersonalityByProduct;
import com.smartgig.database.dao.PersonalityPercentage;
import com.smartgig.database.dao.User;
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
//			stat.execute(CREATE_TB_ADMIN);
//			stat.execute(CREATE_TB_CATEGORY);
//			stat.execute(CREATE_TB_SUB_CATEGORY);
//			stat.execute(CREATE_TB_PRODUCT);
//			stat.execute(CREATE_TB_PRODUCT_LOGS);
			stat.execute(CREATE_TB_EVENT);
			stat.execute(CREATE_TB_CALENDAR);
			stat.execute(CREATE_TB_CALENDAR_EVENT);
//			stat.execute(CREATE_TB_STOPWORDS);
//			stat.execute(CREATE_TB_PRODUCT_KEYWORDS);

			// insertCategory();
			// insertSubCategory();
			// insertSubCategory2();
			// insertAdmin();
			// insertProduct();
//          insertProductSamples();
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

	public ArrayList<String> getInterests(String userID) {
		// claire
		ArrayList<String> interests = new ArrayList<>();

		try {

		} catch (Exception e) {

		}

		return interests;

	}

	/*
	 * INSERT START
	 *******************************************************************************/
//	public void insertAdmin(Admin admin) throws SQLException {
//		String sql = "INSERT INTO ADMIN (adminId,uname, passwd) values (?, ?, ?)";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setInt(1, admin.getAdminId());
//		prepStat.setString(2, admin.getUname());
//		prepStat.setString(3, admin.getPasswd());// TO BE ENCRYPTED
//		prepStat.executeUpdate();
//	}

	public void insertCalendar(Calendar calendar) throws SQLException {
	}

	public void insertCalendarEvent(CalendarEvent calendarEvent) throws SQLException {
	}

//	public void insertCategory(Category category) throws SQLException {
//		String sql = "INSERT INTO CATEGORY (catId,categoryName) values (?, ?)";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setInt(1, category.getCatId());
//		prepStat.setString(2, category.getCategoryName());
//		prepStat.executeUpdate();
//
//	}

	public void insertEvent(Event event) throws SQLException {
	}

	public void insertInterest(Interest interest) throws SQLException {
	}

	public void insertPersonality(Personality personality) throws SQLException {
	}

	public void insertPersonalityPercentage(PersonalityPercentage personalityPercentage) throws SQLException {
	}

//	public void insertProduct(Product product) throws SQLException {
//		String statement = "INSERT INTO PRODUCT (prodId,adminId,catId,subcatId,sourceId,prodName,prodBrand,prodDesc,quantity,price,size,color,gender,prodImg) "
//				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
//		prepStat = (PreparedStatement) connection.prepareStatement(statement);
//		prepStat.setInt(1, product.getProdId());
//		prepStat.setInt(2, product.getAdminId());
//		prepStat.setInt(3, product.getCatId());
//		prepStat.setInt(4, product.getSubCatId());
//		prepStat.setInt(5, product.getSourceId());
//		prepStat.setString(6, product.getProdName());
//		prepStat.setString(7, product.getBrand());
//		prepStat.setString(8, product.getProdDesc());
//		prepStat.setInt(9, product.getQuantity());
//		prepStat.setFloat(10, product.getPrice());
//		prepStat.setString(11, product.getSize());
//		prepStat.setString(12, product.getColor());
//		prepStat.setString(13, product.getGender());
//		prepStat.setBlob(14, product.getImage());
//		prepStat.executeUpdate();
//	}
	public void insertProductPersonality(PersonalityByProduct productpersonality) throws SQLException {
	
		String statement = "INSERT INTO PRODUCT (personalityId,prodId,token,isMusicLover,isSportsFan,isFoodie,isFashionFiend,isBookWorm,isOutdoorEntusiast)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		prepStat = (PreparedStatement) connection.prepareStatement(statement);
		prepStat.setInt(1, productpersonality.getPersonalityId());
		prepStat.setInt(2, productpersonality.getProdId());
		prepStat.setString(3, productpersonality.getToken());
		prepStat.setBoolean(4, productpersonality.isMusicLover());
		prepStat.setBoolean(5, productpersonality.isSportsFan());
		prepStat.setBoolean(6, productpersonality.isFoodie());
		prepStat.setBoolean(7, productpersonality.isFashionFiend());
		prepStat.setBoolean(8, productpersonality.isBookWorm());
		prepStat.setBoolean(9, productpersonality.isOutdoorEntusiast());
		
		prepStat.executeUpdate();
	}

//	public void insertProductLogs(ProductLogs productLogs) throws SQLException {
//	}

//	public void insertSubCategory(SubCategory subCategory) throws SQLException {
//		String sql = "INSERT INTO SUB_CATEGORY (subcatId,catId,subcategoryName,sourceId)" + "values (?, ?,?,?)";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setInt(1, subCategory.getSubCatId());
//		prepStat.setInt(2, subCategory.getCatId());
//		prepStat.setString(3, subCategory.getSubcategoryName());
//		prepStat.setInt(4, subCategory.getSourceId());
//		prepStat.executeUpdate();
//	}

	public void insertUser(User user) throws SQLException {
	}

//	public void insertStopWords(String stopword) throws SQLException {
//		String sql = "INSERT INTO STOPWORDS (stopword) " + "values(?)";
//
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, stopword);
//
//		prepStat.executeUpdate();
//	}
//
//	public boolean isStopWord(String word) throws SQLException {
//		String sql = "SELECT * FROM stopwords WHERE stopword=?;";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, word);
//		// System.out.println("ni sud");
//		res = prepStat.executeQuery();
//
//		return res.next();
//	}

//	public void insertKeywords(int id, String word, int frequency, String status) throws SQLException {
//		String sql = "INSERT INTO product_keywords (productId,keyword,frequency,status) VALUES(?,?,?,?)";
//
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//
//		prepStat.setInt(1, id);
//		prepStat.setString(2, word);
//		prepStat.setInt(3, frequency);
//		prepStat.setString(4, status);
//
//		prepStat.executeUpdate();
//
//	}
//
//	public boolean isWordExisting(String word, int prodId) {
//
//		String sql = "SELECT productId FROM product_keywords WHERE keyword=? AND productId=?;";
//
//		try {
//			prepStat = (PreparedStatement) connection.prepareStatement(sql);
//			prepStat.setInt(1, prodId);
//			prepStat.setString(2, word);
//		
//
//			res = prepStat.executeQuery();
//			
//			return res.next();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}


	/* INSERT END ************************************************************/
	/*
	 * RETRIEVE START
	 ****************************************************************************/
//	public Admin getAdminById(int adminId) throws SQLException {
//		Admin a = new Admin();
//		return a;
//	}

	public Calendar getCalendarById(int calendarId) throws SQLException {
		Calendar c = new Calendar();
		return c;
	}

	public CalendarEvent getCalendarEventById(int calendarEventId) throws SQLException {
		CalendarEvent ce = new CalendarEvent();
		return ce;
	}

//	public Category getCategoryById(int categoryId) throws SQLException {
//		Category c = new Category();
//		return c;
//	}

	public Event getEventById(int eventId) throws SQLException {
		Event e = new Event();
		return e;
	}

	public Interest getInterestById(int interestId) throws SQLException {
		Interest i = new Interest();
		return i;
	}

	public Personality getPersonalityById(int personalityId) throws SQLException {
		Personality p = new Personality();
		return p;
	}

	public PersonalityPercentage getPersonalityPercentageById(int personalityPercentageId) throws SQLException {
		PersonalityPercentage p = new PersonalityPercentage();
		return p;
	}

//	public Product getProductById(int productId) throws SQLException {
//		Product p = new Product();
//		return p;
//	}
//
//	public ProductLogs getProductLogsById(int productLogsId) throws SQLException {
//		ProductLogs p = new ProductLogs();
//		return p;
//	}
//
//	public SubCategory getSubCategoryById() throws SQLException {
//		SubCategory s = new SubCategory();
//		return s;
//	}

	public User getUserById() throws SQLException {
		User u = new User();
		return u;
	}

//	public int getCategoryId(String categoryName) throws SQLException {
//		int catID = 0;
//		String sql = "SELECT catId FROM CATEGORY WHERE categoryName=?;";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, categoryName);
//		res = prepStat.executeQuery();
//
//		res.next();
//
//		return res.getInt("catId");
//	}
//
//	public int getProdCategoryId(String subcategoryName) throws SQLException {
//		int subcatID = 0;
//		int catID = 0;
//		String sql = "SELECT subcatId ,catId FROM SUB_CATEGORY WHERE subcategoryName=?;";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, subcategoryName);
//
//		res = prepStat.executeQuery();
//
//		res.next();
//
//		subcatID = res.getInt("subcatId");
//		catID = res.getInt("catId");
//
//		System.out.println("subCat Id: " + subcatID + subcategoryName);
//		System.out.println("Cat Id: " + catID + subcategoryName);
//		return res.getInt("catId");
//	}
//
//	public List<Product> getProduct() throws SQLException {
//		ArrayList<Product> prodInfoList = new ArrayList<Product>();
//		Product prodInfo = new Product();
//		String productName = null, productBrand = null, productDesc = null, prodColor = null, prodGender = null;
//		int productId = 0;
//		String sql = "SELECT prodId ,prodName,prodBrand,prodDesc,color,gender FROM PRODUCT";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//
//		res = prepStat.executeQuery();
//		while (res.next()) {
//			prodInfo.setProdId(res.getInt("prodId"));
//			prodInfo.setProdName(res.getString("prodName"));
//			prodInfo.setBrand(res.getString("prodBrand"));
//			prodInfo.setProdDesc(res.getString("prodDesc"));
//			prodInfo.setColor(res.getString("color"));
//			prodInfo.setGender(res.getString("gender"));
//
//			prodInfoList.add(new Product(prodInfo.getProdId(), prodInfo.getProdName(), prodInfo.getBrand(),
//					prodInfo.getProdDesc(), prodInfo.getColor(), prodInfo.getGender()));
//		}
//		return prodInfoList;
//
//	}
//	public List<Keywords> getKeywords throws SQLException(){
//		
//	}
//	public int getCategoryIdForSource(String categoryName) throws SQLException {
//		int catID = 0;
//		String sql = "SELECT catId FROM SUB_CATEGORY WHERE subcategoryName=?;";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, categoryName);
//		res = prepStat.executeQuery();
//
//		res.next();
//
//		return res.getInt("catId");
//	}
//
//	public int getSubCategoryId(String subcategoryName) throws SQLException {
//		int subcatID = 0;
//		String sql = "SELECT subcatId FROM SUB_CATEGORY WHERE subcategoryName=?;";
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setString(1, subcategoryName);
//		res = prepStat.executeQuery();
//
//		while (res.next()) {
//			subcatID = res.getInt("subcatId");
//		}
//
//		System.out.println("subCat Id: " + subcatID + subcategoryName);
//
//		return subcatID;
//	}
//
//	public int getKeywordFrequency(int id, String word) throws SQLException {
//		int frequency = 0;
//		String sql = "SELECT * FROM PRODUCT_KEYWORDS WHERE productId=? AND keyword=?";
//
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//
//		prepStat.setInt(1, id);
//		prepStat.setString(2, word);
//
//		res = prepStat.executeQuery();
//
//		while (res.next()) {
//			frequency = res.getInt("frequency");
//		}
//		return frequency;
//	}
//
//	public String getKeywordStemmed(int id, String word) throws SQLException {
//		String words = null;
//		String sql = "SELECT * FROM KEYWORDS";
//
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		//
//		// prepStat.setInt(1,id);
//		// prepStat.setString(2, word);
//		//
//		res = prepStat.executeQuery();
//
//		while (res.next()) {
//			words = res.getString("keyword");
//		}
//		return words;
//	}
//
//	public SubCategory getSubCatInfo(int categoryId) throws SQLException {
//
//		SubCategory subinfo = new SubCategory();
//		String sql = "SELECT * FROM SUB_CATEGORY WHERE subcatId=?;";
//
//		prepStat = (PreparedStatement) connection.prepareStatement(sql);
//		prepStat.setInt(1, categoryId);
//		res = prepStat.executeQuery();
//
//		res.next();
//
//		subinfo.setSubCatId(res.getInt("subcatId"));
//		subinfo.setCatId(res.getInt("catId"));
//		subinfo.setSubcategoryName(res.getString("subcategoryName"));
//		subinfo.setSourceId(res.getInt("sourceId"));
//
//		return subinfo;
//	}

	/*
	 * RETRIEVE END
	 *****************************************************************************/

	/*
	 * UPDATE START
	 *****************************************************************************/
	public void updateAdminById(int adminId) throws SQLException {
	}

	public void updateCalendarById(int calendarId) throws SQLException {
	}

	public void updateCalendarEventById(int calendarEventId) throws SQLException {
	}

	public void updateCategoryById(int categoryId) throws SQLException {
	}

	public void updateEventById(int eventId) throws SQLException {
	}

	public void updateInterestById(int interestId) throws SQLException {
	}

	public void updatePersonalityById(int personalityId) throws SQLException {
	}

	public void updatePersonalityPercentageById(int personalityPercentageId) throws SQLException {
	}

	public void updateProductById(int productId) throws SQLException {
	}

	public void updateProductLogsById(int productLogsId) throws SQLException {
	}

	public void updateSubCategoryById(int subCategoryId) throws SQLException {
	}

	public void updateUserById(int userId) throws SQLException {
	}

	public void updateKeywordFreq(int id, String word, int frequency) throws SQLException {
		String sql = "UPDATE product_keywords SET frequency =? WHERE productId=? AND keyword=?";
		prepStat = (PreparedStatement) connection.prepareStatement(sql);

		prepStat.setInt(1, frequency);
		prepStat.setInt(2, id);
		prepStat.setString(3, word);

		prepStat.executeUpdate();

	}
	/*
	 * UPDATE END
	 *******************************************************************************/
	
//	public void insertAdminDummy() throws SQLException {
//        Admin a = new Admin(0, "tmaeeh", "12345");
//        insertAdmin(a);
//    }

//    public void insertCategorySamples() throws SQLException {
//        ArrayList<Category> sampleCategories = new ArrayList<Category>();
//
//        sampleCategories.add(new Category(0, "Fashion"));
//        sampleCategories.add(new Category(0, "Health & Beauty"));
//        sampleCategories.add(new Category(0, "Watches | Jewelry | Sunglasses"));
//        sampleCategories.add(new Category(0, "Mobiles & Tablets"));
//        sampleCategories.add(new Category(0, "Computers & Laptops"));
//        sampleCategories.add(new Category(0, "Tv | Audio/Video | Gaming,Gadgets"));
//        sampleCategories.add(new Category(0, "Cameras"));
//        sampleCategories.add(new Category(0, "Home & Living"));
//        sampleCategories.add(new Category(0, "Home Appliances"));
//        sampleCategories.add(new Category(0, "Toy , Kids & Babies"));
//        sampleCategories.add(new Category(0, "Travel & Luggages"));
//        sampleCategories.add(new Category(0, "Sports & | Automotives"));
//        sampleCategories.add(new Category(0, "Media , Music & Books"));
//
//        for (Category cat : sampleCategories) {
//            insertCategory(cat);
//        }
//    }

//    public void insertSubCategorySamples() throws SQLException {
//        ArrayList<SubCategory> subcatSamples = new ArrayList<>();
//
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[0]), "Men", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Beauty for Her", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Fragrances", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Men\\'s Care", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Foods Supplement", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Medical Supplies", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[1]), "Personal Care", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[2]), "Watches", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[2]), "Jewellery", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[2]), "Sunglasses", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[3]), "Mobiles", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[3]), "Mobile Accessories", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[3]), "Tablet Accessories", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[3]), "Powerbank", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[4]), "Laptops", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[4]), "Storage", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[4]), "PC Gaming", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[4]), "Computer Accessories", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[5]), "Televisions", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[5]), "Audio Devices", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[5]), "Gaming", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[5]), "Video", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[5]), "Wearable Technology", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[6]), "All Cameras", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[6]), "Video & Action Camera", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[6]), "Camera Accessories", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Kitchen & Dining", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Bedding", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Outdoor & Garden", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Furniture", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Home Decor", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Bath", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[7]), "Pets", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[8]), "Small Kitchen Appliances", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[8]), "Large Home Appliances", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[8]), "Garment Care", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[8]), "Cooling & Heating", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[8]), "Housekeeping", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[9]), "Toys & Babies", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[9]), "Babies", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[9]), "Toys", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[10]), "Travel & Luggage", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[10]), "Travel Accessories", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[11]), "Sports & Outdoors", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[11]), "Automotives", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[12]), "Media, Music & Books", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[12]), "Musical Instruments", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[12]), "Books", 0));
//        subcatSamples.add(new SubCategory(0, getCategoryId(category[12]), "Movies", 0));
//
//        //ADD SUB CATEGORY
//        for (SubCategory sc : subcatSamples) {
//            insertSubCategory(sc);
//        }
//    }

//    public void insertSubCategory2Samples() throws SQLException {
//        ArrayList<SubCategory> subCatSamples = new ArrayList<>();
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[0]), "Shoes", getSubCategoryId(subcategory[0])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[0]), "Bag", getSubCategoryId(subcategory[0])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[0]), "Clothing", getSubCategoryId(subcategory[0])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[1]), "Bag", getSubCategoryId(subcategory[1])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[1]), "Shoes", getSubCategoryId(subcategory[1])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[1]), "Clothing", getSubCategoryId(subcategory[1])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[2]), "Make Up", getSubCategoryId(subcategory[2])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[2]), "Bath & Body", getSubCategoryId(subcategory[2])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[2]), "Skin Care", getSubCategoryId(subcategory[2])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[2]), "Hair Care", getSubCategoryId(subcategory[2])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[2]), "Beauty Tools", getSubCategoryId(subcategory[2])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[3]), "Weight Management", getSubCategoryId(subcategory[3])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[3]), "Beauty Supplements", getSubCategoryId(subcategory[3])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[3]), "Sports Nutrition", getSubCategoryId(subcategory[3])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[3]), "Hair Care", getSubCategoryId(subcategory[3])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[3]), "Beauty Tools", getSubCategoryId(subcategory[3])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[4]), "Men\\'s Watches", getSubCategoryId(subcategory[4])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[4]), "Women\\'s Watches", getSubCategoryId(subcategory[4])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[5]), "Men\\'s Jewellery", getSubCategoryId(subcategory[5])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[5]), "Women\\'s Jewellery", getSubCategoryId(subcategory[5])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[6]), "Men\\'s Sunglasses", getSubCategoryId(subcategory[6])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[6]), "Women\\'s Sunglasses", getSubCategoryId(subcategory[6])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[7]), "Smartphones", getSubCategoryId(subcategory[7])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[7]), "Basic Phones", getSubCategoryId(subcategory[7])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[7]), "Tablets", getSubCategoryId(subcategory[7])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[8]), "Cases & Covers", getSubCategoryId(subcategory[8])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[8]), "Screen Guards", getSubCategoryId(subcategory[8])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[8]), "Car Accessories", getSubCategoryId(subcategory[8])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[9]), "2-in-1s & Touchscreens", getSubCategoryId(subcategory[9])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[9]), "Ultrabooks", getSubCategoryId(subcategory[9])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[9]), "Macbooks", getSubCategoryId(subcategory[9])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[10]), "External Hard Drives", getSubCategoryId(subcategory[10])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[10]), "USB Flash Drives", getSubCategoryId(subcategory[10])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[11]), "Gaming Laptops", getSubCategoryId(subcategory[11])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[11]), "Gaming Mouse", getSubCategoryId(subcategory[11])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[11]), "Console Gaming", getSubCategoryId(subcategory[11])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[12]), "Keyboards", getSubCategoryId(subcategory[12])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[12]), "Monitor", getSubCategoryId(subcategory[12])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[12]), "Mouse", getSubCategoryId(subcategory[12])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[12]), "Speakers", getSubCategoryId(subcategory[12])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[13]), "LED TVs", getSubCategoryId(subcategory[13])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[13]), "Smart TVs", getSubCategoryId(subcategory[13])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[14]), "Home and Audio Theater", getSubCategoryId(subcategory[14])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[14]), "Headphones & Headsets", getSubCategoryId(subcategory[14])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[14]), "MP3 Players", getSubCategoryId(subcategory[14])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[14]), "Portable Speakers", getSubCategoryId(subcategory[14])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[15]), "Gaming Consoles", getSubCategoryId(subcategory[15])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[15]), "Gaming Accessories", getSubCategoryId(subcategory[15])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[15]), "Games", getSubCategoryId(subcategory[15])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[15]), "PC Gaming", getSubCategoryId(subcategory[15])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[16]), "DVD Players", getSubCategoryId(subcategory[16])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[16]), "Streaming Media Players", getSubCategoryId(subcategory[16])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[16]), "Projectors", getSubCategoryId(subcategory[16])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[17]), "Smartwatches", getSubCategoryId(subcategory[17])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[17]), "Activity & Fitness Trackers", getSubCategoryId(subcategory[17])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[18]), "DSLR/SLR", getSubCategoryId(subcategory[18])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[18]), "Mirrorless", getSubCategoryId(subcategory[18])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[18]), "Point & Shoot", getSubCategoryId(subcategory[18])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[18]), "Instant Cameras", getSubCategoryId(subcategory[18])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[19]), "Sports & Action Cameras", getSubCategoryId(subcategory[19])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[19]), "Drones", getSubCategoryId(subcategory[19])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[19]), "Video Cameras", getSubCategoryId(subcategory[19])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[20]), "Memory Cards", getSubCategoryId(subcategory[20])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[20]), "Lenses", getSubCategoryId(subcategory[20])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[20]), "Monopods & Tripods", getSubCategoryId(subcategory[20])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[21]), "Blenders, Mixers & Grinders", getSubCategoryId(subcategory[21])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[21]), "Juicers and Fruit Extractors", getSubCategoryId(subcategory[21])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[21]), "Rice Cookers, Slow Cookers & Steamers", getSubCategoryId(subcategory[21])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[21]), "Microwaves & Ovens", getSubCategoryId(subcategory[21])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[21]), "Induction Cookers & Stoves", getSubCategoryId(subcategory[21])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[22]), "Washing Machine", getSubCategoryId(subcategory[22])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[22]), "Refrigerators", getSubCategoryId(subcategory[22])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[23]), "Steamers & Presses", getSubCategoryId(subcategory[23])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[23]), "Irons", getSubCategoryId(subcategory[23])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[23]), "Sewing Machines", getSubCategoryId(subcategory[23])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[24]), "Air Conditioners", getSubCategoryId(subcategory[24])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[24]), "Fans", getSubCategoryId(subcategory[24])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[24]), "Water Heaters", getSubCategoryId(subcategory[24])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[25]), "Babies", getSubCategoryId(subcategory[25])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[25]), "Kids", getSubCategoryId(subcategory[25])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[25]), "Toys", getSubCategoryId(subcategory[25])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Diapers & Potties", getSubCategoryId(subcategory[26])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Bathing & Grooming", getSubCategoryId(subcategory[26])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Feeding & Nursing", getSubCategoryId(subcategory[26])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Nursery", getSubCategoryId(subcategory[26])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Baby Gear", getSubCategoryId(subcategory[26])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[26]), "Healthy & Safety", getSubCategoryId(subcategory[26])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Educational Toys", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Puzzle & Board Games", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Play Vechicle & Remote Control Toys", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Block & Building Toys", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Dolls Doll Houses", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Stuffed Toys", getSubCategoryId(subcategory[27])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Pretend Play Toys", getSubCategoryId(subcategory[27])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[27]), "Action Figures & Collectibles", getSubCategoryId(subcategory[27])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Luggage", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Suitcases", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Travel Totes", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Backbacks", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Carry Ons", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Belt bag", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Laptop bags & Cases", getSubCategoryId(subcategory[28])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[28]), "Travel Essentials", getSubCategoryId(subcategory[28])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[29]), "Travel Duffels", getSubCategoryId(subcategory[29])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[29]), "Travel Wallets", getSubCategoryId(subcategory[29])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[29]), "Passport Covers", getSubCategoryId(subcategory[29])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[29]), "Travel Kit", getSubCategoryId(subcategory[29])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[30]), "Outdoor & Adventure", getSubCategoryId(subcategory[30])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[30]), "Swim and Surfwear", getSubCategoryId(subcategory[30])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[30]), "Yoga and Fitness", getSubCategoryId(subcategory[30])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[31]), "Helmets", getSubCategoryId(subcategory[31])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[31]), "Car Care", getSubCategoryId(subcategory[31])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[31]), "Exterior Accessories", getSubCategoryId(subcategory[31])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[31]), "Car Accessories", getSubCategoryId(subcategory[31])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[32]), "Musical Instruments", getSubCategoryId(subcategory[32])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[32]), "Books", getSubCategoryId(subcategory[32])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[32]), "Music", getSubCategoryId(subcategory[32])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[32]), "Movies", getSubCategoryId(subcategory[32])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[32]), "TV Series", getSubCategoryId(subcategory[32])));
//
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[33]), "Guitars", getSubCategoryId(subcategory[33])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[33]), "Keyboards & Pianos", getSubCategoryId(subcategory[33])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[33]), "Drums & Percussion", getSubCategoryId(subcategory[33])));
//        subCatSamples.add(new SubCategory(0, getCategoryIdForSource(subcategory[33]), "Instrument Accessories", getSubCategoryId(subcategory[33])));
//
//        //ADD TO SUBCATEGORY TABLE
//        for (SubCategory sc : subCatSamples) {
//            insertSubCategory(sc);
//        }
//    }

//    public void insertProductSamples() throws SQLException, FileNotFoundException {
//        ArrayList<Product> sampleProducts = new ArrayList<Product>();
//        SubCategory subcat = new SubCategory();
//        InputStream inputStream;
//        /*CLOTHING for women********************************************************/
//        subcat = getSubCatInfo(50);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Plains and Prints Thai Short Sleeve Dress",
//                        "Brand",
//                        "Short sleeve shift dress with mix prints in reverse color , geometrical blocking, and asymmetrical hem line",
//                        3,
//                        (float) 1898.00,
//                        "S|M|L|XL",
//                        "mixed prints in reverse color",
//                        "women",
//                        inputStream));
//
//        subcat = getSubCatInfo(50);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Get Laud! SD Maia Dress Dress",
//                        "Get Laud",
//                        "Sleeveless,Stylish and Fashionable to wear,"
//                        + "geometrical blocking, and asymmetrical hem line",
//                        1,
//                        (float) 799.00,
//                        "S",
//                        "Black",
//                        "women",
//                        inputStream
//                )
//        );
//        subcat = getSubCatInfo(50);
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Sugar Clothing Mel Dress",
//                        "Brandless",
//                        "Simply stylish, this gorgeousSugar clothing dress is a super flattering shape to show off your curves. "
//                        + "Team it with the right accessories for casual dressing, high sandals for nights out, "
//                        + "or add some court shoes for work wear that wows - the opportunities are endless with this wardrobe essential.",
//                        1,
//                        (float) 389.00,
//                        "FS",
//                        "White/Black",
//                        "women",
//                        inputStream
//                )
//        );
//        /*SHOES for women********************************************************/
//        subcat = getSubCatInfo(51);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "PONY Shooter Low Sneakers",
//                        "Pony",
//                        "The Shooter Low is PONY\\'s take on the all-American staple sneaker. Designed with the badass girl in mind, the print combines the girly floral and gritty skull prints.",
//                        3,
//                        (float) 2995.00,
//                        "6|7|8",
//                        "Skull Flower/Black",
//                        "women",
//                        inputStream));
//        subcat = getSubCatInfo(51);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "CLN 15G-LOKI Strappy Sandals",
//                        "CLN",
//                        "Seamless,Strappy flat sandal",
//                        4,
//                        (float) 999.00,
//                        "6|8|9",
//                        "Pink",
//                        "women",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(51);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Oli Unique Pointy Pumps",
//                        "Oli",
//                        "Stilletto Heels,Epitome of versatility and elegance",
//                        5,
//                        (float) 799.00,
//                        "5|6|7|8|9",
//                        "Beige",
//                        "women",
//                        inputStream
//                ));
//        /*BAGS for women********************************************************/
//        subcat = getSubCatInfo(52);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Oli Unique DSE058 Marian Tote Bag",
//                        "Oli",
//                        "Being chic is every girl's thing. Looking younger and youthful but still has fashion. "
//                        + "Anything you wear affects what you'll gonna look outside. "
//                        + "Look for something that can make you feel youthful but still suits your style. "
//                        + "Oli Unique DSE058 Marian Tote Bag (Gray) can be your great fashion ally. "
//                        + ",Made from high quality synthetic leather, this Oli Unique DSE058 Marian Tote Bag can provide durability and chicness. "
//                        + "It displays criss cross design and a cute locket which adds more trendy look to its user. ",
//                        7,
//                        (float) 549.00,
//                        "N/A",
//                        "Gray",
//                        "women",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(52);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Longchamp Neo Medium",
//                        "Longchamp",
//                        "New series \"LE PLAGE' popular finished modern addition has emerged. "
//                        + "Become the items in the scene with a removable shoulder strap, more convenience, more user-friendly."
//                        + "Lightly textured leather borders a sleek, classic tote that folds flat when not in use, making it perfect for travel.",
//                        5,
//                        (float) 13000.00,
//                        "N/A",
//                        "Black",
//                        "women",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(52);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Mango Saffiano Effect Satchel Bag",
//                        "Mango",
//                        "Update your accessories collection with this Saffiano Effect Satchel bag by Mango."
//                        + "Designed with a long adjustable strap, it has double handles, an adjustable, removable long strap and a zip fastening."
//                        + "Wear it with blouse and skirt ensembles for a smart finishing touch.",
//                        3,
//                        (float) 1199.00,
//                        "N/A",
//                        "Caramel",
//                        "women",
//                        inputStream
//                ));
//        /*CLOTHING for men********************************************************/
//        subcat = getSubCatInfo(53);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Lee Cooper Men's Polo Shirt with Full Print Details",
//                        "Lee",
//                        "Stocking up your closet with basic pieces is a must. "
//                        + "With that, you also need to make sure that these pieces --shirts, jeans, or shorts, don't have to be so plain and boring,"
//                        + "Look great whatever your age or build, and create a handsome casual vibe without the fuss. If you want to beef up your work-to-weekend wardrobe, "
//                        + "then a Lee Cooper Polo Shirt is your best choice. To make sure that your clothes last longer and continue to look good, you need to take care of them right. "
//                        + "So, wash your clothing either by machine or by hand instead of having it dry cleaned, so that it doesn’t shrink or expand. Use bleach-free products to ensure that"
//                        + " its color doesn’t change. By doing so, you’ll have a good-looking garment that will stand the test of time. ",
//                        1,
//                        (float) 1199.75,
//                        "S|M|L|XL",
//                        "Carmine Rose",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(53);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Freshgear Denim Pants",
//                        "Freshgear",
//                        "Pants,Clean finished,Denim",
//                        2,
//                        (float) 1200.00,
//                        "26|27|28|30|31",
//                        "Black",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(53);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Bobson Tapered Cargo Short",
//                        "Bobson",
//                        "Short,Four Pocket,With Belt",
//                        9,
//                        (float) 750.00,
//                        "30|31|32|33|34",
//                        "Blue",
//                        "men",
//                        inputStream
//                ));
//        /*BAGS for men********************************************************/
//        subcat = getSubCatInfo(54);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Crimson Mens Bag with Print",
//                        "Crimson",
//                        "With its clean and stylish aesthetic, spacious capacity, "
//                        + "and easy functionality, this sling bag looks good on the gym or on the streets.",
//                        7,
//                        (float) 599.75,
//                        "N/A",
//                        "Red|White|Blue",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(54);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Fancyqube Fashion Chest Pack Sports Canvas Man Bag Multifunction Outdoor Satchel Tide ",
//                        "Brandless",
//                        "Fancyqube Bag is important in our life, and  according to functional classification, there are wallets, cosmetic bags,"
//                        + " evening bags,handbags, shoulder bags, shoulder bags, messenger bags,traveling bags and versatile package. "
//                        + "Today women's bag far beyond its practical value, and to become the female apparel supporting an important part. "
//                        + "Currently, there are many styles of bags in the market, and to be using in different  way.",
//                        1,
//                        (float) 665.33,
//                        "N/A",
//                        "Gray",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(54);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Oli Trend DSE022 Plain Backpack",
//                        "Oli",
//                        "A dependable backpack need not be boring or understated. With the Oli Trend DSE022 Plain Backpack,"
//                        + " you can carry all your essentials in a cool and dependable bag that does the job. ",
//                        5,
//                        (float) 399.00,
//                        "N/A",
//                        "Blue",
//                        "men",
//                        inputStream
//                ));
//        /*SHOES for men********************************************************/
//        subcat = getSubCatInfo(55);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Oli Casual Mstyle 18 Boatshoes",
//                        "Oli",
//                        "A man's shoes are as important as his clothes for it could make or break his overall look. "
//                        + "Secure that stylish vibe, even on a normal day,These low cut sneakers feature comfortable insoles which improves mobility,"
//                        + "ensuring a great feel when you're on-the-move. ",
//                        2,
//                        (float) 899.00,
//                        "7|8|9|10|11",
//                        "Black",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(55);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "Safety Jogger Safetyboy Hi-Cut Safety Shoes",
//                        "Brandless",
//                        "Safety Jogger Safetyboy has a breathable and premoulded insole, which results in more ventilation and more comfort. "
//                        + "If you prefer extra strong and lightweight work shoes, choose Anti perforation SJ FLEX insole and Composite. "
//                        + "Safety Jogger Safetyboy has a breathable and premoulded insole, which results in more ventilation and more comfort. If you prefer extra strong and lightweight work shoes, choose Anti perforation SJ FLEX insole and Composite."
//                        + " These innovative technologies guarantee durability and flexibility while workingThese innovative technologies guarantee durability and flexibility while working",
//                        1,
//                        (float) 665.33,
//                        "7|8|9|10|11",
//                        "Black",
//                        "men",
//                        inputStream
//                ));
//        subcat = getSubCatInfo(55);
//        inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + "\\Women\\clothing.jpg"));
//        sampleProducts.add(
//                new Product(
//                        0,
//                        27,
//                        subcat.getCatId(),
//                        subcat.getSubCatId(),
//                        subcat.getSourceId(),
//                        "PENSHOPPE Men's Regular Flip Flops with All Over Print",
//                        "penshoppe",
//                        "Dressing down for those laid back times should not be as bad as putting on your boxers for a lunch out. You can still be stylish and casual at the same time. "
//                        + "It's okay to top off your look with a pair of flip flops when going for that laid-down style.Its design gives off a laid back vibe perfect for casual day-outs, or walk down the parks. "
//                        + "This pair exudes a statement you need for an on-the-go outfit. ",
//                        10,
//                        (float) 149.00,
//                        "7|8|9|10|11",
//                        "Navy Blue",
//                        "men",
//                        inputStream
//                ));
//        //        subcat = getSubCatInfo(<INSERT SUBCATEGORY_ID HERE>);
//        //       inputStream = new FileInputStream(new File(IMAGE_FILE_PATH + <INSERT PATH HERE>));
//        //        sampleProduct.add(new Product());
//        //        sampleProduct.add(new Product());
//        //        
//      
//        //ADD TO PRODUCT TABLE
//        for (Product p : sampleProducts) {
//            insertProduct(p);
//        }
//    }
}
