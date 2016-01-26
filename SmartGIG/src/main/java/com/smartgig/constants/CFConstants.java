/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartgig.constants;


import com.smartgig.database.dao.UserPersonality;
import com.smartgig.database.dto.UserInfo;

import java.util.ArrayList;

import com.smartgig.database.dao.CF_User;
import com.smartgig.database.dao.C_Item;
/**
 *
 * @author jzah
 */
public interface CFConstants {
    /**DUMMY DATA START**********************************************/
    public static final String[] USERS_SAMPLE = {"User A", "User B", "User C", "User D"};
    public static final String[] ITEMS_SAMPLE = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    
    public static final int[][] UI_MATRIX_SAMPLE = { 
                                                {4, 4, 1, 4, 3},
                                                {2, 1, 4, 2, 5},
                                                {3, 1, 3, 2, 1},
                                                {5, 4, 2, -1, 3},//-1 means ?, the user has not rated the item
                                            };
    /**SET BY CLAIRE @DB*/
    public static final ArrayList<UserInfo> USERS = new ArrayList<UserInfo>() {{
                                                        add( new UserInfo( "u1", "Early Adulthood","12/12/1995","Female","Trixie Mae", "Burla" ));
                                                        add( new UserInfo( "u2", "Early Adulthood","12/23/1995","Female","Lauren Christy", "Ponce" ));
                                                        add( new UserInfo( "u3", "Early Adulthood","2/19/1994","Female","Joana Claire", "Alegado" ));
                                                        add( new UserInfo( "u4", "Adolescence","1/10/1996","Female","Jessah", "Daculan" ));
                                                        add( new UserInfo( "u5", "Early Adulthood","2/14/1993","Female","Barbie", "Gurl" ));
                                                    }};
    /**SET BY CLAIRE @DB*/
    public static final ArrayList<UserPersonality> USER_PERSONALITY = new ArrayList<UserPersonality>(){{//model -> from db : Personality
                                                        add( new UserPersonality( 0,"u1", 133, 137, -49, 57, 11, 162));
                                                        add( new UserPersonality( 1,"u2", 129, 224, -220, -75, 218, -195));
                                                        add( new UserPersonality( 2,"u3", 2, 12, -196, 227, 221, 192));
                                                        add( new UserPersonality( 3,"u4", 45, -155, -107, 71, -234, -74));
                                                        add( new UserPersonality( 4,"u5", 180, -248, -144, 132, 241, 186));
                                                    }};
    /**SET BY LAUREN*/
    public static final ArrayList<CF_User> CF_USER = new ArrayList<CF_User>(){{
                                                        add( new CF_User(0, "", "", 0, 0, 0.105, 0.888, -0.038, 0.0, 0.0, 0.0) );
                                                        add( new CF_User(0, "", "", 0, 0, 0.578, 0.959, 0.398, 0.0, 0.0, 0.0) );
                                                        add( new CF_User(0, "", "", 0, 0, -0.72, 0.863, 0.206, 0.0, 0.0, 0.0) );
                                                        add( new CF_User(0, "", "", 0, 0, 0.468, -0.927, 0.167, 0.0, 0.0, 0.0) );
                                                        add( new CF_User(0, "", "", 0, 0, 0.79, 0.897, -0.331, 0.0, 0.0, 0.0) );
                                                    }};
    /**RATING IS COMPUTED BASED ON [USER_TOKENS&USER_PERSONALITY : PRODUCT_TOKENS&PRODUCT_PERSONALITY]
       CALCULATED BY LAUREN
       WEIGHT CONVERSION TO RATINGS BY JZAH*/
    public static final ArrayList<ArrayList<C_Item>> C_ITEM_RATINGS = new ArrayList<ArrayList<C_Item>>(){{
                                                    //RATINGS OF U1 : ROW
                                                    add(
                                                     new ArrayList<C_Item>(){{
                                                         add(new C_Item(0, "u1", 0, 2));//COL
                                                         add(new C_Item(1, "u1", 1, 1));//COL
                                                         add(new C_Item(2, "u1", 2, 2));//COL
                                                         add(new C_Item(3, "u1", 3, 5));//COL
                                                         add(new C_Item(4, "u1", 4, 1));//COL
                                                     }} 
                                                    );
                                                    //RATINGS OF U2
                                                    add(
                                                     new ArrayList<C_Item>(){{
                                                         add(new C_Item(5, "u2", 0, 2));
                                                         add(new C_Item(6, "u2", 1, 1));
                                                         add(new C_Item(7, "u2", 2, 5));
                                                         add(new C_Item(8, "u2", 3, 5));
                                                         add(new C_Item(9, "u2", 4, 3));
                                                     }} 
                                                    );
                                                    //RATINGS OF U3
                                                    add(
                                                     new ArrayList<C_Item>(){{
                                                         add(new C_Item(10, "u3", 0, 2));//2
                                                         add(new C_Item(11, "u3", 1, 5));//5
                                                         add(new C_Item(12, "u3", 2, 4));//4
                                                         add(new C_Item(13, "u3", 4, 0));//0
                                                         add(new C_Item(14, "u3", 5, 4));//4
                                                     }} 
                                                    );
                                                    //RATINGS OF U4
                                                    add(
                                                     new ArrayList<C_Item>(){{
                                                         add(new C_Item(15, "u4", 0, 4));
                                                         add(new C_Item(16, "u4", 1, 3));
                                                         add(new C_Item(17, "u4", 2, 3));
                                                         add(new C_Item(18, "u4", 3, 0));
                                                         add(new C_Item(19, "u4", 4, 0));
                                                     }} 
                                                    );
                                                    //RATINGS OF U5
                                                    add(
                                                     new ArrayList<C_Item>(){{
                                                         add(new C_Item(20, "u5", 1, 5));//0
                                                         add(new C_Item(21, "u5", 2, EMPTY_RATING));//3
                                                         add(new C_Item(22, "u5", 3, 4));
                                                         add(new C_Item(23, "u5", 4, EMPTY_RATING));//EMPTY_RATING
                                                         add(new C_Item(24, "u5", 5, 3));
                                                     }} 
                                                    );
                                                }};
    /**DUMMY DATA END************************************************/
    public static final int RATING = 5;
    public static final int NUMBER_OF_ATTRIBUTES = 11;
    
    public static final double EMPTY_RATING = -6;//TEMP
    public static final int A_USER_INDEX = 4;//TEMP
    public static final String A_USER = "u5";//TEMP
    public static final String[] AGE_STATUS={ "N/A",
                                            "Infancy",
                                            "Early Childhood",
                                            "Preschool Age",
                                            "School Age",
                                            "Adolescence",
                                            "Early Adulthood",
                                            "Adulthood",
                                            "Maturity"};
}
