package frc.robot.subsystems;

import frc.robot.variables.Motors;
import frc.robot.variables.Objects;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.variables.MagicNumbers;

public class Intake {

    public Intake() {

    }

    public void intake(int mode) {
        if (mode == MagicNumbers.defaultIntake) {
            stop();
        }
        if (mode == MagicNumbers.intake) {
            Motors.intake.set(.4);
            Objects.intakeSolenoidExtend.set(Value.kForward);
            Objects.intakeSolenoidRetract.set(Value.kReverse);
        }
        if (mode == MagicNumbers.purgeIntake) {
            Motors.intake.set(-.4);
            Objects.intakeSolenoidExtend.set(Value.kForward);
            Objects.intakeSolenoidRetract.set(Value.kReverse);
        }
    }

    public void stop() {
        Motors.intake.set(0);
        Objects.intakeSolenoidExtend.set(Value.kReverse);
        Objects.intakeSolenoidRetract.set(Value.kForward);
    }
}