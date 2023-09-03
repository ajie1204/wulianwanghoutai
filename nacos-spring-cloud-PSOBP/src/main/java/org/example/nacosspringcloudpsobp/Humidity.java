package org.example.nacosspringcloudpsobp;

//根据前8小时的室内湿度，预测接下来3小时的室内湿度
public class Humidity {

    //输入层节点
    private static final int inputNode=8;

    //隐含层节点
    private static final int hiddenNode=6;

    //输出层节点
    private static final int outputNode=3;

    //输入层连接权值矩阵
    private static final double[][] inputLinkWeightMatrix={
            {-1.14245376765543,1.12716807271139,0.449020496767078,0.810055537121144,-0.578891384718997,-0.0801781134826582,0.329040231610171,0.492525856492197},
            {-0.647933430453595,-0.439725524079915,-0.386845701143996,-1.01176263094948,-0.122330602302805,0.0894156664400489,0.362213794163374,0.636798020062627},
            {0.616891492498912,0.267802368078928,-0.681739389690077,-0.312875390721007,-0.223031086622228,-0.444078116728773,-0.333229112121034,1.31992830842381},
            {-0.171349812403973,-0.645401813464309,1.52881219457884,0.115153347331136,-0.390500253080899,0.580548144759562,-1.17027030022039,0.363509966392801},
            {-0.126112415808609,-0.667888630660651,0.630242510829565,0.748922133758436,-0.781959301670176,0.841345179223526,0.357180402574850,1.14768183667733},
            {-0.336677133434582,1.36968470913582,-1.36867949775133,-0.688749387542365,0.321686512069004,0.188898276507100,1.41104911793695,0.190981383632762}
    };

    //输入层阈值矩阵
    private static final double[]inputThresholdMatrix={0.231083258866419,1.17471919488365,-0.118365471257820,-0.600754519624255,-1.36126643343098,1.54714601977516};

    //输出层连接权值矩阵
    private static final double[][] outputLinkWeightMatrix={
            {0.479277454126386,0.318220732961230,0.749706317640906,0.460823128792956,0.294440804692892,0.803182484887607},
            {0.479277454126386,0.318220732961230,0.749706317640906,0.460823128792956,0.294440804692892,0.803182484887607},
            {0.479277454126386,0.318220732961230,0.749706317640906,0.460823128792956,0.294440804692892,0.803182484887607},
    };

    //输出层阈值
    private static final double[] outputThresholdMatrix={-0.455182032690197,-0.447812107820487,-0.449758538821388};

    public double[] netOutput(double[] input){
        double[] hiddenMatrix = new double[6];

        double max = 98;
        double min1 = 8;
        double min2 = 9;
        //归一化输入数据
        for (int u=0;u<=input.length-1;u++){
            if (u==2||u==5){
                input[u] = 2*(input[u]-min2)/(max-min2)-1;
            }else {
                input[u] = 2*(input[u]-min1)/(max-min1)-1;
            }
        }

        //第一次隐含层输入
        for(int j=0;j<=5;j++){
            for(int k=0;k<=input.length-1;k++){
                hiddenMatrix[j] = hiddenMatrix[j]+input[k]*inputLinkWeightMatrix[j][k];
            }
            hiddenMatrix[j]=hiddenMatrix[j]+inputThresholdMatrix[j];
            //双极S型函数
            hiddenMatrix[j] = 2/(1+Math.exp(-2*hiddenMatrix[j]))-1;
        }
        //第一次输出
        double[] output = new double[3];
        for(int l=0;l<=2;l++){
            for (int p=0;p<=5;p++){
                output[l]=output[l]+hiddenMatrix[p]*outputLinkWeightMatrix[l][p];
            }
            output[l]=output[l]+outputThresholdMatrix[l];
        }
        //输出反归一化

        for(int y=0;y<=output.length-1;y++){
            if (y==0){
                output[y]=(output[y]+1)*(max-min2)/2+min2+0.4;
            }else if (y==1){
                output[y]=(output[y]+1)*(max-min1)/2+min1;
            }else {
                output[y]=(output[y]+1)*(max-min1)/2+min1+0.7;
            }
        }

        return output;
        }


    }
