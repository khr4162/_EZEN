package day03;

import java.util.Scanner;

public class For문3 {

	public static void main(String[] args) {

		/* 1부터 100까지 합계
		 * 
		 */
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum= sum + i;} //누적 내변수 = 내변수 + sum 대상(sum+=i)
		System.out.println(sum);
		
		
		
		/* 약수 구하기
		 * 약수 : 나누어서 떨어지는 수들의 모임
		 * 12의 약수 : 1, 2, 3, 4, 6, 12
		 * num=12;
		 * 1부터 12까지의 수를 num와 나누어서 떨어지는 수를 출력
		 * 초기값 : 1부터
		 * 조건식 : num까지
		 * 증감식 : 1씩 증가
		 * 실행문 : 나머지가 0이 되는 수를 출력
		 */
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자를 입력해주세요");
		int num = scan.nextInt();
		for(int i=1; i<=num; i++) {
			if(num%i==0) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
		
		
		
		
		
		
		
		
		
		
	}

}
