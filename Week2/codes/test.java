import java.util.Scanner;
public class test
{
	public static void main(String[] args) {
		stackmax s = new stackmax();
		s.push(1);
		s.push(3);
		s.push(2);
		System.out.println(s.top());
		System.out.println(s.max());
		s.pop();
		s.pop();
		System.out.println(s.max());
		System.out.println(s.top());
	}
}