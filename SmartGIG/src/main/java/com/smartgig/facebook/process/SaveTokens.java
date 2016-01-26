package com.smartgig.facebook.process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.facebook.dto.AcceptedTokenModel;

import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.trees.TypedDependency;

public class SaveTokens {

	MyDBConnection db = new MyDBConnection();
	Morphology morph = new Morphology();

	public void tokenSaving(List<TypedDependency> tdl, String userID) {
		ArrayList<AcceptedTokenModel> atm = new ArrayList<>();

		ArrayList<TypedDependency> noun = new ArrayList<>();
		ArrayList<TypedDependency> amod = new ArrayList<>();
		ArrayList<TypedDependency> compound = new ArrayList<>();
		ArrayList<TypedDependency> neg = new ArrayList<>();

		for (TypedDependency td : tdl) {
			if (td.reln().toString().equals("nsubj") || td.reln().toString().equals("dobj")) {
				String[] a = td.dep().toString().split("/");
				if (a[1].equals("NN") || a[1].equals("NNS") || a[1].equals("NNP") || a[1].equals("NNPS")) {
					noun.add(td);
				}
			}
			if (td.reln().toString().equals("amod")) {
				amod.add(td);
			}
			if (td.reln().toString().equals("compound")) {
				compound.add(td);
			}
			if (td.reln().toString().equals("neg")) {
				neg.add(td);
			}
		}

		for (TypedDependency td : tdl) {
			System.out.println(td.reln() + " " + td.gov() + " " + td.dep());
		}

		// if JJ is positive
		AcceptedTokenModel accepted;
		for (TypedDependency nn : noun) {

			String[] a = nn.dep().toString().split("/");

			String[] b = nn.gov().toString().split("/");// if positive

			for (TypedDependency n : neg) {

				// if ang noun parehas sa gi-negate
				if (nn.gov().equals(n.gov())) {
					if (!(isPositive(b[0]))) {
						accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), 1, 0);
						atm.add(accepted);
					} else {
						accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), 0, 1);
						atm.add(accepted);
					}
				} else if (!(isPositive(b[0]))) {
					accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), 0, 1);
					atm.add(accepted);
				} else {
					accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), 1, 0);
					atm.add(accepted);
				}
			}

			// if walay negation
			if (neg.isEmpty()) {
				if (!(isPositive(b[0]))) {
					accepted = new AcceptedTokenModel("0",  userID, morph.stem(a[0]), 0, 1);
					atm.add(accepted);
				} else {
					accepted = new AcceptedTokenModel("0",  userID, morph.stem(a[0]), 1, 0);
					atm.add(accepted);
				}
			}
		}

		int pc = 0, nc = 0;
		String word = "";
		for (int j = 0; j < compound.size(); j++) {
			TypedDependency c = compound.get(j);
			String[] base = c.gov().toString().split("/");
			int isAdded = 0;

			String[] a = c.dep().toString().split("/");

			word += " " + a[0];

			for (int i = 0; i < atm.size(); i++) {
				AcceptedTokenModel t = atm.get(i);
				if (base[0].equals(t.getWord())) {

					pc = t.getPositiveCount();
					nc = t.getNegativeCount();
					atm.remove(atm.get(i));
				}
			}

			if (pc == 0 && nc == 0) {
				pc = 1;
			}

			// if nakuha na tanan words sa list
			if (j == (compound.size() - 1) && isAdded == 0) {
				accepted = new AcceptedTokenModel("0", userID, word + " " + base[0], pc, nc);
				atm.add(accepted);
				word = "";

			}
		}

		for (TypedDependency am : amod) {
			String[] a = am.dep().toString().split("/");
			String[] b = am.gov().toString().split("/");
			int isAdded = 0;

			for (int i = 0; i < atm.size(); i++) {
				AcceptedTokenModel t = atm.get(i);
				if (b[0].equals(t.getWord())) {
					isAdded = 1;
					accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), t.getPositiveCount(), t.getNegativeCount());
					atm.add(accepted);
				}
			}

			if (isAdded == 0) {
				accepted = new AcceptedTokenModel("0", userID, morph.stem(a[0]), 1, 0);
				atm.add(accepted);
				accepted = new AcceptedTokenModel("0", userID, morph.stem(b[0]), 1, 0);
				atm.add(accepted);
			}
		}

		// save to db
		for (AcceptedTokenModel temp : atm) {
			System.out.println("---" + temp.getWord() + " " + temp.getPositiveCount() + " " + temp.getNegativeCount());
		}

		for (AcceptedTokenModel t : atm) {
			if (db.tokenExists(userID, t.getWord())) {
				AcceptedTokenModel temp = db.getToken(userID, t.getWord()), temp2 = new AcceptedTokenModel();
				int p = temp.getPositiveCount() + t.getPositiveCount(),
						n = temp.getNegativeCount() + t.getNegativeCount();
				System.out.println(temp.getPositiveCount() + " " + t.getPositiveCount());
				System.out.println(temp.getNegativeCount() + " " + t.getNegativeCount());

				temp2.setTokenID(temp.getTokenID());
				temp2.setUserID(temp.getUserID());
				temp2.setWord(temp.getWord());
				temp2.setPositiveCount(p);
				temp2.setNegativeCount(n);
				db.updateToken(temp2);
				// db.addToken(temp, userID);
			} else {
				if(!(db.stopWordExists(t.getWord())))
					db.addToken(t, userID);
			}

		}

	}

	public boolean isPositive(String word) {
		// if
		boolean flag = db.negativeWordExists(word);
		if (flag) {
			return false;
		} else {
			return true;
		}
	}

}
