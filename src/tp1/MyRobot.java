package tp1;

import javax.vecmath.Vector3d;

import simbad.sim.Agent;

public class MyRobot extends Agent {

	public MyRobot(Vector3d position, String nom) {
		super(position, nom);
		// TODO Auto-generated constructor stub
	}
	public void
	initBehavior() {}
	public void performBehavior() {
		// avance à 0.5 m/s
		setTranslationalVelocity(0.5);
		// changer l'angle fréquemment
		if ((getCounter() % 100)==0)
			setRotationalVelocity(Math.PI/2 * (0.5 - Math.random()));
	}
}
