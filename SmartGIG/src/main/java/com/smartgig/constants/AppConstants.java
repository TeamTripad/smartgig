package com.smartgig.constants;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

import com.smartgig.database.dao.Admin;
import com.smartgig.database.dao.Category;
import com.smartgig.database.dao.SubCategory;

public interface AppConstants {
	public static final String PATH_SMARTGIG="views/SmartGIG/";
	public static final String PATH_ECOMMERCE="views/E-commerce/";
	public static final String PATH_ECOMMERCE_ADMIN="views/E-commerce/Admin/";
	
	/**ADMIN : CATEGORY**/
	public static final int[] ECOMMERCE_ADMIN_STATE = { 0,//default, upon [admin login] or upon clicking [dashboard]
														1,//view main category
														2,//view sub category
														3//item
													  };
	//DUMMY DATA
	public static final Admin DUMMY_ADMIN = new Admin(0,"tripad", DigestUtils.sha512Hex("tripad"));
	public static final ArrayList<Category> DUMMY_CATEGORY_LIST = new ArrayList<Category>() {{
												        add( new Category(1, "Category1") );
												        add( new Category(2, "Category2") );
												        add( new Category(3, "Category3") );
												        add( new Category(4, "Category4") );
												        add( new Category(5, "Category5") );
												        add( new Category(6, "Category6") );
												        add( new Category(7, "Category7") );
												        add( new Category(8, "Category8") );
												        add( new Category(9, "Category9") );
												        add( new Category(10, "Category10") );
												    }};
												    
	public static final ArrayList<SubCategory> DUMMY_SUB_CATEGORY_LIST = new ArrayList<SubCategory>() {{
	    add( new SubCategory(1,1, "SubCategory1", 0) );
	    add( new SubCategory(2,2, "SubCategory2", 0) );
	    add( new SubCategory(3,3, "SubCategory3", 0) );
	    add( new SubCategory(4,4, "SubCategory4", 0) );
	    add( new SubCategory(5,5, "SubCategory5", 0) );
	    add( new SubCategory(6,6, "SubCategory6", 0) );
	    add( new SubCategory(7,7, "SubCategory7", 0) );
	    add( new SubCategory(8,8, "SubCategory8", 0) );
	    add( new SubCategory(9,9, "SubCategory9", 0) );
	    add( new SubCategory(10,10, "SubCategory10", 0) );
	}};
	
}