package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class DriveTrainFC {
    private final DcMotorEx leftFront, rightFront, leftBack, rightBack;
    public double powerMode = 1;
    public IMU imu;
    boolean ok;
    public enum State{
        UP,
        DOWN,
    }
    State state = State.DOWN;
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP));
    public DriveTrainFC(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack, IMU imu) {
        ok = true;
        this.imu = imu;
        imu.initialize(parameters);
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }


    public void goGoVrumVrumFC(Gamepad lastGamepad, Gamepad currentGamepad) {

        double y = -currentGamepad.left_stick_y;
        double x = currentGamepad.left_stick_x;
        double rx = -currentGamepad.right_trigger+currentGamepad.left_trigger;
        if (currentGamepad.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) * powerMode / denominator;
        double backLeftPower = (rotY - rotX + rx) * powerMode / denominator;
        double frontRightPower = (rotY - rotX - rx) * powerMode / denominator;
        double backRightPower = (rotY + rotX - rx) * powerMode / denominator;

        leftFront.setPower(frontLeftPower);
        leftBack.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);

        switch (state){
            case UP:
                powerMode=0.5;
                break;
            case DOWN:
                powerMode=1;
                break;
        }

        if (currentGamepad.circle){
            if (ok) {
                state=State.UP;
                ok=false;
            }
            else {
                state = State.DOWN;
                ok=true;
            }

        }

    }

    }

