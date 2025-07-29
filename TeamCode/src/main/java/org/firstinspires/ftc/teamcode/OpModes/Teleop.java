package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Extendo;
import org.firstinspires.ftc.teamcode.Components.Intake;

@TeleOp(name = "Avocado_Tele(robot centric)")
public class Teleop extends LinearOpMode {


    private DriveTrain chassis;
    private Intake intake;
    private Extendo extendo;
    public Gamepad Gamepad1 = new Gamepad();
    public Gamepad Gamepad2 = new Gamepad();
    DcMotorEx leftFront,leftBack,rightFront,rightBack,extendMotor;
    Servo extend,claw,rclaw,rotation,clipClaw,clipArm;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(Gamepad1);
            intake.intake(Gamepad2);

            telemetry.update();

        }
    }

    private void initializeHardware() {
        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",Gamepad1);
        telemetry.addData("Gamepad2 input",Gamepad2);
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

        extend = hardwareMap.get(Servo.class,"extend");
        claw = hardwareMap.get(Servo.class,"claw");
        rclaw = hardwareMap.get(Servo.class,"rclaw");
        rotation = hardwareMap.get(Servo.class,"rotation");




        intake = new Intake(extend,claw,rclaw,rotation,clipArm,clipClaw);
        chassis = new DriveTrain(leftFront, rightFront, leftBack, rightBack);



    }
}
