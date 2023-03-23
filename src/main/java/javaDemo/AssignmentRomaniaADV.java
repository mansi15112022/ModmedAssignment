package javaDemo;

import java.util.Scanner;

public class AssignmentRomaniaADV {

    public static void main(String[] args) {
        int sum;
        Scanner sc = new Scanner(System.in);
        int[] number = new int[2];

        for(int i=0; i<2; i++){
            number[i] = sc.nextInt();
        }
        

        for(int j =0; j<number.length;j++){

            System.out.println(" User input at "+j +"th position: "+ number[j] );

            String numberToString = String.valueOf(number[j]); 
            validateUserInput(numberToString );

            int ranNum =generateRanNum();

            System.out.println("Random digit : " + ranNum);

            long twl = Long.valueOf(String.valueOf(number[j]) + String.valueOf(ranNum));
            System.out.println(twl);

            String m = String.valueOf(twl);
            System.out.println(m);

            sum = getSum(m);
            System.out.println("sum :" + sum);

            long fnum = Long.valueOf(String.valueOf(twl) + String.valueOf(getCtrlNum(sum)));

            System.out.println("13 digit number is : " + fnum);
        }
    }

    public static void validateUserInput(String numberToString){
        if (numberToString.length() == 9) {
        	
            String numberArray[] = numberToString.split("");
            int a = Integer.parseInt(numberArray[0]);
            if (a < 1) {
                System.out.println("incorrect first digit");
            } else if (a == 7 || a==8) {
                System.out.println("Foreign residents in Romania ");
            }else if (a == 9) {
                System.out.println("Non- residents");
            }

            String yearOfBirth=numberArray[1]+numberArray[2];
            System.out.println("year : " +yearOfBirth );

            if(Integer.parseInt(numberArray[3]) >1 )
                System.out.print(" :: Invalid month");
            else if (Integer.parseInt(numberArray[3])==1 && Integer.parseInt(numberArray[4])>2){
                System.out.print("invalid month");
            }
            else if (Integer.parseInt(numberArray[3])==0 && Integer.parseInt(numberArray[4])==0){
                System.out.print("invalid month");
            }

            int month = Integer.valueOf(numberArray[3] + numberArray[4]);

            System.out.println("Month : " + month);
            int f = Integer.parseInt(numberArray[5]);
            if (f > 3) {
                System.out.print("Invalid date");
            }

            int g = Integer.parseInt(numberArray[6]);
            if (f == 0 && g == 0) {
                System.out.print("Invalid date");
            } else if (f == 3 && g > 1) {
                System.out.print("Invalid date");
            } else if ((f == 3 && g > 0) && (month == 04 || month == 06 || month == 11)) {
                System.out.print("Invalid date");
            } else if (month == 02 && f == 2 && g > 9) {
                System.out.print("Invalid date");
            }
            int fg = Integer.valueOf(String.valueOf(f) + String.valueOf(g));

            System.out.println("Date : " + fg);
            int h = Integer.parseInt(numberArray[7]);
            if (h > 5) {
                System.out.println("Invalid Code");
            }
            int j = Integer.parseInt(numberArray[8]);
            if (h == 5 && j > 2) {
                System.out.println("Invalid Code");
            }
            int hj = Integer.valueOf(String.valueOf(h) + String.valueOf(j));
            System.out.println("Area Code : " + hj);
            System.out.println("Date : " + fg);
        } else {
            System.out.println("Please enter 9 digit number");

        }
    }
    public static int getSum(String num) {
        int sum = 0;
        for (int i = 0; i < num.length() - 1; i++) {

            char p = num.charAt(i);   //236738276
            int a1 = Character.getNumericValue(p);
            sum = sum + ((i + 1) * a1);
        }
        return sum;
    }

    public static int generateRanNum() {
        return (int) Math.floor(Math.random() * 900) + 100;
    }
    public static long getCtrlNum(int sum){
        long  ctrlNum,rem;

        rem = sum - (11 * (sum / 11));
        if (rem == 10) {
            ctrlNum = 1;
        }
        else {
            ctrlNum = rem;
        }
        
        return  ctrlNum;
    }
}

