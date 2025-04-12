package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;

@TeleOp(name = "Fiorosul Tudor")
public class Teleop extends LinearOpMode {


   private DriveTrain chassis;
    ///private Intake intake;
    ///private Arm arm;
    private Gamepad lastGamepad1 = new Gamepad();
    private Gamepad currentGamepad1 = new Gamepad();
    private Gamepad lastGamepad2 = new Gamepad();
    private Gamepad currentGamepad2 = new Gamepad();
    DcMotorEx frontLeftMotor,backLeftMotor,frontRightMotor,backRightMotor;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            lastGamepad1.copy(currentGamepad1);
            lastGamepad2.copy(currentGamepad2);

            chassis.goGoVrumVrum(lastGamepad1, currentGamepad1);
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

        frontLeftMotor = hardwareMap.get(DcMotorEx.class,"frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class,"backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotorEx.class,"backRightMotor");
        imu = hardwareMap.get(IMU.class, "imu");


        MotorConfigurationType m= frontLeftMotor.getMotorType();
        m.setAchieveableMaxRPMFraction(1);

        frontLeftMotor.setMotorType(m);
        frontRightMotor.setMotorType(m);
        backLeftMotor.setMotorType(m);
        backRightMotor.setMotorType(m);


        ///extendMotor = hardwareMap.get(DcMotorEx.class,"extendMotor");
        ///slideShiftMotor = hardwareMap.get(DcMotorEx.class,"slideShiftMotor");

        ///rotateServo = hardwareMap.get(Servo.class,"rotateServo");
        ///intakeServo = hardwareMap.get(Crservo.class,"intakeServo");

        chassis = new DriveTrain(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor,imu);
        ///intake = new Intake(intakeServo,rotateServo);
        ///arm = new Arm(extendMotor, slideShiftMotor);



    }
}
