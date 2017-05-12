import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindPeak {

	private List<Integer> list = new ArrayList<Integer>();

	public int find(int target) {
		int from = 0;
		int to = list.size() - 1;
		int mid = from + (to - from) / 2;

		while (from < to) {
			if (list.get(mid) > list.get(from)) {
				// 中点元素大于起点元素
				// list[form, mid] 区间为升序
				// 旋转点（目标）在 list[mid, to] 区间
				from = mid + 1;
			} else if (list.get(mid) < list.get(from)) {
				// 中点元素小于起点元素
				// 旋转点（目标）在 list[form, mid] 区间
				// list[mid, to] 区间为升序
				to = mid - 1;
			} else {
				return to;
			}
			mid = from + (to - from) / 2;
		}

		return -1;
	}

	public void initialize() {
		Random r = new Random(System.currentTimeMillis());
		int next;
		for (int i = 0; i < 10; i++) {
			do {
				next = r.nextInt(10);
			} while (list.contains(next));

			list.add(next);
		}
		System.out.println(list);

		Collections.sort(list);

		System.out.println(list);

		Collections.rotate(list, 2 + r.nextInt(9));

		System.out.println(list);
	}

	public static void main(String[] args) {
		FindPeak fp = new FindPeak();
		fp.initialize();
		int pos = fp.find(5);
		System.out.println(pos);
	}

}
