package com.smartgig.recommender.collaborative;

import com.smartgig.constants.CFConstants;
import com.smartgig.database.dao.CF_User;
import com.smartgig.database.dao.C_Item;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.smartgig.database.dao.UserPersonality;

import com.smartgig.database.dto.UserInfo;

/**
 *
 * @author jzah
 */ 
public class CFRecommender implements CFConstants{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {   
    /**PROFILE START********************************************************/
        UserInfo a;
        //ArrayList<UserInfo> u;
        int aIndex=0; int[] u={};//TODO -> DB OPERATION : supposed to be fbID of type String
        ArrayList<UserInfo> users = USERS;
        ArrayList<UserPersonality> uprofile = USER_PERSONALITY;
        ArrayList<ArrayList<C_Item>> itemRatings = C_ITEM_RATINGS;
        ArrayList<CF_User> userToUser = new ArrayList<>();

        String aId = USERS.get(aIndex).getFbId(), dateToday="";//TODO : this will soon be used for future queries | user acion triggered

    //TODO -> DB OPERATION : update age every run, check for changes
    /**STEP 1 : Get user vector | a : u[i] | {AgeStatus, Gender}*/
        /**STEP 1.1 : query {a : active user} UserInfo*/
        aIndex = 4;//aId
        a = USERS.get(aIndex);//ACTIVE USER
        /**STEP 1.2 : query {u : ALL other users} UserInfo*/
        u = new int[]{0,1,2,3,4};//uId{}
        /**STEP 1.3 : { a VS u[i] }*/
        /**TODO -> DB OPERATION : set ALL users' ageStatus;*/
        userToUser = getDegreesOfCorrelationBetweenUsers(a, u);
        userToUser = setOtherAttributes(userToUser);//TEMP
        
        printUserInfo(users);
        printCF_USER(userToUser);

        uprofile = getPersonalityPercentage(uprofile);
        printUserPersonality(uprofile);
        printCItems(itemRatings);
        /**TODO -> DB OPERATION : save user[a] VS user[u] vector to USER_CF PREDICTION*/
    /**STEP 2 : */
        
    /**PROFILE END**********************************************************/
        
    /**SAMPLE PCC START**********************************************/     
        //a : the ACTIVE user
        //u : the OTHER user
        //n : the # of items rated by ALL USERS
        //r'a : AVERAGE ratings totalRatings(USER[?])/n
        //w(a,u) : degree of correlation between USER[a] and USER[u]
        //p(a,i) : prediction for the user on item i
        
        /**STEP 3: w(a,u)*/
        calculateItemRatingPearson(itemRatings, userToUser);
        System.out.println("----------PEARSON");
        printCF_USER(userToUser);
        
        calculateUserPearson(userToUser, uprofile);
        System.out.println("----------PEARSON");
        printCF_USER(userToUser);
        
        /**STEP 4: p(a,i)*/
        calculatePrediction(userToUser, itemRatings);
        System.out.println("----------PREDICTION");
        printCItems(itemRatings);
    /**SAMPLE PCC END************************************************/
    }
    
