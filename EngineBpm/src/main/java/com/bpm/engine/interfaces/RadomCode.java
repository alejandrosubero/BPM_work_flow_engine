package com.bpm.engine.interfaces;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public interface RadomCode {

   public  default  String generateCode(String cod) {
        String code = "UW-" + cod + "*" + UUID.randomUUID().toString();
        return code;
    }

    public default String generateCodeLikeNumber(int len){
        String capitalChars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnoprstuvwxyx";
        String numbers = "0123456789";
        String symbols = "*!@#$%^&*()_+=/.?<>";
        String values = capitalChars + smallChars +numbers+symbols;
        Random rndm_method = new Random();
        char[] charArray = new char[len];
        for(int i =0; i<len; i++){
            charArray [i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return String.valueOf(charArray);
    }

    public default Integer generateRandomNumber(Integer minNumber, Integer maxNumber) {
        Integer b = 0;
        if(minNumber > maxNumber){
            b = new Random().nextInt( minNumber  - maxNumber + 1) + maxNumber;
        }else {
            b = new Random().nextInt(maxNumber - minNumber + 1) + minNumber;
        }
        return b;
    }

    public default Integer generateRandomNumber(Integer maxNumber) {
        return new Random().nextInt(maxNumber);
    }

    public default  long generatePositiveRandomLong() {
        long randomNumber = new Random().nextLong();
        return Math.abs(randomNumber);
    }

}
