package com.challenge.kroger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

    static void customSort(int[] arr) {
        if(arr.length == 0) {
            return;
        }

        HashMap<Integer, Integer> frequencyMap = new HashMap<>(arr.length);

        // Generate a frequency map for each value on the array:
        for (int value : arr) {
            if(frequencyMap.containsKey(value)){
                int valueFrequency = frequencyMap.get(value);
                frequencyMap.put(value, valueFrequency + 1);
            } else {
                frequencyMap.put(value, 1);
            }
        }

        // Generate subsets for each frequency
        HashMap<Integer, ArrayList<Integer>> frequencySubsets = new HashMap<>(frequencyMap.size());
        Iterator<Map.Entry<Integer, Integer>> frequencyMapIterator = frequencyMap.entrySet().iterator();

        while(frequencyMapIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = frequencyMapIterator.next();
            int key = entry.getKey();
            int frequency = entry.getValue();
            ArrayList<Integer> subset;
            if (frequencySubsets.containsKey(frequency)) {
                subset = frequencySubsets.get(frequency);
            } else {
                subset = new ArrayList<>();
                frequencySubsets.put(frequency, subset);
            }
            subset.add(key);
        }

        // Print the sorted subsets for each frequency
        Iterator <Map.Entry<Integer, ArrayList<Integer>>> frequencySubsetsIterator =
                frequencySubsets.entrySet().iterator();
        while (frequencySubsetsIterator.hasNext()){
            Map.Entry<Integer, ArrayList<Integer>> entry = frequencySubsetsIterator.next();
            ArrayList<Integer> subset = entry.getValue();
            Collections.sort(subset);
            int frequency = entry.getKey();
            for (int value : subset) {
                for(int i = 0; i < frequency; i++) {
                    System.out.println(value);
                }
            }
        }
    }
    static String mergeStrings(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        int maxArrayLength = charsA.length > charsB.length ? charsA.length : charsB.length;

        for (int i = 0; i < maxArrayLength; i++) {

            if (i < charsA.length) {
                stringBuilder.append(charsA[i]);
            }
            if (i < charsB.length) {
                stringBuilder.append(charsB[i]);
            }
        }
        return stringBuilder.toString();
    }

    static long count(int[] numbers, int k) {
        long count = 0;
        long subarray;

        // Iterate through each number and
        // test every continuous block of numbers after it:
        for (int i = 0; i < numbers.length; i++) {
            subarray = 1;
            for(int j = i; j < numbers.length; j++) {
                subarray *= numbers[j];
                if(subarray < k) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    static List<Map<String, Object>> validateCards(String[] bannedPrefixes, String[] cardsToValidate) {
        if(cardsToValidate == null) {
            // No cards were given, returning an empty list.
            return new ArrayList<>();
        }
        List<Map<String, Object>> validatedCards = new ArrayList<>(cardsToValidate.length);
        Map<String, Object> validatedMap;
        for (String card : cardsToValidate) {
            validatedMap = new HashMap<>();
            validatedMap.put("card", card);
            Boolean isValid = luhnCheck(card);
            validatedMap.put("isValid", isValid);
            Boolean isAllowed = isCardAllowed(card, bannedPrefixes);
            validatedMap.put("isAllowed", isAllowed);
            validatedCards.add(validatedMap);
        }
        return validatedCards;
    }

    private static boolean luhnCheck(String card) {
        if (card == null || card.isEmpty()) {
            return false;
        }

        char[] cardCharacters = card.toCharArray();
        int checkDigit = 0;

        // Add all numbers together except the last digit:
        for (int i = 0; i < cardCharacters.length - 1; i++) {
            char cardChar = cardCharacters[i];
            int cardNumber = Character.getNumericValue(cardChar);
            // Double each number:
            cardNumber *= 2;
            checkDigit += cardNumber;
        }
        checkDigit = checkDigit % 10; // Calculate the remainder of dividing it by 10
        char lastCardChar = cardCharacters[cardCharacters.length - 1];
        int lastCardNumber = Character.getNumericValue(lastCardChar);

        return lastCardNumber == checkDigit;
    }

    private static boolean isCardAllowed(String card, String[] bannedPrefixes) {
        // Empty or null cards not allowed.
        if(card == null || card.isEmpty()) {
            return false;
        }
        // If no banned prefixes are given, all cards are allowed.
        if(bannedPrefixes == null) {
            return true;
        }
        // Compare the card prefix against every banned prefix:
        for (String bannedPrefix : bannedPrefixes) {
            if (card.length() < bannedPrefix.length()) {
                continue; // Can't compare cards if the prefix is larger, skip.
            }
            String cardPrefix = card.substring(0, bannedPrefix.length());
            if(cardPrefix.equals(bannedPrefix)){
                return false;
            }
        }
        return true;
    }
}
