package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Outake;

@TeleOp(name = "Mascul Feroce")
public class Teleop extends LinearOpMode {


    private DriveTrain chassis;
    private Intake intake;
    private Outake outake;
    DcMotorEx leftFront,leftBack,rightFront,rightBack,intakeMotor,rotate,outakeMotor;
    Servo servo1,servo2,push;
    RevColorSensorV3 colorSensor1,colorSensor2;


    @Override
    public void runOpMode() throws InterruptedException {

        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(gamepad1);
            intake.Fiorosul(gamepad2);
            outake.Shooter(gamepad2);
            telemetry.update();

        }
    }

    private void initializeHardware() {

        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",gamepad1);
        telemetry.addData("Gamepad2 input",gamepad2);
        telemetry.addData("0::---------------------------:",0);

        leftFront = hardwareMap.get(DcMotorEx.class,"leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class,"rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class,"leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class,"rightBack");


        MotorConfigurationType m= leftFront.getMotorType();
        m.setAchieveableMaxRPMFraction(1);

        leftFront.setMotorType(m);
        rightFront.setMotorType(m);
        leftBack.setMotorType(m);
        rightFront.setMotorType(m);

        intakeMotor = hardwareMap.get(DcMotorEx.class,"intakeMotor");
        outakeMotor = hardwareMap.get(DcMotorEx.class,"outakeMotor");
        rotate = hardwareMap.get(DcMotorEx.class,"rotate");

        servo1 = hardwareMap.get(Servo.class,"servo1");
        servo2 = hardwareMap.get(Servo.class,"servo2");
        push = hardwareMap.get(Servo.class,"push");

        colorSensor1 = hardwareMap.get(RevColorSensorV3.class,"colorSensor1");
        colorSensor2 = hardwareMap.get(RevColorSensorV3.class,"colorSensor2");

        intake = new Intake(intakeMotor,servo1,servo2,colorSensor1,colorSensor2);
        outake = new Outake(outakeMotor,rotate,push);
        chassis = new DriveTrain(leftFront, rightFront, leftBack, rightBack);



    }
}
