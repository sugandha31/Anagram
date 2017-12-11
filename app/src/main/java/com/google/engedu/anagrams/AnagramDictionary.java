/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AnagramDictionary {

    HashMap<String,ArrayList<String>> lettersToWords = new HashMap<String, ArrayList<String>>();
    HashSet<String> wordSet = new HashSet<String>();
    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
//        int x = base.indexOf(word);
//        int y = base.lastIndexOf(word);

        boolean a = true;

        if(word.contains(base)){
            a = false;
        }
        else{
            a = true;
        }

        return a;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String word = sortLetters(targetWord);


        for(String i : wordSet){
            if(sortLetters(i)==word){
                if(lettersToWords.containsKey(word)){
                    result.add(i);
                    lettersToWords.put(word,result);
                }
                else{
                    lettersToWords.put(word,new ArrayList<String>(Arrays.asList(i)));
                }
            }
        }
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String newWord = word;
        for(char i='a';i<='z';i++){
            for (int j=0;j<word.length();j++){
                newWord = insert(word.toCharArray(),j,i);
            }
            if(wordSet.contains(newWord)){
                result.add(newWord);
            }
        }
        return result;
    }

    public static String insert(char[] array1,int p,char ch){

        char[] array;
        String str = getSaltString(array1.toString());
        array = str.toCharArray();
        char[] result = new char[array.length];
        for(int i = 0; i < p; i++)
            result[i] = array[i];
        result[p] = ch;
        for(int i = p + 1; i < array.length; i++)
            result[i] = array[i - 1];
        return result.toString();
    }

    public static String getSaltString(String word) {

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < word.length()) { // length of the random string.
            int index = (int) (rnd.nextFloat() * word.length());
            salt.append(word.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }



    public String pickGoodStarterWord() {
        return "stop";
    }

    public String sortLetters(String word){
        char x[] = word.toCharArray();
        Arrays.sort(x);
        String m = x.toString();
        return m;
    }
}
