package com.example.romannumeralconverter;

public class FormatConverter {
    public String toRoman(int input){
        String str = "";
        if(input < 0)
            return "Invalid Input";

        while(input >= 1000){
            str += "M";
            input -= 1000;
        }
        while(input >= 900){
            str += "CM";
            input -= 900;
        }
        while(input >= 500){
            str += "D";
            input -= 500;
        }
        while(input >= 400){
            str += "CD";
            input -= 400;
        }
        while(input >= 100){
            str += "C";
            input -= 100;
        }
        while(input >= 90){
            str += "XC";
            input -= 90;
        }
        while(input >= 50){
            str += "L";
            input -= 50;
        }
        while(input >= 40){
            str += "XL";
            input -= 40;
        }
        while(input >= 10){
            str += "X";
            input -= 10;
        }
        while(input >= 9){
            str += "IX";
            input -= 9;
        }
        while(input >= 5){
            str += "V";
            input -= 5;
        }
        while(input >= 4){
            str += "IV";
            input -= 4;
        }
        while(input >= 1){
            str += "I";
            input -= 1;
        }


        //return roman numeral
        return str;
    }

    public int toDecimal(String romanNumeral){
        int number = 0;
        int endNumber = 0;
        String roman = romanNumeral.toUpperCase();


        for(int i = roman.length() - 1; i>=0; i--){
            char ch = roman.charAt(i);
            if(ch == 'M'){
                number = decimalHelper(1000, endNumber, number);
                endNumber = 1000;
            }
            else if(ch == 'D'){
                number = decimalHelper(500, endNumber, number);
                endNumber = 500;
            }
            else if(ch == 'C'){
                number = decimalHelper(100, endNumber, number);
                endNumber = 100;
            }
            else if(ch == 'L'){
                number = decimalHelper(50, endNumber, number);
                endNumber = 50;
            }
            else if(ch == 'X'){
                number = decimalHelper(10, endNumber, number);
                endNumber = 10;
            }
            else if(ch == 'V'){
                number = decimalHelper(5, endNumber, number);
                endNumber = 5;
            }
            else if(ch == 'I'){
                number = decimalHelper(1, endNumber, number);
                endNumber = 1;
            }
        }
        return number;
    }

    private int decimalHelper(int number, int end, int endNumber){
        if(end > number){
            return endNumber - number;
        }
        else{
            return endNumber + number;
        }
    }
}
