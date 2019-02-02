public class NBody{
	private static String imageToStart = "images/starfield.jpg";

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
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);

		StdDraw.enableDoubleBuffering();
		double tnow = 0;

		while(tnow<T){
			double[] xForces = new double[planets.length];
		    double[] yForces = new double[planets.length];

		    for(int i=0;i<planets.length;i++){
		    	Planet p = planets[i];
		    	double xF = p.calcNetForceExertedByX(planets);
		    	double yF = p.calcNetForceExertedByY(planets);
		    	xForces[i] = xF;
		    	yForces[i] = yF;   
		    }
		    for(int i=0;i<planets.length;i++){
		    	Planet p = planets[i];
		    	p.update(dt, xForces[i], yForces[i]); 
		    }
		    
		    //Draw the background
		    StdDraw.picture(0, 0, imageToStart);

		    for(Planet p : planets){
		    	p.draw();
		    }

		    StdDraw.show();
		    StdDraw.pause(10);
		    
		    tnow+=dt;
		}

		//print out the Universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            		      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  			planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}