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
import org.firstinspires.ftc.teamcode.Components.MonoSwerve;
import org.firstinspires.ftc.teamcode.Components.Outake;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "Mascul Feroce")
public class TeleTest extends LinearOpMode {

    MonoSwerve chassis;
    DcMotorEx motor;
    Servo ch1;
    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(gamepad1);
        }
    }
    private void initializeHardware() {

        motor = hardwareMap.get(DcMotorEx.class,"motor");
        ch1 = hardwareMap.get(Servo.class,"ch1");
        MotorConfigurationType m= motor.getMotorType();
        m.setAchieveableMaxRPMFraction(1);
        motor.setMotorType(m);
        chassis = new MonoSwerve(ch1,motor);


    }
}
