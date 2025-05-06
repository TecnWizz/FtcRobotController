package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Components.DriveTrainRC;
import org.firstinspires.ftc.teamcode.Components.Extendo;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Lift;

@TeleOp(name = "Avocado_Tele(robot centric)")
public class TeleopRC extends LinearOpMode {


    private DriveTrainRC chassis;
    private Intake intake;
    private Lift lift;
    private Extendo extendo;
    public Gamepad aGamepad = new Gamepad();
    public Gamepad bGamepad = new Gamepad();
    DcMotorEx leftFront,leftBack,rightFront,rightBack,extendMotor,liftMotor1,liftMotor2;
    CRServo intake1,intake2;
    Servo rotate;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();

        waitForStart();

        while (opModeIsActive()) {


            chassis.goGoVrumVrumRC(gamepad1);
           /// intake.aspirator(gamepad2);
           /// lift.dropDown(gamepad2);
           /// extendo.extend(gamepad2);

            telemetry.update();

        }
    }

    private void initializeHardware() {
        telemetry.addData("0::---------------------------:",0);
        telemetry.addData("Gamepad1 input",aGamepad);
        telemetry.addData("Gamepad2 input",bGamepad);
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

/*
        extendMotor = hardwareMap.get(DcMotorEx.class,"extendMotor");
        intake1 = hardwareMap.get(CRServo.class,"intake1");
        intake2 = hardwareMap.get(CRServo.class,"intake2");
        liftMotor1 = hardwareMap.get(DcMotorEx.class,"liftMotor1");
        liftMotor2 = hardwareMap.get(DcMotorEx.class,"liftMotor2");
        rotate = hardwareMap.get(Servo.class,"rotate");


        intake = new Intake(intake1,intake2,rotate);
        lift = new Lift(liftMotor1,liftMotor2);
        extendo = new Extendo(extendMotor);

 */
        chassis = new DriveTrainRC(leftFront, rightFront, leftBack, rightBack);



    }
}
