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
public class TeleopFC extends LinearOpMode {


   private DriveTrainFC chassis;
    private Intake intake;
    private Lift lift;
    private Extendo extendo;
    private Gamepad aGamepad;
    private Gamepad bGamepad;
    DcMotorEx frontLeft,backLeft,frontRight,backRight,liftMotor1,liftMotor2,extendMotor;
    CRServo intake1,intake2;
    Servo rotate;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            chassis.goGoVrumVrumFC(aGamepad);
            ///intake.aspirator(gamepad2);
            ///lift.dropDown(gamepad2);
            ///extendo.extend(gamepad2);

            telemetry.update();

        }
    }

    private void initializeHardware() {
        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",aGamepad);
        telemetry.addData("Gaempad2 input",bGamepad);
        telemetry.addData("0::---------------------------:",0);

        frontLeft = hardwareMap.get(DcMotorEx.class,"frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class,"frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class,"backLeft");
        backRight = hardwareMap.get(DcMotorEx.class,"backRight");
        imu = hardwareMap.get(IMU.class,"imu");
/*
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

         intake = new Intake(intake1,intake2,rotate);
        extendo = new Extendo (extendMotor);
        lift = new Lift(liftMotor1,liftMotor2);


 */
        chassis = new DriveTrainFC(frontLeft, frontRight, backLeft, backRight,imu);



    }
}
