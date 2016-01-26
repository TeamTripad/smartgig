package com.smartgig.facebook.process;

import java.util.ArrayList;

import com.smartgig.database.connection.MyDBConnection;

public class Personality {
	String userID = "";
    String[] categories = {"musicLover", "fashionfiend", "outdoorEnthusiast"};
    int personalityCnt=0;
    
    public ArrayList<String> getAllInterestTokensFromDB() {
        MyDBConnection db = new MyDBConnection();
        return db.getInterests(userID);
    }

    public void countPersonality() {
        ArrayList<String> interests = getAllInterestTokensFromDB();
        ArrayList<String> associatedWords;
        ArrayList<String> categoryBagOfWords;
        int found = 0;

        for (String token : interests) {
            associatedWords = getAssociatedWord(token);
            for (String aw : associatedWords) {
                for (String category : categories) {
                    categoryBagOfWords = getCategoryBagOfWords(category);
                    while (found == 0) {
                        for(String bagOfWord: categoryBagOfWords){
                            if(aw.equalsIgnoreCase(bagOfWord)){
                                personalityCnt++;
                                found=1;
                               
                            }
                        }
                    }

                }
            }

        }

    }

    public ArrayList<String> getAssociatedWord(String word) {
        ArrayList<String> associatedWords = new ArrayList<>();

        return associatedWords;
    }

    public ArrayList<String> getCategoryBagOfWords(String category) {
        ArrayList<String> bagOfWords = new ArrayList<>();

        return bagOfWords;
    }
}
