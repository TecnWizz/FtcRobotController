package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Arm {
    private DcMotor extendMotor, slideShiftMotor;
    private static final double TICKS_PER_ROTATION = 2786.2;
    private static final double ROTATION_DIVISOR = 2;
    private PIDController extendPID, slideShiftPID;

    public Arm(DcMotor extendMotor, DcMotor slideShiftMotor) {
        this.extendMotor = extendMotor;
        this.slideShiftMotor = slideShiftMotor;
        resetEncoders(extendMotor, slideShiftMotor);

        extendPID = new PIDController(0.01, 0.0001, 0.0005);
        slideShiftPID = new PIDController(0.01, 0.0001, 0.0005);
    }

    private void resetEncoders(DcMotor... motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void armControl(Gamepad currentGamepad) {
        int targetExtendPosition = (int) (TICKS_PER_ROTATION / ROTATION_DIVISOR);
        int targetSlideShiftPosition = (int) (TICKS_PER_ROTATION / ROTATION_DIVISOR);


        int extendPosition = extendMotor.getCurrentPosition();
        int slideShiftPosition = slideShiftMotor.getCurrentPosition();

        if (Math.abs(currentGamepad.left_stick_y) > 0.35) {
            targetExtendPosition += (int) (currentGamepad.left_stick_y * 50);
        }
        if (Math.abs(currentGamepad.right_stick_y) > 0.35) {
            targetSlideShiftPosition += (int) (currentGamepad.right_stick_y * 50);
        }

        double extendPower = extendPID.calculate(targetExtendPosition, extendPosition);
        double slideShiftPower = slideShiftPID.calculate(targetSlideShiftPosition, slideShiftPosition);

        extendMotor.setPower(Math.max(-1, Math.min(1, extendPower)));
        slideShiftMotor.setPower(Math.max(-1, Math.min(1, slideShiftPower)));
    }

    private static class PIDController {
        private double Kp, Ki, Kd;
        private double previousError = 0;
        private double integral = 0;

        public PIDController(double Kp, double Ki, double Kd) {
            this.Kp = Kp;
            this.Ki = Ki;
            this.Kd = Kd;
        }

        public double calculate(double target, double current) {
            double error = target - current;
            integral += error;
            double derivative = error - previousError;
            previousError = error;

            return (Kp * error) + (Ki * integral) + (Kd * derivative);
        }
    }
}
