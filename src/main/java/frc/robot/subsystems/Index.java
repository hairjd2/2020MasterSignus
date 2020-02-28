package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.variables.MagicNumbers;
import frc.robot.variables.Motors;

public class Index {

    private int numCells;
    private static boolean flushIndex = false;
    private int timer = 0;
    private boolean end = false;
    boolean startOfIndex = false;

    DigitalInput ind0 = new DigitalInput(1);
    DigitalInput ind5 = new DigitalInput(2);

    public Index(final int numCells) {
        this.numCells = numCells;
    }

    public void countCells(int change) { 
        numCells += change;
    }

    public void setFlush(boolean flushIndex) {
        Index.flushIndex = flushIndex;
    }

    public int getNumCells() {
        return numCells;
    }

    public boolean getInd5() {
        return ind5.get();
    }

    public void moveCells(double speed) {
        Motors.indexLead.set(speed);
        Motors.indexFollower.follow(Motors.indexLead, false);
        Motors.moveUp.set(0.7 * speed);
    }

    public void moveTo5() {
        if (!ind5.get()) {
            moveCells(0.5);
        } else {
            moveCells(0);
        }
    }

    public void resetInd() {
        if (!ind0.get()) {
            moveCells(-0.5);
        } else {
            moveCells(0.0);
        }

        if (ind0.get()) {
            moveCells(0.5);
        } else {
            moveCells(0.0);
        }
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void backgroundIndex() {
        if (!flushIndex) {
            if (timer < MagicNumbers.ballSpacing) {
                if (ind0.get()) {
                    moveCells(0.5);
                    end = true;
                } else if (end) {
                    moveCells(0.5);
                    timer++;
                }
            } else {
                moveCells(0.0);
                timer = 0;
                end = false;
            }
        }
    }
    
	public void purgeIndex() {
        moveCells(0.5);
	}

    public void indexOverride(double percent) {
        moveCells(percent);
    }
    
	public void manualOuttake() {
        setFlush(true);
        moveCells(0.5);
    }
}