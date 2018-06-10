package de.hsa.games.fatsquirrel.space;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.*;

import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;

public final class XY {
	private static int counter = 0;
	private final int x;
	private final int y;
	public static final XY UP = new XY(0,-1);
	public static final XY DOWN = new XY(0,1);
	public static final XY LEFT = new XY(-1,0);
	public static final XY RIGHT = new XY(1,0);
	public static final XY UP_LEFT = new XY(-1,-1);
	public static final XY UP_RIGHT = new XY(1,-1);
	public static final XY DOWN_LEFT = new XY(-1,1);
	public static final XY DOWN_RIGHT = new XY(1,1);
	public static final XY STAY = new XY(0,0);

	public XY(int x, int y) {
		// TODO - implement XY.XY
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static XY getRndFreePos(EntitySet entitySet, BoardConfig boardConfig) {

		XY newxy = new XY(ThreadLocalRandom.current().nextInt(boardConfig.getFieldWidth()),
				ThreadLocalRandom.current().nextInt(boardConfig.getFieldHeight()));

		while (entitySet.checkPosFree(newxy) != true) {
			newxy = new XY(ThreadLocalRandom.current().nextInt(boardConfig.getFieldWidth()),
					ThreadLocalRandom.current().nextInt(boardConfig.getFieldHeight()));
		}

		return newxy;

	}
	
	 public static int getDist(Entity entity, XY pos) {           // errechnet Distanz einer übergebenen Entity und einer Position im KOOS
		 return abs(pyth(pos.getX()-entity.getLoc().getX(),pos.getY()-entity.getLoc().getY())); 
	 }
	 
	 public static int pyth(int a, int b) {
		 return (int) sqrt(pow(a,2)+(pow(b, 2)));
	 }

	public static XY genVecfromRand() {
		XY loc;
		switch (ThreadLocalRandom.current().nextInt(1, 8 + 1)) {
		case 1:
			return new XY(1, 0);
		case 2:
			return new XY(-1, -1);
		case 3:
			return new XY(0, -1);
		case 4:
			return new XY(1, -1);
		case 5:
			return new XY(1, 0);
		case 6:
			return new XY(1, 1);
		case 7:
			return new XY(0, 1);
		case 8:
			return new XY(-1, 1);
		default:
			return null;
		}
	}

	public XY addVec(XY xy) {
		return new XY(this.x + xy.getX(), this.y + xy.getY());
	}
	public static XY getVec(XY xy1, XY xy2) {
	   //x1: Spitze , x2: Fuß -> Spitze minus Fuß = Vektor von x2 zu x1
		return new XY(xy1.getX()-xy2.getX(), xy1.getY()-xy2.getY());
	}
	public static XY vecToDir(XY xy) {
		double alpha=0;
		int x=abs(xy.getX());
		int y=abs(xy.getY());
		
		if(xy.getX()>0&&xy.getY()<0)
			alpha=toDegrees(atan(y/x));
		if(xy.getX()>0&&xy.getY()>0)
		   alpha=toDegrees(atan(y/x))+90;
		if(xy.getX()<0&&xy.getY()>0)
		   alpha=toDegrees(atan(y/x))+180;
		if(xy.getX()<0&&xy.getY()<0)
		   alpha=toDegrees(atan(y/x))+270;
		if(xy.getX()==0&&xy.getY()<0)
			return new XY(0,-1);
		if(xy.getX()==0&&xy.getY()>0)
			return new XY(0,1);
		if(xy.getX()>0&&xy.getY()==0)
			return new XY(1,0);
		if(xy.getX()<0&&xy.getY()==0)
			return new XY(-1,0);
		if(alpha>338&&alpha<=23)
			return new XY(0,-1);
		if(alpha>24&&alpha<=67)
			return new XY(1,-1);
		if(alpha>68&&alpha<=113)
			return new XY(1,0);
		if(alpha>114&&alpha<=158)
			return new XY(1,1);
		if(alpha>159&&alpha<=203)
			return new XY(0,1);
		if(alpha>204&&alpha<=248)
			return new XY(-1,1);
		if(alpha>249&&alpha<=293)
			return new XY(-1,0);
		if(alpha>294&&alpha<=337)
			return new XY(-1,-1);		
		return genVecfromRand();
	}
	public static XY invertVec(XY xy) {
		return new XY(-xy.getX(),-xy.getY());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof XY) {
			return ((XY) o).x == this.x && ((XY)o).y == this.y;
		}
		return false;
	}
	
}