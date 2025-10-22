package org.firstinspires.ftc.teamcode.OpModes;


import android.util.Size;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Outake;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "Mascul Feroce")
public class Teleop extends LinearOpMode {


    private DriveTrain chassis;
    private Outake outake; Telemetry telemetry;
    private AprilTagProcessor tagProcessor;
    double fx,fy,cx,cy;
    DcMotorEx rotate,launchMotor,leftFront,leftBack,rightBack,rightFront,shoot;
    WebcamName webcam1;
    RevColorSensorV3 colorSensor1,colorSensor2;
    private VisionPortal vPortal;
    private AprilTagDetection tag;




    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(gamepad1);
            outake.autoShooter();
        }
    }
    private void initializeHardware() {

        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setLensIntrinsics(fx,fy,cx,cy)
                .build();

        vPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(webcam1)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

        leftFront = hardwareMap.get(DcMotorEx.class,"leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class,"rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class,"leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class,"rightBack");
        shoot = hardwareMap.get(DcMotorEx.class,"shoot");
        rotate = hardwareMap.get(DcMotorEx.class,"rotate");
        webcam1 = hardwareMap.get(WebcamName.class,"webcam1");

        MotorConfigurationType m= leftFront.getMotorType();
        m.setAchieveableMaxRPMFraction(1);

        leftFront.setMotorType(m);
        rightFront.setMotorType(m);
        leftBack.setMotorType(m);
        rightFront.setMotorType(m);

        chassis = new DriveTrain(leftFront, rightFront, leftBack, rightBack);
        outake = new Outake(shoot,rotate);

        telemetry.addData("Rotation",Math.toDegrees(tag.ftcPose.bearing));
        telemetry.addData("Distance",tag.ftcPose.range);
        telemetry.addData("ID",tag.id);

    }
}
