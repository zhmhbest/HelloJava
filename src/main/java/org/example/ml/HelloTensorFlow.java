package org.example.ml;

import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.types.TInt32;


public class HelloTensorFlow {
    public static void main(String[] args) {
        System.out.println("Hello TensorFlow " + TensorFlow.version());

        Tensor<TInt32> x = TInt32.scalarOf(10);
        System.out.println(x);
        System.out.println(x.shape());
        System.out.println(x.dataType());
    }
}