    public static void cfRecommenderTest(){
    	/**PROFILE START********************************************************/
        UserInfo a;
        //ArrayList<UserInfo> u;
        int aIndex=0; int[] u={};//TODO -> DB OPERATION : supposed to be fbID of type String
        ArrayList<UserInfo> users = USERS;
        ArrayList<UserPersonality> uprofile = USER_PERSONALITY;
        ArrayList<ArrayList<C_Item>> itemRatings = C_ITEM_RATINGS;
        ArrayList<CF_User> userToUser = new ArrayList<>();

        String aId = USERS.get(aIndex).getFbId(), dateToday="";//TODO : this will soon be used for future queries | user acion triggered

	    //TODO -> DB OPERATION : update age every run, check for changes
	    /**STEP 1 : Get user vector | a : u[i] | {AgeStatus, Gender}*/
	        /**STEP 1.1 : query {a : active user} UserInfo*/
	        aIndex = 4;//aId
	        a = USERS.get(aIndex);//ACTIVE USER
	        /**STEP 1.2 : query {u : ALL other users} UserInfo*/
	        u = new int[]{0,1,2,3,4};//uId{}
	        /**STEP 1.3 : { a VS u[i] }*/
	        /**TODO -> DB OPERATION : set ALL users' ageStatus;*/
	        userToUser = getDegreesOfCorrelationBetweenUsers(a, u);
	        userToUser = setOtherAttributes(userToUser);//TEMP
	        
	        printUserInfo(users);
	        printCF_USER(userToUser);
	
	        uprofile = getPersonalityPercentage(uprofile);
	        printUserPersonality(uprofile);
	        printCItems(itemRatings);
	        /**TODO -> DB OPERATION : save user[a] VS user[u] vector to USER_CF PREDICTION*/
	    /**STEP 2 : */
	        
	    /**PROFILE END**********************************************************/
	        
	    /**SAMPLE PCC START**********************************************/     
	        //a : the ACTIVE user
	        //u : the OTHER user
	        //n : the # of items rated by ALL USERS
	        //r'a : AVERAGE ratings totalRatings(USER[?])/n
	        //w(a,u) : degree of correlation between USER[a] and USER[u]
	        //p(a,i) : prediction for the user on item i
	        
	        /**STEP 3: w(a,u)*/
	        calculateItemRatingPearson(itemRatings, userToUser);
	        System.out.println("----------PEARSON");
	        printCF_USER(userToUser);
	        
	        calculateUserPearson(userToUser, uprofile);
	        System.out.println("----------PEARSON");
	        printCF_USER(userToUser);
	        
	        /**STEP 4: p(a,i)*/
	        calculatePrediction(userToUser, itemRatings);
	        System.out.println("----------PREDICTION");
	        printCItems(itemRatings);
	    /**SAMPLE PCC END************************************************/
	}
    
    public static ArrayList<CF_User> getDegreesOfCorrelationBetweenUsers(UserInfo a, int[] uAddress){
        ArrayList<CF_User> w = new ArrayList<>();
        
        for(int address : uAddress){//supposed to be fbId for ALL other users
            UserInfo ui = USERS.get(address);//query user u == fbId
            UserPersonality upi= USER_PERSONALITY.get(address);//supposed to be fbId
            
            int ageStat=0, gender=0;
            if(ui.getAgeStatus().contentEquals(a.getAgeStatus())){
                ageStat = 1;
            }
            if(ui.getGender().contentEquals(a.getGender())){
                gender = 1;
            }
            
            w.add(new CF_User(  address,
                                a.getFbId(),
                                ui.getFbId(),
                                ageStat,
                                gender,
                                0.0,
                                0.0,
                                0.0,
                                0.0,
                                0.0,
                                0.0));
        }
        return w;
    }
    /**TO DO @DB*/
    public static ArrayList<UserPersonality> getPersonalityPercentage(ArrayList<UserPersonality> userPersonality){
        for(UserPersonality up : userPersonality){
            double total=0.0;
            //TODO ->DB :  query sum of personality weight of user
            total = up.getBookWormWeight()+up.getFashionFiendWeight()+up.getFoodieWeight()
                    +up.getMusicLoverWeight()+up.getOutdoorEnthusiastWeight()+up.getSportsFanWeight();
            
            //TODO ->DB: save or update each personaity's percentage
            up.setBookWormPercentage(up.getBookWormWeight()/total);
            up.setFashionFiendPercentage(up.getFashionFiendWeight()/total);
            up.setFoodiePercentage(up.getFoodieWeight()/total);
            up.setMusicLoverPercentage(up.getMusicLoverWeight()/total);
            up.setOutdoorEnthusiastPercentage(up.getOutdoorEnthusiastWeight()/total);
            up.setSportsFanPercentage(up.getSportsFanWeight()/total);
        }
        return userPersonality;
    }
    public static ArrayList<CF_User> setOtherAttributes(ArrayList<CF_User> cfUser){
        for(int i=0; i<cfUser.size(); i++){
            CF_User cfu = cfUser.get(i);
            cfu.setFavoritesCosSim(CF_USER.get(i).getFavoritesCosSim());
            cfu.setHobbiesCosSim(CF_USER.get(i).getHobbiesCosSim());
            cfu.setItemRatingPearson(CF_USER.get(i).getItemRatingPearson());
        }
        return cfUser;
    }
    public static String getDateToday(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");//yyyy/MM/dd hh:mm:ss
        Date date = new Date();
                
        return dateFormat.format(date);
    }
    /**
     * Discretize age based on Erikson's Stages of psychosocial development
     */
    public static String discretizeAge(double age){
        String s = "";
     
        if(age<0){ s=AGE_STATUS[0];}
        else if(age>=0 && age<=1.5){ s=AGE_STATUS[1];}//0-1.5 years : Infancy
        else if(age>=1.6 && age<=4.5){ s=AGE_STATUS[2]; }//1.6-4.5 years : Early Childhood
        else if(age>=4.6 && age<=5.5){ s=AGE_STATUS[3]; }//4.6-5.5 years : Preschool Age
        else if(age>=5.6 && age<13){ s=AGE_STATUS[4]; }//5.6-12 years : School Age
        else if(age>=13 && age<20){ s=AGE_STATUS[5]; }//13-19 years : Adolescence
        else if(age>=20 && age<40){s=AGE_STATUS[6]; }//20-39 years : Early Adulthood
        else if(age>=40 && age<65){ s=AGE_STATUS[7]; }//40-64 years : Adulthood
        else if(age>=65){ s=AGE_STATUS[8]; }//65 above : Maturity
        
        return s;
    }
    public static double[] discretizeRatings(double[] weights, double min, double max){
        double[] rating = new double[weights.length];
        double over = max-(min-1);
        int cnt=0;
        
        for(double w : weights){
            if (w==min || w==max){
                rating[cnt++] = Math.round((w-(min-1))/over*RATING);
            }else{
                rating[cnt++] = Math.round((w-min)/over*RATING);
            }
        }
        return rating;
    }
    public static double getMinimumWeight(double[] weights){
        double min=weights[0];
       
        for(double w : weights)
        {
            if(w<min){
                min=w;
            }
        }
       
        return min;
    }
    public static double getMaximumWeight(double[] weights){
        double max=weights[0];
        
        for(double w : weights)
        {
            if(w>max){
                max=w;
            }
        }
        
        return max;
    }
    
