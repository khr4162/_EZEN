		
package product;
import java.util.Scanner;
public interface ProductInterface {
	void add(Scanner scan);
	void printProduct();
	void orderPick(String name, int count);
	void printOrder();
}
