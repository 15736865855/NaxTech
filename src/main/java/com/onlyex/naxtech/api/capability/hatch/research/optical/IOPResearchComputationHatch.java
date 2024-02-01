package com.onlyex.naxtech.api.capability.hatch.research.optical;

public interface IOPResearchComputationHatch extends IOPResearchComputationProvider {

    /** 注释指出了 isTransmitter() 方法用于判断该数据访问孔（Data Access Hatch）是否传输或接收 OPRWU/t */
    boolean isTransmitter();
}
