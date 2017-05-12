import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindPeak {

	private static List<Integer> list = new ArrayList<Integer>();

	public int find(int target) {
		int from = 0;
		int to = list.size() - 1;

		int mid = from + (to - from) / 2;
		int midValue, before, after;

		while (from <= to) {
			midValue = list.get(mid);
			before = list.get(mid - 1);
			after = list.get(mid + 1);

			if (midValue > before && midValue > after) {
				// 当中点值比两边的值都大时 该点为峰值点
				return mid;
			} else if (midValue > before && midValue < after) {
				// 中点的值比左边大 比右边小
				// 中点处在上坡阶段 峰值在右半部分
				// 将尾节点设为中点下一个节点
				from = mid + 1;
			} else if (midValue < before && midValue > after) {
				// 中点的值比左边小 比右边大
				// 中点处在下坡阶段 峰值在左半部分
				// 将尾节点设为中点上一个节点
				to = mid - 1;
			}

			// 重新计算中点 否则可能陷入无限循环
			mid = from + (to - from) / 2;
		}

		return -1;
	}

	public void initialize(int size) {
		Random r = new Random(System.currentTimeMillis());

		// 生成峰值的 zero-based 位置
		// 保证其位置处于列表中部
//		int point = size / 3 + r.nextInt(size / 3);

		// 生成峰值的 zero-based 位置
		// 保证其为 1 至 list.size() - 2
		// 否则不能形成峰值列表而退化成有序列表
		int point = 1 + r.nextInt(size - 2);

		System.out.println(point);

		// 生成第一个参照值
		int previous = r.nextInt(size / 3);

		int next;

		// 生成升序部分
		for (int i = 0; i <= point; i++) {
			// 保证生成的下一个值至少比上一个值大 1
			next = previous + 1 + r.nextInt(size / 3);

			list.add(next);
			previous = next;
		}

		for (int i = point + 1; i < size; i++) {
			// 保证生成的下一个值至少比上一个值小 1
			next = previous - 1 - r.nextInt(size / 3);

			list.add(next);
			previous = next;
		}

		System.out.println(list);

	}

	public static void main(String[] args) {
		FindPeak fp = new FindPeak();
		int size = 10;
		fp.initialize(size);
		int pos = fp.find(5);
		System.out.println("pos " + pos + " with value " + list.get(pos));
	}

}
