import java.io.*;
import java.util.*;

class Solution {
	Node[] node;
	StringTokenizer st;

	class Node {
		int val, pre, next;

		public Node(int val) {
			this.val = val;
			pre = val - 1;
			next = val + 1;
		}

		public void setPre(int pre) {
			this.pre = pre;
		}

		public void setNext(int next) {
			this.next = next;
		}

		public void remove() {
			if (pre >= 0) {
				node[pre].next = this.next;
			}

			if (next < node.length) {
				node[next].pre = this.pre;
			}
		}

		public void restore() {
			if (pre >= 0) {
				node[pre].next = val;
			}

			if (next < node.length) {
				node[next].pre = val;
			}
		}
	}

	public String solution(int n, int k, String[] cmd) {
		StringBuilder answer = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();

		int ptr = k;
		node = new Node[n];

		for (int i = 0; i < n; i++) {
			node[i] = new Node(i);
		}

		for (String cm : cmd) {
			st = new StringTokenizer(cm, " ");
			char c = st.nextToken().charAt(0);

			if (c == 'C') {
				stack.push(ptr);
				node[ptr].remove();
				// System.out.printf("%d 삭제\n", ptr);

				if (node[ptr].next == n) {
					ptr = node[ptr].pre;
				} else {
					ptr = node[ptr].next;
				}

				// System.out.printf("포인터 위치: %d\n\n", ptr);
			} else if (c == 'Z') {
				int id = stack.pop();
				// System.out.printf("%d 복원\n", id);
				node[id].restore();

				// System.out.printf("포인터 위치: %d\n\n", ptr);
			} else if (c == 'U') {
				int num = Integer.parseInt(st.nextToken());
				// System.out.printf("위로 %d만큼 이동\n", num);

				for (int i = 0; i < num; i++) {
					ptr = node[ptr].pre;
				}

				// System.out.printf("포인터 위치: %d\n\n", ptr);
			} else if (c == 'D') {
				int num = Integer.parseInt(st.nextToken());
				// System.out.printf("아래로 %d만큼 이동\n", num);

				for (int i = 0; i < num; i++) {
					ptr = node[ptr].next;
				}

				// System.out.printf("포인터 위치: %d\n\n", ptr);
			}
		}

		boolean[] enabled = new boolean[n];

		for (int i = 0; i < n; i++) {
			enabled[i] = true;
		}

		while (!stack.isEmpty()) {
			enabled[stack.pop()] = false;
		}

		for (boolean st : enabled) {
			if (st) {
				answer.append("O");
			} else {
				answer.append("X");
			}
		}

		return answer.toString();
	}
}