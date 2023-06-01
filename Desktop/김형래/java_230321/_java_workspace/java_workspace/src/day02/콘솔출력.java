package day02;

public class 콘솔출력 {

	public static void main(String[] args) {
		/* system.out.print(); // 줄 바꿈 불가능
		 * system.out.println(); // 줄 바꿈 가능
		 * system.out.printf(); // 
		 * 
		 * 서식지정자를 통해 출력할 데이터의 서식을 지정할 수 있음.
		 * %c : 한 글자 / %s : 문자열 / %d : 10진 정수 / %f : 실수
		 * 
		 * escape sequence
		 * \', \", \n(줄 바꿈), \r(캐리지 리턴), \t(탭)
		 */
		int num1 = 3;
		int num2 = 5;
		System.out.println(num1 + num2);
		System.out.print(num1 + num2);
		System.out.println(num1+ "+" + num2 + "=" + (num1 + num2));
		System.out.printf("%d+%d=%d\n", num1, num2, (num1 + num2));
		
		double num3 = 78;
		double num4 = 7;
		System.out.println(num3+ "/" + num4 + "=" + (num3 / num4));
		System.out.printf("%.0f / %.0f = %.2f\n", num3, num4, (num3 / num4));
		
		
		
		
		
	}

}
