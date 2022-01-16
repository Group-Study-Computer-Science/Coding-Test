import java.awt.Point;

class Solution {
	public boolean solution(int[][] key, int[][] lock) {
		for (int i = 0; i < 4; i++) {
			if (openLock(key, lock)) {
				return true;
			}
			key = rotateKey(key);
		}
		return false;
	}

	public boolean openLock(int[][] key, int[][] lock) {
		int keySize = key.length;
		int lockSize = lock.length;

		for (int y = 1 - keySize; y < lockSize; y++) {
			for (int x = 1 - keySize; x < lockSize; x++) {
				if (checkOpened(joinKeyAndLock(key, lock, new Point(x, y)))) {
					return true;
				}
			}
		}

		return false;
	}

	// key와 lock을 합친 결과를 반환한다.
	public int[][] joinKeyAndLock(int[][] key, int[][] lock, Point keyStartPos) {
		int keySize = key.length;
		int lockSize = lock.length;
		int[][] result = new int[lockSize][lockSize];

		for (int y = 0; y < lockSize; y++) {
			for (int x = 0; x < lockSize; x++) {
				result[y][x] = lock[y][x];
			}
		}

		for (int y = 0; y < keySize; y++) {
			for (int x = 0; x < keySize; x++) {
				int tmpY = y + keyStartPos.y;
				int tmpX = x + keyStartPos.x;

				if (0 <= tmpY && tmpY < lockSize && 0 <= tmpX && tmpX < lockSize) {
					result[tmpY][tmpX] += key[y][x];
				}
			}
		}

		return result;
	}

	public boolean checkOpened(int[][] result) {
		int size = result.length;

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (result[y][x] != 1) {
					return false;
				}
			}
		}

		return true;
	}

	// key를 우측으로 90도 회전시킨다.
	public int[][] rotateKey(int[][] key) {
		int keySize = key.length;
		int[][] newKey = new int[keySize][keySize];

		for (int y = 0; y < keySize; y++) {
			for (int x = 0; x < keySize; x++) {
				newKey[y][x] = key[keySize - x - 1][y];
			}
		}

		return newKey;
	}

	public void printMap(int[][] map) {
		int mapSize = map.length;
		System.out.println();

		for (int y = 0; y < mapSize; y++) {
			for (int x = 0; x < mapSize; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
	}
}