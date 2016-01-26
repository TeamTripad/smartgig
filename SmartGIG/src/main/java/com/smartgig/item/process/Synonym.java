package com.smartgig.item.process;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

public class Synonym {
	  public Synonym()  {
		  try {
			setUpJWNL();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

	    public static void setUpJWNL() throws JWNLException {
	        try {
	            JWNL.initialize(new FileInputStream("file_properties.xml"));
	        } catch (FileNotFoundException e) {
	            System.out.println("Error initializing Stemmer:file_properties.xml not found");
	        } catch (JWNLException e) {
	            System.out.println("Error initializing Stemmer: " + e.toString());
	        }
	    }

	    public Set<String>lookupSynonyms(String lexicalForm) throws JWNLException {
	    	Set<String> synonyms = new HashSet<String>();
	    	
	        Dictionary dictionary = Dictionary.getInstance();
	        
	        IndexWord indexWord = dictionary.getIndexWord(POS.NOUN, lexicalForm);
	
	        if (indexWord == null) {
	            return synonyms;
	        }
	        Synset[] synSets = indexWord.getSenses();
	        for (Synset synset : synSets) {
	            Word[] words = synset.getWords();
	            for (Word word : words) {
	                synonyms.add(word.getLemma());
	            }
	        }
	        synonyms.remove(lexicalForm);

	        return  synonyms;
	    }
}