    public static int calculateN(int[][] ui_matrix){
        int n = 0, cnt=0, rSize=USERS_SAMPLE.length, cSize=ITEMS_SAMPLE.length;
        
        for(int itemCol=0; itemCol<cSize; itemCol++)
        {
            for(int userRow=0; userRow<rSize; userRow++)
            {
                if(ui_matrix[userRow][itemCol]>0)
                {
                    cnt++;
                }
            }
            
            if(cnt==rSize) n++;
            cnt=0;
        }
        
        return n;
    }
    public static double[] calculateRA(int[][] ui_matrix, int n){
        int size = ui_matrix.length, cnt=0;
        double raMatrix[]=new double[size];
        
        for(int[] uMatrix : ui_matrix)
        {
            int total = 0;
            for(int rating : uMatrix)
            {
                total+=rating;
            } 
            raMatrix[cnt++] = (double)total/(double)n;
            total=0;
        }
        return raMatrix;
    }
    /**
     * @param userPersonality
     * @param cItemRatings
     * @param cfUser
     * @return
     */
    public static void calculateItemRatingPearson(ArrayList<ArrayList<C_Item>> cItemRatings,ArrayList<CF_User> cfUser){
        double[] pearson = {};
        int n =0, colSize=cItemRatings.get(0).size(), cnt=0, index=0;
        int[] bitVector = new int[colSize];
        double[] ratings = new double[colSize];
        
        /**STEP 1 : GET n  */
        bitVector = findBitVectorItem(cItemRatings);
        n = getN(bitVector);
        System.out.println("---------BIT VECTOR");
        printArray(bitVector);
        
        /**STEP 2 : r'a(USER[X]) */
        for(ArrayList<C_Item> cirs : cItemRatings){
            for(C_Item cir: cirs){
                //query all the same fb_id ex: u1==u1
                ratings[cnt++]=cir.getRating();
            }
            cfUser.get(index++).setAverage(calculateAverage(ratings, bitVector, n));
            cnt=0;
        }index=0;
        
        /**STEP 3: w(a,u)*/
        //a : Active user
        CF_User cfuA = cfUser.get(A_USER_INDEX);
        ArrayList<C_Item> ciA = cItemRatings.get(A_USER_INDEX);
        double[] aRatings = new double[ciA.size()];
        for(C_Item c : ciA){
            aRatings[index++] = c.getRating();
        }index=0;
        
        ratings = new double[colSize];
        for(ArrayList<C_Item> cirs : cItemRatings){
            for(C_Item cir: cirs){
                //if(!cir.getFbId().contentEquals(A_USER)){
                    ratings[cnt++]=cir.getRating();
                //}
            }cnt=0;
            cfUser.get(index).setItemRatingPearson(calculateDegreeOfCorrelation(cfUser.get(index).getAverage(), cfuA.getAverage(), ratings, aRatings));
            index++;
        }index=0;
        
        //return cfUser;
    }
    public static void calculateUserPearson(ArrayList<CF_User> cfUser, ArrayList<UserPersonality> userPersonality){
        double[] pearson = {}; int size = cfUser.size(), n=0, index=0;
        ArrayList<double[]> attributeValues = new ArrayList<>();
        int[] bitVector = new int[NUMBER_OF_ATTRIBUTES];
        double[] average = new double[size];
        
        for(int i=0; i<size; i++){
            UserPersonality up = userPersonality.get(i);
            CF_User cfu = cfUser.get(i);
            
            double[] val = {up.getBookWormPercentage(),
                            up.getFashionFiendPercentage(),
                            up.getFoodiePercentage(),
                            up.getMusicLoverPercentage(),
                            up.getOutdoorEnthusiastPercentage(),
                            up.getSportsFanPercentage(),
                            cfu.getIsSameAgeStatus(),
                            cfu.getIsSameGender(),
                            cfu.getFavoritesCosSim(),
                            cfu.getHobbiesCosSim(),
                            cfu.getItemRatingPearson()};
            
            attributeValues.add(val);
        }
        
        
        /**STEP 1 : GET n  */
        bitVector = findBitVector(attributeValues);
        n = getN(bitVector);
        System.out.println("---------BIT VECTOR");
        printArray(bitVector);
        
        /**STEP 2 : r'a(USER[X]) */
        for(double[] values : attributeValues){
            cfUser.get(index++).setAverage(calculateAverage(values, bitVector, n));
        }index=0;
        
        /**STEP 3: w(a,u)*/
        //a : Active user
        double[] aRating = attributeValues.get(A_USER_INDEX);
        
        for(double[] values : attributeValues){
            cfUser.get(index).setUserPearson(calculateDegreeOfCorrelation(cfUser.get(index).getAverage(), average[A_USER_INDEX], values, attributeValues.get(A_USER_INDEX)));
            index++;
        }
        index=0;
        /**STEP 4: p(a,i)*/
        //return cfUser;
    }
    public static void calculatePrediction(ArrayList<CF_User> cfUser, ArrayList<ArrayList<C_Item>> cItemRatings){
        int colSize=cItemRatings.get(0).size(), rowSize=cItemRatings.size(), index=0;
        int[] bitVector = new int[colSize];
        double[] ratings = new double[colSize];
        double dotProd=0.0, absPearsonSum=0.0;
        boolean flag = false;
        
        CF_User a = cfUser.get(A_USER_INDEX);
        /**STEP 1 : GET bitVector  */
        bitVector = findBitVectorItem(cItemRatings);
        int m = getM(bitVector);
        int n = getN(bitVector);
        System.out.println("N : "+n);
        System.out.println("M : "+m);
        /**STEP 2 : GET n*/
        System.out.println("---------BIT VECTOR");
        printArray(bitVector);
        
        /**STEP 3 : */
        for(int i=0; i<bitVector.length; i++){
            if(bitVector[i]==0){
                System.out.println("I : "+i);
                for(int row=0; row<rowSize; row++){
                    //if(row == A_USER_INDEX){
                        //for(int col=0; col<colSize; col++){
                            if(cItemRatings.get(row).get(i).getRating() >-6 ){// && row!=A_USER_INDEX
                                double rating = cItemRatings.get(row).get(i).getRating();
                                double average = cfUser.get(row).getAverage();
                                double pearson = cfUser.get(row).getUserPearson();
                                dotProd+= ((rating-average)*pearson);
                                absPearsonSum+= Math.abs(pearson);
                            }else{
                                index=row;
                                flag = true;
                            }
                        //}
                        if(flag){
                            System.out.println("DOT_PROD : "+dotProd);
                            System.out.println("ABS_PEARSON : "+absPearsonSum);
                            cItemRatings.get(index).get(i).setRating(dotProd/absPearsonSum);//Math.round(dotProd/absPearsonSum)
                        }
                    //}
                       index=0; flag = false;
                }
            }
        }
    }
    public static int getN(int[] bitVector){
        int n=0;
        for(int b : bitVector){
            if(b==1){
                n++;
            }
        }        
        System.out.println("N : "+n);
        return n;
    }
    public static int getM(int[] bitVector){
        int m=0;
        for(int b : bitVector){
            if(b==0){
                m++;
            }
        }        
        return m;
    }
    
