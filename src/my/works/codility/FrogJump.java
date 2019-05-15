package my.works.codility;

public class FrogJump {

	public int solution(int X, int Y, int D) {
		int diff = Y-X;
		if(diff%D!=0)
			return 1+(diff/D);
		return (diff/D);
	}
}
