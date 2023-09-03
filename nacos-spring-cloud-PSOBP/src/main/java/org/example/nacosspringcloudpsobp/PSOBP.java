package org.example.nacosspringcloudpsobp;

public class PSOBP {


    //输入层节点
    private static final int inputNode=5;

    //隐含层节点
    private static final int hiddenNode=4;

    //输出层节点
    private static final int outputNode=1;

    //输入层连接权值矩阵
    private static final double[][] inputLinkWeightMatrix={
            {0.174478576916326,-0.0786838722690102,0.242876180899592,-0.196795795641637,-0.918223363792578},
            {-0.853726440386588,0.515676676455395,-0.756848857175082,0.583353937319185,-0.304807578201698},
            {0.00133237615207743,-0.466636549759190,-0.416689572087531,0.125543886631226,1.02165934915020},
            {0.601927674482093,-0.285992225830059,-0.0814183393722257,0.862053853451619,-0.380995852337922}
    };

    //输入层阈值矩阵
    private static final double[] inputThresholdMatrix={0.0601683843387000,0.344478927211240,0.168635262167992,-0.112598951031805};

    //输出层连接权值矩阵
    private static final double[] outputLinkWeightMatrix={-0.921142546743532,-0.162061984373712,0.483212204053865,0.0236428571109784};

    //输出层阈值
    private static final double outputThresholdMatrix=0.0332341532913226;

    public double[] netOutput(double[] input){
        double[] hiddenMatrix = new double[4];
        //归一化输入数据
        for(int i=0;i<=input.length-1;i++){
            //双极S型函数
            double s = 2/(1+Math.exp(-input[i]))-1;
            input[i] = s;
        }
        //第一次隐含层输入
        for(int j=0;j<=3;j++){
            for(int k=0;k<=input.length-1;k++){
                hiddenMatrix[j] = hiddenMatrix[j]+input[k]*inputLinkWeightMatrix[j][k];
            }
            hiddenMatrix[j]=hiddenMatrix[j]+inputThresholdMatrix[j];
        }
        //第一次输出
        double output = 0;
        for(int l=0;l<=3;l++){
            output = output + hiddenMatrix[l]*outputLinkWeightMatrix[0];
        }
        output=output+outputThresholdMatrix;
        return null;

    }

}
