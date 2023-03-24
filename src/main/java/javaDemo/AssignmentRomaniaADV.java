package javaDemo;

import java.util.Scanner;

public class AssignmentRomaniaADV {

	public static void main(String[] args) {
		int sum;
		Scanner sc = new Scanner(System.in);
		int[] number = new int[1];

		for (int i = 0; i < 1; i++) {
			number[i] = sc.nextInt();
		}

		for (int j = 0; j < number.length; j++) {

			System.out.println(" Number entered by user at " + j + "th position is : " + number[j]);

			String numToStr = String.valueOf(number[j]);
			validateUserInput(numToStr);

			int ranNum = generateRanNum();

			System.out.println("Random number generated is :" + ranNum);

			long concatNumRan = Long.valueOf(String.valueOf(number[j]) + String.valueOf(ranNum));
			System.out.println(concatNumRan);

			String m = String.valueOf(concatNumRan);
			// System.out.println(m);

			sum = getSum(m);
			System.out.println("Sum is :" + sum);

			long finalNum = Long.valueOf(String.valueOf(concatNumRan) + String.valueOf(getCtrlNum(sum)));

			System.out.println("Final 13 digit number is : " + finalNum);
		}
	}

	public static void validateUserInput(String numToStr) {
		if (numToStr.length() == 9) {

			// gender code description
			String numberArray[] = numToStr.split("");
			int GenderCodeDigit = Integer.parseInt(numberArray[0]);

			if (GenderCodeDigit < 1) {
				System.out.println("incorrect first digit");
			} else if (GenderCodeDigit == 7 || GenderCodeDigit == 8) {
				System.out.println("Foreign residents in Romania ");
			} else if (GenderCodeDigit == 9) {
				System.out.println("Non- residents");
			}

			 //Birth year description
			int century= 0;
			if(GenderCodeDigit==1 || GenderCodeDigit==2 || GenderCodeDigit==7 || GenderCodeDigit==8) {
				century= 1900;
			}else if (GenderCodeDigit==3 || GenderCodeDigit==4) {
				century =1800;
			}else if (GenderCodeDigit==5 || GenderCodeDigit==6) {
				century =2000;
			}
			
			String birthYearCode = numberArray[1] + numberArray[2];
			int birthYear = century + Integer.valueOf(birthYearCode);
			System.out.println("Year of birth is : " + birthYear); //for gender code =9 we don't have century information in program so for it only birth code will be displayed

			// Birth month description
			int f = Integer.valueOf(numberArray[3]);
			int g = Integer.valueOf(numberArray[4]);
			int month = (f * 10) + g;
			if (month < 01 || month > 12) {
				System.out.println("Birth month is invalid");
			} else {
				System.out.println("Birth month is : " + month);
			}

			//// Birth date description
			int h = Integer.parseInt(numberArray[5]);
			int k = Integer.parseInt(numberArray[6]);
			int day = (h * 10) + k;
			if (day < 01 || day > 31) {
				System.out.println("Invalid date");
			} else if ((month == 04 || month == 06 || month == 11) && (day > 30)) {
				System.out.println("Invalid date");
			} else if ((month == 02) && (day > 29)) {
				System.out.println("Invalid date");
			} else if ((month == 02) && (((birthYear)%4)!=0) && (day == 29)) {
				System.out.println("Invalid date");
			}else {
				System.out.println("Day of birth is " + day);
			}

			// country code description
			int l = Integer.parseInt(numberArray[7]);
			int m = Integer.parseInt(numberArray[8]);
			int areaCode = (l * 10) + m;
			if (areaCode < 01 || areaCode > 52) {
				System.out.println("Invalid area code");
			} else {
				System.out.println("Area code is " + areaCode);
			}
		} else {
			System.out.println("Please enter 9 digit number");

		}
	}
	
	public static int getSum(String num) {
		int sum = 0;
		for (int i = 0; i < num.length() - 1; i++) {

			char p = num.charAt(i);
			int a1 = Character.getNumericValue(p);
			sum = sum + ((i + 1) * a1);
		}
		return sum;
	}

	public static int generateRanNum() {
		return (int) Math.floor(Math.random() * 900) + 100;
	}

	public static long getCtrlNum(int sum) {
		long ctrlNum, rem;

		rem = sum - (11 * (sum / 11));
		if (rem == 10) {
			ctrlNum = 1;
		} else {
			ctrlNum = rem;
		}

		return ctrlNum;
	}
}
