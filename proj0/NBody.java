public class NBody{
	public static String imageToStart = "images/starfield.jpg";

	public static double readRadius(String s){
		In in = new In(s);
		int n = in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Planet[] readPlanets(String s){

		In in = new In(s);
		int n = in.readInt();//number of planets
		double r = Double.valueOf(in.readString());

		Planet[] pp = new Planet[n];
		for(int i=0;i<n;i++) {
			double xP = Double.valueOf(in.readString());
			double yP = Double.valueOf(in.readString());
			double xV = Double.valueOf(in.readString());
			double yV = Double.valueOf(in.readString());
			double m  = Double.valueOf(in.readString());
			String img = in.readString();
			
			Planet p = new Planet(xP, yP, xV, yV, m, img);
			pp[i] = p;
		}
		return pp;
	}

	public static void main(String[] args){
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename= args[2];
		double radius = readRadius(filename);
		Planet[] PlanetList = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, imageToStart);


	for(int i=0;i<PlanetList.length;i++){
			Planet p = PlanetList[i];
			p.draw();
		}
	}
}