    public static double calculateDegreeOfCorrelation(double uAverage, double aAverage, double[] uRatings, double[] aRatings){
        double sumProdDif = 0.0, doc=0.0, sd=0.0, sumProdSquareA=0.0, sumProdSquareU=0.0;
        
        for(int i=0; i<uRatings.length; i++){
            sumProdDif += ((uRatings[i] - uAverage)*(aRatings[i]-aAverage));
            sumProdSquareA += Math.pow((double)(aRatings[i]-aAverage), 2.0);
            sumProdSquareU += Math.pow((double)(uRatings[i]-uAverage), 2.0);
        }
        
        doc = sumProdDif/(Math.sqrt(sumProdSquareA)*Math.sqrt(sumProdSquareU));
        return doc;
    }
    public static int[] findBitVectorItem(ArrayList<ArrayList<C_Item>> cItemRatings){
        int n=0, cnt=0, sizeRow=cItemRatings.size(), sizeCol = cItemRatings.get(0).size();
        int[] bitVector = new int[sizeCol];
        
        for(int col=0; col<sizeCol; col++){
            //TODO -> @DB : db function to count all rated items, boolean ang return type
            for(int row=0; row<sizeRow; row++){
                if(cItemRatings.get(row).get(col).getRating()>EMPTY_RATING){
                    cnt++;
                }
            }
            if(cnt==sizeRow){bitVector[col]=1;}
            cnt =0;
        }
        return bitVector;
    }
    /**
     *
     * @param allUsers
     * @return
     */
    public static int[] findBitVector(ArrayList<double[]> allUsers){
       int[] bitVector = new int[NUMBER_OF_ATTRIBUTES];
       int sizeCol = NUMBER_OF_ATTRIBUTES, sizeRow=allUsers.size(), cnt=0;
       
       for(int col=0; col<sizeCol; col++){
            //TODO -> @DB : db function to count all rated items, boolean ang return type
            for(int row=0; row<sizeRow; row++){
                if(allUsers.get(row)[col]>EMPTY_RATING){
                    cnt++;
                }
            }
            if(cnt==sizeRow){bitVector[col]=1;}
            cnt =0;
        }
       return bitVector;
    }
    public static double calculateAverage(double[] ratings, int[] bitVector, int n){
        double total=0.0;
        for(int i=0; i<ratings.length; i++){
            if(bitVector[i]==1){
                total+=ratings[i];
                System.out.println("rating[ "+i+"] : "+ratings[i]);
            }
        }
        System.out.println("TOTAL : "+total);
        return (total/n);
    }
    /**
     * @param array1
     * @param array2
     * @return
     */
    public static double[] calculatePearson(double[] array1, double[] array2){
        double[] pearson = {};
        //a : the ACTIVE user
        //u : the OTHER user
        //n : the # of items rated by ALL USERS
        //r'a : AVERAGE ratings totalRatings(USER[?])/n
        //w(a,u) : degree of correlation between USER[a] and USER[u]
        //p(a,i) : prediction for the user on item i
        
        /**STEP 1 : GET n  */
        /**STEP 2 : r'a(USER[X]) */
        /**STEP 4: p(a,i)*/
        /**STEP 3: w(a,u)*/
        
        /**STEP 4: p(a,i)*/
        return pearson;
    }
    
