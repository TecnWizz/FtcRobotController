package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrainRC;

@TeleOp(name="Avocado_Tele_1p(robot centric)")
public class Tele_one_player extends LinearOpMode {

    private DriveTrainRC chassisV2;

    ///private IntakeV2 intakeV2;
    ///private ArmV2 armV2;
    private Gamepad lastGamepad1 = new Gamepad();
    private Gamepad currentGamepad1 = new Gamepad();
    private Gamepad lastGamepad2 = new Gamepad();
    private Gamepad currentGamepad2 = new Gamepad();
    DcMotorEx frontLeft,backLeft,frontRight,backRight;


    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()){
            chassisV2.goGoVrumVrumRC(currentGamepad1,lastGamepad1);

        }

        }
    public void initializeHardware(){
        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",currentGamepad1);
        telemetry.addData("Gaempad2 input",currentGamepad2);
        telemetry.addData("0::---------------------------:",0);

        frontLeft = hardwareMap.get(DcMotorEx.class,"frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class,"frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class,"backLeft");
        backRight = hardwareMap.get(DcMotorEx.class,"backRight");



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

        chassisV2 = new DriveTrainRC(frontLeft, frontRight, backLeft, backRight);
        ///intakeV2 = new IntakeV2(intakeServo,rotateServo);
        ///armV2 = new ArmV2(extendMotor, slideShiftMotor);
    }
    }

