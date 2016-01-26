package com.smartgig.item.process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.ecommerce.database.dao.Product;
import com.smartgig.ecommerce.database.connection.MyEcommerceDBConnection;
import com.smartgig.facebook.process.Postagger;

import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import net.didion.jwnl.JWNLException;

public class PreprocessingEcommerce {

	MyEcommerceDBConnection db = new MyEcommerceDBConnection();


	Morphology morph = new Morphology();
	MaxentTagger tagger = new MaxentTagger("tagger/english-left3words-distsim.tagger");
	// rStopwords = new RemoveStopWords();

	public String[] acceptedTags = { "NN", "NNS", "NNP", "NNPS", "JJ", "JJR", "JJS", "VB", "VBD", "VBG", "VBN", "VBP",
			"VBZ", "." };

	public List<String> addItemNameToken() throws SQLException, JWNLException {
		ArrayList<String> pNameList = new ArrayList<>();
		List<Product> prodList = db.getProduct();
		ArrayList<String> stemmed = new ArrayList<>();

		boolean found = false;
		int frequency = 1;

		for (Product str : prodList) {
			// gikuha ang product NAME and product ID gikan sa DB
			String tokenName = str.getProdName();
			int id = str.getProdId();

			// Mao ni ang pagtagged gamit ang maxentTagger
			String tagged = tagger.tagString(tokenName);
			
			// ang tagged product name kay g.split by space para ma separate
			String[] splitTaggedBySpace = tagged.split(" ");
			 System.out.println(id +"word: " + splitTaggedBySpace[0]+ " tag:" +  splitTaggedBySpace[1]);
			// g.split by underscore siya para makuha na ang words and tags
			for (String s : splitTaggedBySpace) {
				String[] splitTaggedByUnderScore = s.split("_");
				String tokenProdName = splitTaggedByUnderScore[0];
				String stemmedWords = morph.stem(tokenProdName);

				// g.check ig ang tag words kay accepted tag ba siya(noun,verb,adjective)
				for (String accpTags : acceptedTags) {
					if (splitTaggedByUnderScore[1].equals(accpTags)) {						
						//g-check ig ang stemmedwords kay stopwords ba siya
						if (!db.isStopWord(stemmedWords.trim())) {
							//gicheck ig ang word kay ni exist na ba siya sa DB
							if (!db.isWordExisting(stemmedWords, id)) {
								if (!db.isWordExisting(stemmedWords,id)) {
									 System.out.println(id +"IF word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.insertKeywords(id, stemmedWords,
									// frequency, "not updated");
								} else {
									 System.out.println("ELSE word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.updateKeywordFreq(id, word,
									// (db.getKeywordFrequency(id, word) + 1));
								}
					
					
							
							ArrayList<String> synProdName = new ArrayList<>();
							Synonym syno = new Synonym();
							synProdName.addAll(syno.lookupSynonyms(stemmedWords));
							for (String synonyms : synProdName) {
								if (!db.isWordExisting(synonyms, id)) {
									System.out.println(id +"IF SYNONYM word: " + stemmedWords+ " syno word:" + synonyms);
										//db.insertKeywords(id, synonyms,frequency, "not updated");
									} else {
										System.out.println("ELSE SYNONYM word: " + stemmedWords + "syno :" + synonyms);
										//db.updateKeywordFreq(id, word,db.getKeywordFrequency(id, synonyms)+ 1);
									}
								}
			
						
							
							} else {
								 System.out.println("a stopword :" + tokenName);
							}
						}
					}
				}
			}
		}
		return pNameList;

	}
	public List<String> addItemDescoken() throws SQLException, JWNLException {
		ArrayList<String> pDescList = new ArrayList<>();
		List<Product> prodList = db.getProduct();
		ArrayList<String> stemmed = new ArrayList<>();

		boolean found = false;
		int frequency = 1;

		for (Product str : prodList) {
			// gikuha ang product NAME and product ID gikan sa DB
			String tokenDesc = str.getProdDesc();
			int id = str.getProdId();

			// Mao ni ang pagtagged gamit ang maxentTagger
			String tagged = tagger.tagString(tokenDesc);
			
			// ang tagged product name kay g.split by space para ma separate
			String[] splitTaggedBySpace = tagged.split(" ");
			 System.out.println(id +"word: " + splitTaggedBySpace[0]+ " tag:" +  splitTaggedBySpace[1]);
			// g.split by underscore siya para makuha na ang words and tags
			for (String s : splitTaggedBySpace) {
				String[] splitTaggedByUnderScore = s.split("_");
				String tokenProdName = splitTaggedByUnderScore[0];
				String stemmedWords = morph.stem(tokenProdName);

				// g.check ig ang tag words kay accepted tag ba siya(noun,verb,adjective)
				for (String accpTags : acceptedTags) {
					if (splitTaggedByUnderScore[1].equals(accpTags)) {						
						//g-check ig ang stemmedwords kay stopwords ba siya
						if (!db.isStopWord(stemmedWords.trim())) {
							//gicheck ig ang word kay ni exist na ba siya sa DB
							if (!db.isWordExisting(stemmedWords, id)) {
								if (!db.isWordExisting(stemmedWords,id)) {
									 System.out.println(id +"IF word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.insertKeywords(id, stemmedWords,
									// frequency, "not updated");
								} else {
									 System.out.println("ELSE word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.updateKeywordFreq(id, word,
									// (db.getKeywordFrequency(id, word) + 1));
								}
					
					
							
							ArrayList<String> synProdName = new ArrayList<>();
							Synonym syno = new Synonym();
							synProdName.addAll(syno.lookupSynonyms(stemmedWords));
							for (String synonyms : synProdName) {
								if (!db.isWordExisting(synonyms, id)) {
									System.out.println(id +"IF SYNONYM word: " + stemmedWords+ " syno word:" + synonyms);
										//db.insertKeywords(id, synonyms,frequency, "not updated");
									} else {
										System.out.println("ELSE SYNONYM word: " + stemmedWords + "syno :" + synonyms);
										//db.updateKeywordFreq(id, word,db.getKeywordFrequency(id, synonyms)+ 1);
									}
								}					
							
							} else {
								 System.out.println("a stopword :" + tokenDesc);
							}
						}
					}
				}
			}
		}
		return pDescList;
	}
	public List<String> addItemColorcoken() throws SQLException, JWNLException {
		ArrayList<String> pColorList = new ArrayList<>();
		List<Product> prodList = db.getProduct();
		ArrayList<String> stemmed = new ArrayList<>();

		boolean found = false;
		int frequency = 1;

		for (Product str : prodList) {
			// gikuha ang product NAME and product ID gikan sa DB
			String tokenColor = str.getColor();
			int id = str.getProdId();

			// Mao ni ang pagtagged gamit ang maxentTagger
			String tagged = tagger.tagString(tokenColor);
			
			// ang tagged product name kay g.split by space para ma separate
			String[] splitTaggedBySpace = tagged.split(" ");
			 System.out.println(id +"word: " + splitTaggedBySpace[0]+ " tag:" +  splitTaggedBySpace[1]);
			// g.split by underscore siya para makuha na ang words and tags
			for (String s : splitTaggedBySpace) {
				String[] splitTaggedByUnderScore = s.split("_");
				String tokenProdName = splitTaggedByUnderScore[0];
				String stemmedWords = morph.stem(tokenProdName);

				// g.check ig ang tag words kay accepted tag ba siya(noun,verb,adjective)
				for (String accpTags : acceptedTags) {
					if (splitTaggedByUnderScore[1].equals(accpTags)) {						
						//g-check ig ang stemmedwords kay stopwords ba siya
						if (!db.isStopWord(stemmedWords.trim())) {
							//gicheck ig ang word kay ni exist na ba siya sa DB
							if (!db.isWordExisting(stemmedWords, id)) {
								if (!db.isWordExisting(stemmedWords,id)) {
									 System.out.println(id +"IF word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.insertKeywords(id, stemmedWords,
									// frequency, "not updated");
								} else {
									 System.out.println("ELSE word: " + tokenProdName+ " Stemmed word:" + stemmedWords);
									// db.updateKeywordFreq(id, word,
									// (db.getKeywordFrequency(id, word) + 1));
								}
					
					
							
							ArrayList<String> synProdName = new ArrayList<>();
							Synonym syno = new Synonym();
							synProdName.addAll(syno.lookupSynonyms(stemmedWords));
							for (String synonyms : synProdName) {
								if (!db.isWordExisting(synonyms, id)) {
									System.out.println(id +"IF SYNONYM word: " + stemmedWords+ " syno word:" + synonyms);
										//db.insertKeywords(id, synonyms,frequency, "not updated");
									} else {
										System.out.println("ELSE SYNONYM word: " + stemmedWords + "syno :" + synonyms);
										//db.updateKeywordFreq(id, word,db.getKeywordFrequency(id, synonyms)+ 1);
									}
								}					
							
							} else {
								 System.out.println("a stopword :" + tokenColor);
							}
						}
					}
				}
			}
		}
		return pColorList;
	}
	
	public List<String> addItemBrandToke() throws SQLException{
		ArrayList<String> pBrandList = new ArrayList<>();
		List<Product> prodList = db.getProduct();
		for (Product str : prodList) {
			String tokenBrand = str.getBrand();
			int id = str.getProdId();
			//System.out.println(tokenBrand);
			if (!db.isWordExisting(tokenBrand,id)) {
				System.out.println(id +"IF id: " + id+ " word:" + tokenBrand);
				//db.insertKeywords(id,tokenBrand,frequency, "not updated");
			}
			else{
				System.out.println(id +"ELSE IF id: " + id+ " word:" + tokenBrand);
				//db.updateKeywordFreq(id, tokenBrand,db.getKeywordFrequency(id, synonyms)+ 1);
			}
		}
		return pBrandList;	
	}

	public boolean isAcceptedTags(String tags) {
		for (String accptTags : acceptedTags) {
			return true;
		}
		return false;

	}

}
