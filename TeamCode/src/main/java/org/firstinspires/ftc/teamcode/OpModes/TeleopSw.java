package org.firstinspires.ftc.teamcode.OpModes;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.DualSwerve;
import org.firstinspires.ftc.teamcode.Components.Outake;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name="Bro nu face Swerve ✌ ")
public class TeleopSw extends LinearOpMode {
    DualSwerve chassis;
    DcMotorEx motor1,motor2;
    Servo ch1,ch2;
    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(gamepad1);
        }
    }
    private void initializeHardware() {

        motor1 = hardwareMap.get(DcMotorEx.class,"motor1");
        motor2 = hardwareMap.get(DcMotorEx.class,"motor2");
        MotorConfigurationType m= motor1.getMotorType();
        m.setAchieveableMaxRPMFraction(1);
        motor1.setMotorType(m);
        motor2.setMotorType(m);

        chassis = new DualSwerve(ch1,ch2,motor1,motor2);

    }
}
