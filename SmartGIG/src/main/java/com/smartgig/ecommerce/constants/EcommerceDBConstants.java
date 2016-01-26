package com.smartgig.ecommerce.constants;

public interface EcommerceDBConstants {
	public static final String DRIVER = "com.mysql.jdbc.Driver"; //dynamic loading of driver
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"; // location of an access dataBase
    public static final String DB_NAME_ECOMMERCE = "smartGIGEcommerce";
    public static final String CREATE_DB_ECOMMERCE = "CREATE DATABASE IF NOT EXISTS " + DB_NAME_ECOMMERCE + ";";
    public static final String USE_DB = "USE " + DB_NAME_ECOMMERCE + ";";
    public static final String[] category = {"Fashion", "Health & Beauty", "Watches | Jewelry | Sunglasses",
            "Mobiles & Tablets", "Computers & Laptops", "Tv | Audio/Video | Gaming,Gadgets", "Cameras", "Home & Living",
            "Home Appliances", "Toy , Kids & Babies", "Travel & Luggages", "Sports & | Automotives", "Media , Music & Books"};
   public static final String[] subcategory = {"Women", "Men", "Beauty for Her", "Foods Supplement",
            "Watches", "Jewellery", "Sunglasses", "Mobiles", "Mobile Accessories", "Laptops", "Storage", "PC Gaming",
            "Computer Accessories", "Televisions", "Audio Devices", "Gaming", "Video", "Wearable Technology", "All Cameras",
            "Video & Action Camera", "Camera Accessories", "Small Kitchen Appliances", "Large Home Appliances", "Garment Care",
            "Cooling & Heating", "Toys & Babies", "Babies", "Toys", "Travel & Luggage", "Travel Accessories",
            "Sports & Outdoors", "Automotives", "Media, Music & Books", "Musical Instruments"};
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
           + "prodId int PRIMARY KEY AUTO_INCREMENT,"
           + "adminId int,"//FK
           + "catId int,"//FK
           + "subcatId int,"//FK
           + "sourceId int,"
           + "prodName VARCHAR(100),"
           + "prodBrand VARCHAR(50),"
           + "prodDesc VARCHAR(2000),"
           + "quantity int,"
           + "price float,"
           + "size VARCHAR(50),"
           + "color VARCHAR(200),"
           + "gender VARCHAR(20),"
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
   public static final String CREATE_TB_PERSONALITY = "CREATE TABLE IF NOT EXISTS personality("
           + "personalityId int PRIMARY KEY AUTO_INCREMENT,"
           + "prodId VARCHAR(50),"
           + "isMusicLover  BOOLEAN,"
           + "isSportsFan BOOLEAN,"
           + "isFoodie BOOLEAN,"
           + "isFashionFiend BOOLEAN,"
           + "isBookWorm BOOLEAN,"
           + "isOutdoorEntusiast BOOLEAN,"
           + "weight LONG,"
           + "CONSTRAINT FOREIGN KEY (prodId) REFERENCES product(prodId) ON UPDATE CASCADE ON DELETE CASCADE);";
   public static final String CREATE_TB_STOPWORDS = "CREATE TABLE IF NOT EXISTS stopwords("
           + "stopwordId int PRIMARY KEY AUTO_INCREMENT,"
           + "stopword VARCHAR(50))";
   public static final String CREATE_TB_PRODUCT_KEYWORDS = "CREATE TABLE IF NOT EXISTS product_keywords("
           + "pKeywordId int PRIMARY KEY AUTO_INCREMENT,"
           + "productId int,"
           + "keyword VARCHAR(50),"
           + "frequency int,"
           + "status VARCHAR(30))";
   public static final String IMAGE_FILE_PATH = "C:\\Users\\tmaeeh\\Desktop\\ecommerceIMG";
   public static final String STOPWORDPATH = "D:\\THESIS\\SmartGIG\\stopwords.txt";
}
