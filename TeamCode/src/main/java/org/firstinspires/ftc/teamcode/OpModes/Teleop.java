package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrainFC;

@TeleOp(name = "Avocado_Tele(field centric)")
public class Teleop extends LinearOpMode {


   private DriveTrainFC chassis;

    ///private Intake intake;
    ///private Arm arm;
    private Gamepad lastGamepad1 = new Gamepad();
    private Gamepad currentGamepad1 = new Gamepad();
    private Gamepad lastGamepad2 = new Gamepad();
    private Gamepad currentGamepad2 = new Gamepad();
    DcMotorEx frontLeft,backLeft,frontRight,backRight;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            lastGamepad1.copy(currentGamepad1);
            lastGamepad2.copy(currentGamepad2);

            chassis.goGoVrumVrumFC(lastGamepad1, currentGamepad1);
            ///arm.armControl(currentGamepad2);
            ///intake.aspirator(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            telemetry.update();

        }
    }

    private void initializeHardware() {
        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",currentGamepad1);
        telemetry.addData("Gaempad2 input",currentGamepad2);
        telemetry.addData("0::---------------------------:",0);

        frontLeft = hardwareMap.get(DcMotorEx.class,"frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class,"frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class,"backLeft");
        backRight = hardwareMap.get(DcMotorEx.class,"backRight");
        imu = hardwareMap.get(IMU.class,"imu");

        MotorConfigurationType m= frontLeft.getMotorType();
        m.setAchieveableMaxRPMFraction(1);

        frontLeft.setMotorType(m);
        frontRight.setMotorType(m);
        backLeft.setMotorType(m);
        backRight.setMotorType(m);


        ///extendMotor = hardwareMap.get(DcMotorEx.class,"extendMotor");
        ///slideShiftMotor = hardwareMap.get(DcMotorEx.class,"slideShiftMotor");

        ///rotateServo = hardwareMap.get(Servo.class,"rotateServo");
        ///intakeServo = hardwareMap.get(Crservo.class,"intakeServo");

        chassis = new DriveTrainFC(frontLeft, frontRight, backLeft, backRight,imu);
        ///intake = new Intake(intakeServo,rotateServo);
        ///arm = new Arm(extendMotor, slideShiftMotor);



    }
}
