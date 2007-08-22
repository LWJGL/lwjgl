package org.lwjgl.d3d;

import java.math.BigInteger;

public class D3DPresentStats {
    public int PresentCount;            //UINT
    public int PresentRefreshCount;     //UINT
    public int SyncRefreshCount;        //UINT
    public BigInteger SyncQPCTime;      //LARGE_INTEGER
    public BigInteger SyncGPUTime;      //LARGE_INTEGER
}
