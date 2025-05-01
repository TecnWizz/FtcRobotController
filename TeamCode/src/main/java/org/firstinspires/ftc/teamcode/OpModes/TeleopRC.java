package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrainFC;
import org.firstinspires.ftc.teamcode.Components.Extendo;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Lift;

@TeleOp(name = "Avocado_Tele(field centric)")
public class TeleopRC extends LinearOpMode {


   private DriveTrainFC chassis;
    private Intake intake;
    private Lift lift;
    private Extendo extendo;
    private Gamepad lastGamepad1 = new Gamepad();
    private Gamepad currentGamepad1 = new Gamepad();
    private Gamepad lastGamepad2 = new Gamepad();
    private Gamepad currentGamepad2 = new Gamepad();
    DcMotorEx frontLeft,backLeft,frontRight,backRight,liftMotor1,liftMotor2,extendMotor;
    CRServo intake1,intake2;
    Servo rotate;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            lastGamepad1.copy(currentGamepad1);
            lastGamepad2.copy(currentGamepad2);

            chassis.goGoVrumVrumFC(lastGamepad1, currentGamepad1);
            intake.aspirator(currentGamepad2);
            lift.dropDown(currentGamepad2);
            extendo.extend(currentGamepad2);

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

        extendMotor = hardwareMap.get(DcMotorEx.class,"extendMotor");
        liftMotor1 = hardwareMap.get(DcMotorEx.class,"liftMotor1");
        liftMotor2 = hardwareMap.get(DcMotorEx.class,"liftMotor2");

        rotate = hardwareMap.get(Servo.class,"rotate");
        intake1 = hardwareMap.get(CRServo.class,"intake1");
        intake2 = hardwareMap.get(CRServo.class,"intake2");

        MotorConfigurationType m= frontLeft.getMotorType();
        m.setAchieveableMaxRPMFraction(1);

        frontLeft.setMotorType(m);
        frontRight.setMotorType(m);
        backLeft.setMotorType(m);
        backRight.setMotorType(m);

        chassis = new DriveTrainFC(frontLeft, frontRight, backLeft, backRight,imu);
        intake = new Intake(intake1,intake2,rotate);
        extendo = new Extendo (extendMotor);
        lift = new Lift(liftMotor1,liftMotor2);



    }
}
