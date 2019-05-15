package my.works.codility;

public class ArraysRightRotation {

	public int[] rotRight(int[] a, int d) {

		int[] resultantArr = new int[a.length];

		for(int i=0;i<a.length;i++) {
			int index = (i+(a.length+d))%a.length;
			resultantArr[index] = a[i];
		}

		return resultantArr;
	}

}
