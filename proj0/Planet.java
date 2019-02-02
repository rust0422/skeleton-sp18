public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass  = m;
		imgFileName = img;
	}

	public Planet(Planet P){
		xxPos = P.xxPos;
		yyPos = P.yyPos;
		xxVel = P.xxVel;
		yyVel = P.yyVel;
		mass  = P.mass;
		imgFileName = P.imgFileName;
	}

	/** Calculate distance between two planet*/
	public double calcDistance(Planet p){
		double xxDis = this.xxPos-p.xxPos;
		double yyDis = this.yyPos-p.yyPos;
		return Math.sqrt(xxDis*xxDis+yyDis*yyDis);
	}

	/** Calculate Force exerted by some planet*/
	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double force = G * p.mass * this.mass / (r * r);
		return force;
	}

	/** Describe the force exerted in the X directions.
        Args(Planet):
            Planet Object p.
        Returns(double):
            Force exerted in the X direction
    */
    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return (F * dx) / r;
    }


    /** Describe the force exerted in the Y directions.
        Args(Planet):
            Planet Object p.
        Returns(double):
            Force exerted in the Y direction.
    */
    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return (F * dy) / r;
    }

	/** Calculate Force exerted by all other planets in X directions*/
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netForceX = 0;
		for(int i = 0;i<allPlanets.length;i++){
			Planet p = allPlanets[i];
			if(this.equals(p)){
				continue;
			}
			double f = this.calcForceExertedByX(p);
			netForceX += f;
		}
		return netForceX;
	}

	/** Calculate Force exerted by all other planets in Y directions*/
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netForceY = 0;
		for(int i = 0;i<allPlanets.length;i++){
			Planet p = allPlanets[i];
			if(this.equals(p)){
				continue;
			}
			double f = this.calcForceExertedByY(p);
			netForceY += f;
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY){
		double axx = fX/this.mass;
		double ayy = fY/this.mass;

		this.xxVel += dt * axx;
		this.yyVel += dt * ayy;

		this.xxPos +=  xxVel * dt;
		this.yyPos +=  yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+imgFileName);
	}








}