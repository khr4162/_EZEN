package day05;

public class String1 {

	public static void main(String[] args) {
		/* String 클래스 = 문자열을 다루는 클래스
		 * String str = new String();
		 * String str = "가나다"; // 일반 자료형처럼 이용 가능
		 * 
		 * 
		 */
		String str = "hello world";
		System.out.println(str);
		
		//charAt(index) : index번지에 있는 문자열을 반환
		System.out.println("--charAt--");
		System.out.println(str.charAt(0)); //몇 번째 글자
		
		//length : 전체 글자의 길이(숫자)
		System.out.println("--length--");
		System.out.println(str.length());
		
		//compareTo(str) : str문자와 비교해서 같으면 0, 사전순으로 앞이면 -1, 뒤면 1(문자의 오름 및 내림차순)
		System.out.println("--compareto(str)");
		System.out.println("b".compareTo("a"));
		System.out.println("b".compareTo("b"));
		System.out.println("b".compareTo("g"));
		
		//concat(str) : 이어붙이기 (+ 연산자와 같은 의미)
		System.out.println("--concat--");
		System.out.println("abc".concat("def"));
		System.out.println("abc"+"def");
		
		//equals(str) : 두 문자열이 같은지 확인(대소문자 구분)
		System.out.println("--equals--");
		System.out.println("abc".equals("def"));
		System.out.println("abc".equals("abc"));
		System.out.println("abc"=="abc");
		
		//indexOf(str) : 문자의 위치를 찾아주는 기능, 없으면 -1을 반환
		//lastindexOf(str) : 마지막에서부터 위치 찾기
		System.out.println("--indexOf--");
		System.out.println("abc".indexOf("b"));
		String email = "khr4162@naver.com.";
		System.out.println(email.indexOf("@"));
		System.out.println(email.indexOf(".")); //중복이면 첫 발견 위치
		System.out.println(email.lastIndexOf(".")); //끝에서부터 찾기
		
		//substring(str) : 문자열 추출
		System.out.println("--substring--");
		System.out.println(email.substring(3)); //해당 위치부터 끝까지 추출
		System.out.println(email.substring(0, 5)); //뒤의 숫자는 갯수(0번지부터 5개)
		System.out.println(email.substring(0, email.indexOf("@")));
		System.out.println(email.substring(email.indexOf("@")+1));
		
		//trim : 불필요한 공백 삭제
		System.out.println("--trim--");
		System.out.println("  hello    ".trim());
		
		//replace : 글자 치환
		System.out.println("--replace--");
		System.out.println("Hello world".replace("world", "JAVA")); //모든 값이 다 바뀜
		
		//split : 특정 값를 기준으로 나누기
		System.out.println("--split--");
		String str1= "dog,cat,tiger";
		String[] arr = str1.split(",");
		for(String tmp : arr) {
			System.out.println(tmp+" "); //배열화
		}
		
		String num1 = "1";
		String num2 = "2";
		String sum = num1 + num2;
		System.out.println(sum);
		
		//parseInt : 문자를 숫자로 변환(진짜 많이 씀)
		//Integer : int의 클래스
		System.out.println("--parseInt--");
		int num3 = Integer.parseInt(num1);
		System.out.println(num3);
		int num4 = Integer.parseInt(num2);
		System.out.println(num4);
		int sum1 = num3+num4;
		System.out.println(sum1);
		
		
		
		
	}

}
