package com.smartgig.facebook.process;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

import com.smartgig.database.connection.MyDBConnection;

import edu.stanford.nlp.trees.Tree;

public class Preprocessing {
	MyDBConnection db = new MyDBConnection();

	// get all the posts are pure english.
	public void processRelevant(String message, String userID) {
		try {
			int isEngl = 0;
			// parse the post

			message = message.replace('.', ';');
			String[] sentences = message.split(";");
			System.out.println("im here" + "\n" + message);
			for (String sentence : sentences) {
				String sentence2 = removeSpecialCharacters(removeEmoticon(sentence+"."));
				System.out.println("\n\n" + sentence2);
				Postagger parser = new Postagger();
				Tree tree = parser.parse(sentence2);
				List<Tree> leaves = tree.getLeaves();
				// Print words and Pos Tags
				for (Tree leaf : leaves) {
					Tree parent = leaf.parent(tree);
					if (parent != null) {
						System.out.println(leaf.label().value() + "_" + parent.label().value() + " ");
						if (!(parent.label().value().equals("NNP") || parent.label().value().equals("NNPS")
								|| parent.label().value().equals("."))) {
							if (!(isEnglish(removeSpecialCharacters(leaf.label().value())))) {
								isEngl = 0;
							} else {
								isEngl = 1;
							}
						}
					}
				}

				// print if the post is english.
				if (isEngl == 1) {

					System.out.println("---------------------------------------");
					System.out.println(sentence2);

					getPOSdependencies(sentence2, userID);

				}

			}
		} catch (Exception ex) {
			System.out.println("ERROR IN PREPROCESSING: " + ex.toString());
		}
		// }
	}

	// if the word is english
	public boolean isEnglish(String word) {
		return db.englishWordExists(word);
	}

	// remove special characters in a post.
	public String removeSpecialCharacters(String post) {

		// A-Z 65-90
		// a-z 97 - 122
		// ñ - 241
		// Ñ - 209
		String sentence = "";
		int flagspace = 0;
		for (int i = 0; i < post.length(); i++) {
			if (post.charAt(i) == ' ') {
				if (flagspace == 0) {
					sentence += post.charAt(i) + "";
					flagspace = 1;
				}
			} else {
				if (isLetter(post.charAt(i))) {
					sentence += post.charAt(i);
					flagspace = 0;
				} else {
					// dot and comma
					if ((int) post.charAt(i) == 46 || (int) post.charAt(i) == 44) {
						if (i > 0) {
							if (isLetter(post.charAt(i - 1))) {
								// System.out.println(message.charAt(i-1) +""+
								// message.charAt(i+1));
								sentence += post.charAt(i);
							}
						}
					} else if ((int) post.charAt(i) == 39) {
						// for the '
						if (i > 0 && i < (post.length() - 1)) {
							if (isLetter(post.charAt(i - 1)) && isLetter(post.charAt(i + 1))) {
								sentence += post.charAt(i);
							}
						}
					}

				}
			}

		}

		return sentence.trim();
	}

	public String removeEmoticon(String message) {
		String sentence = "";
		int flagspace = 0;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ') {
				if (flagspace == 0) {
					sentence += message.charAt(i);
					flagspace = 1;
				}
			} else {
				if ((int) message.charAt(i) == 58 || (int) message.charAt(i) == 59) {
					if (i > 0) {
						if (message.charAt(i - 1) == ' ') {
							sentence += "";
						} else {
							sentence += message.charAt(i);
						}
					} else {
						if (message.charAt(i + 2) == ' ') {
							sentence += "";
						} else {
							sentence += message.charAt(i);
						}
					}
				} else {
					if (i > 0) {
						if (((int) message.charAt(i - 1) == 58 || (int) message.charAt(i - 1) == 59)) {
							sentence += "";
						} else {
							sentence += message.charAt(i);
						}
					} else {
						sentence += message.charAt(i);
					}
				}

				flagspace = 0;
			}

		}

		return sentence;
	}

	public boolean isLetter(char letter) {
		int ch = (int) letter;
		if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || ch == 241 || ch == 209) {
			return true;
		}
		return false;
	}

	public void getPOSdependencies(String post, String userID) {
		Postagger parser = new Postagger();
		String[] sentence;
		if (post != null) {
			parser.getPOSdependencies(post, userID);
		}
	}
}
