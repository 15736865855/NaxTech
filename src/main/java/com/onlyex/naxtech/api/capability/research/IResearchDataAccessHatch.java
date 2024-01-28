package com.onlyex.naxtech.api.capability.research;

import com.onlyex.naxtech.api.capability.IDataAccessHatch;

public interface IResearchDataAccessHatch extends IDataAccessHatch {

    /**
     * 这个方法用于判断该数据访问孔（Data Access Hatch）是否通过电缆传输数据。
     * 返回一个布尔值，表示该数据访问孔（Data Access Hatch）是否通过电缆传输数据。
     */
    boolean isTransmitter();
}
