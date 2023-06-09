package day02;

public class For문1 {

	public static void main(String[] args) {
		/* 반복문 : 규칙적인 작업을 반복적으로 할때 사용하는 문법
		 * 종류 : for, while, do~while
		 * for(1초기화 ; 25조건식 ; 47증감연산식){
		 * 36실행문
		 * }
		 * 
		 * 1. 초기화 : 조건식이나, 실행문에서 사용하는 변수를 초기화 시키는 역할
		 * 처음 한 번만 실행(생략가능)
		 * 2. 조건식 : 반복문의 반복을 결정하는 식. 참이면 반복, 거짓이면 그만.
		 * 생략 가능 => 생략하면 무조건 true
		 * 3. 증감연산식 : 조건식에서 사용되는 변수를 증가/감소 시켜서 반복 횟수를 결정(생략가능)
		 * 
		 */
		
		/* 1 ~ 10까지 출력하는 예제
		 * 초기화 : 1부터 시작(i=1)
		 * 조건식 : 10까지(i<=10)
		 * 증감연산식 : i의 값은 1씩 증가(i = i+1)==(i++)
		 * 실행문 : i를 출력븐
		 * 반복 종료 후 실행문
		 *
		 */
		for(int i =1; i<= 10; i++) {
			System.out.println(i+", ");
		}
		
		/* 지역변수의 범위{선언  종결}
		 * 
		 * 
		 */
		System.out.println(); // 줄 바꿈 용도
		
		for(int i =1; i<= 10; i+=2) { //(i=i+a) = i+=a
			System.out.print(i+", ");
		}
		
		/* 10부터 1까지 출력
		 * 초기값
		 * 조건
		 * 증감식
		 */
		
		System.out.println();
		for(int i =10; i > 0; i--) {
			System.out.print(i + ", ");
		}
		
		/* 1 ~ 10까지 짝수만
		 * 
		 */
		
		
		System.out.println();
		for(int i =2; i<=10; i+=2) {
			System.out.print(i + ", ");
		}
		
		/* 1 ~ 10까지 짝수만
		 * if문을 사용해서 조건에 맞을 때만 출력
		 */

		System.out.println();
		for(int i=1; i<=10; i++) {
			if(i % 2 == 0) {
				System.out.print(i + ", ");
			}
		}
		
		/* 1 ~ 10까지의 합계
		 * 실행문 : 누적
		 * 실행 후 출력
		 */
		System.out.println();
		
		int sum = 0;
		for(int i=1; i<=10; i++) {
			sum = sum + i;
		}
		System.out.println(sum);
		
		
		
		
		
	
	}
	
}

