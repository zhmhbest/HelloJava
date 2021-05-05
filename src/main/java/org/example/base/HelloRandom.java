package org.example.base;/*
 * Random: 随机数
 */
import java.util.Random;

public class HelloRandom {
    public static void main(String[] args) {
         Random random = new Random();
         // Random random = new Random(0);

        // 布尔值
        System.out.print("生成{true, false}内随机值: ");
        for(int i=0; i<6; i++) {
            System.out.printf("%b, ", random.nextBoolean());
        }
        System.out.print('\n');

        // 整数
        int upperBound = 3;
        System.out.printf("生成[0, %d)内的整数: ", upperBound);
        for(int i=0; i<6; i++) {
            System.out.printf("%d, ", random.nextInt(upperBound));
        }
        System.out.print('\n');

        // 整数
        System.out.printf("在32位整数空间范围内均匀分布: %d\n", random.nextInt());
        System.out.printf("在64位整数空间范围内均匀分布: %d\n", random.nextLong());

        // 浮点数
        System.out.print("生成[0.0, 1.0)内均匀分布的随机值: ");
        for(int i=0; i<6; i++) {
            System.out.printf("%f, ", random.nextFloat());
            // System.out.printf("%f, ", random.nextDouble());
        }
        System.out.print('\n');

        // 高斯分布
        System.out.print("生成高斯分布(mean=0.0, std=1.0)的随机值: ");
        for(int i=0; i<6; i++) {
            System.out.printf("%f, ", random.nextGaussian());
        }
        System.out.print('\n');
    }
}
