package com.smartgig.recommender.content;

import java.sql.SQLException;
import java.util.ArrayList;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.database.dto.*;
import com.smartgig.database.dao.*;

/**
 *
 * @author lauren
 */
public class ContentBased {
	public static MyDBConnection db = new MyDBConnection();

	public static void main(String args[]) {
		try {
			String fbId = "1";
			ArrayList<ProductKeyword> nonUpdatedItemKeyList = db.identifyDistinctTokens();
			int itemCount = db.countItems();

			ArrayList<IDF> idfList = db.returnIDF();
			ArrayList<Product> prodList = db.getProduct();
			System.out.println("prodList size: " + prodList.size());
			System.out.println("idfList size: " + idfList.size());
			computeTF_IDF_Items(prodList, idfList);

			ArrayList<InterestKeyword> nonUpdatedInterestList = db.getInterestKeywords();
			computeTF_IDF_Interest(nonUpdatedInterestList, fbId);
			// inig human aneh update tanan nga naa sa idf

			computeItemLength(prodList);

			ArrayList<TF_IDF> tf_idf_InterestList = db.getTF_IDF_interest(fbId);
			computeInterestLength(fbId, tf_idf_InterestList);

			computeCosSim(prodList, tf_idf_InterestList, fbId);
			displayRankItems(fbId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// //STEP 2 AND 3 - plan B
	public static void computeTF_IDF_Items(ArrayList<Product> prodList, ArrayList<IDF> idfList) throws SQLException {
		int cnt = 0;
		while (cnt < prodList.size()) {
			int cnt2 = 0;
			while (cnt2 < idfList.size()) {
				double idf = idfList.get(cnt2).getValue();
				int frequency = db.returnKeywordFrequency(prodList.get(cnt).getProdId(),
						idfList.get(cnt2).getKeyword());
				double tf_idf = idf * frequency;
				if (db.isProductExisting(prodList.get(cnt).getProdId(), idfList.get(cnt2).getKeyword())
				    && db.isTF_IDF_Items_Existing(prodList.get(cnt).getProdId(), idfList.get(cnt2).getKeyword())) {
					if (tf_idf > 0.0) {
						db.updateTF_IDF_Items(prodList.get(cnt).getProdId(), idfList.get(cnt2).getKeyword(), tf_idf);
					}
				} else {
					if (tf_idf > 0.0) {
						db.insertTF_IDF_Items(prodList.get(cnt).getProdId(), idfList.get(cnt2).getKeyword(), tf_idf);
					}
				}
				cnt2++;
			}
			cnt++;
		}
	}

	// STEP 4 - plan B
	public static void computeTF_IDF_Interest(ArrayList<InterestKeyword> interestList, String fbId)
			throws SQLException {
		int cnt = 0;
		int maxFrequency = db.countInterest(fbId);
		while (cnt < interestList.size()) {
			double idf = db.getIDF(interestList.get(cnt).getKeyword());
			double tf_idf = ((double) interestList.get(cnt).getFrequency() / (double) maxFrequency) * idf;
			if (db.isTF_IDF_Interest_Existing(interestList.get(cnt).getFbId(), interestList.get(cnt).getKeyword())) {
				System.out.println("interest updated");
				db.updateTF_IDF_Interest(fbId, interestList.get(cnt).getKeyword(), tf_idf);
				db.updateIDF(interestList.get(cnt).getKeyword());
			} else {
				System.out.println("interest inserted");
				db.insertTF_IDF_Interest(interestList.get(cnt).getFbId(), interestList.get(cnt).getKeyword(), tf_idf);
				db.updateIDF(interestList.get(cnt).getKeyword());
			}
			cnt++;
		}
	}

	// STEP 5
	public static double returnSquare(double value) {
		return Math.pow(value, 2);
	}

	public static double returnSquareRoot(double value) {
		return Math.sqrt(value);
	}

	public static void computeItemLength(ArrayList<Product> prodList) throws SQLException {
		int cnt = 0;
		while (cnt < prodList.size()) {
			ArrayList<TF_IDF> itemLengthList = db.getTF_IDF_items(prodList.get(cnt).getProdId());
			int cnt2 = 0;
			double sum = 0;
			while (cnt2 < itemLengthList.size()) {
				sum += returnSquare(itemLengthList.get(cnt2).getValue());
				cnt2++;
			}
			double length = returnSquareRoot(sum);
			db.insertItemLength(prodList.get(cnt).getProdId(), length);
			cnt++;
		}
	}

	public static void computeInterestLength(String fbId, ArrayList<TF_IDF> interestLengthList) throws SQLException {
		int cnt = 0;
		double sum = 0;
		while (cnt < interestLengthList.size()) {
			sum += returnSquare(interestLengthList.get(cnt).getValue());
			cnt++;
		}
		double length = returnSquareRoot(sum);
		db.insertInterestLength(fbId, length);
	}

	// STEP 6
	public static void computeCosSim(ArrayList<Product> prodList, ArrayList<TF_IDF> tf_idf_InterestList, String fbId)
			throws SQLException {
		int cnt = 0;
		while (cnt < prodList.size()) {
			int cnt2 = 0;
			double sum = 0;
			while (cnt2 < tf_idf_InterestList.size()) {
				double tf_idf = db.returnTF_IDF_itemsVal(prodList.get(cnt).getProdId(),
						tf_idf_InterestList.get(cnt2).getWord());
				sum += tf_idf_InterestList.get(cnt2).getValue() * tf_idf;
				cnt2++;
			}
			double itemLength = db.getItemLength(prodList.get(cnt).getProdId());
			double interestLength = db.getInterestLength(fbId);
			double cosSim = sum / (itemLength * interestLength);
			// System.out.println(">itemID : "+prodList.get(cnt).getProdID());
			// System.out.println(">cosSim : "+cosSim);
			db.insertCosSim(prodList.get(cnt).getProdId(), fbId, cosSim);
			cnt++;
		}
	}

	public static void displayRankItems(String fbId) throws SQLException {
		System.out.println("Display Recommended Items\n");
		ArrayList<CosSimilarity> cs = db.returnItems(fbId);
		int cnt = 0;
		while (cnt < cs.size()) {
			Product prod = db.returnRankedItems(cs.get(cnt).getProdId());
			System.out.println("itemName : " + prod.getProdName());
			System.out.println("price : " + prod.getPrice());
			System.out.println("cosSimilarity : " + cs.get(cnt).getValue());
			System.out.println("---------------------------------------\n");
			cnt++;
		}
	}
	

}