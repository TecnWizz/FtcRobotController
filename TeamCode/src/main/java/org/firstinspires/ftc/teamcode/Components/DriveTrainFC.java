package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class DriveTrainFC {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    public double powerMode = 1;
    public double currentPower = powerMode;
    public IMU imu;
    boolean ok = true;
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP));
    public DriveTrainFC(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight, IMU imu) {
        ok = true;
        this.imu = imu;
        imu.initialize(parameters);
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }


    public void goGoVrumVrumFC(Gamepad lastGamepad, Gamepad currentGamepad) {

        double y = currentGamepad.left_stick_y;
        double x = -currentGamepad.left_stick_x;
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

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        switch (state){
            case UP:
                powerMode=0.5;
                break;
            case DOWN:
                powerMode=1;
                break;
        }

        if (currentGamepad.circle){
            if (ok==true) {
                state=State.UP;
                ok=false;
            }
            else if (ok==false){
                state = State.DOWN;
                ok=true;
            }

        }

    }

    }