    public static void printMatrix(int[][] ui_matrix, String[] items, String[] users){
        int rSize=users.length, cSize=items.length;
        System.out.println("----------MATRIX");
        for(String i : items){System.out.print("\t"+i);}
        System.out.println("");
        
        for(int row=0; row<rSize; row++)
        {
            System.out.print(users[row]+" : ");
            for(int col=0; col<cSize; col++)
            {
                if((ui_matrix[row][col])==-1){System.out.print( "?\t" );}
                else{System.out.print( (ui_matrix[row][col])+"\t" );}
            }
            System.out.println("");
        }
    }
    public static void printArray(double[] ra){
        int cnt=0;
        System.out.println("----------ARRAY OF DOUBLE");
        for(double d : ra){
            System.out.println("arr[ "+(cnt++)+" ] : "+d);
        }
    }
    public static void printArray(int[] ra){
        int cnt=0;
        System.out.println("----------ARRAY OF DOUBLE");
        for(int d : ra){
            System.out.println("arr[ "+(cnt++)+" ] : "+d);
        }
    }
    public static void printCF_USER(ArrayList<CF_User> cfuser){
        for(CF_User cfu : cfuser){
            System.out.println( "-----------USER[a] TO USER[u] VECTOR"
                                +"\n\t cfUserId : "+cfu.getCfUserId()
                                +"\n\t aFbId : "+cfu.getaFbId()
                                +"\n\t uFbId : "+cfu.getuFbId()
                                +"\n\t favoritesCosSim : "+cfu.getFavoritesCosSim()
                                +"\n\t hobbiesCosSim : "+cfu.getHobbiesCosSim()
                                +"\n\t itemRatingPearson : "+cfu.getItemRatingPearson()
                                +"\n\t average : "+cfu.getAverage()
                                +"\n\t userPearson : "+cfu.getUserPearson()
                                +"\n\t rating : "+cfu.getRating()
                                +"\n\n");
        }
    }
    public static void printUserInfo(ArrayList<UserInfo> userInfo){
        for(UserInfo ui : userInfo){
            System.out.println("----------USER INFO"
                                +"\n\t fbId : "+ui.getFbId()
                                +"\n\t birthDay : "+ui.getBirthday()
                                +"\n\t ageStatus : "+ui.getAgeStatus()
                                +"\n\t gender : "+ui.getGender()
                                +"\n\t fname : "+ui.getFname()
                                +"\n\t lname : "+ui.getLname()
                                +"\n\n");
        }
    }
    public static void printUserPersonality(ArrayList<UserPersonality> userPersonality){
        for(UserPersonality up : userPersonality){
            System.out.println("----------USER PERSONALITY\n"
                                +"\n\t userPersonalityId : "+up.getUserPersonalityId()
                                +"\n\t fbId : "+up.getFbId()
                                +"\n\t musicLoverWeight : "+up.getMusicLoverWeight()
                                +"\n\t sportsFanWeight : "+up.getSportsFanWeight()
                                +"\n\t foodieWeight : "+up.getFoodieWeight()
                                +"\n\t fashionFiendWeight : "+up.getFashionFiendWeight()
                                +"\n\t bookWormWeight : "+up.getBookWormWeight()
                                +"\n\t outdoorEnthusiastWeight : "+up.getOutdoorEnthusiastWeight()
                                +"\n----------PERSONALITY PERCENTAGE"
                                +"\n\t musicLoverPercentage : "+up.getMusicLoverPercentage()
                                + "\n\t sportsFanPercentage : "+up.getSportsFanPercentage()
                                +"\n\t foodiePercentage : "+up.getFoodiePercentage()
                                +"\n\t fashionFiendPercentage : "+up.getFashionFiendPercentage()
                                +"\n\t bookWormPercentage : "+up.getBookWormPercentage()
                                +"\n\t outdoorEnthusiastPercentage : "+up.getOutdoorEnthusiastPercentage()
                                +"\n\n");
        }
    }
    public static void printCItems(ArrayList<ArrayList<C_Item>> cItemRatings){
        for(ArrayList<C_Item> cirs : cItemRatings){
            System.out.println("----------C_ITEM_RATINGS OF "+(cirs.get(0).getFbId()));
            for(C_Item cir : cirs){
                System.out.println("cItemId : "+cir.getcItemId()
                                    +"\n\titem [ "+cir.getProductId()+" ] : "+cir.getRating()+"\n");
            }
            System.out.println("\n");
        }
    }
    public static void printCItem(ArrayList<C_Item> cirs){
        System.out.println("----------C_ITEM_RATINGS OF "+(cirs.get(0).getFbId()));
        for(C_Item cir : cirs){
            System.out.println("cItemId : "+cir.getcItemId()
                                +"\n\titem [ "+cir.getProductId()+" ] : "+cir.getRating()+"\n");
        }
        System.out.println("\n");
    }
    public static void printCItem(C_Item cir){
        System.out.println("cItemId : "+cir.getcItemId()
                                +"\n\titem [ "+cir.getProductId()+" ] : "+cir.getRating()+"\n");
    }
}