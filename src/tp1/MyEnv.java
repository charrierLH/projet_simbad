package tp1;

import javax.vecmath.Vector3d;
import simbad.sim.Arch;
import simbad.sim.EnvironmentDescription;

public class MyEnv extends EnvironmentDescription {
	public MyEnv(){
		add(new Arch(new Vector3d(3,0,-3),this));
		add(new MyRobot(new Vector3d(0, 0, 0),"mon robot"));
	}
}
