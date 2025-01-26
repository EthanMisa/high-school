package MainFiles;

public class Hard extends Info {
	
	public Hard() {
		super();
		setLives();
	}

	@Override
	public void setLives() {
		setLives(2);
		
	}

	@Override
	public void updateHangMan() {
		setLives(getLives()-1);		
		if(getLives() == 2) {
			setHead(false);
			setBody(false);
			setArms(false);
			setLegs(false);
		}
		if(getLives() == 1) {
			setHead(true);
			setBody(true);
			setArms(false);
			setLegs(false);
		}
		if(getLives() == 0) {
			setHead(true);
			setBody(true);
			setArms(true);
			setLegs(true);
			
		}
		
	}

}
