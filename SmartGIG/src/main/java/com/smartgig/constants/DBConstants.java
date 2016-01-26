package com.smartgig.constants;

public interface DBConstants {
	public static final String DRIVER = "com.mysql.jdbc.Driver"; // dynamic loading of driver
	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"; //location
																				// of
																				// an
																				// access
																				// dataBase
	public static final String DB_NAME = "smartGIG";
	public static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";";
	public static final String USE_DB = "USE " + DB_NAME + ";";
	public static final String[] category = { "Fashion", "Health & Beauty", "Watches | Jewelry | Sunglasses",
			"Mobiles & Tablets", "Computers & Laptops", "Tv | Audio/Video | Gaming,Gadgets", "Cameras", "Home & Living",
			"Home Appliances", "Toy , Kids & Babies", "Travel & Luggages", "Sports & | Automotives",
			"Media , Music & Books" };
	public static final String[] subcategory = { "Women", "Men", "Beauty for Her", "Foods Supplement", "Watches",
			"Jewellery", "Sunglasses", "Mobiles", "Mobile Accessories", "Laptops", "Storage", "PC Gaming",
			"Computer Accessories", "Televisions", "Audio Devices", "Gaming", "Video", "Wearable Technology",
			"All Cameras", "Video & Action Camera", "Camera Accessories", "Small Kitchen Appliances",
			"Large Home Appliances", "Garment Care", "Cooling & Heating", "Toys & Babies", "Babies", "Toys",
			"Travel"
			+ " & Luggage", "Travel Accessories", "Sports & Outdoors", "Automotives", "Media, Music & Books",
			"Musical Instruments" };
	public static final String CREATE_TB_USER = "CREATE TABLE IF NOT EXISTS user(" 
			+ "fbId VARCHAR(50) PRIMARY KEY,"
			+ "age VARCHAR(3)," 
			+ "birthDate DATE," 
			+ "gender VARCHAR(10)," 
			+ "fName VARCHAR(50),"
			+ "lName VARCHAR(50));";
	public static final String CREATE_TB_INTEREST = "CREATE TABLE IF NOT EXISTS interest("
			+ "interestId int PRIMARY KEY AUTO_INCREMENT," 
			+ "fbId VARCHAR(50)," 
			+ "token VARCHAR(50),"
			+ "positiveCnt int," 
			+ "negativeCnt int,"
			+ "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_PERSONALITY_PRODUCT = "CREATE TABLE IF NOT EXISTS personality_product("
			+ "personalityProdId int PRIMARY KEY AUTO_INCREMENT," 
			+ "prodId VARCHAR(50)," 
			+ "prodToken VARCHAR(50),"
			+ "isMusicLover  BOOLEAN," 
			+ "isSportsFan BOOLEAN," 
			+ "isFoodie BOOLEAN," 
			+ "isFashionFiend BOOLEAN,"
			+ "isBookWorm BOOLEAN," 
			+ "isOutdoorEntusiast BOOLEAN,"
			+ "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
    public static final String CREATE_TB_PERSONALITY = "CREATE TABLE IF NOT EXISTS personality("
            + "personalityId int PRIMARY KEY AUTO_INCREMENT,"
            + "fbId VARCHAR(50),"
            + "token VARCHAR(50),"
            + "isMusicLover  BOOLEAN,"
            + "isSportsFan BOOLEAN,"
            + "isFoodie BOOLEAN,"
            + "isFashionFiend BOOLEAN,"
            + "isBookWorm BOOLEAN,"
            + "isOutdoorEntusiast BOOLEAN,"
            + "weight LONG,"
            + "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_PERSONALITY_PERCENTAGE = "CREATE TABLE IF NOT EXISTS personality_percentage("
			+ "personality_percentageId int PRIMARY KEY AUTO_INCREMENT," 
			+ "personalityId int," + "fbId VARCHAR(50),"
			+ "musicLover  LONG," + "sportsFan LONG," 
			+ "bookWorm LONG,"
			+ "outdoorEnthusiast LONG," 
			+ "foodie LONG,"
			+ "CONSTRAINT FOREIGN KEY (personalityId) REFERENCES personality(personalityId),"
			+ "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_ADMIN = "CREATE TABLE IF NOT EXISTS admin("
			+ "adminId int PRIMARY KEY AUTO_INCREMENT,"
			+ "uname VARCHAR(20)," 
			+ "passwd VARCHAR(50));";
	public static final String CREATE_TB_CATEGORY = "CREATE TABLE IF NOT EXISTS category("
			+ "catId int PRIMARY KEY AUTO_INCREMENT," 
			+ "categoryName VARCHAR(50));";
	public static final String CREATE_TB_SUB_CATEGORY = "CREATE TABLE IF NOT EXISTS sub_category("
			+ "subcatId int PRIMARY KEY AUTO_INCREMENT," 
			+ "catId int," 
			+ "subcategoryName VARCHAR(50),"
			+ "sourceId int,"
			+ "CONSTRAINT FOREIGN KEY (catId) REFERENCES category(catId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_PRODUCT = "CREATE TABLE IF NOT EXISTS product("
			+ "prodId int PRIMARY KEY AUTO_INCREMENT," + "adminId int,"// FK
			+ "catId int,"// FK
			+ "subcatId int,"// FK
			+ "sourceId int," 
			+ "prodName VARCHAR(50)," 
			+ "prodBrand VARCHAR(50)," 
			+ "prodDesc VARCHAR(2000),"
			+ "quantity int,"
			+ "price float," 
			+ "size VARCHAR(10)," 
			+ "color VARCHAR(200)," 
			+ "prodImg mediumblob,"
			+ "CONSTRAINT FOREIGN KEY (adminId) REFERENCES admin(adminId),"
			+ "CONSTRAINT FOREIGN KEY (catId) REFERENCES category(catId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_PRODUCT_LOGS = "CREATE TABLE IF NOT EXISTS product_logs("
			+ "product_logsId int PRIMARY KEY AUTO_INCREMENT,"
			+ "giverFbId VARCHAR(50),"
			+ "receiverFbId VARCHAR(50),"
			+ "prodId int," 
			+ "entryDate DATE,"
			+ "prodQty int,"
			+ "CONSTRAINT FOREIGN KEY (giverFbId) REFERENCES user(fbId),"
			+ "CONSTRAINT FOREIGN KEY (receiverFbId) REFERENCES user(fbId),"
			+ "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_EVENT = "CREATE TABLE IF NOT EXISTS event("
			+ "eventId int PRIMARY KEY AUTO_INCREMENT," 
			+ "eventName VARCHAR(50));";
	public static final String CREATE_TB_CALENDAR = "CREATE TABLE IF NOT EXISTS calendar("
			+ "calendarId int PRIMARY KEY AUTO_INCREMENT," 
			+ "fbId VARCHAR(50)," 
			+ "eventId int,"
			+ "eventDate DATE,"
			+ "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId),"
			+ "CONSTRAINT FOREIGN KEY (eventId) REFERENCES event(eventId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TB_CALENDAR_EVENT = "CREATE TABLE IF NOT EXISTS calendar_event("
			+ "calendar_eventId int PRIMARY KEY AUTO_INCREMENT," 
			+ "calendarId int," 
			+ "eventId int,"
			+ "CONSTRAINT FOREIGN KEY (calendarId) REFERENCES calendar(calendarId),"
			+ "CONSTRAINT FOREIGN KEY (eventId) REFERENCES event(eventId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String DROP_ALL_ECOMMERCE_TABLE = "DROP TABLE IF EXISTS calendar_event;\n"
			+ "DROP TABLE IF EXISTS calendar;\n" + "DROP TABLE IF EXISTS event;\n"
			+ "DROP TABLE IF EXISTS product_logs;\n" + "DROP TABLE IF EXISTS product;\n"
			+ "DROP TABLE IF EXISTS category;\n" + "DROP TABLE IF EXISTS sub_category;\n"
			+ "DROP TABLE IF EXISTS admin;\n" + "DROP TABLE IF EXISTS personality_percentage;\n"
			+ "DROP TABLE IF EXISTS personality;\n" + "DROP TABLE IF EXISTS interest;\n" + "DROP TABLE IF EXISTS user;";
	public static final String IMAGE_FILE_PATH = "C:\\Users\\tmaeeh\\Desktop\\ecommerceIMG";
	public static final String CREATE_TB_STOPWORDS = "CREATE TABLE IF NOT EXISTS stopwords("
            + "stopwordId int PRIMARY KEY AUTO_INCREMENT,"
            + "stopword VARCHAR(50))";
    public static final String CREATE_TB_PRODUCT_KEYWORDS = "CREATE TABLE IF NOT EXISTS product_keywords("
            + "pKeywordId int PRIMARY KEY AUTO_INCREMENT,"
            + "productId int,"
            + "keyword VARCHAR(50),"
            + "frequency int,"
            + "status VARCHAR(30))";
	// Lauren - START
	public static final String CREATE_IDF_TABLE = "CREATE TABLE IF NOT EXISTS idf("
			+ "keywordID INT PRIMARY KEY AUTO_INCREMENT," 
			+ "keyword VARCHAR(255) NOT NULL," 
			+ "frequency INT,"
			+ "value DOUBLE," 
			+ "status VARCHAR(30));";
	public static final String CREATE_TF_IDF_ITEMS ="CREATE TABLE IF NOT EXISTS tf_idf_items("
            + "tf_idf_ID INT PRIMARY KEY AUTO_INCREMENT,"
            + "prodId INT,"
            + "keyword VARCHAR(255) NOT NULL,"
            + "value DOUBLE,"
            + "status VARCHAR(30),"
            + "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_TF_IDF_INTEREST = "CREATE TABLE IF NOT EXISTS tf_idf_interest("
            + "tf_idf_ID INT PRIMARY KEY AUTO_INCREMENT,"
            + "fbId VARCHAR(50),"
            + "keyword VARCHAR(255) NOT NULL,"
            + "value DOUBLE,"
            + "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_ITEM_LENGTH = "CREATE TABLE IF NOT EXISTS item_Length("
            + "item_lengthID INT PRIMARY KEY AUTO_INCREMENT,"
            + "prodId INT,"
            + "value DOUBLE,"
            + "CONSTRAINT FOREIGN KEY (prodID) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_INTEREST_LENTH = "CREATE TABLE IF NOT EXISTS interest_length("
            + "interest_lengthID INT PRIMARY KEY AUTO_INCREMENT,"
            + "fbId VARCHAR(50),"
            + "value DOUBLE,"
            + "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String CREATE_COSINE_SIM = "CREATE TABLE IF NOT EXISTS cos_Sim("
            + "cosSimID INT PRIMARY KEY AUTO_INCREMENT,"
            + "prodId INT,"
            + "fbId VARCHAR(50),"
            + "value DOUBLE);";
	public static final String SELECT_PRODUCT_KEYWORDS_STAT = "SELECT * FROM product_keywords WHERE status = ?";
	public static final String SELECT_COUNT_PRODUCT = "SELECT COUNT(*) FROM product";
	public static final String SELECT_IDF = "SELECT * FROM idf WHERE status=?";
	public static final String SELECT_PRODUCT = "SELECT * FROM product";
	public static final String SELECT_INTEREST = "SELECT * FROM interest WHERE status=?";
	public static final String SELECT_PRODUCT_KEYWORDS = "SELECT * FROM product_keywords WHERE prodId = ? AND keyword = ?";
	public static final String SELECT_COUNT_INTEREST = "SELECT COUNT(*) FROM interest WHERE fbId = ?";
	public static final String SELECT_IDF_KEY = "SELECT * FROM idf WHERE keyword = ?";
	public static final String SELECT_TF_IDF_INTEREST = "SELECT * FROM tf_idf_interest WHERE fbId = ? AND keyword = ?";
	public static final String UPDATE_TF_IDF_INTEREST = "UPDATE tf_idf_interest SET value=? WHERE fbId =? AND keyword=?";
	public static final String UPDATE_IDF = "UPDATE idf SET status=? WHERE keyword=?";
	public static final String INSERT_TF_IDF_INTEREST = "INSERT INTO tf_idf_interest (fbId,keyword,value) VALUES(?,?,?)";
	public static final String SELECT_TF_IDF_ITEMS = "SELECT * FROM tf_idf_items WHERE prodId = ? AND keyword = ?";
	public static final String UPDATE_TF_IDF_ITEMS = "UPDATE tf_idf_items SET value=? WHERE prodId =? AND keyword=?";
	public static final String INSERT_TF_IDF_ITEMS = "INSERT INTO tf_idf_items (prodId,keyword,value,status) VALUES(?,?,?,?)";
	public static final String SELECT_TF_IDF_ITEMS_ID = "SELECT * FROM  tf_idf_items WHERE prodId = ?";
	public static final String INSERT_ITEM_LENGTH = "INSERT INTO item_Length (prodId,value) VALUES(?,?);";
	public static final String INSERT_INTEREST_LENGTH = "INSERT INTO interest_Length (fbId,value) VALUES(?,?)";
	public static final String SELECT_TF_IDF_INTEREST_ID = "SELECT * FROM  tf_idf_interest WHERE fbId = ?";
	public static final String SELECT_ITEM_LENGTH = "SELECT * FROM item_length WHERE prodId = ?;";
	public static final String SELECT_INTEREST_LENGTH = "SELECT * FROM interest_length WHERE fbId = ?";
	public static final String INSERT_COS_SIM = "INSERT INTO cos_sim (prodId,fbId,value) VALUES(?,?,?)";
	public static final String SELECT_PRODUCT_ID = "SELECT * FROM product WHERE prodId=?";
	public static final String SELECT_COS_SIM = "SELECT * FROM cos_sim WHERE fbId=? ORDER BY value DESC";
	// Lauren - END
	// Lauren - addtnl
	public static final String CREATE_USER_CONTACTS ="CREATE TABLE IF NOT EXISTS user_contacts("
            + "contactId INT PRIMARY KEY AUTO_INCREMENT,"
            + "user_fbId VARCHAR(50),"
            + "contact_fbId VARCHAR(50),"
            + "CONSTRAINT FOREIGN KEY (user_fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE,"
            + "CONSTRAINT FOREIGN KEY (contact_fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
	public static final String INSERT_CALENDAR_EVENT = "INSERT INTO calendar (fbId,eventId,eventDate) VALUES(?,?,?)";
	// Tmae
	public static final String STOPWORDPATH = "D:\\THESIS\\SmartGIG\\stopwords.txt";

}
