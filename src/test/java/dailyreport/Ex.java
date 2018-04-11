package dailyreport;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

public class Ex {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Nhập chuỗi đầu vào:");
			String s = scanner.nextLine();
			if (s.length() > 0) {
				Character r;
				for (int i = 0; i < s.length(); i++) {
					r = s.charAt(i);
					if(r.equals(s.charAt(i)))
					s.replace(r.toString(), "");
				}
			} else
				System.out.println("Chưa nhập kìa");
			System.out.println();
		}

	}

	public void test1() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Nhập chuỗi đầu vào:");
			String s = scanner.nextLine();
			if (s.length() > 0) {
				Map<Character, Integer> map = new HashMap<>(105);
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (map.containsKey(c)) {
						map.put(c, map.get(c) + 1);
					} else
						map.put(c, 1);
				}
				System.out.println(map);
				// get value
				Optional<Object> op = map.entrySet().stream().filter(e -> e.getValue() == 1).findFirst()
						.map(Entry::getKey);
				if (op.isPresent())
					System.out.println("Tìm thấy: " + op.get());
				else
					System.out.println("Không tìm thấy.");
			} else
				System.out.println("Chưa nhập kìa");
			System.out.println();
		}
	}
}
