package org.lwjgl.d3d;

public class D3DPShaderCaps2_0 {
    public long Caps;                   //4 DWORD
    public int DynamicFlowControlDepth; //4 INT
    public int NumTemps;                //4 INT
    public int StaticFlowControlDepth;  //4 INT
    public int NumInstructionSlots;     //4 INT

    public String toString() {
        return
            "\n                   Caps = " + Caps +
            "\nDynamicFlowControlDepth = " + DynamicFlowControlDepth +
            "\n               NumTemps = " + NumTemps +
            "\n StaticFlowControlDepth = " + StaticFlowControlDepth +
            "\n    NumInstructionSlots = " + NumInstructionSlots;
    }
}