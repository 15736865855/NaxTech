package com.onlyex.naxtech.api.capability.research;

import gregtech.api.capability.IDataAccessHatch;

public interface IResearchDataAccessHatch extends IDataAccessHatch {

    /**
     * @return if this hatch transmits data through cables
     */
    boolean isTransmitter();
}
