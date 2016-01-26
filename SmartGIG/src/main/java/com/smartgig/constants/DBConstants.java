package com.smartgig.constants;

public interface DBConstants {
	public static final String DRIVER = "com.mysql.jdbc.Driver"; //dynamic loading of driver
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"; // location of an access dataBase
    public static final String DB_NAME = "smartGIG";
//    public static final String DB_NAME_ECOMMERCE = "smartGIGEcommerce";
//    public static final String CREATE_DB_ECOMMERCE = "CREATE DATABASE IF NOT EXISTS " + DB_NAME_ECOMMERCE + ";";
    public static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";";
    public static final String USE_DB = "USE " + DB_NAME + ";";
    public static final String DELIMETER = "' .,!|/\\";
//    public static final String STOPWORDPATH = "D:\\THESIS\\SmartGIG\\stopwords.txt";
//    public static final String[] category = {"Fashion", "Health & Beauty", "Watches | Jewelry | Sunglasses",
//        "Mobiles & Tablets", "Computers & Laptops", "Tv | Audio/Video | Gaming,Gadgets", "Cameras", "Home & Living",
//        "Home Appliances", "Toy , Kids & Babies", "Travel & Luggages", "Sports & | Automotives", "Media , Music & Books"};
//    public static final String[] subcategory = {"Women", "Men", "Beauty for Her", "Foods Supplement",
//        "Watches", "Jewellery", "Sunglasses", "Mobiles", "Mobile Accessories", "Laptops", "Storage", "PC Gaming",
//        "Computer Accessories", "Televisions", "Audio Devices", "Gaming", "Video", "Wearable Technology", "All Cameras",
//        "Video & Action Camera", "Camera Accessories", "Small Kitchen Appliances", "Large Home Appliances", "Garment Care",
//        "Cooling & Heating", "Toys & Babies", "Babies", "Toys", "Travel & Luggage", "Travel Accessories",
//        "Sports & Outdoors", "Automotives", "Media, Music & Books", "Musical Instruments"};
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
//    public static final String CREATE_TB_PERSONALITY_PRODUCT = "CREATE TABLE IF NOT EXISTS personality("
//            + "personalityProdId int PRIMARY KEY AUTO_INCREMENT,"
//            + "prodId VARCHAR(50),"
//            + "prodtoken VARCHAR(50),"
//            + "isMusicLover  BOOLEAN,"
//            + "isSportsFan BOOLEAN,"
//            + "isFoodie BOOLEAN,"
//            + "isFashionFiend BOOLEAN,"
//            + "isBookWorm BOOLEAN,"
//            + "isOutdoorEntusiast BOOLEAN,"
//            + "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
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
            + "personalityId int,"
            + "fbId VARCHAR(50),"
            + "musicLover  LONG,"
            + "sportsFan LONG,"
            + "bookWorm LONG,"
            + "outdoorEnthusiast LONG,"
            + "foodie LONG,"
            + "CONSTRAINT FOREIGN KEY (personalityId) REFERENCES personality(personalityId),"
            + "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId) ON UPDATE CASCADE ON DELETE CASCADE);";
//    public static final String CREATE_TB_ADMIN = "CREATE TABLE IF NOT EXISTS admin("
//            + "adminId int PRIMARY KEY AUTO_INCREMENT,"
//            + "uname VARCHAR(20),"
//            + "passwd VARCHAR(50));";
//    public static final String CREATE_TB_CATEGORY = "CREATE TABLE IF NOT EXISTS category("
//            + "catId int PRIMARY KEY AUTO_INCREMENT,"
//            + "categoryName VARCHAR(50));";
//    public static final String CREATE_TB_SUB_CATEGORY = "CREATE TABLE IF NOT EXISTS sub_category("
//            + "subcatId int PRIMARY KEY AUTO_INCREMENT,"
//            + "catId int,"
//            + "subcategoryName VARCHAR(50),"
//            + "sourceId int,"
//            + "CONSTRAINT FOREIGN KEY (catId) REFERENCES category(catId) ON UPDATE CASCADE ON DELETE CASCADE);";
//    public static final String CREATE_TB_PRODUCT = "CREATE TABLE IF NOT EXISTS product("
//            + "prodId int PRIMARY KEY AUTO_INCREMENT,"
//            + "adminId int,"//FK
//            + "catId int,"//FK
//            + "subcatId int,"//FK
//            + "sourceId int,"
//            + "prodName VARCHAR(100),"
//            + "prodBrand VARCHAR(50),"
//            + "prodDesc VARCHAR(2000),"
//            + "quantity int,"
//            + "price float,"
//            + "size VARCHAR(50),"
//            + "color VARCHAR(200),"
//            + "gender VARCHAR(20),"
//            + "prodImg mediumblob,"
//            + "CONSTRAINT FOREIGN KEY (adminId) REFERENCES admin(adminId),"
//            + "CONSTRAINT FOREIGN KEY (catId) REFERENCES category(catId) ON UPDATE CASCADE ON DELETE CASCADE);";
//    public static final String CREATE_TB_PRODUCT_LOGS = "CREATE TABLE IF NOT EXISTS product_logs("
//            + "product_logsId int PRIMARY KEY AUTO_INCREMENT,"
//            + "giverFbId VARCHAR(50),"
//            + "receiverFbId VARCHAR(50),"
//            + "prodId int,"
//            + "entryDate DATE,"
//            + "prodQty int,"
//            + "CONSTRAINT FOREIGN KEY (giverFbId) REFERENCES user(fbId),"
//            + "CONSTRAINT FOREIGN KEY (receiverFbId) REFERENCES user(fbId),"
//            + "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
    public static final String CREATE_TB_EVENT = "CREATE TABLE IF NOT EXISTS event("
            + "eventId int PRIMARY KEY AUTO_INCREMENT,"
            + "eventName VARCHAR(50));";
    public static final String CREATE_TB_CALENDAR = "CREATE TABLE IF NOT EXISTS calendar("
            + "calendarId int PRIMARY KEY AUTO_INCREMENT,"
            + "fbId VARCHAR(50),"
            + "eventId int,"
            + "CONSTRAINT FOREIGN KEY (fbId) REFERENCES user(fbId),"
            + "CONSTRAINT FOREIGN KEY (eventId) REFERENCES event(eventId) ON UPDATE CASCADE ON DELETE CASCADE);";
    public static final String CREATE_TB_CALENDAR_EVENT = "CREATE TABLE IF NOT EXISTS calendar_event("
            + "calendar_eventId int PRIMARY KEY AUTO_INCREMENT,"
            + "calendarId int,"
            + "eventId int,"
            + "CONSTRAINT FOREIGN KEY (calendarId) REFERENCES calendar(calendarId),"
            + "CONSTRAINT FOREIGN KEY (eventId) REFERENCES event(eventId) ON UPDATE CASCADE ON DELETE CASCADE);";
    public static final String DROP_ALL_ECOMMERCE_TABLE = "DROP TABLE IF EXISTS calendar_event;\n"
            + "DROP TABLE IF EXISTS calendar;\n"
            + "DROP TABLE IF EXISTS event;\n"
            + "DROP TABLE IF EXISTS product_logs;\n"
            + "DROP TABLE IF EXISTS product;\n"
            + "DROP TABLE IF EXISTS category;\n"
            + "DROP TABLE IF EXISTS sub_category;\n"
            + "DROP TABLE IF EXISTS admin;\n"
            + "DROP TABLE IF EXISTS personality_percentage;\n"
            + "DROP TABLE IF EXISTS personality;\n"
            + "DROP TABLE IF EXISTS interest;\n"
            + "DROP TABLE IF EXISTS user;";
//    public static final String CREATE_TB_STOPWORDS = "CREATE TABLE IF NOT EXISTS stopwords("
//            + "stopwordId int PRIMARY KEY AUTO_INCREMENT,"
//            + "stopword VARCHAR(50))";
//    public static final String CREATE_TB_PRODUCT_KEYWORDS = "CREATE TABLE IF NOT EXISTS product_keywords("
//            + "pKeywordId int PRIMARY KEY AUTO_INCREMENT,"
//            + "productId int,"
//            + "keyword VARCHAR(50),"
//            + "frequency int,"
//            + "status VARCHAR(30))";
//    public static final String IMAGE_FILE_PATH = "C:\\Users\\tmaeeh\\Desktop\\ecommerceIMG";
}
