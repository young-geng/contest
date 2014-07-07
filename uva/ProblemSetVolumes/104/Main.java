import java.util.*;

public class Main {
	static double[][] map;
	static LinkedList<Route> queue;
    static double[] marked;
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNextInt()) {
			int dim = cin.nextInt();
			map = new double[dim][dim];
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (j == i) {
						map[i][j] = 1;
						map[j][i] = 1;
						continue;
					}
					map[i][j] = cin.nextDouble();
				}
			}
			ArrayList<Integer> ans = null;
			for (int i = 0; i < dim; i++) {
				queue = new LinkedList<Route>();
				queue.add(new Route(i));
                marked = new double[dim];
				while (!queue.isEmpty() && queue.getFirst().pathLength() <= dim + 1) {
					Route current = queue.remove();
					if (current.isGoal()) {
						if (ans == null || ans.size() > current.pathLength()) {
							ans = current.path;
						}
						break;
					}
                    if (current.val <= marked[current.lastStep()]) {
                        continue;
                    } else {
                    	marked[current.lastStep()] = current.val;
                    }
					for (int j = 0; j < dim; j++) {
						if (j == current.lastStep())
							continue;
						queue.add(new Route(current, j, current.val * map[current.lastStep()][j]));
					}
				}
			}
			if (ans == null) {
				System.out.println("no arbitrage sequence exists");
			} else {
				System.out.print(ans.get(0).intValue() + 1);
				for (int i = 1; i < ans.size(); i++) {
					System.out.print(" " + (ans.get(i).intValue() + 1));
				}
				System.out.println();
			}


		}
	}

}


class Route {
	public static double INIT_VAL = 10000;
	public double val;
	public ArrayList<Integer> path;

	public Route(Route prev, int nextStep, double newVal) {
		path = (ArrayList<Integer>)prev.path.clone();
		path.add(nextStep);
		this.val = newVal;
	}

	public Route(int initStep) {
		path = new ArrayList<Integer>();
		path.add(initStep);
		val = INIT_VAL;
	}

	public boolean isGoal() {
		return path.get(0).equals(path.get(path.size() - 1)) && val >= INIT_VAL * 1.01; 
	}

	public int pathLength() {
		return path.size();
	}

	public int lastStep() {
		return path.get(path.size() - 1).intValue();
	}
}
