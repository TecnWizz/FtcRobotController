package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Sper ca merge", group = "Autonomous")
public class auto extends LinearOpMode {

    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, intakeMotor, slideShiftMotor, extendMotor;
    private Servo rotateServo, clawServo;

    private final ElapsedTime timer = new ElapsedTime();

    private final ElapsedTime movementTimer = new ElapsedTime();


    private static final double MOTOR_POWER = 0.6;
    private static final double TURN_POWER = 0.5;

    private int counter = 0;

    @Override
    public void runOpMode() {
        initHardware();

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        telemetry.update();

        waitForStart();
        timer.reset();

        driveToObservationZone();

        telemetry.addData("Path", "Complete");
        telemetry.addData("Elapsed Time", timer.seconds());
        telemetry.update();
    }
    private void initHardware() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
       /// intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
       /// extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
       /// slideShiftMotor = hardwareMap.get(DcMotor.class, "slideShiftMotor");
       /// rotateServo = hardwareMap.get(Servo.class, "rotateServo");
       /// clawServo = hardwareMap.get(Servo.class, "clawServo");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);


        stopAllMotors();
    }
    private void driveToObservationZone() {
        strafeRight(MOTOR_POWER,1);
    }

    private void spinRight(double power, double seconds){
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(-power);
        backRightMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void spinLeft (double power, double seconds){
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void diagFrontRight (double power, double seconds){
        frontLeftMotor.setPower(power);
        backRightMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();

    }

    private void diagFrontLeft (double power, double seconds){
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }

    private void diagBackRight (double power, double seconds){
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }

    private void diagBackLeft (double power, double seconds){
        frontLeftMotor.setPower(-power);
        backRightMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void driveForward(double power, double seconds) {
        if (timer.seconds() >= 30.0) return;

        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);

        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Driving Forward", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void strafeRight (double power, double seconds){
        if (timer.seconds() >= 30.0) return;
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
        backLeftMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Turning Right", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void strafeLeft (double power, double seconds){
        if (timer.seconds() >= 30.0) return;
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backRightMotor.setPower(-power);
        backLeftMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Turning Right", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private  void driveBackwards(double power, double seconds){
        if (timer.seconds() >= 30.0) return;
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(-power);
        backRightMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Turning Right", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void turnRight(double power, double seconds) {
        if (timer.seconds() >= 30.0) return;

        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(-power);

        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Turning Right", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
    private void turnLeft(double power, double seconds) {
        if (timer.seconds() >= 30.0) return;

        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(-power);
        backRightMotor.setPower(power);

        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("Turning Left", "Time: %.2f", movementTimer.seconds());
            telemetry.update();
        }

        stopAllMotors();
    }
  /*  private void extendArm(double power, double seconds){
        if (timer.seconds() >= 30.0) return;

        extendMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("extendArm", "Time: %.2f", movementTimer.seconds());
            telemetry.update();

        }
        stopAllMotors();
        }
    private void extendArmReverse (double power, double seconds){
        if (timer.seconds() >= 30.0) return;

        extendMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("extendArm", "Time: %.2f", movementTimer.seconds());
            telemetry.update();

        }
        stopAllMotors();
    }
    private void intake(double power, double seconds){
        if (timer.seconds() >= 30.0) return;

        intakeMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("extendArm", "Time: %.2f", movementTimer.seconds());
            telemetry.update();

        }
        stopAllMotors();

    }
    private void clawIn(){
        if (timer.seconds() >= 30.0) return;
        clawServo.setPosition(1);
    }
    private void clawOut()
    {
        if (timer.seconds() >= 30.0) return;
        clawServo.setPosition(0.1);
    }
    private void rotate(){
        if (timer.seconds() >= 30.0) return;
        rotateServo.setPosition(0.96);

    }
    private void roateBack(){
        if (timer.seconds() >= 30.0) return;
        rotateServo.setPosition(0);
    }
    private void slideShift(double power, double seconds){
        if (timer.seconds() >= 30.0) return;
        slideShiftMotor.setPower(power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("extendArm", "Time: %.2f", movementTimer.seconds());
            telemetry.update();

        }
        stopAllMotors();
    }
    private void slideShiftReverse(double power, double seconds){
        if (timer.seconds() >= 30.0) return;
        slideShiftMotor.setPower(-power);
        movementTimer.reset();
        while (opModeIsActive() && movementTimer.seconds() < seconds && timer.seconds() < 30.0) {
            telemetry.addData("extendArm", "Time: %.2f", movementTimer.seconds());
            telemetry.update();

        }
        stopAllMotors();
    }

   */
    private void stopAllMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
