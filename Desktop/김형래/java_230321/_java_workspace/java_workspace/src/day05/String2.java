package day05;

public class String2 {

	public static void main(String[] args) {
		/* 이것이자바다.txt 파일에서 파일명과 확장자를 분리하여 출력
		 * 파일명 : 이것이자바다
		 * 확장자 : txt
		 */
		
		String str = "이것이자바다.txt";
		System.out.println("파일명 : " + str.substring(0, str.indexOf(".")));
		System.out.println("확장자 : " + str.substring(str.indexOf(".")+1, str.length()));
		
		//String name - str.substring(0, str.indexOf("."));
		//String file - str.substring(str.indexOf(".")+1);
		//
		
		//contains : 해당 글자가 포함되어있는지 boolean
		System.out.println(str.contains("자바"));
		
		
		
	}

}
