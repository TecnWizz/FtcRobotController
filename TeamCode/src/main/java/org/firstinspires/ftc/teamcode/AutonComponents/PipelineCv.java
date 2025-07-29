package org.firstinspires.ftc.teamcode.AutonComponents;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineCv extends OpenCvPipeline {
    Mat output = new Mat();
    Scalar rectColor = new Scalar(255, 0.0, 0.0);
    @Override
    public Mat processFrame(Mat input) {


        return (output);

    }

}
