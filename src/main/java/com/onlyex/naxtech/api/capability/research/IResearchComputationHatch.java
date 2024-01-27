package com.onlyex.naxtech.api.capability.research;

public interface IResearchComputationHatch extends IResearchComputationProvider {

    /** If this hatch transmits or receives CWU/t. */
    boolean isTransmitter();
}
