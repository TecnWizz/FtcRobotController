package org.firstinspires.ftc.teamcode.OpModes;


import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Outake;
import org.firstinspires.ftc.teamcode.Components.Outake2;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.List;

@TeleOp(name = "Mascul Feroce")
public class Teleop extends LinearOpMode {


    private DriveTrain chassis; private Intake intake; private CRServo servo;
    private Outake2 outake; Servo transfer;
    private VisionPortal visionPortal;
    private AprilTagProcessor tagProcessor;

    DcMotorEx intakeMotor,rotate,leftFront,leftBack,rightBack,rightFront,shoot1,shoot2;
    WebcamName webcam1;
    RevColorSensorV3 colorSensor1,colorSensor2;


    public static Telemetry dashboard;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        waitForStart();

        while (opModeIsActive()) {
            outake.aimbot();
            dashboard.addLine("started dashboard");
            dashboard.update();
        }
    }
    private void initializeHardware() {
/*
        servo = hardwareMap.get(CRServo.class,"servo");
        intakeMotor = hardwareMap.get(DcMotorEx.class,"intakeMotor");
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

*/
        dashboard = FtcDashboard.getInstance().getTelemetry();
        transfer = hardwareMap.get(Servo.class,"transfer");
        shoot1 = hardwareMap.get(DcMotorEx.class,"shoot1");
        shoot2 = hardwareMap.get(DcMotorEx.class,"shoot2");
        webcam1 = hardwareMap.get(WebcamName.class,"Webcam1");
        rotate = hardwareMap.get(DcMotorEx.class,"rotate");
        outake = new Outake2(shoot1,shoot2,rotate,webcam1,transfer);
    }
}
