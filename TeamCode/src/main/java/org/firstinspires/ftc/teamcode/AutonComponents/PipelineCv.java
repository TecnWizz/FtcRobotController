package org.firstinspires.ftc.teamcode.AutonComponents;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineCv extends OpenCvPipeline {
    Mat YCbCr = new Mat();
    Mat leftCrop;
    Mat rightCrop;
    double leftavgfin;
    double rightavgfin;
    Mat output = new Mat();
    Scalar rectColor = new Scalar(255, 0.0, 0.0);
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input,YCbCr,Imgproc.COLOR_RGB2YCrCb);
        telemetry.addLine("Pipeline running");

        Rect leftRect = new Rect(1,1,319,359);
        Rect rightRect = new Rect(320,1,319,359);

        input.copyTo(output);
        Imgproc.rectangle(output,leftRect,rectColor,2);
        Imgproc.rectangle(output,rightRect,rectColor,2);

        leftCrop = YCbCr.submat(leftRect);
        rightCrop = YCbCr.submat(rightRect);

        Core.extractChannel(leftCrop,leftCrop,2);
        Core.extractChannel(rightCrop,rightCrop, 2);

        Scalar leftavg = Core.mean(leftCrop);
        Scalar rightavg = Core.mean(rightCrop);

        leftavgfin = leftavg.val[0];
        rightavgfin = rightavg.val[0];

        if (leftavgfin>rightavgfin){
            telemetry.addLine("Left");
        }
        else {
            telemetry.addLine("Right");
        }

        return (output);

    }

}
