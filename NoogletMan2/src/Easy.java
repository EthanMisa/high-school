package MainFiles;

public class Easy extends Info{
	
	public Easy() {
		super();
		setLives();
	}

	@Override
	public void setLives() {
		setLives(4);
		
	}

	@Override
	public void updateHangMan() {
		setLives(getLives()-1);
		if(getLives() == 4) {
			setHead(false);
			setBody(false);
			setArms(false);
			setLegs(false);
		}
		if(getLives() == 3) {
			setHead(true);
			setBody(false);
			setArms(false);
			setLegs(false);
		}
		if(getLives() == 2) {
			setHead(true);
			setBody(true);
			setArms(false);
			setLegs(false);
		}
		if(getLives() == 1) {
			setHead(true);
			setBody(true);
			setArms(true);
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
